package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.converter.MenuConverterMapper;
import com.besscroft.pisces.admin.domain.dto.MenuDictDto;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.vo.MetaVo;
import com.besscroft.pisces.admin.domain.vo.RouterVo;
import com.besscroft.pisces.framework.common.entity.Menu;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.admin.mapper.MenuMapper;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:39
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final SecurityUtils securityUtils;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Map<String, Object> getTreeListById(@NonNull Long userId) {
        Map<String, Object> data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
        if (CollectionUtils.isEmpty(data)) {
            synchronized (this) {
                data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
                if (CollectionUtils.isEmpty(data)) {
                    // 获取用户的菜单
                    List<Menu> menuList = this.baseMapper.getAllByUserId(userId);
                    if (CollectionUtils.isEmpty(menuList)) return data;
                    List<MenuDto> menuDtos = MenuConverterMapper.INSTANCE.MenuToMenuDtoList(menuList);
                    // 处理菜单
                    menuDtos = getMenuDtos(menuDtos);
                    // 处理路由
                    List<RouterVo> routerVoList = getRouter(menuDtos);
                    data = new HashMap<>();
                    data.put("menus", routerVoList);
                    redisTemplate.boundHashOps("system").put("user:tree:" + userId, data);
                }
            }
        }
        return data;
    }

    @Override
    public List<MenuDto> getMenuList(String queryKey) {
        List<MenuDto> menuDtos = new ArrayList<>();
        List<Menu> menuList = this.baseMapper.selectAllByQueryKey(queryKey);
        if (!CollectionUtils.isEmpty(menuList)) {
            menuDtos = MenuConverterMapper.INSTANCE.MenuToMenuDtoList(menuList);
            // 处理菜单
            menuDtos = getMenuDtos(menuDtos);
        }
        return menuDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(@NonNull Long menuId, @NonNull Boolean isHide) {
        eventPublisher.publishEvent(new ClearCacheEvent("system"));
        return this.baseMapper.updateStatusById(menuId, isHide ? 1 : 0) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMenu(@NonNull Long menuId) {
        eventPublisher.publishEvent(new ClearCacheEvent("system"));
        return this.baseMapper.UpdateDelById(menuId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenu(@NonNull Menu menu) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        menu.setUpdater(currentAdmin.getUsername());
        menu.setUpdateTime(LocalDateTime.now());
        log.debug("更新菜单[menu={}]", menu);
        if (!Objects.equals(0, menu.getParentId())) {
            Menu parentMenu = this.baseMapper.selectById(menu.getParentId());
            menu.setParentTitle(parentMenu.getTitle());
        }
        eventPublisher.publishEvent(new ClearCacheEvent("system"));
        return this.baseMapper.updateById(menu) > 0;
    }

    @Override
    public Set<Long> getIdsByRoleId(@NonNull Long roleId) {
        List<Menu> menuList = this.baseMapper.findAllByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuList)) return new HashSet<>();
        return menuList.stream()
                .map(Menu::getId)
                .collect(Collectors.toSet());
//        List<MenuDto> menuDtos = MenuConverterMapper.INSTANCE.MenuToMenuDtoList(menuList);
//        // 处理菜单
//        menuDtos = getMenuDtos(menuDtos);
//        Set<Long> parentIds = menuDtos.stream()
//                .filter(menu -> menu.getParentId() == 0 && CollectionUtils.isEmpty(menu.getChildren()))
//                .map(MenuDto::getId)
//                .collect(Collectors.toSet());
//        if (!CollectionUtils.isEmpty(parentIds)) ids.addAll(parentIds);
//        return ids;
    }

    @Override
    public List<MenuDto> getAll() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", 1);
        List<Menu> menuList = this.baseMapper.selectList(queryWrapper);
        List<MenuDto> menuDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            menuDtos = MenuConverterMapper.INSTANCE.MenuToMenuDtoList(menuList);
            // 处理菜单
            menuDtos = getMenuDtos(menuDtos);
        }
        return menuDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMenu(@NonNull Menu menu) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        menu.setCreator(currentAdmin.getUsername());
        menu.setCreateTime(LocalDateTime.now());
        if (!Objects.equals(0, menu.getParentId())) {
            Menu parentMenu = this.baseMapper.selectById(menu.getParentId());
            menu.setParentTitle(parentMenu.getTitle());
        }
        eventPublisher.publishEvent(new ClearCacheEvent("system"));
        return this.baseMapper.insert(menu) > 0;
    }

    @Override
    public List<MenuDictDto> getMenuDict() {
        List<MenuDictDto> menuDictDtoList = (List<MenuDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.MENU);
        if (CollectionUtils.isEmpty(menuDictDtoList)) {
            synchronized (this) {
                menuDictDtoList = (List<MenuDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.MENU);
                if (CollectionUtils.isEmpty(menuDictDtoList)) {
                    List<MenuDto> menuDtoList = getAll();
                    if (!CollectionUtils.isEmpty(menuDtoList)) {
                        menuDictDtoList = getMenuDictHandler(menuDtoList);
                        redisTemplate.opsForValue().set(SystemDictConstants.MENU, menuDictDtoList);
                    }
                }
            }
        }
        return menuDictDtoList;
    }

    /**
     * 菜单字典处理
     * @param menuDtoList 菜单树封装对象集合
     * @return 菜单字典
     */
    private List<MenuDictDto> getMenuDictHandler(@NonNull List<MenuDto> menuDtoList) {
        return menuDtoList.stream().map(menuDto -> {
            MenuDictDto menuDictDto = new MenuDictDto();
            menuDictDto.setLabel(menuDto.getTitle());
            menuDictDto.setValue(menuDto.getId());
            if (!CollectionUtils.isEmpty(menuDto.getChildren())) {
                List<MenuDictDto> dictDtos = getMenuDictHandler(menuDto.getChildren());
                menuDictDto.setChildren(dictDtos);
            }
            return menuDictDto;
        }).collect(Collectors.toList());
    }

    /**
     * 菜单树层级处理
     * @param menuList 菜单
     * @return 菜单
     */
    private List<MenuDto> getMenuDtos(@NonNull List<MenuDto> menuList) {
        List<MenuDto> parentMenus = menuList.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parentMenus)) {
            return menuList;
        }
        List<MenuDto> menus = menuList.stream().filter(menu -> menu.getParentId() != 0).collect(Collectors.toList());
        parentMenus.forEach(menu -> {
            if (!CollectionUtils.isEmpty(menus)) {
                List<MenuDto> childMenu = getChildMenu(menu.getId(), menus);
                menu.setChildren(childMenu);
            } else {
                menu.setChildren(new ArrayList<>());
            }
        });
        return parentMenus;
    }

    /**
     * 菜单递归
     * @param menuId 菜单id
     * @param menuList 子菜单集合
     * @return 菜单
     */
    private List<MenuDto> getChildMenu(@NonNull Long menuId, @Nullable List<MenuDto> menuList) {
        if (CollectionUtils.isEmpty(menuList)) return new ArrayList<>();
        List<MenuDto> menus = menuList.stream().filter(menu -> menu.getParentId() == menuId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(menus)) {
            menus.forEach(menu -> {
                List<MenuDto> childMenu = getChildMenu(menu.getId(), menuList);
                menu.setChildren(childMenu);
            });
        }
        return menus;
    }

    /**
     * 获取路由信息
     * @param menuDtos 菜单
     * @return 路由
     */
    private List<RouterVo> getRouter(@Nullable List<MenuDto> menuDtos) {
        List<RouterVo> routerVoList = new LinkedList<>();
        if (!CollectionUtils.isEmpty(menuDtos)) {
            menuDtos.forEach(menuDto -> {
                RouterVo routerVo = new RouterVo();
                if (!StringUtils.isEmpty(menuDto.getRedirect())) {
                    routerVo.setRedirect(menuDto.getRedirect());
                }
                routerVo.setName(menuDto.getName());
                routerVo.setPath(menuDto.getPath());
                routerVo.setComponent(menuDto.getComponent());
                MetaVo metaVo = new MetaVo();
                metaVo.setTitle(menuDto.getTitle());
                metaVo.setIcon(menuDto.getIcon());
                metaVo.setIsLink(menuDto.getIsLink());
                metaVo.setIsHide(menuDto.getIsHide() == 0);
                metaVo.setIsFull(menuDto.getIsFull() == 1);
                metaVo.setIsAffix(menuDto.getIsAffix() == 1);
                metaVo.setIsKeepAlive(menuDto.getIsKeepAlive() == 1);
                routerVo.setMeta(metaVo);
                if (menuDto.getChildren().size() > 0 && !menuDto.getChildren().isEmpty()) {
                    List<RouterVo> childRouter = getChildRouter(menuDto.getChildren());
                    routerVo.setChildren(childRouter);
                }
                routerVoList.add(routerVo);
            });
        }
        return routerVoList;
    }

    /**
     * 子路由处理
     * @param menuDtos 子菜单
     * @return 子路由
     */
    private List<RouterVo> getChildRouter(@Nullable List<MenuDto> menuDtos) {
        List<RouterVo> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuDtos)) {
            menuDtos.forEach(child -> {
                RouterVo router = new RouterVo();
                if (!StringUtils.isEmpty(child.getRedirect())) {
                    child.setRedirect(child.getRedirect());
                }
                router.setPath(child.getPath());
                router.setName(child.getName());
                router.setRedirect(child.getRedirect());
                router.setComponent(child.getComponent());
                MetaVo metaVo = new MetaVo();
                metaVo.setTitle(child.getTitle());
                metaVo.setIcon(child.getIcon());
                metaVo.setIsLink(child.getIsLink());
                metaVo.setIsHide(child.getIsHide() == 0);
                metaVo.setIsFull(child.getIsFull() == 1);
                metaVo.setIsAffix(child.getIsAffix() == 1);
                metaVo.setIsKeepAlive(child.getIsKeepAlive() == 1);
                router.setMeta(metaVo);
                if (child.getChildren().size() > 0 && !child.getChildren().isEmpty()) {
                    List<RouterVo> childRouter = getChildRouter(child.getChildren());
                    router.setChildren(childRouter);
                }
                list.add(router);
            });
        }
        return list;
    }

}

package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.converter.MenuConverterMapper;
import com.besscroft.pisces.admin.domain.dto.MenuDictDto;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.vo.MetaVo;
import com.besscroft.pisces.admin.domain.vo.RouterVo;
import com.besscroft.pisces.admin.entity.Menu;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.mapper.MenuMapper;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.util.SecurityUtils;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Override
    public Map<String, Object> getTreeListById(Long userId) {
        Map<String, Object> data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
        if (CollectionUtils.isEmpty(data)) {
            synchronized (this) {
                data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
                if (CollectionUtils.isEmpty(data)) {
                    // 获取用户的菜单
                    List<Menu> menuList = this.baseMapper.getAllByUserId(userId);
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
    public List<MenuDto> getMenuListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
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
    public boolean changeStatus(Long menuId, Boolean hidden) {
        redisTemplate.delete("system");
        return this.baseMapper.updateStatusById(menuId, hidden ? 1 : 0) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMenu(Long menuId) {
        redisTemplate.delete("system");
        return this.baseMapper.UpdateDelById(menuId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenu(Menu menu) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        menu.setUpdater(currentAdmin.getUsername());
        menu.setUpdateTime(LocalDateTime.now());
        log.debug("更新菜单[menu={}]", menu);
        if (!Objects.equals(0, menu.getParentId())) {
            Menu parentMenu = this.baseMapper.selectById(menu.getParentId());
            menu.setParentTitle(parentMenu.getTitle());
        }
        redisTemplate.delete("system");
        return this.baseMapper.updateById(menu) > 0;
    }

    @Override
    public Set<Long> getIdsByRoleId(Long roleId) {
        List<Menu> menuList = this.baseMapper.findAllByRoleId(roleId);
        return menuList.stream().filter(menu -> menu.getParentId() != 0).map(Menu::getId).collect(Collectors.toSet());
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
    public boolean addMenu(Menu menu) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        menu.setCreator(currentAdmin.getUsername());
        menu.setCreateTime(LocalDateTime.now());
        if (!Objects.equals(0, menu.getParentId())) {
            Menu parentMenu = this.baseMapper.selectById(menu.getParentId());
            menu.setParentTitle(parentMenu.getTitle());
        }
        redisTemplate.delete("system");
        return this.baseMapper.insert(menu) > 0;
    }

    @Override
    public List<MenuDictDto> getMenuDict() {
        List<MenuDto> menuDtoList = getAll();
        return getMenuDictHandler(menuDtoList);
    }

    /**
     * 菜单字典处理
     * @param menuDtoList 菜单树封装对象集合
     * @return 菜单字典
     */
    private List<MenuDictDto> getMenuDictHandler(List<MenuDto> menuDtoList) {
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
    private List<MenuDto> getMenuDtos(List<MenuDto> menuList) {
        List<MenuDto> parentMenus = menuList.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parentMenus)) {
            return menuList;
        }
        List<MenuDto> menus = menuList.stream().filter(menu -> menu.getParentId() != 0).collect(Collectors.toList());
        parentMenus.forEach(menu -> {
            List<MenuDto> childMenu = getChildMenu(menu.getId(), menus);
            menu.setChildren(childMenu);
        });
        return parentMenus;
    }

    /**
     * 菜单递归
     * @param menuId 菜单id
     * @param menuList 子菜单集合
     * @return 菜单
     */
    private List<MenuDto> getChildMenu(Long menuId, List<MenuDto> menuList) {
        List<MenuDto> menus = menuList.stream().filter(menu -> menu.getParentId() == menuId).collect(Collectors.toList());
        menus.forEach(menu -> {
            List<MenuDto> childMenu = getChildMenu(menu.getId(), menuList);
            menu.setChildren(childMenu);
        });
        return menus;
    }

    /**
     * 获取路由信息
     * @param menuDtos 菜单
     * @return 路由
     */
    private List<RouterVo> getRouter(List<MenuDto> menuDtos) {
        List<RouterVo> routerVoList = new LinkedList<>();
        menuDtos.forEach(menuDto -> {
            RouterVo routerVo = new RouterVo();
            routerVo.setName(menuDto.getName());
            routerVo.setPath(menuDto.getPath());
            routerVo.setHidden(menuDto.getHidden() != 0);
            routerVo.setComponent(menuDto.getComponent());
            routerVo.setMeta(new MetaVo(menuDto.getTitle(), menuDto.getIcon(), false));
            if (menuDto.getChildren().size() > 0 && !menuDto.getChildren().isEmpty()) {
                routerVo.setAlwaysShow(true);
                routerVo.setRedirect("noRedirect");
                List<RouterVo> childRouter = getChildRouter(menuDto.getChildren());
                routerVo.setChildren(childRouter);
            }
            routerVoList.add(routerVo);
        });
        return routerVoList;
    }

    /**
     * 子路由处理
     * @param menuDtos 子菜单
     * @return 子路由
     */
    private List<RouterVo> getChildRouter(List<MenuDto> menuDtos) {
        List<RouterVo> list = new ArrayList<>();
        menuDtos.forEach(child -> {
            RouterVo router = new RouterVo();
            router.setPath(child.getPath());
            router.setName(child.getName());
            router.setComponent(child.getComponent());
            router.setMeta(new MetaVo(child.getTitle(), child.getIcon(), false));
            router.setHidden(child.getHidden() != 0);
            if (child.getChildren().size() > 0 && !child.getChildren().isEmpty()) {
                List<RouterVo> childRouter = getChildRouter(child.getChildren());
                router.setChildren(childRouter);
            }
            list.add(router);
        });
        return list;
    }

}

package com.besscroft.pisces.admin.service.impl;

import com.besscroft.pisces.admin.converter.MenuConverterMapper;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.vo.MetaVo;
import com.besscroft.pisces.admin.domain.vo.RouterVo;
import com.besscroft.pisces.admin.entity.Menu;
import com.besscroft.pisces.admin.repository.MenuRepository;
import com.besscroft.pisces.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:39
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Map<String, Object> getTreeListById(Long userId) {
        Map<String, Object> data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
        if (CollectionUtils.isEmpty(data)) {
            synchronized (this) {
                data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
                if (CollectionUtils.isEmpty(data)) {
                    // 获取用户的菜单
                    List<Menu> menuList = menuRepository.getAllByUserId(userId);
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

    /**
     * 菜单层级处理
     * @param menuList
     * @return
     */
    private List<MenuDto> getMenuDtos(List<MenuDto> menuList) {
        List<MenuDto> parentMenus = menuList.stream().filter(menu -> Objects.equals(1, menu.getLevel())).collect(Collectors.toList());
        List<MenuDto> menus = menuList.stream().filter(menu -> !Objects.equals(1, menu.getLevel())).collect(Collectors.toList());
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
     * @return
     */
    private List<MenuDto> getChildMenu(Long menuId, List<MenuDto> menuList) {
        List<MenuDto> menus = menuList.stream().filter(menu -> Objects.equals(menuId, menu.getLevel())).collect(Collectors.toList());
        menus.forEach(menu -> {
            List<MenuDto> childMenu = getChildMenu(menu.getId(), menuList);
            menu.setChildren(childMenu);
        });
        return menus;
    }

    /**
     * 获取路由信息
     * @param menuDtos
     * @return
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
     * @param menuDtos
     * @return
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

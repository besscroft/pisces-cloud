package com.besscroft.pisces.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
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
import java.util.*;

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
        if (CollUtil.isEmpty(data)) {
            synchronized (this) {
                data = (Map<String, Object>) redisTemplate.boundHashOps("system").get("user:tree:" + userId);
                if (CollUtil.isEmpty(data)) {
                    List<Menu> menuList = menuRepository.getParentListById(userId);
                    List<MenuDto> menuDtos = MenuConverterMapper.INSTANCE.MenuToMenuDtoList(menuList);
                    menuDtos.forEach(menuDto -> {
                        List<Menu> childListById = menuRepository.getChildListById(userId, menuDto.getId());
                        menuDto.setChildren(childListById);
                    });
                    List<RouterVo> routerVoList = new LinkedList<>();
                    menuDtos.forEach(menuDto -> {
                        RouterVo routerVo = new RouterVo();
                        routerVo.setName(menuDto.getName());
                        routerVo.setPath(menuDto.getPath());
                        routerVo.setHidden(menuDto.getHidden() != 0);
                        routerVo.setComponent(menuDto.getComponent());
                        routerVo.setMeta(new MetaVo(menuDto.getTitle(), menuDto.getIcon(), false));
                        List<RouterVo> list = new ArrayList<>();
                        if (menuDto.getChildren().size() > 0 && !menuDto.getChildren().isEmpty()) {
                            routerVo.setAlwaysShow(true);
                            routerVo.setRedirect("noRedirect");
                            menuDto.getChildren().forEach(child -> {
                                RouterVo router = new RouterVo();
                                router.setPath(child.getPath());
                                router.setName(child.getName());
                                router.setComponent(child.getComponent());
                                router.setMeta(new MetaVo(child.getTitle(), child.getIcon(), false));
                                router.setHidden(child.getHidden() != 0);
                                list.add(router);
                            });
                            routerVo.setChildren(list);
                        }
                        routerVoList.add(routerVo);
                    });
                    data = new HashMap<>();
                    data.put("menus", routerVoList);
                    redisTemplate.boundHashOps("system").put("user:tree:" + userId, data);
                }
            }
        }
        return data;
    }

}

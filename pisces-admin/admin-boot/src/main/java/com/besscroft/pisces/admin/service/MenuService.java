package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.domain.dto.MenuDictDto;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取当前用户菜单动态路由
     * @param userId 用户 id
     * @return 当前用户的所有动态路由
     */
    Map<String, Object> getTreeListById(Long userId);

    /**
     * 获取菜单列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return 菜单列表分页对象
     */
    List<MenuDto> getMenuListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 更改菜单可用状态
     * @param menuId 菜单 id
     * @param hidden 可用状态
     * @return 成功状态
     */
    boolean changeStatus(Long menuId, Boolean hidden);

    /**
     * 根据菜单 id 删除用户（软删除）
     * @param menuId 菜单 id
     * @return
     */
    boolean deleteMenu(Long menuId);

    /**
     * 更新菜单
     * @param menu 菜单实体
     * @return
     */
    boolean updateMenu(Menu menu);

    /**
     * 根据角色 id 获取菜单 id 列表
     * @param roleId 角色 id
     * @return 菜单 id 列表
     */
    Set<Long> getIdsByRoleId(Long roleId);

    /**
     * 获取所有菜单树
     * @return 所有菜单树
     */
    List<MenuDto> getAll();

    /**
     * 新增菜单
     * @param menu 菜单实体
     * @return
     */
    boolean addMenu(Menu menu);

    /**
     * 获取菜单字典
     * @return
     */
    List<MenuDictDto> getMenuDict();

}

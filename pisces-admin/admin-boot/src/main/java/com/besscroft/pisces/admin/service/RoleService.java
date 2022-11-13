package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.domain.dto.RoleDictDto;
import com.besscroft.pisces.framework.common.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:37
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取角色列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return 角色列表分页对象
     */
    List<Role> getRoleListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 更改角色可用状态
     * @param roleId 角色 id
     * @param status 可用状态
     */
    void changeStatus(Long roleId, Boolean status);

    /**
     * 更新角色的菜单
     * @param roleId 角色 id
     * @param menuIds 菜单 id 列表
     */
    void updateMenu(Long roleId, Set<Long> menuIds);

    /**
     * 更新角色的资源
     * @param roleId 角色 id
     * @param resourceIds 资源 id 列表
     */
    void updateResource(Long roleId, Set<Long> resourceIds);

    /**
     * 根据角色 id 删除角色（软删除）
     * @param roleId 角色 id
     */
    void deleteRole(Long roleId);

    /**
     * 新增角色
     * @param role 角色实体
     */
    void addRole(Role role);

    /**
     * 更新角色
     * @param role 角色实体
     */
    void updateRole(Role role);

    /**
     * 获取角色字典
     * @return 角色字典
     */
    List<RoleDictDto> getRoleDict();

    /**
     * 根据用户 id 获取角色信息
     * @param userId 用户 id
     * @return 角色信息
     */
    List<Role> getRoleByUserId(Long userId);

}

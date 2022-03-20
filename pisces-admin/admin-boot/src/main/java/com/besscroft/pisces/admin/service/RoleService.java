package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.entity.Role;

import java.util.List;

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
     * @param roleId 角色id
     * @param status 可用状态
     * @return 成功状态
     */
    boolean changeStatus(Long roleId, Boolean status);

}

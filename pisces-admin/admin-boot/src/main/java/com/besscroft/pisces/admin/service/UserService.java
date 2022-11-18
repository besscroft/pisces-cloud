package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.domain.dto.UserListDto;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.framework.common.result.AjaxResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 19:17
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param account 账户
     * @param password 密码
     * @return
     */
    AjaxResult login(String account, String password);

    /**
     * 退出登录处理
     */
    void loginOut();

    /**
     * 获取当前已登录用户信息
     * @return 用户信息
     */
    User getCurrentAdmin();

    /**
     * 获取认证后的用户信息
     * @return 用户信息
     */
    Map<String, Object> getUserInfo();

    /**
     * 获取用户对应的角色列表
     * @param userId 用户 id
     * @return 用户的角色列表
     */
    List<Role> getRoleList(Long userId);

    /**
     * 获取用户列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @param departId 部门id
     * @return 用户列表分页对象
     */
    List<UserListDto> getUserListPage(Integer pageNum, Integer pageSize, String queryKey, Long departId);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getUser(String username);

    /**
     * 更改用户可用状态
     * @param userId 用户id
     * @param status 可用状态
     */
    void changeStatus(Long userId, Boolean status);

    /**
     * 新增用户
     * @param user 用户实体
     */
    void addUser(User user);

    /**
     * 更新用户
     * @param user 用户实体
     */
    void updateUser(User user);

    /**
     * 根据用户 id 删除用户（软删除）
     * @param userId 用户 id
     */
    void deleteUser(Long userId);

    /**
     * 更新用户角色
     * @param userId 用户 id
     * @param roleIds 角色 id 列表
     */
    void updateRole(Long userId, Set<Long> roleIds);

    /**
     * 更新用户部门
     * @param userId 用户 id
     * @param departId 部门 id
     */
    void updateDepart(Long userId, Long departId);

    /**
     * 登录用户密码更新
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String oldPassword, String newPassword);

}

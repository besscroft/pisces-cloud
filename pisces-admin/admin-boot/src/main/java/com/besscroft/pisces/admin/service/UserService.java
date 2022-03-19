package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.result.AjaxResult;

import java.util.List;
import java.util.Map;

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
     * 获取当前已登录用户信息
     * @return 用户信息
     */
    User getCurrentAdmin();

    /**
     * 获取认证后的用户信息
     * @return
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
     * @return 用户列表分页对象
     */
    List<User> getUserListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getUser(String username);

}

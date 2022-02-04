package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.result.AjaxResult;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 19:17
 */
public interface UserService {

    /**
     * 登录
     * @param account 账户
     * @param password 密码
     * @return
     */
    AjaxResult login(String account, String password);

}

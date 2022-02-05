package com.besscroft.pisces.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.dto.UserDto;
import com.besscroft.pisces.auth.entity.User;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 13:15
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserDto loadUserByUsername(String username);

}

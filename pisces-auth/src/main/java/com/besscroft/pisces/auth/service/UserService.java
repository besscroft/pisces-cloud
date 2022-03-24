package com.besscroft.pisces.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.auth.entity.User;
import com.besscroft.pisces.framework.common.dto.UserDto;

/**
 * @Description 用户服务
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

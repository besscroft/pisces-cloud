package com.besscroft.pisces.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.framework.common.entity.User;

/**
 * @Description 用户 mapper 接口
 * @Author Bess Croft
 * @Date 2022/2/4 13:16
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);

}

package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:34
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据关键词查询用户信息
     * @param queryKey 关键词
     * @return 用户信息
     */
    List<User> selectAllByQueryKey(@Param("queryKey") String queryKey);

}

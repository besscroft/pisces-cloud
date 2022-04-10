package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    /**
     * 修改用户可用状态
     * @param userId 用户id
     * @param status 可用状态
     * @return 生效行数
     */
    int updateStatusById(@Param("userId") Long userId,
                         @Param("status") Integer status);

    /**
     * 根据用户id更新用户信息
     * @param user 用户信息
     * @return
     */
    int updateByUserId(@Param("user") User user);

    /**
     * 根据id更改用户删除状态
     * @param userId 用户id
     * @return
     */
    int updateDelById(@Param("userId") Long userId);

    /**
     * 根据用户 id 删除用户所有角色
     * @param userId 用户 id
     * @return
     */
    int deleteUserRoleById(@Param("userId") Long userId);

    /**
     * 根据用户 id 新增用户角色
     * @param userId 用户 id
     * @param roleIds 角色 id 列表
     * @return
     */
    int insertUserRole(@Param("userId") Long userId,
                       @Param("roleIds") Set<Long> roleIds);

}

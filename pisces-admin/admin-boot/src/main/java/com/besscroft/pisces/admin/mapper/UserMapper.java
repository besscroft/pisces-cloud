package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.domain.dto.UserListDto;
import com.besscroft.pisces.framework.common.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Description 用户 Mapper 接口
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
     * @param departId 部门id
     * @return 用户信息
     */
    List<UserListDto> selectAllByQueryKey(@Param("queryKey") String queryKey,
                                          @Param("departId") Long departId);

    /**
     * 修改用户可用状态
     * @param userId 用户id
     * @param status 可用状态
     * @return 生效行数
     */
    int updateStatusById(@Param("userId") Long userId,
                         @Param("status") Integer status);

    /**
     * 根据用户 id 删除用户所有角色
     * @param userId 用户 id
     * @return 生效行数
     */
    int deleteUserRoleById(@Param("userId") Long userId);

    /**
     * 根据用户 id 新增用户角色
     * @param userId 用户 id
     * @param roleIds 角色 id 列表
     * @return 生效行数
     */
    int insertUserRole(@Param("userId") Long userId,
                       @Param("roleIds") Set<Long> roleIds);

    /**
     * 根据用户 id 查询是否绑定部门
     * @param userId 用户 id
     * @return 1->已绑定;null->未绑定
     */
    Integer selectExistDepartByUserId(@Param("userId") Long userId);

    /**
     * 新增用户部门绑定关系
     * @param userId 用户 id
     * @param departId 部门 id
     * @return 生效行数
     */
    int insertUserDepart(@Param("userId") Long userId,
                         @Param("departId") Long departId);

    /**
     * 根据用户 id 更新用户部门
     * @param userId 用户 id
     * @param departId 部门 id
     * @return 生效行数
     */
    int updateUserDepart(@Param("userId") Long userId,
                         @Param("departId") Long departId);

    /**
     * 根据用户 id 更新密码
     * @param userId 用户 id
     * @param newPassword 加密后的密码
     * @return 生效行数
     */
    int updatePasswordById(@Param("userId") Long userId,
                           @Param("newPassword") String newPassword);

}

package com.besscroft.pisces.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.auth.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户 id 查询用户所有的角色
     * @param userId 用户id
     * @return 角色列表
     */
    @Select("SELECT " +
            "   pisces_auth_role.* " +
            "FROM " +
            "   pisces_auth_role " +
            "INNER JOIN " +
            "   pisces_auth_user_role " +
            "ON " +
            "   pisces_auth_role.id = pisces_auth_user_role.role_id " +
            "WHERE " +
            "   pisces_auth_user_role.user_id = #{userId} ")
    List<Role> findListByUserId(Long userId);

}

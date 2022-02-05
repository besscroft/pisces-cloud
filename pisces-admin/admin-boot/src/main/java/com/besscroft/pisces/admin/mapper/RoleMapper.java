package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取用户所有的角色
     * @param userId 用户 id
     * @return 用户的角色集合
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
    List<Role> findRoleListByUserId(@Param("userId") Long userId);

}

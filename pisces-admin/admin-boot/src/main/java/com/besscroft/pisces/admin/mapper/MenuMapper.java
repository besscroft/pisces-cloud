package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取用户的父菜单
     * @param userId 用户id
     * @return 用户的父菜单集合
     */
    @Select("SELECT " +
            "   pisces_auth_menu.* " +
            "FROM " +
            "   pisces_auth_menu " +
            "INNER JOIN " +
            "   pisces_auth_role_menu " +
            "ON " +
            "   pisces_auth_menu.id = pisces_auth_role_menu.menu_id " +
            "INNER JOIN " +
            "   pisces_auth_user_role " +
            "ON " +
            "   pisces_auth_role_menu.role_id = pisces_auth_user_role.role_id " +
            "WHERE " +
            "   pisces_auth_user_role.user_id =#{userId} ")
    List<Menu> getParentListById(@Param("userId") Long userId);

    /**
     * 根据父菜单id获取用户的子菜单
     * @param userId 用户id
     * @param parentId 父菜单id
     * @return 用户的子菜单集合
     */
    @Select("SELECT " +
            "   pisces_auth_menu.* " +
            "FROM " +
            "   pisces_auth_menu " +
            "INNER JOIN " +
            "   pisces_auth_role_menu " +
            "ON " +
            "   pisces_auth_menu.id = pisces_auth_role_menu.menu_id " +
            "INNER JOIN " +
            "   pisces_auth_user_role " +
            "ON " +
            "   pisces_auth_role_menu.role_id = pisces_auth_user_role.role_id " +
            "WHERE " +
            "   pisces_auth_user_role.user_id =#{userId} " +
            "AND " +
            "   pisces_auth_menu.parent_id = #{parentId}")
    List<Menu> getChildListById(@Param("userId") Long userId,
                                @Param("parentId") Long parentId);

}

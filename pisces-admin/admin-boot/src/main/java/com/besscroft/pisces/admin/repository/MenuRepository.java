package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Menu;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface MenuRepository extends ReactiveCrudRepository<Menu, Long> {

    /**
     * 获取用户的父菜单
     * @param userId 用户id
     * @return 用户的父菜单集合
     */
    @Query("SELECT " +
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
            "   pisces_auth_user_role.user_id = :userId ")
    Flux<List<Menu>> getParentListById(Long userId);

    /**
     * 根据父菜单id获取用户的子菜单
     * @param userId 用户id
     * @param parentId 父菜单id
     * @return 用户的子菜单集合
     */
    @Query("SELECT " +
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
            "   pisces_auth_user_role.user_id = :userId " +
            "AND " +
            "   pisces_auth_menu.parent_id = :parentId ")
    Flux<List<Menu>> getChildListById(Long userId, Long parentId);

}

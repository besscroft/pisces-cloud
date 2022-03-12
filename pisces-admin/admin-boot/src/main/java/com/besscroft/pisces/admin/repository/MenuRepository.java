package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    /**
     * 根据用户 id 查询所有菜单
     * @param userId 用户 id
     * @return 用户的菜单集合
     */
    @Query(value = "SELECT " +
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
                "   pisces_auth_user_role.user_id = :userId ", nativeQuery = true)
    List<Menu> getAllByUserId(@Param("userId") Long userId);

}

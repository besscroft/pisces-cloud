package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 获取用户所有的角色
     * @param userId 用户 id
     * @return 用户的角色集合
     */
    @Query(value = "SELECT " +
                "   pisces_auth_role.* " +
                "FROM " +
                "   pisces_auth_role " +
                "INNER JOIN " +
                "   pisces_auth_user_role " +
                "ON " +
                "   pisces_auth_role.id = pisces_auth_user_role.role_id " +
                "WHERE " +
                "   pisces_auth_user_role.user_id = :userId ", nativeQuery = true)
    List<Role> findRoleListByUserId(@Param("userId") Long userId);

}

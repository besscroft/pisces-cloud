package com.besscroft.pisces.auth.repository;

import com.besscroft.pisces.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据用户 id 查询用户所有的角色
     * @param userId 用户id
     * @return 角色列表
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
    List<Role> findListByUserId(@Param("userId") Long userId);

}

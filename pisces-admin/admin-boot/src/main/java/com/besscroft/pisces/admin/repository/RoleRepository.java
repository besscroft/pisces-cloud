package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Role;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    /**
     * 获取用户所有的角色
     * @param userId 用户 id
     * @return 用户的角色集合
     */
    @Query("SELECT " +
            "   pisces_auth_role.* " +
            "FROM " +
            "   pisces_auth_role " +
            "INNER JOIN " +
            "   pisces_auth_user_role " +
            "ON " +
            "   pisces_auth_role.id = pisces_auth_user_role.role_id " +
            "WHERE " +
            "   pisces_auth_user_role.user_id = :userId ")
    Flux<List<Role>> findRoleListByUserId(Long userId);

}

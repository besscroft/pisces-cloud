package com.besscroft.pisces.auth.repository;

import com.besscroft.pisces.auth.entity.Role;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    /**
     * 根据用户 id 查询用户所有的角色
     * @param userId 用户id
     * @return 角色列表
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
    Flux<List<Role>> findListByUserId(Long userId);

}

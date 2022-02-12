package com.besscroft.pisces.auth.repository;

import com.besscroft.pisces.auth.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 13:16
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    @Query("SELECT " +
            "   * " +
            "FROM " +
            "   pisces_auth_user " +
            "WHERE " +
            "   username = :username ")
    Mono<User> findByUsername(String username);

}

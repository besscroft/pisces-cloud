package com.besscroft.pisces.auth.repository;

import com.besscroft.pisces.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 13:16
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);

}

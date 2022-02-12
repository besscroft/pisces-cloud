package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:34
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}

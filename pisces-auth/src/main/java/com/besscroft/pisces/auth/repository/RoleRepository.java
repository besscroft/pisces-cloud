package com.besscroft.pisces.auth.repository;

import com.besscroft.pisces.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}

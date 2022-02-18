package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface DepartRepository extends JpaRepository<Depart, Long>, JpaSpecificationExecutor<Depart> {
}

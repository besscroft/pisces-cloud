package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Resource;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface ResourceRepository extends ReactiveCrudRepository<Resource, Long> {
}

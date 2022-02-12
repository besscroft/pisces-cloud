package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.entity.Log;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:37
 */
public interface LogRepository extends ReactiveCrudRepository<Log, Long> {
}

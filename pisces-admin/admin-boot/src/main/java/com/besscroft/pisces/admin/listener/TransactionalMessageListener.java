package com.besscroft.pisces.admin.listener;

import com.besscroft.pisces.admin.event.ClearCacheEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @Description 事务事件监听
 * @Author Bess Croft
 * @Date 2022/5/21 22:40
 */
@Component
@RequiredArgsConstructor
public class TransactionalMessageListener {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 事务 COMMIT 之后，执行删除缓存操作
     * @param clearCacheEvent
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void clearCache(ClearCacheEvent clearCacheEvent) {
        redisTemplate.delete(clearCacheEvent.getCacheKey());
    }

}

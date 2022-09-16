package com.besscroft.pisces.gateway.retry.impl;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.exception.PiscesException;
import com.besscroft.pisces.gateway.retry.AuthRetry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Description 异步远程调用——处理权限关系载入缓存——重试方法
 * @Author Bess Croft
 * @Date 2022/9/16 20:40
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRetryImpl implements AuthRetry {

    private final WebClient.Builder webBuilder;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @Retryable(
            value = PiscesException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000L, maxDelay = 3000L, multiplier = 2))
    public boolean retryAuthTask() {
        CompletableFuture.supplyAsync(() -> webBuilder.build().get()
                .uri("lb://pisces-admin/resource/init")
                .retrieve()
                .bodyToMono(Object.class).subscribe());
        Map<Object, Object> roleResourceMap = redisTemplate.opsForHash().entries(AuthConstants.PERMISSION_RULES_KEY);
        log.info("执行请求权限缓存！");
        if (CollectionUtils.isEmpty(roleResourceMap)) {
            throw new PiscesException(500, "执行请求权限缓存失败啦！");
        }
        return true;
    }

    /**
     * 达到最大重试次数,或抛出了一个没有指定进行重试的异常
     * recover 机制
     * @param e 异常
     */
    @Recover
    public boolean recover(PiscesException e) {
        log.error("达到最大重试次数，或抛出了一个没有指定进行重试的异常:", e);
        return false;
    }

}

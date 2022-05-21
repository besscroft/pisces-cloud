package com.besscroft.pisces.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Description 定义缓存删除 Event
 * @Author Bess Croft
 * @Date 2022/5/21 22:40
 */
public class ClearCacheEvent extends ApplicationEvent {

    @Getter
    private String cacheKey;

    public ClearCacheEvent(String cacheKey) {
        super(cacheKey);
        this.cacheKey = cacheKey;
    }

}

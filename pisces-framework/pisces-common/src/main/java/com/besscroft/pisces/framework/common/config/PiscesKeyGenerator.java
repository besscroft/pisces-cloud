package com.besscroft.pisces.framework.common.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description 自定义 key 生成策略
 * @Author Bess Croft
 * @Date 2022/5/21 22:06
 */
@Configuration
@Component("piscesKeyGenerator")
public class PiscesKeyGenerator implements KeyGenerator {

    /**
     * 这里的策略为 "::pisces"，表示系统默认生成的 key，不带业务性质
     * 比如正常业务 key 可能为："system:user::1"
     * 而此策略生成的 key 为："system:user::pisces"
     * @param target
     * @param method
     * @param params
     * @return
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return "pisces";
    }

}

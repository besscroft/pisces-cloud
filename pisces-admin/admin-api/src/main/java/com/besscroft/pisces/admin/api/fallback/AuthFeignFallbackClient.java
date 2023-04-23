package com.besscroft.pisces.admin.api.fallback;

import com.besscroft.pisces.admin.api.AuthFeignClient;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 认证中心请求服务熔断器
 * @Author Bess Croft
 * @Time 2021/11/21 12:20
 */
@Slf4j
@Component
public class AuthFeignFallbackClient implements AuthFeignClient {

    @Override
    public AjaxResult getAccessToken(Map<String, String> parameters) {
        log.error("feign 远程调用认证用户服务异常:url:{/oauth/token}, data{}", parameters);
        return AjaxResult.error(5001, "服务暂时不可用");
    }

}

package com.besscroft.pisces.admin.service.impl;

import com.besscroft.pisces.admin.api.AuthFeignClient;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.constant.AuthConstants;
import com.besscroft.pisces.result.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 19:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthFeignClient authFeignClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public AjaxResult login(String account, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstants.SYSTEM_CLIENT_ID);
        params.put("client_secret", AuthConstants.SYSTEM_CLIENT_SECRET);
        params.put("grant_type", AuthConstants.OAUTH2_PASSWORD);
        params.put("username", account);
        params.put("password", password);
        AjaxResult accessToken = authFeignClient.getAccessToken(params);
        LOGGER.info("accessToken 颁发成功:{}", accessToken);
        Map<String, String> data = (Map<String, String>) accessToken.get("data");
        redisTemplate.opsForValue().set(AuthConstants.SYSTEM_CLIENT_ID + ":token:user:" + account, data.get("token").toString());
        return accessToken;
    }

}

package com.besscroft.pisces.framework.common.util;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.framework.common.exception.PiscesException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description 用户安全信息帮助类
 * @Author Bess Croft
 * @Date 2022/3/24 16:48
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityUtils {

    private final HttpServletRequest request;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取当前登录用户的信息
     * @return
     */
    public User getCurrentAdmin() {
        String header = request.getHeader(AuthConstants.USER_TOKEN_HEADER);
        Assert.notNull(header, "暂未登录或 token 已经过期！");
        Map userDto = null;
        try {
            userDto = objectMapper.readValue(header, Map.class);
        } catch (JsonProcessingException e) {
            throw new PiscesException(500, "token 无效！");
        }
        return (User) redisTemplate.opsForValue().get(String.join(":", AuthConstants.SYSTEM_USER, String.valueOf(userDto.get("user_name"))));
    }

}

package com.besscroft.pisces.auth.service;

import com.besscroft.pisces.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 用户服务 单元测试类
 * @Author Bess Croft
 * @Date 2022/2/13 11:59
 */
@SpringBootTest
public class UserServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loadUserByUsername() throws JsonProcessingException {
        String username = "admin";
        UserDto userDto = userService.loadUserByUsername(username);
        assertNotNull(userDto, "根据用户名查询用户信息失败！");
        LOGGER.info("用户信息:{}", objectMapper.writeValueAsString(userDto));
    }

}

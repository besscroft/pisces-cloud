package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.result.AjaxResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 用户服务 单元测试类
 * @Author Bess Croft
 * @Date 2022/2/12 22:32
 */
@SpringBootTest
public class UserServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login() throws JsonProcessingException {
        String account = "admin";
        String password = "666666";
        AjaxResult login = userService.login(account, password);
        assertNotNull(login, "请求失败！");
        LOGGER.info("登录结果:{}", objectMapper.writeValueAsString(login));
    }

    @Test
    void getCurrentAdmin() throws JsonProcessingException {
        User currentAdmin = userService.getCurrentAdmin();
        assertNotNull(currentAdmin, "获取登录用户失败！");
        LOGGER.info("用户信息:{}", objectMapper.writeValueAsString(currentAdmin));
    }

    @Test
    void getUserInfo() throws JsonProcessingException {
        Map<String, Object> userInfo = userService.getUserInfo();
        assertNotNull(userInfo, "获取认证后的用户信息失败！");
        LOGGER.info("用户信息:{}", objectMapper.writeValueAsString(userInfo));
    }

    @Test
    void getRoleList() throws JsonProcessingException {
        Long userId = 1L;
        List<Role> roleList = userService.getRoleList(userId);
        assertNotNull(roleList, "获取用户对应的角色列表失败！");
        LOGGER.info("角色列表:{}", objectMapper.writeValueAsString(roleList));
    }

}

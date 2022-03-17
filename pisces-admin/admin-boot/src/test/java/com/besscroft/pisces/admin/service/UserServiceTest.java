package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.constant.HttpStatus;
import com.besscroft.pisces.result.AjaxResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 用户服务 单元测试类
 * @Author Bess Croft
 * @Date 2022/2/12 22:32
 */
@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 登录测试依赖上游系统，否则无法颁发 token
     * @throws JsonProcessingException
     */
    @Test
    @DisplayName("登录方法测试")
    void login() throws JsonProcessingException {
        // 准备测试数据
        String account = "admin";
        String password = "666666";
        AjaxResult login = userService.login(account, password);
        // 验证是否与我们预期的状态值相符
        assertEquals(HttpStatus.SUCCESS, login.get("code"));
        assertNotNull(login.get("data"));
        log.info("登录方法测试成功:{}", objectMapper.writeValueAsString(login));
    }

    @Test
    @DisplayName("获取用户角色列表方法测试")
    void getRoleList() throws JsonProcessingException {
        Long userId = 1L;
        List<Role> roleList = userService.getRoleList(userId);
        assertNotNull(roleList, "获取用户对应的角色列表失败！");
        log.info("获取用户角色列表方法测试成功:{}", objectMapper.writeValueAsString(roleList));
    }

    @Test
    @DisplayName("用户列表（分页）方法测试")
    void getUserListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        Page<User> listPage = userService.getUserListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("用户列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("根据用户名获取用户方法测试")
    void getUser() {
        String username = "admin";
        User user = userService.getUser(username);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        log.info("根据用户名获取用户方法测试成功！");
    }

}

package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.LoginParam;
import com.besscroft.pisces.admin.domain.param.user.UserPageListParam;
import com.besscroft.pisces.constant.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/17 20:26
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static LoginParam loginParam;
    private static UserPageListParam userPageListParam;

    @BeforeAll
    static void beforeUserControllerTest() {
        loginParam = new LoginParam();
        loginParam.setUsername("admin");
        loginParam.setPassword("666666");
        userPageListParam = new UserPageListParam();
        userPageListParam.setPageNum(1);
        userPageListParam.setPageSize(10);
        userPageListParam.setQueryKey("");
    }

    @Test
    @DisplayName("登录接口测试")
    void login() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(loginParam, "loginParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("登录接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("用户列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(userPageListParam, "userPageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userPageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("用户列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("用户信息获取接口接口测试")
    void get() throws Exception {
        // 创建测试用例
        String username = "admin";
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/user/info/" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("用户信息获取接口接口测试成功:{}", map.get("data"));
    }

}

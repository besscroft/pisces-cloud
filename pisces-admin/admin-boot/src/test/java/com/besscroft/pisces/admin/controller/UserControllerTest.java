package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.LoginParam;
import com.besscroft.pisces.admin.domain.param.user.*;
import com.besscroft.pisces.framework.common.constant.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private static ChangeUserStatusParam changeUserStatusParam;
    private static AddUserParam addUserParam;
    private static UpdateUserParam updateUserParam;
    private static UpdateRoleByUserParam updateRoleByUserParam;
    private static UpdateDepartByUserParam updateDepartByUserParam;

    @BeforeAll
    static void beforeUserControllerTest() {
        loginParam = new LoginParam();
        loginParam.setUsername("admin");
        loginParam.setPassword("666666");

        userPageListParam = new UserPageListParam();
        userPageListParam.setPageNum(1);
        userPageListParam.setPageSize(10);
        userPageListParam.setQueryKey("");

        changeUserStatusParam = new ChangeUserStatusParam();
        changeUserStatusParam.setUserId(2L);
        changeUserStatusParam.setStatus(true);

        addUserParam = new AddUserParam();
        addUserParam.setUsername("unitTest");
        addUserParam.setPassword("666666");
        addUserParam.setAvatar("");
        addUserParam.setEmail("unitTest@qq.com");
        addUserParam.setName("unitTest");
        addUserParam.setRealName("单元测试");
        addUserParam.setTelephone("0");
        addUserParam.setBirthday(LocalDateTime.now());
        addUserParam.setSex(1);
        addUserParam.setRemark("这是一条单元测试新增的数据");

        updateUserParam = new UpdateUserParam();
        updateUserParam.setId(6L);
        updateUserParam.setAvatar("");
        updateUserParam.setEmail("unitTest@qq.com");
        updateUserParam.setName("unitTest");
        updateUserParam.setRealName("单元测试");
        updateUserParam.setTelephone("0");
        updateUserParam.setBirthday(LocalDateTime.now());
        updateUserParam.setSex(1);
        updateUserParam.setRemark("这是一条单元测试更新的数据");

        updateRoleByUserParam = new UpdateRoleByUserParam();
        updateRoleByUserParam.setUserId(6L);
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(2L);
        updateRoleByUserParam.setRoleIds(roleIds);

        updateDepartByUserParam = new UpdateDepartByUserParam();
        updateDepartByUserParam.setUserId(6L);
        updateDepartByUserParam.setDepartId(6L);
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
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
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

    @Test
    @DisplayName("更改用户可用状态接口测试")
    void change() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(changeUserStatusParam, "changeUserStatusParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/user/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changeUserStatusParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更改用户可用状态接口测试成功！");
    }

    @Test
    @DisplayName("新增用户接口测试")
    void addUser() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(addUserParam, "addUserParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addUserParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("新增用户接口测试成功！");
    }

    @Test
    @DisplayName("更新用户接口测试")
    void updateUser() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateUserParam, "updateUserParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新用户接口测试成功！");
    }

    @Test
    @DisplayName("根据用户 id 删除用户接口测试")
    void delete() throws Exception {
        // 创建测试用例
        Long userId = 6L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("删除用户接口测试成功！");
    }

    @Test
    @DisplayName("更新用户角色接口测试")
    void updateRole() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateRoleByUserParam, "updateRoleParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/user/update/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRoleByUserParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新用户角色接口测试成功！");
    }

    @Test
    @DisplayName("更新用户部门接口测试")
    void updateDepart() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateDepartByUserParam, "updateDepartByUserParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/user/update/depart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDepartByUserParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新用户部门接口测试成功！");
    }

}

package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.role.ChangeRoleStatusParam;
import com.besscroft.pisces.admin.domain.param.role.RolePageListParam;
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
 * @Date 2022/3/20 21:27
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static RolePageListParam rolePageListParam;
    private static ChangeRoleStatusParam changeRoleStatusParam;

    @BeforeAll
    static void beforeUserControllerTest() {
        rolePageListParam = new RolePageListParam();
        rolePageListParam.setPageNum(1);
        rolePageListParam.setPageSize(10);
        rolePageListParam.setQueryKey("");

        changeRoleStatusParam = new ChangeRoleStatusParam();
        changeRoleStatusParam.setRoleId(2L);
        changeRoleStatusParam.setStatus(true);
    }

    @Test
    @DisplayName("角色列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(rolePageListParam, "rolePageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/role/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rolePageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("角色列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("更改角色可用状态接口测试")
    void change() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(changeRoleStatusParam, "changeRoleStatusParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/role/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changeRoleStatusParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更改角色可用状态接口测试成功！");
    }

}

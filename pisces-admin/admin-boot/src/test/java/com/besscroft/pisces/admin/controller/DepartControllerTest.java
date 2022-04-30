package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.depart.AddDepartParam;
import com.besscroft.pisces.admin.domain.param.depart.DepartPageListParam;
import com.besscroft.pisces.admin.domain.param.depart.UpdateDepartParam;
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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/24 19:44
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class DepartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static DepartPageListParam departPageListParam;
    private static AddDepartParam addDepartParam;
    private static UpdateDepartParam updateDepartParam;

    @BeforeAll
    static void beforeDepartControllerTest() {
        departPageListParam = new DepartPageListParam();
        departPageListParam.setPageNum(1);
        departPageListParam.setPageSize(10);
        departPageListParam.setQueryKey("");

        addDepartParam = new AddDepartParam();
        addDepartParam.setParentId(1L);
        addDepartParam.setName("测试部门名称");
        addDepartParam.setDescription("这是一条单元测试更新的部门");
        addDepartParam.setSort(2);

        updateDepartParam = new UpdateDepartParam();
        updateDepartParam.setDepartId(100L);
        updateDepartParam.setParentId(1L);
        updateDepartParam.setName("测试部门名称");
        updateDepartParam.setDescription("这是一条单元测试更新的部门");
        updateDepartParam.setSort(2);
    }

    @Test
    @DisplayName("组织/部门列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(departPageListParam, "departPageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/depart/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departPageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("组织/部门列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("组织/部门删除接口接口测试")
    void delete() throws Exception {
        // 创建测试用例
        Long departId = 6L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/depart/delete/" + departId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("组织/部门删除接口测试成功！");
    }

    @Test
    @DisplayName("新增组织/部门接口测试")
    void addResource() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(addDepartParam, "addDepartParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/depart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addDepartParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("新增组织/部门接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("更新组织/部门接口测试")
    void updateResource() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateDepartParam, "updateDepartParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/depart/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDepartParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新组织/部门接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("部门字典接口测试")
    void getDepartDict() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/depart/getDepartDict")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("部门字典接口测试成功:{}", map.get("data"));
    }

}

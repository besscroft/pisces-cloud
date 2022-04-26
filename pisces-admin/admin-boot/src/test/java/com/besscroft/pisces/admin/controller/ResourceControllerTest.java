package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.resource.ResourcePageListParam;
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
 * @Date 2022/3/24 19:05
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static ResourcePageListParam resourcePageListParam;

    @BeforeAll
    static void beforeResourceControllerTest() {
        resourcePageListParam = new ResourcePageListParam();
        resourcePageListParam.setPageNum(1);
        resourcePageListParam.setPageSize(10);
        resourcePageListParam.setQueryKey("");
    }

    @Test
    @DisplayName("资源列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(resourcePageListParam, "resourcePageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/resource/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourcePageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("资源列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("获取资源树接口测试")
    void getAll() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/resource/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("获取资源树接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("根据角色 id 查询资源 id 列表接口测试")
    void getByRoleId() throws Exception {
        // 创建测试用例
        Long roleId = 2L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/resource/getId/role/" + roleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("根据角色 id 查询资源 id 列表接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("资源删除接口接口测试")
    void delete() throws Exception {
        // 创建测试用例
        Long resourceId = 6L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/resource/delete/" + resourceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("资源删除接口测试成功！");
    }

}

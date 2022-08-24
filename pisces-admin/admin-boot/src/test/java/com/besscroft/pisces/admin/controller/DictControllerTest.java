package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.dict.DictPageListParam;
import com.besscroft.pisces.framework.common.constant.HttpStatus;
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
 * @Date 2022/8/19 10:17
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class DictControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static DictPageListParam dictPageListParam;

    @BeforeAll
    static void beforeDictControllerTest() {
        dictPageListParam = new DictPageListParam();
        dictPageListParam.setPageNum(1);
        dictPageListParam.setPageSize(10);
        dictPageListParam.setQueryKey("");
    }

    @Test
    @DisplayName("查询分组内所有字典接口测试")
    void groupQuery() throws Exception {
        // 创建测试用例
        String groupName = "RESOURCE";

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/dict/groupQuery")
                        .param("groupName", groupName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("查询分组内所有字典接口测试成功！");
    }

    @Test
    @DisplayName("字典分页接口测试")
    void pageList() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(dictPageListParam, "dictPageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/dict/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dictPageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("字典分页接口测试成功！");
    }

}

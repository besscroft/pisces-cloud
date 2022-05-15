package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.white.AddWhiteParam;
import com.besscroft.pisces.admin.domain.param.white.UpdateWhiteParam;
import com.besscroft.pisces.admin.domain.param.white.WhitePageListParam;
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
 * @Date 2022/5/14 22:26
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class WhiteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static WhitePageListParam whitePageListParam;
    private static AddWhiteParam addWhiteParam;
    private static UpdateWhiteParam updateWhiteParam;

    @BeforeAll
    static void beforeWhiteControllerTest() {
        whitePageListParam = new WhitePageListParam();
        whitePageListParam.setPageNum(1);
        whitePageListParam.setPageSize(10);
        whitePageListParam.setQueryKey("");

        addWhiteParam = new AddWhiteParam();
        addWhiteParam.setTitle("test");
        addWhiteParam.setPath("/test");
        addWhiteParam.setRemark("接口测试生成的数据！");

        updateWhiteParam = new UpdateWhiteParam();
        updateWhiteParam.setWhiteId(100L);
        updateWhiteParam.setTitle("test");
        updateWhiteParam.setPath("/test");
        updateWhiteParam.setRemark("这时一条接口测试更新的数据");
    }

    @Test
    @DisplayName("白名单列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(whitePageListParam, "whitePageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/white/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(whitePageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("白名单列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("新增白名单接口测试")
    void addWhite() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(addWhiteParam, "addWhiteParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/white/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addWhiteParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("新增白名单接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("更新白名单接口测试")
    void updateWhite() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateWhiteParam, "updateWhiteParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/white/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateWhiteParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新白名单接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("删除白名单接口测试")
    void deleteById() throws Exception {
        // 创建测试用例
        Long whiteId = 100L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/white/delete/" + whiteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("删除白名单接口测试成功！");
    }

    @Test
    @DisplayName("获取白名单字典接口测试")
    void getWhiteDict() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/white/getWhiteDict")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("获取白名单字典接口测试成功:{}", map.get("data"));
    }

}

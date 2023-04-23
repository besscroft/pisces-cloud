package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.resourceCategory.AddResourceCategoryParam;
import com.besscroft.pisces.admin.domain.param.resourceCategory.ResourceCategoryPageListParam;
import com.besscroft.pisces.admin.domain.param.resourceCategory.UpdateResourceCategoryParam;
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
 * @Description 资源分类控制器测试类
 * @Author Bess Croft
 * @Date 2022/3/24 19:42
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static ResourceCategoryPageListParam resourceCategoryPageListParam;
    private static AddResourceCategoryParam addResourceCategoryParam;
    private static UpdateResourceCategoryParam updateResourceCategoryParam;

    @BeforeAll
    static void beforeResourceCategoryControllerTest() {
        resourceCategoryPageListParam = new ResourceCategoryPageListParam();
        resourceCategoryPageListParam.setPageNum(1);
        resourceCategoryPageListParam.setPageSize(10);
        resourceCategoryPageListParam.setQueryKey("");

        addResourceCategoryParam = new AddResourceCategoryParam();
        addResourceCategoryParam.setCategoryName("测试资源类别");
        addResourceCategoryParam.setDescription("测试资源类别说明");
        addResourceCategoryParam.setSort(10);

        updateResourceCategoryParam = new UpdateResourceCategoryParam();
        updateResourceCategoryParam.setResourceCategoryId(100L);
        updateResourceCategoryParam.setCategoryName("测试更新资源类别");
        updateResourceCategoryParam.setDescription("测试更新资源类别说明");
        updateResourceCategoryParam.setSort(10);
    }

    @Test
    @DisplayName("资源类别列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(resourceCategoryPageListParam, "resourceCategoryPageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/resource/category/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceCategoryPageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("资源类别列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("资源类别删除接口接口测试")
    void delete() throws Exception {
        // 创建测试用例
        Long resourceCategoryId = 6L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/resource/category/delete/" + resourceCategoryId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("资源类别删除接口测试成功！");
    }

    @Test
    @DisplayName("资源类别字典查询接口测试")
    void getResourceCategoryDict() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/resource/category/getResourceCategoryDict")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("资源类别字典查询接口测试成功！");
    }

    @Test
    @DisplayName("新增资源类别接口测试")
    void addResourceCategory() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(addResourceCategoryParam, "addResourceCategoryParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/resource/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addResourceCategoryParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("新增资源类别接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("更新资源类别接口测试")
    void updateResourceCategory() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateResourceCategoryParam, "updateResourceCategoryParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/resource/category/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateResourceCategoryParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新资源类别接口测试成功:{}", map.get("data"));
    }

}

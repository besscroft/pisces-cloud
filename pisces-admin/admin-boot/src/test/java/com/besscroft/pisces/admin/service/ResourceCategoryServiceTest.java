package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/24 18:56
 */
@Slf4j
@SpringBootTest
public class ResourceCategoryServiceTest {

    @Autowired
    private ResourceCategoryService resourceCategoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("获取资源类别列表（分页）方法测试")
    void getResourceCategoryListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<ResourceCategory> listPage = resourceCategoryService.getResourceCategoryListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("获取资源类别列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("删除资源类别方法测试")
    void deleteUser() {
        Long resourceCategoryId = 6L;
        boolean flag = resourceCategoryService.deleteResourceCategory(resourceCategoryId);
        assertTrue(flag, "删除资源类别失败！");
        log.info("删除资源类别测试成功！");
    }

    @Test
    @DisplayName("获取资源类别字典方法测试")
    void getResourceCategoryDict() throws JsonProcessingException {
        List<ResourceCategoryDictDto> categoryDictDtos = resourceCategoryService.getResourceCategoryDict();
        assertNotNull(categoryDictDtos);
        log.info("获取资源类别字典方法测试成功:{}", objectMapper.writeValueAsString(categoryDictDtos));
    }

}

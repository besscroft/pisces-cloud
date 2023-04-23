package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.framework.common.entity.ResourceCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 资源类别服务测试类
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
        resourceCategoryService.deleteResourceCategory(resourceCategoryId);
        log.info("删除资源类别测试成功！");
    }

    @Test
    @DisplayName("获取资源类别字典方法测试")
    void getResourceCategoryDict() throws JsonProcessingException {
        List<ResourceCategoryDictDto> categoryDictDtos = resourceCategoryService.getResourceCategoryDict();
        assertNotNull(categoryDictDtos);
        log.info("获取资源类别字典方法测试成功:{}", objectMapper.writeValueAsString(categoryDictDtos));
    }

    @Test
    @DisplayName("新增资源类别方法测试")
    void addResourceCategory() {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setId(100L);
        resourceCategory.setCategoryName("测试资源类别");
        resourceCategory.setDescription("这是一条由单元测试生成的数据");
        resourceCategory.setSort(10);
        resourceCategoryService.addResourceCategory(resourceCategory);
        log.info("新增资源类别方法测试成功！");
    }

    @Test
    @DisplayName("更新资源类别方法测试")
    void updateResourceCategory() {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setId(100L);
        resourceCategory.setCategoryName("测试资源类别");
        resourceCategory.setDescription("这是一条由单元测试更新的数据");
        resourceCategory.setSort(10);
        resourceCategoryService.updateResourceCategory(resourceCategory);
        log.info("更新资源类别方法测试成功！");
    }

}

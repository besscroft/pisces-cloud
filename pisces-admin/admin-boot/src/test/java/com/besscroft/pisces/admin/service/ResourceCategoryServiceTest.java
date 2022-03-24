package com.besscroft.pisces.admin.service;

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

}

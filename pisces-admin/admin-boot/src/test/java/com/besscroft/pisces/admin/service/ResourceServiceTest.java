package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.ResourceDto;
import com.besscroft.pisces.framework.common.entity.Resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 资源服务测试类
 * @Author Bess Croft
 * @Date 2022/3/17 21:18
 */
@Slf4j
@SpringBootTest
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("初始化资源角色规则测试")
    void initRoleResourceMap() throws JsonProcessingException {
        Map<String, List<String>> roleResourceMap = resourceService.initRoleResourceMap();
        assertNotNull(roleResourceMap, "获取资源角色规则失败！");
        log.info("初始化资源角色规则测试成功:{}", objectMapper.writeValueAsString(roleResourceMap));
    }

    @Test
    @DisplayName("获取资源列表（分页）方法测试")
    void getResourceListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<Resource> listPage = resourceService.getResourceListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("获取资源列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("获取所有资源树方法测试")
    void getAll() throws JsonProcessingException {
        List<ResourceDto> resourceDtoList = resourceService.getAll();
        assertNotNull(resourceDtoList);
        log.info("获取所有资源树方法测试成功:{}", objectMapper.writeValueAsString(resourceDtoList));
    }

    @Test
    @DisplayName("根据角色 id 获取资源 id 列表测试")
    void getIdsByRoleId() throws JsonProcessingException {
        Long roleId = 2L;
        Set<Long> ids = resourceService.getIdsByRoleId(roleId);
        assertNotNull(ids);
        log.info("根据角色 id 获取资源 id 列表测试成功:{}", objectMapper.writeValueAsString(ids));
    }

    @Test
    @DisplayName("删除资源方法测试")
    void deleteUser() {
        Long resourceId = 6L;
        resourceService.deleteResource(resourceId);
        log.info("删除资源测试成功！");
    }

    @Test
    @DisplayName("新增资源方法测试")
    void addResource() {
        // 创建测试实例
        Resource resource = new Resource();
        resource.setCategoryId(1L);
        resource.setName("测试资源名称");
        resource.setDescription("这是一条单元测试创建的资源");
        resource.setUrl("/unitTest");
        resourceService.addResource(resource);
        log.info("新增资源方法测试成功！");
    }

    @Test
    @DisplayName("更新资源方法测试")
    void updateResource() {
        // 创建测试实例
        Resource resource = new Resource();
        resource.setId(100L);
        resource.setCategoryId(1L);
        resource.setName("测试资源名称");
        resource.setDescription("这是一条单元测试更新的资源");
        resource.setUrl("/unitTest");
        resourceService.updateResource(resource);
        log.info("更新资源方法测试成功！");
    }

}

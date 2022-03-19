package com.besscroft.pisces.admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 菜单服务 单元测试类
 * @Author Bess Croft
 * @Date 2022/2/13 11:56
 */
@Slf4j
@SpringBootTest
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("获取当前用户菜单动态路由方法测试")
    void getTreeListById() throws JsonProcessingException {
        Long userId = 1L;
        Map<String, Object> map = menuService.getTreeListById(userId);
        assertNotNull(map, "获取当前用户菜单动态路由失败！");
        log.info("获取当前用户菜单动态路由方法测试成功:{}", objectMapper.writeValueAsString(map));
    }

}

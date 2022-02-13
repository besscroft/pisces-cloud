package com.besscroft.pisces.admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description 菜单服务 单元测试类
 * @Author Bess Croft
 * @Date 2022/2/13 11:56
 */
@SpringBootTest
public class MenuServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTreeListById() throws JsonProcessingException {
        Long userId = 1L;
        Map<String, Object> map = menuService.getTreeListById(userId);
        assertNotNull(map, "获取当前用户菜单动态路由失败！");
        LOGGER.info("用户菜单动态路由:{}", objectMapper.writeValueAsString(map));
    }

}

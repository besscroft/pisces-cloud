package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.entity.Role;
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
 * @Date 2022/3/20 21:03
 */
@Slf4j
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("角色列表（分页）方法测试")
    void getRoleListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<Role> listPage = roleService.getRoleListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("角色列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("更改角色可用状态方法测试")
    void changeStatus() {
        Long roleId = 2L;
        Boolean status = true;
        boolean flag = roleService.changeStatus(roleId, status);
        assertTrue(flag, "更改角色可用状态失败！");
        log.info("更改角色可用状态方法测试成功！");
    }

}

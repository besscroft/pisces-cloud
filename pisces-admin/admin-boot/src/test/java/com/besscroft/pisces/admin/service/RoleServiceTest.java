package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.RoleDictDto;
import com.besscroft.pisces.admin.entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Test
    @DisplayName("更新角色的菜单方法测试")
    void updateMenu() {
        Long roleId = 2L;
        Set<Long> menuIds = new HashSet();
        menuIds.add(1L);
        menuIds.add(2L);
        roleService.updateMenu(roleId, menuIds);
        log.info("更新角色的菜单方法测试成功！");
    }

    @Test
    @DisplayName("更新角色的资源方法测试")
    void updateResource() {
        Long roleId = 2L;
        Set<Long> resourceIds = new HashSet();
        resourceIds.add(1L);
        resourceIds.add(2L);
        roleService.updateResource(roleId, resourceIds);
        log.info("更新角色的资源方法测试成功！");
    }

    @Test
    @DisplayName("根据角色 id 删除角色（软删除）方法测试")
    void deleteById() {
        Long roleId = 3L;
        boolean b = roleService.deleteById(roleId);
        assertTrue(b, "根据角色 id 删除角色（软删除）方法测试失败！");
        log.info("根据角色 id 删除角色（软删除）方法测试成功！");
    }

    @Test
    @DisplayName("新增角色方法测试")
    void AddRole() {
        Role role = Role.builder()
                .roleName("单元测试角色")
                .roleCode("unitTest")
                .description("单元测试生成的角色")
                .sort(3).build();
        boolean b = roleService.AddRole(role);
        assertTrue(b, "新增角色方法测试测试失败！");
        log.info("新增角色方法测试成功！");
    }

    @Test
    @DisplayName("更新角色方法测试")
    void UpdateRole() {
        Role role = Role.builder()
                .id(3L)
                .roleName("单元测试角色")
                .roleCode("unitTest")
                .description("单元测试更新的角色")
                .sort(3).build();
        boolean b = roleService.AddRole(role);
        assertTrue(b, "更新角色方法测试失败！");
        log.info("更新角色方法测试成功！");
    }

    @Test
    @DisplayName("获取角色字典方法测试")
    void getRoleDict() throws JsonProcessingException {
        List<RoleDictDto> roleDict = roleService.getRoleDict();
        assertNotNull(roleDict);
        log.info("获取角色字典方法测试成功:{}", objectMapper.writeValueAsString(roleDict));
    }

}

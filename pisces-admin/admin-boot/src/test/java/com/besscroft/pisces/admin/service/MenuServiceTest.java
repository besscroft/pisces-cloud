package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.MenuDictDto;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.entity.Menu;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("获取菜单列表（分页）方法测试")
    void getMenuListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<MenuDto> listPage = menuService.getMenuListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("获取菜单列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("更改菜单可用状态方法测试")
    void changeStatus() {
        Long menuId = 200L;
        Boolean status = true;
        boolean flag = menuService.changeStatus(menuId, status);
        assertTrue(flag, "更改菜单可用状态失败！");
        log.info("更改菜单可用状态方法测试成功！");
    }

    @Test
    @DisplayName("更新菜单方法测试")
    void updateUser() {
        Menu user = Menu.builder()
                .id(67L)
                .parentId(67L)
                .title("单元测试")
                .name("单元测试")
                .parentTitle("单元测试")
                .level(5)
                .component("单元测试")
                .path("路由地址")
                .icon("")
                .sort(100).build();
        boolean flag = menuService.updateMenu(user);
        assertTrue(flag, "更新菜单失败！");
        log.info("更新菜单方法测试成功！");
    }

    @Test
    @DisplayName("根据菜单 id 删除用户方法测试")
    void deleteMenu() {
        Long menuId = 67L;
        boolean flag = menuService.deleteMenu(menuId);
        assertTrue(flag, "删除菜单失败！");
        log.info("根据菜单id删除用户方法测试成功！");
    }

    @Test
    @DisplayName("根据角色 id 获取菜单 id 列表方法测试")
    void getIdsByRoleId() throws JsonProcessingException {
        Long roleId = 2L;
        Set<Long> ids = menuService.getIdsByRoleId(roleId);
        assertNotNull(ids);
        log.info("根据角色 id 获取菜单 id 列表方法测试成功:{}", objectMapper.writeValueAsString(ids));
    }

    @Test
    @DisplayName("获取所有菜单树方法测试")
    void getAll() throws JsonProcessingException {
        List<MenuDto> menuDtoList = menuService.getAll();
        assertNotNull(menuDtoList);
        log.info("获取所有菜单树方法测试成功:{}", objectMapper.writeValueAsString(menuDtoList));
    }

    @Test
    @DisplayName("新增菜单方法测试")
    void addMenu() {
        Menu user = Menu.builder()
                .parentId(67L)
                .title("单元测试")
                .name("单元测试")
                .parentTitle("单元测试")
                .level(5)
                .component("单元测试")
                .path("路由地址")
                .icon("")
                .sort(100).build();
        boolean flag = menuService.addMenu(user);
        assertTrue(flag, "新增菜单失败！");
        log.info("新增菜单方法测试成功！");
    }

    @Test
    @DisplayName("获取菜单字典方法测试")
    void getMenuDict() throws JsonProcessingException {
        List<MenuDictDto> menuDict = menuService.getMenuDict();
        assertNotNull(menuDict);
        log.info("获取菜单字典方法测试成功:{}", objectMapper.writeValueAsString(menuDict));
    }

}

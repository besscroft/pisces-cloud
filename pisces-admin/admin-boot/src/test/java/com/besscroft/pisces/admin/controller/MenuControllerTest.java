package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.menu.AddMenuParam;
import com.besscroft.pisces.admin.domain.param.menu.ChangeMenuStatusParam;
import com.besscroft.pisces.admin.domain.param.menu.MenuPageListParam;
import com.besscroft.pisces.admin.domain.param.menu.UpdateMenuParam;
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
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/24 19:46
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static MenuPageListParam menuPageListParam;
    private static ChangeMenuStatusParam changeMenuStatusParam;
    private static UpdateMenuParam updateMenuParam;
    private static AddMenuParam addMenuParam;

    @BeforeAll
    static void beforeMenuControllerTest() {
        menuPageListParam = new MenuPageListParam();
        menuPageListParam.setPageNum(1);
        menuPageListParam.setPageSize(10);
        menuPageListParam.setQueryKey("");

        changeMenuStatusParam = new ChangeMenuStatusParam();
        changeMenuStatusParam.setMenuId(2L);
        changeMenuStatusParam.setHidden(true);

        updateMenuParam = new UpdateMenuParam();
        updateMenuParam.setId(67L);
        updateMenuParam.setParentId(67L);
        updateMenuParam.setTitle("单元测试");
        updateMenuParam.setName("单元测试");
        updateMenuParam.setLevel(5);
        updateMenuParam.setComponent("路由地址");
        updateMenuParam.setIcon("");
        updateMenuParam.setSort(100);

        addMenuParam = new AddMenuParam();
        addMenuParam.setParentId(67L);
        addMenuParam.setTitle("单元测试");
        addMenuParam.setName("单元测试");
        addMenuParam.setLevel(5);
        addMenuParam.setComponent("路由地址");
        addMenuParam.setIcon("");
        addMenuParam.setSort(100);
    }

    @Test
    @DisplayName("菜单列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(menuPageListParam, "menuPageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/menu/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuPageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("菜单列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("更改菜单可用状态接口测试")
    void change() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(changeMenuStatusParam, "changeMenuStatusParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/menu/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changeMenuStatusParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更改菜单可用状态接口测试成功！");
    }

    @Test
    @DisplayName("更新菜单信息接口测试")
    void updateMenu() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateMenuParam, "updateMenuParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/menu/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateMenuParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新菜单信息接口测试成功！");
    }

    @Test
    @DisplayName("根据菜单 id 删除菜单接口")
    void delete() throws Exception {
        // 创建测试用例
        Long menuId = 67L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/menu/delete/" + menuId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("根据菜单 id 删除菜单接口测试成功！");
    }

    @Test
    @DisplayName("根据角色 id 查询菜单 id 列表接口测试")
    void getByRoleId() throws Exception {
        // 创建测试用例
        Long roleId = 2L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/menu/getId/role/" + roleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("根据角色 id 查询菜单 id 列表接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("获取所有菜单接口测试")
    void getAll() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/menu/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("获取所有菜单接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("新增菜单接口测试")
    void addMenu() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(addMenuParam, "addMenuParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/menu/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMenuParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("新增菜单接口测试成功！");
    }

    @Test
    @DisplayName("菜单字典接口测试")
    void getMenuDict() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/menu/getMenuDict")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("菜单字典接口测试成功:{}", map.get("data"));
    }

}

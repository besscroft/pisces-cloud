package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.role.*;
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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/20 21:27
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static RolePageListParam rolePageListParam;
    private static ChangeRoleStatusParam changeRoleStatusParam;
    private static UpdateMenuByRoleParam updateMenuByRoleParam;
    private static UpdateResourceByRoleParam updateResourceByRoleParam;
    private static AddRoleParam addRoleParam;
    private static UpdateRoleByRoleParam updateRoleByRoleParam;

    @BeforeAll
    static void beforeRoleControllerTest() {
        rolePageListParam = new RolePageListParam();
        rolePageListParam.setPageNum(1);
        rolePageListParam.setPageSize(10);
        rolePageListParam.setQueryKey("");

        changeRoleStatusParam = new ChangeRoleStatusParam();
        changeRoleStatusParam.setRoleId(2L);
        changeRoleStatusParam.setStatus(true);

        Set<Long> menuIds = new HashSet();
        menuIds.add(1L);
        menuIds.add(2L);
        updateMenuByRoleParam = new UpdateMenuByRoleParam();
        updateMenuByRoleParam.setRoleId(2L);
        updateMenuByRoleParam.setMenuIds(menuIds);

        Set<Long> resourceIds = new HashSet();
        menuIds.add(1L);
        menuIds.add(2L);
        updateResourceByRoleParam = new UpdateResourceByRoleParam();
        updateResourceByRoleParam.setRoleId(2L);
        updateResourceByRoleParam.setResourceIds(resourceIds);

        addRoleParam = new AddRoleParam();
        addRoleParam.setRoleName("单元测试角色");
        addRoleParam.setRoleCode("unitCode");
        addRoleParam.setDescription("单元测试创建的角色");
        addRoleParam.setSort(3);

        updateRoleByRoleParam = new UpdateRoleByRoleParam();
        updateRoleByRoleParam.setId(3L);
        updateRoleByRoleParam.setRoleName("单元测试角色");
        updateRoleByRoleParam.setRoleCode("unitCode");
        updateRoleByRoleParam.setDescription("单元测试更新的角色");
        updateRoleByRoleParam.setSort(3);
    }

    @Test
    @DisplayName("角色列表接口（分页）接口测试")
    void list() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(rolePageListParam, "rolePageListParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/role/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rolePageListParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        Assertions.assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("角色列表接口（分页）接口测试成功:{}", map.get("data"));
    }

    @Test
    @DisplayName("更改角色可用状态接口测试")
    void change() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(changeRoleStatusParam, "changeRoleStatusParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/role/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changeRoleStatusParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更改角色可用状态接口测试成功！");
    }

    @Test
    @DisplayName("更改角色菜单接口测试")
    void updateMenu() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateMenuByRoleParam, "updateMenuParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/role/update/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateMenuByRoleParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更改角色菜单接口测试成功！");
    }

    @Test
    @DisplayName("更改角色资源接口测试")
    void updateResource() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateResourceByRoleParam, "updateResourceParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/role/update/resource")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateResourceByRoleParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更改角色资源接口测试成功！");
    }

    @Test
    @DisplayName("角色删除接口测试")
    void delete() throws Exception {
        Long roleId = 3L;
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/role/delete/" + roleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("角色删除接口测试成功！");
    }

    @Test
    @DisplayName("新增角色接口测试")
    void add() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(addRoleParam, "addRoleParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/role/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addRoleParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("新增角色接口测试成功！");
    }

    @Test
    @DisplayName("更新角色接口测试")
    void update() throws Exception {
        // 验证测试用例是否创建
        assertNotNull(updateRoleByRoleParam, "updateRoleParam is null");

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/role/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRoleByRoleParam)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("更新角色接口测试成功！");
    }

    @Test
    @DisplayName("角色字典接口测试")
    void getRoleDict() throws Exception {
        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/role/getRoleDict")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("角色字典接口测试成功！");
    }

    @Test
    @DisplayName("根据用户 id 获取角色信息接口测试")
    void getRoleById() throws Exception {
        // 创建测试用例
        Long userId = 1L;

        // 发起测试请求
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/role/get/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        // 验证 http 状态码
        assertEquals(HttpStatus.SUCCESS, response.getStatus());
        Map map = objectMapper.readValue(response.getContentAsString(), Map.class);
        // 验证业务状态码
        assertEquals(HttpStatus.SUCCESS, map.get("code"));
        log.info("根据用户 id 获取角色信息接口测试成功！");
    }

}

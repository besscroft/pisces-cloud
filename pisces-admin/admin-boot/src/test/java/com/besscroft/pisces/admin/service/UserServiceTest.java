package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.UserListDto;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.framework.common.constant.HttpStatus;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description 用户服务测试类
 * @Author Bess Croft
 * @Date 2022/2/12 22:32
 */
@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 登录测试依赖上游系统，否则无法颁发 token
     * @throws JsonProcessingException
     */
    @Test
    @DisplayName("登录方法测试")
    void login() throws JsonProcessingException {
        // 准备测试数据
        String account = "admin";
        String password = "666666";
        AjaxResult login = userService.login(account, password);
        // 验证是否与我们预期的状态值相符
        Assertions.assertEquals(HttpStatus.SUCCESS, login.get("code"));
        assertNotNull(login.get("data"));
        log.info("登录方法测试成功:{}", objectMapper.writeValueAsString(login));
    }

    @Test
    @DisplayName("获取用户角色列表方法测试")
    void getRoleList() throws JsonProcessingException {
        Long userId = 1L;
        List<Role> roleList = userService.getRoleList(userId);
        assertNotNull(roleList, "获取用户对应的角色列表失败！");
        log.info("获取用户角色列表方法测试成功:{}", objectMapper.writeValueAsString(roleList));
    }

    @Test
    @DisplayName("用户列表（分页）方法测试")
    void getUserListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        Long departId = null;
        List<UserListDto> listPage = userService.getUserListPage(pageNumber, pageSize, queryKey, departId);
        assertNotNull(listPage);
        log.info("用户列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("根据用户名获取用户方法测试")
    void getUser() {
        String username = "admin";
        User user = userService.getUser(username);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        log.info("根据用户名获取用户方法测试成功！");
    }

    @Test
    @DisplayName("更改用户可用状态方法测试")
    void changeStatus() {
        Long userId = 2L;
        Boolean status = true;
        userService.changeStatus(userId, status);
        log.info("更改用户可用状态方法测试成功！");
    }

    @Test
    @DisplayName("新增用户方法测试")
    void addUser() {
        User user = User.builder()
                .username("unitTest")
                .password("666666")
                .avatar("")
                .email("unitTest@qq.com")
                .name("unitTest")
                .realName("单元测试")
                .telephone("0")
                .birthday(LocalDateTime.now())
                .sex(1)
                .remark("这是一条单元测试新增的数据").build();
        userService.addUser(user);
        log.info("新增用户测试成功！");
    }

    @Test
    @DisplayName("更新用户方法测试")
    void updateUser() {
        User user = User.builder()
                .username("unitTest")
                .password("666666")
                .avatar("")
                .email("unitTest@qq.com")
                .name("unitTest")
                .realName("单元测试")
                .telephone("0")
                .birthday(LocalDateTime.now())
                .sex(1)
                .remark("这是一条单元测试新增的数据").build();
        userService.updateUser(user);
        log.info("更新用户测试成功！");
    }

    @Test
    @DisplayName("删除用户方法测试")
    void deleteUser() {
        Long userId = 6L;
        userService.deleteUser(userId);
        log.info("删除用户测试成功！");
    }

    @Test
    @DisplayName("更新用户角色方法测试")
    void updateRole() {
        Long userId = 6L;
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(2L);
        userService.updateRole(userId, roleIds);
        log.info("更新用户角色测试成功！");
    }

    @Test
    @DisplayName("更新用户部门方法测试")
    void updateDepart() {
        Long userId = 6L;
        Long departId = 6L;
        userService.updateDepart(userId, departId);
        log.info("更新用户部门测试成功！");
    }

    @Test
    @DisplayName("登录用户密码更新方法测试")
    void updatePassword() {
        String oldPassword = "666666";
        String newPassword = "123456";
        userService.updatePassword(oldPassword, newPassword);
        log.info("登录用户密码更新成功！");
    }

}

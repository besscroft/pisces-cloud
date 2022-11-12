package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.api.AuthFeignClient;
import com.besscroft.pisces.admin.domain.dto.UserListDto;
import com.besscroft.pisces.admin.mapper.DepartMapper;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.mapper.UserMapper;
import com.besscroft.pisces.admin.sender.MessageSender;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.exception.PiscesException;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 19:17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthFeignClient authFeignClient;
    private final MenuService menuService;
    private final RoleMapper roleMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final MessageSender messageSender;
    private final SecurityUtils securityUtils;
    private final DepartMapper departMapper;

    @Override
    public AjaxResult login(@NonNull String account, @NonNull String password) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstants.SYSTEM_CLIENT_ID);
        params.put("client_secret", AuthConstants.SYSTEM_CLIENT_SECRET);
        params.put("grant_type", AuthConstants.OAUTH2_PASSWORD);
        params.put("username", account);
        params.put("password", password);
        AjaxResult accessToken = authFeignClient.getAccessToken(params);
        LOGGER.info("accessToken 颁发成功:{}", accessToken);
        Map<String, String> data = (Map<String, String>) accessToken.get("data");
        redisTemplate.opsForValue().set(String.join(":", AuthConstants.SYSTEM_CLIENT_ID, account), data.get("token"));
        CompletableFuture.runAsync(RunnableWrapper.of(() -> {
            messageSender.sendBark(String.format("时间：%s，用户：%s 登录系统！", LocalDateTime.now(), account));
        }));
        return accessToken;
    }

    @Override
    public void loginOut() {
        User currentAdmin = getCurrentAdmin();
        redisTemplate.boundHashOps("system").delete("user:tree:" + currentAdmin.getId());
        redisTemplate.delete(String.join(":", AuthConstants.SYSTEM_CLIENT_ID, currentAdmin.getUsername()));
        redisTemplate.delete(String.join(":", AuthConstants.SYSTEM_USER, currentAdmin.getUsername()));
    }

    @Override
    public User getCurrentAdmin() {
        return securityUtils.getCurrentAdmin();
    }

    @Override
    public Map<String, Object> getUserInfo() {
        User currentAdmin = getCurrentAdmin();
        if (ObjectUtils.isEmpty(currentAdmin)) throw new PiscesException("登录已过期，请重新登录！");
        Map<String, Object> data = menuService.getTreeListById(currentAdmin.getId());
        data.put("username", currentAdmin.getUsername());
        data.put("realName", currentAdmin.getRealName());
        data.put("avatar", currentAdmin.getAvatar());
        List<Role> roleList = getRoleList(currentAdmin.getId());
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> roles = roleList.stream().map(Role::getRoleCode).collect(Collectors.toList());
            data.put("roles", roles);
            List<String> roleNames = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
            data.put("roleNames", roleNames);
        }
        try {
            LOGGER.info("用户信息.[user={}].[info={}]", currentAdmin.getUsername(), objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            LOGGER.warn("用户信息解析失败！");
        }
        return data;
    }

    @Override
    public List<Role> getRoleList(@NonNull Long userId) {
        return roleMapper.findAllByUserId(userId);
    }

    @Override
    public List<UserListDto> getUserListPage(Integer pageNum, Integer pageSize, String queryKey, Long departId) {
        if (Objects.nonNull(departId)) {
            // 如果部门id为根节点，那么查询所有用户
            // todo 多层级部门
            Integer exist = departMapper.selectParentExistById(departId);
            if (ObjectUtils.isEmpty(exist) || exist == 0) departId = null;
        }
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey, departId);
    }

    @Override
    public User getUser(@NonNull String username) {
        User user = this.baseMapper.findByUsername(username);
        Assert.notNull(user, "未查询到该用户信息！");
        user.setPassword("");
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(@NonNull Long userId, @NonNull Boolean status) {
        return this.baseMapper.updateStatusById(userId, status ? 1 : 0) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(@NonNull User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        log.debug("新增用户[user={}]", user);
        return this.baseMapper.insert(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(@NonNull User user) {
        User currentAdmin = getCurrentAdmin();
        user.setUpdater(currentAdmin.getUsername());
        user.setUpdateTime(LocalDateTime.now());
        log.debug("更新用户[user={}]", user);
        return this.baseMapper.updateByUserId(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(@NonNull Long userId) {
        return this.baseMapper.updateDelById(userId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(@NonNull Long userId, @NonNull Set<Long> roleIds) {
        int i = this.baseMapper.deleteUserRoleById(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return i > 0;
        }
        return this.baseMapper.insertUserRole(userId, roleIds) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDepart(@NonNull Long userId, @NonNull Long departId) {
        Integer exist = this.baseMapper.selectExistDepartByUserId(userId);
        if (Objects.isNull(exist)) {
            return this.baseMapper.insertUserDepart(userId, departId) > 0;
        }
        return this.baseMapper.updateUserDepart(userId, departId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String oldPassword, String newPassword) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        Assert.notNull(currentAdmin, "用户登录已失效！");
        User user = this.baseMapper.selectById(currentAdmin.getId());
        Assert.notNull(user, "未查询到该用户信息！");
        String encodeOldPassword = passwordEncoder.encode(oldPassword);
        if (Objects.equals(encodeOldPassword, user.getPassword()))
            throw new PiscesException("密码不一致，请重新输入！");
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        Assert.isTrue(this.baseMapper.updatePasswordById(user.getId(), encodeNewPassword) > 0, "更新密码失败！");
    }

}

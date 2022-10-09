package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.api.AuthFeignClient;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.mapper.UserMapper;
import com.besscroft.pisces.admin.sender.MessageSender;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        CompletableFuture.runAsync(() -> {
            messageSender.sendBark(String.format("时间：%s，用户：%s 登录系统！", LocalDateTime.now(), account));
        });
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
        Map<String, Object> data = menuService.getTreeListById(currentAdmin.getId());
        data.put("username", currentAdmin.getRealName());
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
    public List<User> getUserListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    public User getUser(@NonNull String username) {
        User user = this.baseMapper.findByUsername(username);
        Assert.notNull(user, "未查询到该用户信息！");
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

}

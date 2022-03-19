package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.api.AuthFeignClient;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.mapper.UserMapper;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.constant.AuthConstants;
import com.besscroft.pisces.result.AjaxResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 19:17
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthFeignClient authFeignClient;
    private final MenuService menuService;
    private final RoleMapper roleMapper;
    private final HttpServletRequest request;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public AjaxResult login(String account, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstants.SYSTEM_CLIENT_ID);
        params.put("client_secret", AuthConstants.SYSTEM_CLIENT_SECRET);
        params.put("grant_type", AuthConstants.OAUTH2_PASSWORD);
        params.put("username", account);
        params.put("password", password);
        AjaxResult accessToken = authFeignClient.getAccessToken(params);
        LOGGER.info("accessToken 颁发成功:{}", accessToken);
        Map<String, String> data = (Map<String, String>) accessToken.get("data");
        redisTemplate.opsForValue().set(AuthConstants.SYSTEM_CLIENT_ID + ":token:user:" + account, data.get("token").toString());
        return accessToken;
    }

    @Override
    public User getCurrentAdmin() {
        String header = request.getHeader(AuthConstants.USER_TOKEN_HEADER);
        if(StringUtils.isEmpty(header)){
            LOGGER.error("暂未登录或 token 已经过期！");
        }
        Map<String, Object> userDto = null;
        try {
            userDto = objectMapper.readValue(header, Map.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("token 无效！");
        }
        return this.baseMapper.selectById(Long.valueOf(String.valueOf(userDto.get("id"))));
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
        }
        // todo 登录时间更新
        try {
            LOGGER.info("用户信息.[user={}].[info={}]", currentAdmin.getUsername(), objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            LOGGER.warn("用户信息解析失败！");
        }
        return data;
    }

    @Override
    public List<Role> getRoleList(Long userId) {
        return roleMapper.findAllByUserId(userId);
    }

    @Override
    public List<User> getUserListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = this.baseMapper.selectAllByQueryKey(queryKey);
        return userList;
    }

    @Override
    public User getUser(String username) {
        return this.baseMapper.findByUsername(username);
    }

}

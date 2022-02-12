package com.besscroft.pisces.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.besscroft.pisces.admin.api.AuthFeignClient;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.repository.UserRepository;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.constant.AuthConstants;
import com.besscroft.pisces.dto.UserDto;
import com.besscroft.pisces.result.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthFeignClient authFeignClient;

    @Autowired
    protected MenuService menuService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
        if(StrUtil.isEmpty(header)){
            LOGGER.error("暂未登录或token已经过期");
        }
        UserDto userDto = JSONUtil.toBean(header, UserDto.class);
        Mono<User> userMono = userRepository.findById(userDto.getId());
        return userMono.block();
    }

    @Override
    public Map<String, Object> getUserInfo() {
        User currentAdmin = getCurrentAdmin();
        Map<String, Object> data = menuService.getTreeListById(currentAdmin.getId());
        data.put("username", currentAdmin.getRealName());
        data.put("avatar", currentAdmin.getAvatar());
        List<Role> roleList = getRoleList(currentAdmin.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        // todo 登录时间更新

        return data;
    }

    @Override
    public List<Role> getRoleList(Long userId) {
        return null;
    }

}

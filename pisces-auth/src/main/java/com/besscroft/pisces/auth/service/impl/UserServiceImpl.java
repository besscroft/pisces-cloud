package com.besscroft.pisces.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.auth.mapper.RoleMapper;
import com.besscroft.pisces.auth.mapper.UserMapper;
import com.besscroft.pisces.auth.entity.Role;
import com.besscroft.pisces.auth.entity.User;
import com.besscroft.pisces.auth.service.UserService;
import com.besscroft.pisces.framework.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 13:15
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RoleMapper roleMapper;

    @Override
    public UserDto loadUserByUsername(String username) {
        User user = this.baseMapper.findByUsername(username);
        if (!Objects.isNull(user)) {
            List<Role> roles = roleMapper.findAllByUserId(user.getId());
            UserDto dto = new UserDto();
            dto.setUsername(username);
            dto.setPassword(user.getPassword());
            dto.setId(user.getId());
            dto.setStatus(user.getStatus());
            if(!CollectionUtils.isEmpty(roles)){
                List<String> roleStrList = roles.stream().map(item -> item.getId() + "_" + item.getRoleName()).collect(Collectors.toList());
                dto.setRoles(roleStrList);
            }
            return dto;
        }
        return null;
    }

}

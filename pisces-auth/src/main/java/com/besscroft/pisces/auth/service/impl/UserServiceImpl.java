package com.besscroft.pisces.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.auth.domain.UserDto;
import com.besscroft.pisces.auth.entity.Role;
import com.besscroft.pisces.auth.entity.User;
import com.besscroft.pisces.auth.mapper.RoleMapper;
import com.besscroft.pisces.auth.mapper.UserMapper;
import com.besscroft.pisces.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 13:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDto loadUserByUsername(String username) {
        User user = this.baseMapper.findByUsername(username);
        if (ObjectUtil.isNotNull(user)) {
            List<Role> roles = roleMapper.findListByUserId(user.getId());
            UserDto dto = new UserDto();
            dto.setUsername(username);
            dto.setPassword(user.getPassword());
            dto.setId(user.getId());
            dto.setStatus(user.getStatus());
            if(CollUtil.isNotEmpty(roles)){
                List<String> roleStrList = roles.stream().map(item -> item.getId() + "_" + item.getRoleName()).collect(Collectors.toList());
                dto.setRoles(roleStrList);
            }
            return dto;
        }
        return null;
    }

}

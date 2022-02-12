package com.besscroft.pisces.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.besscroft.pisces.auth.repository.UserRepository;
import com.besscroft.pisces.dto.UserDto;
import com.besscroft.pisces.auth.entity.Role;
import com.besscroft.pisces.auth.entity.User;
import com.besscroft.pisces.auth.repository.RoleRepository;
import com.besscroft.pisces.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 13:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).block();
        if (ObjectUtil.isNotNull(user)) {
            List<Role> roles = roleRepository.findListByUserId(user.getId()).blockFirst();
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

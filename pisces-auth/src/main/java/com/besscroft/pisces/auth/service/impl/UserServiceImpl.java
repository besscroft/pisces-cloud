package com.besscroft.pisces.auth.service.impl;

import com.besscroft.pisces.auth.repository.UserRepository;
import com.besscroft.pisces.dto.UserDto;
import com.besscroft.pisces.auth.entity.Role;
import com.besscroft.pisces.auth.entity.User;
import com.besscroft.pisces.auth.service.UserService;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (!Objects.isNull(user)) {
            List<Role> roles = userRepository.findById(user.getId()).get().getRoles();
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

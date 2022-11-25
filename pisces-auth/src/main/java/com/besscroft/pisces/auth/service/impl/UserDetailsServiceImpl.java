package com.besscroft.pisces.auth.service.impl;

import com.besscroft.pisces.auth.domain.SecurityUser;
import com.besscroft.pisces.auth.service.UserService;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.constant.MessageConstant;
import com.besscroft.pisces.framework.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 自定义用户认证和授权
 * @Author Bess Croft
 * @Date 2022/2/4 13:14
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final HttpServletRequest request;
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        String clientId = request.getParameter(AuthConstants.CLIENT_ID);
        UserDto userDto = null;
        switch (clientId) {
            case AuthConstants.SYSTEM_CLIENT_ID: // 系统用户
                userDto = userService.loadUserByUsername(username);
                break;
        }
        if (userDto == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser user = new SecurityUser(userDto);
        if (!user.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return user;
    }

}

package com.besscroft.pisces.framework.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 登录用户信息
 * @Author Bess Croft
 * @Date 2022/2/4 12:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    private String clientId;

    private List<String> roles;

}

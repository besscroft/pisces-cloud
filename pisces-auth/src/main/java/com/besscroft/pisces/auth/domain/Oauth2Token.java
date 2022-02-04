package com.besscroft.pisces.auth.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 16:08
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Oauth2Token {

    /** 访问令牌 */
    private String token;

    /** 刷新令牌 */
    private String refreshToken;

    /** 访问令牌头前缀 */
    private String tokenHead;

    /** 有效时间(秒) */
    private int expiresIn;

}

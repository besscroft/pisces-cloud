package com.besscroft.pisces.auth.controller;

import com.besscroft.pisces.auth.domain.Oauth2Token;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 参考文档：https://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/endpoint/TokenEndpoint.html
 *
 * https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide
 *
 * 这里是对 TokenEndpoint 包装了一层 Controller
 * @Description 认证中心接口
 * @Author Bess Croft
 * @Date 2022/2/4 16:06
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Tag(name = "认证中心接口")
public class AuthController {

    private final TokenEndpoint tokenEndpoint;

    private final Set<HttpMethod> allowedRequestMethods = new HashSet<>(Arrays.asList(HttpMethod.POST));

    @GetMapping("/oauth/token")
    @Operation(summary = "OAuth2 认证生成 token")
    public CommonResult<Oauth2Token> getAccessToken(
            Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {

        if (!allowedRequestMethods.contains(HttpMethod.GET)) {
            throw new HttpRequestMethodNotSupportedException("GET");
        }
        return postAccessToken(principal, parameters);
    }

    /**
     * OAuth2 认证生成 token
     * @param principal
     * @param parameters:{grant_type:"授权模式",client_id:"Oauth2客户端ID",client_secret:"Oauth2客户端秘钥",refresh_token:"刷新token",username:"登录用户名",password:"登录密码"}
     * @return token
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/token")
    @Operation(summary = "OAuth2 认证生成 token")
    public CommonResult<Oauth2Token> postAccessToken(Principal principal,
             @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        log.info("请求到了，parameters:{}",parameters);
        if (!(principal instanceof Authentication)) {
            throw new InsufficientAuthenticationException(
                    "There is no client authentication. Try adding an appropriate authentication filter.");
        }
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2Token oauth2Token = Oauth2Token.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstants.JWT_TOKEN_PREFIX).build();
        log.info("生成的 token:{}",oauth2Token);
        return CommonResult.success("登录成功！", oauth2Token);
    }

}

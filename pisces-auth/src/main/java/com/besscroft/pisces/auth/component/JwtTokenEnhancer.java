package com.besscroft.pisces.auth.component;

import com.besscroft.pisces.auth.domain.SecurityUser;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description JWT 内容增强处理
 * @Author Bess Croft
 * @Date 2022/2/4 12:19
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(@NonNull OAuth2AccessToken oAuth2AccessToken, @NonNull OAuth2Authentication oAuth2Authentication) {
        SecurityUser user = (SecurityUser) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put("id", user.getId());
        info.put("account", user.getUsername());
        info.put("client_id",user.getClientId());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }

}

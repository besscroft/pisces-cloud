package com.besscroft.pisces.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Description 公钥获取接口
 * @Author Bess Croft
 * @Date 2022/2/4 16:32
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/publicKey")
public class PublicKeyController {

    private final KeyPair keyPair;

    /**
     * https://spring.io/guides/tutorials/spring-boot-oauth2/#_social_login_authserver
     * @return
     */
    @GetMapping("/get")
    public Map<String, Object> loadPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

}

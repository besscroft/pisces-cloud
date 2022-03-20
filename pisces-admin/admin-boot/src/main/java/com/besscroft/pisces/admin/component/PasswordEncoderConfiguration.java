package com.besscroft.pisces.admin.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description 密码处理器
 * @Author Bess Croft
 * @Date 2022/3/20 15:04
 */
@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 默认为 bcrypt
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

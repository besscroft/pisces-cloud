package com.besscroft.pisces.auth.config;

import com.besscroft.pisces.auth.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 16:34
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/publicKey/get", "/oauth/token").permitAll()
                // 下面这行是 SpringBoot Actuator 放行，如果不想用，可以注释掉
                .and().authorizeRequests().antMatchers("/actuator/**").anonymous()
                // 下面这行是 swagger api 放行，如果不想用，可以注释掉
                .and().authorizeRequests().antMatchers("/v3/**", "/**/*.js", "/**/*.css", "/**/*.png", "/**/*.ico").anonymous()
                .anyRequest().authenticated();
    }

    /**
     * 如果不配置的话，SpringBoot会自动配置一个AuthenticationManager,并覆盖掉内存中的用户
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-updated
     *
     * https://docs.spring.io/spring-security/site/docs/5.0.0.RELEASE/reference/htmlsingle/#ns-password-encoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 默认为 bcrypt
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}

package com.besscroft.pisces.gateway.config;

import com.besscroft.pisces.constant.AuthConstants;
import com.besscroft.pisces.gateway.authorization.AuthorizationManager;
import com.besscroft.pisces.gateway.filter.AuthGlobalFilter;
import com.besscroft.pisces.gateway.filter.IgnoreUrlsRemoveJwtFilter;
import com.besscroft.pisces.gateway.handler.PiscesServerAccessDeniedHandler;
import com.besscroft.pisces.gateway.handler.PiscesServerAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;

/**
 * @Description 网关配置
 * @Author Bess Croft
 * @Date 2022/2/2 22:27
 */
@AllArgsConstructor
@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
public class WebFluxSecurityConfiguration {

    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;
    private final AuthGlobalFilter authGlobalFilter;

    @Bean
    @Order(0)
    public SecurityWebFilterChain systemSecurityWebFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        // 自定义token过期或未登录返回结果
        http.oauth2ResourceServer().authenticationEntryPoint(piscesServerAuthenticationEntryPoint());
        http.addFilterBefore(authGlobalFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        // 对白名单路径，直接移除 JWT 请求头
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                // 白名单配置
                .pathMatchers("").permitAll()
                // 鉴权管理器配置
                .anyExchange().access(authorizationManager)
                .and()
                .exceptionHandling()
                // 未授权处理器
                .accessDeniedHandler(piscesServerAccessDeniedHandler())
                // 未认证处理器
                .authenticationEntryPoint(piscesServerAuthenticationEntryPoint())
                // 关闭csrf
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    public PiscesServerAccessDeniedHandler piscesServerAccessDeniedHandler() {
        return new PiscesServerAccessDeniedHandler();
    }

    @Bean
    public PiscesServerAuthenticationEntryPoint piscesServerAuthenticationEntryPoint() {
        return new PiscesServerAuthenticationEntryPoint();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.JWT_AUTHORITIES_KEY);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}

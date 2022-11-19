package com.besscroft.pisces.file.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description SpringDoc 配置
 * @Author Bess Croft
 * @Date 2022/7/19 20:38
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Pisces File")
                        .description("一站式微服务解决方案，基于 Spring Cloud 2021.0.1 & Alibaba 2021.0.1.0 + Spring Security OAuth2 + PostgreSQL + Mybatis-Plus 构建。")
                        .version("v2.0.0")
                        .contact(new Contact()
                                .name("Bess Croft")
                                .email("besscroft@foxmail.com"))
                        .license(new License().name("MIT license").url("https://github.com/besscroft/pisces-cloud/blob/main/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Pisces Cloud 文档")
                        .url("https://developer.besscroft.com/pisces/"));
    }

}

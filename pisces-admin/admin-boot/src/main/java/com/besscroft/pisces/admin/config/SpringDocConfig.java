package com.besscroft.pisces.admin.config;

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
 * @Date 2022/6/3 20:21
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Pisces Admin")
                        .description("Spring shop sample application")
                        .version("v1.1.2")
                        .contact(new Contact()
                                .name("Bess Croft")
                                .email("besscroft@foxmail.com"))
                        .license(new License().name("GPL-3.0 license").url("https://github.com/besscroft/pisces-cloud/blob/main/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Pisces Cloud 文档")
                        .url("https://developer.besscroft.com/pisces/"));
    }

}

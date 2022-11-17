package com.besscroft.pisces.file.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description 对象存储自动配置
 * @Author Bess Croft
 * @Date 2022/8/5 13:18
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OSSProperties.class)
@Import({AliyunOSSConfiguration.class, MinioOSSConfiguration.class})
public class OSSAutoConfiguration {
}

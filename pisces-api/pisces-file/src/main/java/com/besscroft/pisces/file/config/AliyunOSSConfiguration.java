package com.besscroft.pisces.file.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.besscroft.pisces.file.service.StorageService;
import com.besscroft.pisces.file.service.impl.OssServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 阿里云 OSS 配置
 * @Author Bess Croft
 * @Date 2022/8/5 12:07
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "oss.aliyun", name = "active", havingValue = "true")
@ConditionalOnClass(OSS.class)
public class AliyunOSSConfiguration {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Bean(name = "aliyunStorageService")
    StorageService aliyunStorage(OSSProperties ossProperties) {
        OSS ossClient = ossClient(ossProperties);
        return new OssServiceImpl(ossClient, ossProperties);
    }

    @Bean
    public OSS ossClient(OSSProperties ossProperties) {
        OSSProperties.Aliyun aliyun = ossProperties.getAliyun();
        try {
            log.info("oss 初始化:{}", objectMapper.writeValueAsString(aliyun));
        } catch (JsonProcessingException ignored) {
            log.error("oss 初始化配置解析异常！");
        }
        return new OSSClientBuilder()
                .build(aliyun.getEndpoint(),
                        aliyun.getAccessKeyId(),
                        aliyun.getAccessKeySecret());
    }

}

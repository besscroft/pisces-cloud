package com.besscroft.pisces.file.config;

import com.besscroft.pisces.file.service.StorageService;
import com.besscroft.pisces.file.service.impl.MinioServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Minio OSS 配置
 * @Author Bess Croft
 * @Date 2022/11/16 13:54
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "oss.minio", name = "active", havingValue = "true")
@ConditionalOnClass(MinioClient.class)
public class MinioOSSConfiguration {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Bean(name = "minioStorageService")
    StorageService minioStorage(OSSProperties ossProperties) {
        MinioClient client = minioClient(ossProperties);
        return new MinioServiceImpl(client, ossProperties);
    }

    @Bean
    public MinioClient minioClient(OSSProperties ossProperties) {
        OSSProperties.Minio minio = ossProperties.getMinio();
        try {
            log.info("minio 初始化:{}", objectMapper.writeValueAsString(minio));
        } catch (JsonProcessingException ignored) {
            log.error("minio 初始化配置解析异常！");
        }
        return new MinioClient.Builder()
                .endpoint(minio.getEndpoint())
                .credentials(minio.getAccessKey(), minio.getSecretKey())
                .build();
    }

}

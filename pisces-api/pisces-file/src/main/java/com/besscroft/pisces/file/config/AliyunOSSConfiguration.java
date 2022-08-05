package com.besscroft.pisces.file.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.besscroft.pisces.file.service.StorageService;
import com.besscroft.pisces.file.service.impl.OssServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 阿里云 OSS 配置
 * @Author Bess Croft
 * @Date 2022/8/5 12:07
 */
@Configuration
@ConditionalOnProperty(prefix = "oss.aliyun", name = "active", havingValue = "true")
@ConditionalOnClass(OSS.class)
public class AliyunOSSConfiguration {

    @Bean(name = "aliyunStorageService")
    StorageService aliyunStorage(OSSProperties ossProperties) {
        OSS ossClient = ossClient(ossProperties);
        return new OssServiceImpl(ossClient, ossProperties);
    }

    @Bean
    public OSS ossClient(OSSProperties ossProperties) {
        OSSProperties.Aliyun aliyun = ossProperties.getAliyun();
        OSS ossClient = new OSSClientBuilder()
                .build(aliyun.getEndpoint(),
                        aliyun.getAccessKeyId(),
                        aliyun.getAccessKeySecret());
        return ossClient;
    }

}

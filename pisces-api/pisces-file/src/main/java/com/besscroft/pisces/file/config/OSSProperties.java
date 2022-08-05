package com.besscroft.pisces.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description 对象存储
 * @Author Bess Croft
 * @Date 2022/8/5 11:58
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {

    private Aliyun aliyun;

    /**
     * 阿里云 OSS
     */
    @Data
    public static class Aliyun {
        private boolean active;
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
        private String prefix;
    }

}

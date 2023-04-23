package com.besscroft.pisces.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.besscroft.pisces.file.config.OSSProperties;
import com.besscroft.pisces.file.service.StorageService;
import com.besscroft.pisces.framework.common.exception.PiscesException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description 阿里云 OSS 实现类
 * @Author Bess Croft
 * @Date 2022/8/1 21:49
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements StorageService {

    private final OSS ossClient;
    private final OSSProperties ossProperties;

    @Override
    public String putObject(@Nullable String bucketName, @NonNull String objectName, @NonNull InputStream inputStream, @NonNull String contentType) {
        if (null == bucketName) {
            bucketName = ossProperties.getAliyun().getBucketName();
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        this.ossClient.putObject(bucketName, ossProperties.getAliyun().getPrefix() + objectName, inputStream, objectMetadata);
        return getObjectUrl(bucketName, objectName);
    }

    @Override
    public String putObjectCdn(@Nullable String bucketName, @NonNull String objectName, @NonNull InputStream inputStream, @NonNull String contentType) {
        if (null == bucketName) {
            bucketName = ossProperties.getAliyun().getBucketName();
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        this.ossClient.putObject(bucketName, ossProperties.getAliyun().getPrefix() + objectName, inputStream, objectMetadata);
        return getObjectCdnUrl(bucketName, objectName);
    }

    @Override
    public InputStream getObject(@NonNull String bucketName, @NonNull String objectName) {
        return this.ossClient.getObject(bucketName, ossProperties.getAliyun().getPrefix() + objectName).getObjectContent();
    }

    @Override
    public String getObjectUrl(@NonNull String bucketName, @NonNull String objectName) {
        try {
            URL url = new URL(ossProperties.getAliyun().getEndpoint());
            StringBuilder sb = new StringBuilder();
            sb.append(url.getProtocol())
                    .append(bucketName)
                    .append(".")
                    .append(url.getPath())
                    .append("/")
                    .append(ossProperties.getAliyun().getPrefix())
                    .append(objectName);
            return sb.toString();
        } catch (MalformedURLException e) {
            log.error("地址获取失败！:{}", e);
            throw new PiscesException("地址获取失败！");
        }
    }

    @Override
    public String getObjectCdnUrl(@NonNull String bucketName, @NonNull String objectName) {
        StringBuilder sb = new StringBuilder();
        sb.append(ossProperties.getAliyun().getCdnEndpoint())
                .append("/")
                .append(ossProperties.getAliyun().getCdnPrefix())
                .append(objectName);
        return sb.toString();
    }

    @Override
    public void removeObject(@NonNull String bucketName, @NonNull String objectName) {
        this.ossClient.deleteObject(bucketName, ossProperties.getAliyun().getPrefix() + objectName);
    }

}

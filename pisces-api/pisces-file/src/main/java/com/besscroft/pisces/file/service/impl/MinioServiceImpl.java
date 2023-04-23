package com.besscroft.pisces.file.service.impl;

import com.besscroft.pisces.file.config.OSSProperties;
import com.besscroft.pisces.file.service.StorageService;
import com.besscroft.pisces.framework.common.exception.PiscesException;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Description Minio OSS 实现类
 * @Author Bess Croft
 * @Date 2022/11/16 14:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements StorageService {

    private final MinioClient minioClient;
    private final OSSProperties ossProperties;

    @Override
    public String putObject(@Nullable String bucketName, @NonNull String objectName, @NonNull InputStream inputStream, @NonNull String contentType) {
        if (null == bucketName) {
            bucketName = ossProperties.getMinio().getBucketName();
        }
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(ossProperties.getMinio().getPrefix() + objectName)
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(contentType)
                    .build();
            this.minioClient.putObject(objectArgs);
        } catch (Exception e) {
            log.error("文件上传失败:{}", e);
            throw new PiscesException("文件上传失败！");
        }
        return getObjectUrl(bucketName, objectName);
    }

    @Override
    public String putObjectCdn(@Nullable String bucketName, @NonNull String objectName, @NonNull InputStream inputStream, @NonNull String contentType) {
        if (null == bucketName) {
            bucketName = ossProperties.getMinio().getBucketName();
        }
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(ossProperties.getMinio().getCdnPrefix() + objectName)
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(contentType)
                    .build();
            this.minioClient.putObject(objectArgs);
        } catch (Exception e) {
            log.error("文件上传失败:{}", e);
            throw new PiscesException("文件上传失败！");
        }
        return getObjectCdnUrl(bucketName, objectName);
    }

    @Override
    public InputStream getObject(@NonNull String bucketName, @NonNull String objectName) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(ossProperties.getMinio().getPrefix() + objectName)
                .build();
        try {
            return minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            log.error("获取文件失败:{}", e);
            throw new PiscesException("获取文件失败！");
        }
    }

    @Override
    public String getObjectUrl(@NonNull String bucketName, @NonNull String objectName) {
        StringBuilder sb = new StringBuilder();
        sb.append(ossProperties.getMinio().getEndpoint())
                .append("/")
                .append(bucketName)
                .append("/")
                .append(ossProperties.getMinio().getPrefix())
                .append(objectName);
        return sb.toString();
    }

    @Override
    public String getObjectCdnUrl(@NonNull String bucketName, @NonNull String objectName) {
        StringBuilder sb = new StringBuilder();
        sb.append(ossProperties.getMinio().getCdnEndpoint())
                .append("/")
                .append(bucketName)
                .append("/")
                .append(ossProperties.getMinio().getCdnPrefix())
                .append(objectName);
        return sb.toString();
    }

    @Override
    public void removeObject(@NonNull String bucketName, @NonNull String objectName) {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(ossProperties.getMinio().getPrefix() + objectName)
                .build();
        try {
            this.minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            log.error("删除文件失败:{}", e);
            throw new PiscesException("删除文件失败！");
        }
    }

}

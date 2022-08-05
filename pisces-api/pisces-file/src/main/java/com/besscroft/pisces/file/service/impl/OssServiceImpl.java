package com.besscroft.pisces.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.besscroft.pisces.file.config.OSSProperties;
import com.besscroft.pisces.file.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/1 21:49
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements StorageService {

    private final OSS ossClient;
    private final OSSProperties ossProperties;

    @Override
    public String putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception {
        if (null == bucketName) {
            bucketName = ossProperties.getAliyun().getBucketName();
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        PutObjectResult objectResult = this.ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);
        ResponseMessage response = objectResult.getResponse();
        String uri = response.getUri();
        return uri;
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return this.ossClient.getObject(bucketName, objectName).getObjectContent();
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://")
                .append(bucketName)
                .append(".")
                .append(ossProperties.getAliyun().getEndpoint())
                .append("/")
                .append(ossProperties.getAliyun().getPrefix())
                .append(objectName);
        return stringBuffer.toString();
    }

    @Override
    public void removeObject(String bucketName, String objectName) throws Exception {
        this.ossClient.deleteObject(bucketName, objectName);
    }

}

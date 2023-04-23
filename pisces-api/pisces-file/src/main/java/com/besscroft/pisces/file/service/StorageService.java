package com.besscroft.pisces.file.service;

import java.io.InputStream;

/**
 * @Description 存储服务接口
 * @Author Bess Croft
 * @Date 2022/8/1 21:48
 */
public interface StorageService {

    /**
     * 存放对象进存储桶
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @param inputStream 对象的输入流
     * @param contentType Http MimeType
     * @return 文件链接
     */
    String putObject(String bucketName, String objectName, InputStream inputStream, String contentType);

    /**
     * 存放对象进存储桶
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @param inputStream 对象的输入流
     * @param contentType Http MimeType
     * @return CDN 文件链接
     */
    String putObjectCdn(String bucketName, String objectName, InputStream inputStream, String contentType);

    /**
     * 从存储桶中获取对象
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @return 对象的输入流
     */
    InputStream getObject(String bucketName, String objectName);

    /**
     * 获取对象的地址
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @return 文件地址
     */
    String getObjectUrl(String bucketName, String objectName);

    /**
     * 获取对象的地址
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @return CDN 文件地址
     */
    String getObjectCdnUrl(String bucketName, String objectName);

    /**
     * 从存储桶中删除对象
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     */
    void removeObject(String bucketName, String objectName);

}

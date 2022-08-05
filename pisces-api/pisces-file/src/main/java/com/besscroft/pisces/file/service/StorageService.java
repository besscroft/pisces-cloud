package com.besscroft.pisces.file.service;

import java.io.InputStream;

/**
 * @Description
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
     * @throws Exception
     */
    String putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;

    /**
     * 从存储桶中获取对象
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @return 对象的输入流
     * @throws Exception
     */
    InputStream getObject(String bucketName, String objectName) throws Exception;

    /**
     * 获取对象的地址
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @return 文件地址
     * @throws Exception
     */
    String getObjectUrl(String bucketName, String objectName) throws Exception;

    /**
     * 从存储桶中删除对象
     * @param bucketName 存储桶名称
     * @param objectName 自定义对象名称
     * @throws Exception
     */
    void removeObject(String bucketName, String objectName) throws Exception;

}

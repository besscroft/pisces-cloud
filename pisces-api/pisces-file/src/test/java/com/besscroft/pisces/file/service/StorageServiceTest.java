package com.besscroft.pisces.file.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/8 14:26
 */
@Slf4j
@SpringBootTest
public class StorageServiceTest {

    @Autowired
    private Map<String, StorageService> storageService;

    @Test
    @DisplayName("存放对象进存储桶方法测试")
    void putObject() {

    }

    @Test
    @DisplayName("从存储桶中获取对象方法测试")
    void getObject() {

    }

    @Test
    @DisplayName("获取对象的地址方法测试")
    void getObjectUrl() {

    }

    @Test
    @DisplayName("从存储桶中删除对象方法测试")
    void removeObject() {

    }

}

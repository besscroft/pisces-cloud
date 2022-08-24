package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.entity.Dict;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/19 10:21
 */
@Slf4j
@SpringBootTest
public class DictServiceTest {

    @Autowired
    private DictService dictService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("根据字典分组获取所有字典方法测试")
    void queryAllByGroup() throws JsonProcessingException {
        String groupName = "RESOURCE";
        List<Dict> dictList = dictService.queryAllByGroup(groupName);
        assertNotNull(dictList);
        log.info("根据字典分组获取所有字典方法测试成功:{}", objectMapper.writeValueAsString(dictList));
    }

    @Test
    @DisplayName("字典分页方法测试")
    void pageList() throws JsonProcessingException {
        Integer pageNum = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<Dict> dictList = dictService.pageList(pageNum, pageSize, queryKey);
        assertNotNull(dictList);
        log.info("字典分页方法测试成功:{}", objectMapper.writeValueAsString(dictList));
    }

}

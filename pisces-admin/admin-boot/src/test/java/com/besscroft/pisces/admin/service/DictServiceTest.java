package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.framework.common.entity.Dict;
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

    @Test
    @DisplayName("新增字典方法测试")
    void addDict() {
        Dict dict = new Dict();
        dict.setGroupName("UNIT_TEST");
        dict.setKey("UNIT_KEY");
        dict.setValue("UNIT_VALUE");
        dict.setRemark("这是一条单元测试新增的数据！");
        dictService.addDict(dict);
        log.info("新增字典方法测试成功！");
    }

    @Test
    @DisplayName("更新字典方法测试")
    void updateDict() {
        Dict dict = new Dict();
        dict.setGroupName("UNIT_TEST");
        dict.setKey("UNIT_KEY");
        dict.setValue("UNIT_VALUE");
        dict.setRemark("这是一条单元测试更新的数据！");
        dictService.addDict(dict);
        log.info("更新字典方法测试成功！");
    }

    @Test
    @DisplayName("删除字典（软删除）方法测试")
    void deleteUser() {
        Long dictId = 100L;
        dictService.deleteDict(dictId);
        log.info("删除字典（软删除）测试成功！");
    }

}

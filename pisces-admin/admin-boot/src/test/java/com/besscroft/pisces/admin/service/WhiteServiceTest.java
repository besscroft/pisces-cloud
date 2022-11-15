package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.framework.common.entity.White;
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
 * @Date 2022/5/14 22:21
 */
@Slf4j
@SpringBootTest
public class WhiteServiceTest {

    @Autowired
    private WhiteService whiteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("获取白名单列表（分页）方法测试")
    void getWhiteListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<White> listPage = whiteService.getWhiteListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("获取白名单列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("新增白名单方法测试")
    void addUser() {
        White white = White.builder()
                .title("unitTest")
                .path("/unit")
                .remark("这时一条单元测试生成的数据").build();
        whiteService.addWhite(white);
        log.info("新增白名单方法测试成功！");
    }

    @Test
    @DisplayName("更新白名单方法测试")
    void updateWhite() {
        White white = White.builder()
                .id(100L)
                .title("unitTest")
                .path("/unit")
                .remark("这时一条单元测试更新的数据").build();
        whiteService.updateWhite(white);
        log.info("更新白名单方法测试成功！");
    }

    @Test
    @DisplayName("删除白名单方法测试")
    void deleteWhite() {
        Long whiteId = 100L;
        whiteService.deleteWhite(whiteId);
        log.info("删除白名单方法测试成功！");
    }

    @Test
    @DisplayName("白名单字典方法测试")
    void getWhiteDict() throws JsonProcessingException {
        List<WhiteDictDto> whiteDict = whiteService.getWhiteDict();
        assertNotNull(whiteDict);
        log.info("白名单字典方法测试成功:{}", objectMapper.writeValueAsString(whiteDict));
    }

}

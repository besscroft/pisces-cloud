package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.entity.Depart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/24 19:04
 */
@Slf4j
@SpringBootTest
public class DepartServiceTest {

    @Autowired
    private DepartService departService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("获取部门/组织列表（分页）方法测试")
    void getMenuListPage() throws JsonProcessingException {
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String queryKey = "";
        List<Depart> listPage = departService.getDepartListPage(pageNumber, pageSize, queryKey);
        assertNotNull(listPage);
        log.info("获取部门/组织列表（分页）方法测试成功:{}", objectMapper.writeValueAsString(listPage));
    }

    @Test
    @DisplayName("删除部门/组织方法测试")
    void deleteUser() {
        Long departId = 6L;
        boolean flag = departService.deleteDepart(departId);
        assertTrue(flag, "删除部门/组织失败！");
        log.info("删除部门/组织测试成功！");
    }

}

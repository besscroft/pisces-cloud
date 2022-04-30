package com.besscroft.pisces.admin.service;

import com.besscroft.pisces.admin.domain.dto.DepartDictDto;
import com.besscroft.pisces.admin.domain.dto.DepartDto;
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
        List<DepartDto> listPage = departService.getDepartListPage(pageNumber, pageSize, queryKey);
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

    @Test
    @DisplayName("新增部门方法测试")
    void addResource() {
        // 创建测试实例
        Depart depart = new Depart();
        depart.setParentId(1L);
        depart.setName("测试部门名称");
        depart.setDescription("这是一条单元测试创建的部门");
        depart.setSort(2);
        boolean flag = departService.addDepart(depart);
        assertTrue(flag, "新增部门失败！");
        log.info("新增部门方法测试成功！");
    }

    @Test
    @DisplayName("更新部门方法测试")
    void updateResource() {
        // 创建测试实例
        Depart depart = new Depart();
        depart.setId(100L);
        depart.setParentId(1L);
        depart.setName("测试部门名称");
        depart.setDescription("这是一条单元测试更新的部门");
        depart.setSort(2);
        boolean flag = departService.updateDepart(depart);
        assertTrue(flag, "更新部门失败！");
        log.info("更新部门方法测试成功！");
    }

    @Test
    @DisplayName("获取部门字典方法测试")
    void getDepartDict() throws JsonProcessingException {
        List<DepartDictDto> departDictDtos = departService.getDepartDict();
        assertNotNull(departDictDtos);
        log.info("获取部门字典方法测试成功:{}", objectMapper.writeValueAsString(departDictDtos));
    }

}

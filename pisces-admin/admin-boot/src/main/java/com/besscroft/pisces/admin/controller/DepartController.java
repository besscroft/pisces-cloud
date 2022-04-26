package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.depart.DepartPageListParam;
import com.besscroft.pisces.admin.entity.Depart;
import com.besscroft.pisces.admin.service.DepartService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 组织/部门接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/depart")
public class DepartController {

    private final DepartService departService;

    /**
     * 组织/部门列表接口（分页）
     * @param param 请求参数
     * @return 组织/部门列表分页数据
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody @Valid DepartPageListParam param) {
        // todo 返回树形结构
        List<Depart> listPage = departService.getDepartListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 组织/部门删除接口
     * @param departId 组织/部门 id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable("id") Long departId) {
        boolean b = departService.deleteDepart(departId);
        Assert.isTrue(b, "组织/部门删除失败！");
        return AjaxResult.success("删除成功！");
    }

}

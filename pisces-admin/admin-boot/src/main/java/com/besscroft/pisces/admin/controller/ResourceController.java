package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.dto.ResourceDto;
import com.besscroft.pisces.admin.domain.param.resource.ResourcePageListParam;
import com.besscroft.pisces.admin.entity.Resource;
import com.besscroft.pisces.admin.service.ResourceService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Description 资源接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    /**
     * 资源列表接口（分页）
     * @param param 请求参数
     * @return 资源列表分页数据
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody @Valid ResourcePageListParam param) {
        // todo 返回树形结构
        List<Resource> listPage = resourceService.getResourceListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 获取资源树接口
     * @return
     */
    @GetMapping("/getAll")
    public AjaxResult getAll() {
        List<ResourceDto> resourceDtoList = resourceService.getAll();
        return AjaxResult.success(resourceDtoList);
    }

    /**
     * 根据角色 id 查询资源 id 列表接口
     * @param roleId 角色 id
     * @return 资源 id 列表
     */
    @GetMapping("/getId/role/{roleId}")
    public AjaxResult getByRoleId(@PathVariable(name = "roleId") Long roleId) {
        Set<Long> ids = resourceService.getIdsByRoleId(roleId);
        return AjaxResult.success(ids);
    }

    /**
     * 资源删除接口
     * @param resourceId 资源 id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable("id") Long resourceId) {
        boolean b = resourceService.deleteResource(resourceId);
        Assert.isTrue(b, "资源删除失败！");
        return AjaxResult.success("删除成功！");
    }

}

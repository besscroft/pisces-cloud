package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.ResourceConverterMapper;
import com.besscroft.pisces.admin.domain.dto.ResourceDto;
import com.besscroft.pisces.admin.domain.param.resource.AddResourceParam;
import com.besscroft.pisces.admin.domain.param.resource.ResourcePageListParam;
import com.besscroft.pisces.admin.domain.param.resource.UpdateResourceParam;
import com.besscroft.pisces.framework.common.entity.Resource;
import com.besscroft.pisces.admin.service.ResourceService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 资源接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@Tag(name = "资源接口")
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
    @Operation(summary = "资源列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<Resource>> list(@RequestBody @Valid ResourcePageListParam param) {
        // todo 返回树形结构
        List<Resource> listPage = resourceService.getResourceListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 获取资源树接口
     * @return
     */
    @Operation(summary = "获取资源树接口")
    @GetMapping("/getAll")
    public CommonResult<List<ResourceDto>> getAll() {
        List<ResourceDto> resourceDtoList = resourceService.getAll();
        return CommonResult.success(resourceDtoList);
    }

    /**
     * 根据角色 id 查询资源 id 列表接口
     * @param roleId 角色 id
     * @return 资源 id 列表
     */
    @Operation(summary = "根据角色 id 查询资源 id 列表接口")
    @GetMapping("/getId/role/{roleId:[\\d]+}")
    public CommonResult<Set<Long>> getByRoleId(@PathVariable(name = "roleId") Long roleId) {
        Set<Long> ids = resourceService.getIdsByRoleId(roleId);
        return CommonResult.success(ids);
    }

    /**
     * 资源删除接口
     * @param resourceId 资源 id
     * @return
     */
    @Operation(summary = "资源删除接口")
    @DeleteMapping("/delete/{id:[\\d]+}")
    public AjaxResult deleteById(@PathVariable("id") Long resourceId) {
        resourceService.deleteResource(resourceId);
        return AjaxResult.success("删除成功！");
    }

    /**
     * 新增资源接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "新增资源接口")
    @PostMapping("/add")
    public AjaxResult addResource(@RequestBody @Valid AddResourceParam param) {
        Resource resource = ResourceConverterMapper.INSTANCE.AddParamToResource(param);
        resourceService.addResource(resource);
        return AjaxResult.success("新增成功！");
    }

    /**
     * 更新资源接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "更新资源接口")
    @PutMapping("/update")
    public AjaxResult updateResource(@RequestBody @Valid UpdateResourceParam param) {
        Resource resource = ResourceConverterMapper.INSTANCE.UpdateParamToResource(param);
        resourceService.updateResource(resource);
        return AjaxResult.success("更新成功！");
    }

    /**
     * 资源角色规则接口
     * @return
     */
    @Operation(summary = "资源角色规则接口")
    @GetMapping("/init")
    public CommonResult<Map<String, List<String>>> initRoleResourceMap() {
        Map<String, List<String>> stringListMap = resourceService.initRoleResourceMap();
        return CommonResult.success(stringListMap);
    }

}

package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.admin.domain.param.resourceCategory.AddResourceCategoryParam;
import com.besscroft.pisces.admin.domain.param.resourceCategory.ResourceCategoryPageListParam;
import com.besscroft.pisces.admin.domain.param.resourceCategory.UpdateResourceCategoryParam;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 资源类别接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource/category")
public class ResourceCategoryController {

    private final ResourceCategoryService resourceCategoryService;

    /**
     * 资源类别列表接口（分页）
     * @param param 请求参数
     * @return 资源类别列表分页数据
     */
    @PostMapping("/list")
    public CommonResult<CommonPage<ResourceCategory>> list(@RequestBody @Valid ResourceCategoryPageListParam param) {
        List<ResourceCategory> listPage = resourceCategoryService.getResourceCategoryListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 资源类别删除接口
     * @param resourceCategoryId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable("id") Long resourceCategoryId) {
        boolean b = resourceCategoryService.deleteResourceCategory(resourceCategoryId);
        Assert.isTrue(b, "资源类别删除失败！");
        return AjaxResult.success("删除成功！");
    }

    /**
     * 资源类别字典查询接口
     * @return 资源类别字典
     */
    @GetMapping("/getResourceCategoryDict")
    public CommonResult<List<ResourceCategoryDictDto>> getResourceCategoryDict() {
        List<ResourceCategoryDictDto> resourceCategoryDict = resourceCategoryService.getResourceCategoryDict();
        return CommonResult.success(resourceCategoryDict);
    }

    /**
     * 新增资源类别接口
     * @param param 请求参数
     * @return
     */
    @PostMapping("/add")
    public AjaxResult addResourceCategory(@RequestBody AddResourceCategoryParam param) {
        ResourceCategory resourceCategory = ResourceCategory.builder()
                .categoryName(param.getCategoryName())
                .description(param.getDescription())
                .sort(param.getSort()).build();
        boolean b = resourceCategoryService.addResourceCategory(resourceCategory);
        Assert.isTrue(b, "新增资源类别失败！");
        return AjaxResult.success("新增成功！");
    }

    /**
     * 更新资源类别接口
     * @param param 请求参数
     * @return
     */
    @PutMapping("/update")
    public AjaxResult updateResourceCategory(@RequestBody @Valid UpdateResourceCategoryParam param) {
        ResourceCategory resourceCategory = ResourceCategory.builder()
                .id(param.getResourceCategoryId())
                .categoryName(param.getCategoryName())
                .description(param.getDescription())
                .sort(param.getSort()).build();
        boolean b = resourceCategoryService.updateResourceCategory(resourceCategory);
        Assert.isTrue(b, "更新资源类别失败！");
        return AjaxResult.success("更新成功！");
    }

}

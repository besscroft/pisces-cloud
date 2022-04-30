package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.admin.domain.param.resourceCategory.ResourceCategoryPageListParam;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
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
    public AjaxResult list(@RequestBody @Valid ResourceCategoryPageListParam param) {
        List<ResourceCategory> listPage = resourceCategoryService.getResourceCategoryListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(CommonPage.restPage(listPage));
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
    public AjaxResult getResourceCategoryDict() {
        List<ResourceCategoryDictDto> resourceCategoryDict = resourceCategoryService.getResourceCategoryDict();
        return AjaxResult.success(resourceCategoryDict);
    }

}

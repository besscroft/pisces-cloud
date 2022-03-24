package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.resourceCategory.ResourceCategoryPageListParam;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

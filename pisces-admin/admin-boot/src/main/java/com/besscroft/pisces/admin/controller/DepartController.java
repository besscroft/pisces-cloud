package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.DepartConverterMapper;
import com.besscroft.pisces.admin.domain.dto.DepartDictDto;
import com.besscroft.pisces.admin.domain.dto.DepartDto;
import com.besscroft.pisces.admin.domain.param.depart.AddDepartParam;
import com.besscroft.pisces.admin.domain.param.depart.DepartPageListParam;
import com.besscroft.pisces.admin.domain.param.depart.UpdateDepartParam;
import com.besscroft.pisces.framework.common.entity.Depart;
import com.besscroft.pisces.admin.service.DepartService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "组织/部门接口")
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
    @Operation(summary = "组织/部门列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<DepartDto>> list(@RequestBody @Valid DepartPageListParam param) {
        // todo 返回树形结构
        List<DepartDto> listPage = departService.getDepartListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 组织/部门删除接口
     * @param departId 组织/部门 id
     * @return
     */
    @Operation(summary = "组织/部门删除接口")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable("id") Long departId) {
        boolean b = departService.deleteDepart(departId);
        Assert.isTrue(b, "组织/部门删除失败！");
        return AjaxResult.success("删除成功！");
    }

    /**
     * 新增组织/部门接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "新增组织/部门接口")
    @PostMapping("/add")
    public AjaxResult addResource(@RequestBody @Valid AddDepartParam param) {
        Depart depart = DepartConverterMapper.INSTANCE.AddParamToDepart(param);
        boolean b = departService.addDepart(depart);
        Assert.isTrue(b, "新增部门成功！");
        return AjaxResult.success("新增成功！");
    }

    /**
     * 更新组织/部门接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "更新组织/部门接口")
    @PutMapping("/update")
    public AjaxResult updateResource(@RequestBody @Valid UpdateDepartParam param) {
        Depart depart = DepartConverterMapper.INSTANCE.UpdateParamToDepart(param);
        boolean b = departService.updateDepart(depart);
        Assert.isTrue(b, "更新部门失败！");
        return AjaxResult.success("更新成功！");
    }

    /**
     * 部门字典接口
     * @return
     */
    @Operation(summary = "部门字典接口")
    @GetMapping("/getDepartDict")
    public CommonResult<List<DepartDictDto>> getDepartDict() {
        List<DepartDictDto> departDict = departService.getDepartDict();
        return CommonResult.success(departDict);
    }

}

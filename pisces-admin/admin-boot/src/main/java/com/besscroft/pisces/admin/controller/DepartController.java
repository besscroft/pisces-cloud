package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.DepartConverterMapper;
import com.besscroft.pisces.admin.domain.dto.DepartDictDto;
import com.besscroft.pisces.admin.domain.dto.DepartDto;
import com.besscroft.pisces.admin.domain.dto.DepartTreeDto;
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

    @Operation(summary = "组织/部门列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<DepartDto>> list(@RequestBody @Valid DepartPageListParam param) {
        // todo 返回树形结构
        List<DepartDto> listPage = departService.getDepartListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    @Operation(summary = "组织/部门删除接口")
    @DeleteMapping("/delete/{id:[\\d]+}")
    public AjaxResult deleteById(@PathVariable("id") Long departId) {
        departService.deleteDepart(departId);
        return AjaxResult.success("删除成功！");
    }

    @Operation(summary = "新增组织/部门接口")
    @PostMapping("/add")
    public AjaxResult addResource(@RequestBody @Valid AddDepartParam param) {
        Depart depart = DepartConverterMapper.INSTANCE.AddParamToDepart(param);
        departService.addDepart(depart);
        return AjaxResult.success("新增成功！");
    }

    @Operation(summary = "更新组织/部门接口")
    @PutMapping("/update")
    public AjaxResult updateResource(@RequestBody @Valid UpdateDepartParam param) {
        Depart depart = DepartConverterMapper.INSTANCE.UpdateParamToDepart(param);
        departService.updateDepart(depart);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "部门字典接口")
    @GetMapping("/getDepartDict")
    public CommonResult<List<DepartDictDto>> getDepartDict() {
        List<DepartDictDto> departDict = departService.getDepartDict();
        return CommonResult.success(departDict);
    }

    @Operation(summary = "部门树接口")
    @GetMapping("/getUserDepartList")
    public CommonResult<List<DepartTreeDto>> getUserDepartList() {
        List<DepartTreeDto> departDict = departService.getUserDepartList();
        return CommonResult.success(departDict);
    }

}

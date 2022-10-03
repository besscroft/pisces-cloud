package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.DictConverterMapper;
import com.besscroft.pisces.admin.domain.param.dict.AddDictParam;
import com.besscroft.pisces.admin.domain.param.dict.DictPageListParam;
import com.besscroft.pisces.admin.domain.param.dict.UpdateDictParam;
import com.besscroft.pisces.framework.common.entity.Dict;
import com.besscroft.pisces.admin.service.DictService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 字典接口
 * @Author Bess Croft
 * @Date 2022/8/19 10:06
 */
@Tag(name = "字典接口")
@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

    private final DictService dictService;

    @Operation(summary = "查询分组内所有字典接口")
    @GetMapping("/groupQuery")
    public CommonResult<List<Dict>> groupQuery(@RequestParam("groupName") String groupName) {
        List<Dict> dicts = dictService.queryAllByGroup(groupName);
        return CommonResult.success(dicts);
    }

    @Operation(summary = "字典分页接口")
    @PostMapping("/list")
    public CommonResult<CommonPage<Dict>> pageList(@Valid @RequestBody DictPageListParam param) {
        List<Dict> dictList = dictService.pageList(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(dictList));
    }

    @Operation(summary = "新增字典接口")
    @PostMapping("/add")
    public AjaxResult add(@Valid @RequestBody AddDictParam param) {
        Dict dict = DictConverterMapper.INSTANCE.AddParamToDict(param);
        boolean b = dictService.addDict(dict);
        Assert.isTrue(b, "新增字典失败！");
        return AjaxResult.success("新增字典成功！");
    }

    @Operation(summary = "更新字典接口")
    @PutMapping("/update")
    public AjaxResult update(@Valid @RequestBody UpdateDictParam param) {
        Dict dict = DictConverterMapper.INSTANCE.UpdateParamToDict(param);
        boolean b = dictService.updateDict(dict);
        Assert.isTrue(b, "更新字典失败！");
        return AjaxResult.success("更新字典成功！");
    }

    @Operation(summary = "删除字典接口")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        boolean b = dictService.deleteDict(id);
        Assert.isTrue(b, "删除字典失败！");
        return AjaxResult.success();
    }

}

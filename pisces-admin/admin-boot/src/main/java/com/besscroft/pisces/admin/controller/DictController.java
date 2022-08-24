package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.dict.DictPageListParam;
import com.besscroft.pisces.admin.entity.Dict;
import com.besscroft.pisces.admin.service.DictService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

}

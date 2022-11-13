package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.WhiteConverterMapper;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.admin.domain.param.white.AddWhiteParam;
import com.besscroft.pisces.admin.domain.param.white.UpdateWhiteParam;
import com.besscroft.pisces.admin.domain.param.white.WhitePageListParam;
import com.besscroft.pisces.framework.common.entity.White;
import com.besscroft.pisces.admin.service.WhiteService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 白名单接口
 * @Author Bess Croft
 * @Date 2022/5/14 18:22
 */
@Tag(name = "白名单接口")
@RestController
@RequestMapping("/white")
@RequiredArgsConstructor
public class WhiteController {

    private final WhiteService whiteService;

    /**
     * 白名单列表接口（分页）
     * @param param 请求参数
     * @return 白名单列表分页数据
     */
    @Operation(summary = "白名单列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<White>> list(@RequestBody @Valid WhitePageListParam param) {
        List<White> listPage = whiteService.getWhiteListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 新增白名单接口
     * @return
     */
    @Operation(summary = "新增白名单接口")
    @PostMapping("/add")
    public AjaxResult addWhite(@RequestBody AddWhiteParam param) {
        White white = WhiteConverterMapper.INSTANCE.AddParamToWhite(param);
        whiteService.addWhite(white);
        return AjaxResult.success("新增成功！");
    }

    /**
     * 更新白名单接口
     * @return
     */
    @Operation(summary = "更新白名单接口")
    @PutMapping("/update")
    public AjaxResult updateWhite(@RequestBody @Valid UpdateWhiteParam param) {
        White white = WhiteConverterMapper.INSTANCE.UpdateParamToWhile(param);
        whiteService.updateWhite(white);
        return AjaxResult.success("更新成功！");
    }

    /**
     * 删除白名单接口
     * @param whiteId 白名单 id
     * @return
     */
    @Operation(summary = "删除白名单接口")
    @DeleteMapping("/delete/{id:[\\d]+}")
    public AjaxResult deleteById(@PathVariable("id") Long whiteId) {
        whiteService.deleteWhite(whiteId);
        return AjaxResult.success("删除成功！");
    }

    /**
     * 获取白名单字典
     * @return
     */
    @Operation(summary = "获取白名单字典")
    @GetMapping("/getWhiteDict")
    public CommonResult<List<WhiteDictDto>> getWhiteDict() {
        List<WhiteDictDto> whiteDict = whiteService.getWhiteDict();
        return CommonResult.success(whiteDict);
    }

}

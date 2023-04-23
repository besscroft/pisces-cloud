package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.MenuConverterMapper;
import com.besscroft.pisces.admin.domain.dto.MenuDictDto;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.param.menu.AddMenuParam;
import com.besscroft.pisces.admin.domain.param.menu.ChangeMenuStatusParam;
import com.besscroft.pisces.admin.domain.param.menu.MenuPageListParam;
import com.besscroft.pisces.admin.domain.param.menu.UpdateMenuByMenuParam;
import com.besscroft.pisces.framework.common.entity.Menu;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Description 菜单接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@Tag(name = "菜单接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * 菜单列表接口
     * @param param 请求参数
     * @return 菜单列表数据
     */
    @Operation(summary = "菜单列表接口")
    @PostMapping("/list")
    public CommonResult<List<MenuDto>> list(@RequestBody @Valid MenuPageListParam param) {
        List<MenuDto> listPage = menuService.getMenuList(param.getQueryKey());
        return CommonResult.success(listPage);
    }

    @Operation(summary = "更改菜单可用状态接口")
    @PutMapping("/change")
    public AjaxResult change(@RequestBody @Valid ChangeMenuStatusParam param) {
        menuService.changeStatus(param.getMenuId(), param.getIsHide());
        return AjaxResult.success("更改成功！");
    }

    @Operation(summary = "更新菜单信息接口")
    @PutMapping("/update")
    public AjaxResult updateMenu(@RequestBody @Valid UpdateMenuByMenuParam param) {
        Menu menu = MenuConverterMapper.INSTANCE.UpdateParamToMenu(param);
        menuService.updateMenu(menu);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "根据菜单 id 删除菜单接口")
    @DeleteMapping("/delete/{menuId:[\\d]+}")
    public AjaxResult delete(@PathVariable(name = "menuId") Long menuId) {
        menuService.deleteMenu(menuId);
        return AjaxResult.success("删除成功！");
    }

    @Operation(summary = "根据角色 id 查询菜单 id 列表接口")
    @GetMapping("/getId/role/{roleId:[\\d]+}")
    public CommonResult<Set<Long>> getByRoleId(@PathVariable(name = "roleId") Long roleId) {
        Set<Long> ids = menuService.getIdsByRoleId(roleId);
        return CommonResult.success(ids);
    }

    @Operation(summary = "获取所有菜单接口")
    @GetMapping("/getAll")
    public CommonResult<List<MenuDto>> getAll() {
        List<MenuDto> menuDtoList = menuService.getAll();
        return CommonResult.success(menuDtoList);
    }

    @Operation(summary = "新增菜单接口")
    @PostMapping("/add")
    public AjaxResult addMenu(@RequestBody @Valid AddMenuParam param) {
        Menu menu = MenuConverterMapper.INSTANCE.AddParamToMenu(param);
        menuService.addMenu(menu);
        return AjaxResult.success("新增菜单成功！");
    }

    @GetMapping("/getMenuDict")
    public CommonResult<List<MenuDictDto>> getMenuDict() {
        List<MenuDictDto> menuDict = menuService.getMenuDict();
        return CommonResult.success(menuDict);
    }

}

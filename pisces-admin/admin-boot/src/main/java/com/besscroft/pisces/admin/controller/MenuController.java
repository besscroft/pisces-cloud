package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.dto.MenuDictDto;
import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.param.menu.AddMenuParam;
import com.besscroft.pisces.admin.domain.param.menu.ChangeMenuStatusParam;
import com.besscroft.pisces.admin.domain.param.menu.MenuPageListParam;
import com.besscroft.pisces.admin.domain.param.menu.UpdateMenuParam;
import com.besscroft.pisces.admin.entity.Menu;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import com.besscroft.pisces.framework.common.result.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * 菜单列表接口（分页）
     * @param param 请求参数
     * @return 菜单列表分页数据
     */
    @PostMapping("/list")
    public CommonResult<CommonPage<MenuDto>> list(@RequestBody @Valid MenuPageListParam param) {
        List<MenuDto> listPage = menuService.getMenuListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 更改菜单可用状态接口
     * @param param 请求参数
     * @return
     */
    @PutMapping("/change")
    public AjaxResult change(@RequestBody ChangeMenuStatusParam param) {
        boolean b = menuService.changeStatus(param.getMenuId(), param.getHidden());
        Assert.isTrue(b, "更改菜单可用状态失败！");
        return AjaxResult.success("更改成功！");
    }

    /**
     * 更新菜单信息接口
     * @param param 请求参数
     * @return
     */
    @PutMapping("/update")
    public AjaxResult updateMenu(@RequestBody @Valid UpdateMenuParam param) {
        Menu menu = Menu.builder()
                .id(param.getId())
                .parentId(param.getParentId())
                .title(param.getTitle())
                .name(param.getName())
                .level(param.getLevel())
                .component(param.getComponent())
                .path(param.getPath())
                .icon(param.getIcon())
                .sort(param.getSort()).build();
        boolean b = menuService.updateMenu(menu);
        Assert.isTrue(b, "更新菜单失败！");
        return AjaxResult.success("更新成功！");
    }

    /**
     * 根据菜单 id 删除菜单接口
     * @param menuId 菜单 id
     * @return
     */
    @DeleteMapping("/delete/{menuId}")
    public AjaxResult delete(@PathVariable(name = "menuId") Long menuId) {
        boolean b = menuService.deleteMenu(menuId);
        Assert.isTrue(b, "删除失败！");
        return AjaxResult.success("删除成功！");
    }

    /**
     * 根据角色 id 查询菜单 id 列表接口
     * @param roleId 角色 id
     * @return 菜单 id 列表
     */
    @GetMapping("/getId/role/{roleId}")
    public CommonResult<Set<Long>> getByRoleId(@PathVariable(name = "roleId") Long roleId) {
        Set<Long> ids = menuService.getIdsByRoleId(roleId);
        return CommonResult.success(ids);
    }

    /**
     * 获取所有菜单接口
     * @return 所有菜单树
     */
    @GetMapping("/getAll")
    public CommonResult<List<MenuDto>> getAll() {
        List<MenuDto> menuDtoList = menuService.getAll();
        return CommonResult.success(menuDtoList);
    }

    /**
     * 新增菜单接口
     * @param param 请求参数
     * @return
     */
    @PostMapping("/add")
    public AjaxResult addMenu(@RequestBody @Valid AddMenuParam param) {
        Menu menu = Menu.builder()
                .parentId(param.getParentId())
                .title(param.getTitle())
                .name(param.getName())
                .level(param.getLevel())
                .component(param.getComponent())
                .path(param.getPath())
                .icon(param.getIcon())
                .sort(param.getSort()).build();
        boolean b = menuService.addMenu(menu);
        Assert.isTrue(b, "新增菜单失败！");
        return AjaxResult.success("新增菜单成功！");
    }

    /**
     * 菜单字典接口
     * @return
     */
    @GetMapping("/getMenuDict")
    public CommonResult<List<MenuDictDto>> getMenuDict() {
        List<MenuDictDto> menuDict = menuService.getMenuDict();
        return CommonResult.success(menuDict);
    }

}

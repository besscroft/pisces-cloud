package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.param.menu.ChangeMenuStatusParam;
import com.besscroft.pisces.admin.domain.param.menu.MenuPageListParam;
import com.besscroft.pisces.admin.domain.param.menu.UpdateMenuParam;
import com.besscroft.pisces.admin.entity.Menu;
import com.besscroft.pisces.admin.service.MenuService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public AjaxResult list(@RequestBody @Valid MenuPageListParam param) {
        List<MenuDto> listPage = menuService.getMenuListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(CommonPage.restPage(listPage));
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
    public AjaxResult updateMenu(@RequestBody UpdateMenuParam param) {
        Menu menu = Menu.builder()
                .id(param.getId())
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
     * 根据菜单id删除菜单接口
     * @param id 菜单id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable(name = "id") Long id) {
        boolean b = menuService.deleteMenu(id);
        Assert.isTrue(b, "删除失败！");
        return AjaxResult.success("删除成功！");
    }

}

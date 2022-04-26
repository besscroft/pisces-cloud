package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.dto.RoleDictDto;
import com.besscroft.pisces.admin.domain.param.role.*;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.service.RoleService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 角色接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:51
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 角色列表接口（分页）
     * @param param 请求参数
     * @return 角色列表分页数据
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody @Valid RolePageListParam param) {
        List<Role> listPage = roleService.getRoleListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 更改角色可用状态接口
     * @param param 请求参数
     * @return
     */
    @PutMapping("/change")
    public AjaxResult change(@RequestBody ChangeRoleStatusParam param) {
        boolean b = roleService.changeStatus(param.getRoleId(), param.getStatus());
        Assert.isTrue(b, "更改角色可用状态失败！");
        return AjaxResult.success("更改成功！");
    }

    /**
     * 更改角色菜单接口
     * @param param 请求参数
     * @return
     */
    @PostMapping("/update/menu")
    public AjaxResult updateMenu(@RequestBody @Valid UpdateMenuParam param) {
        roleService.updateMenu(param.getRoleId(), param.getMenuIds());
        return AjaxResult.success("更新成功！");
    }

    /**
     * 更改角色资源接口
     * @param param 请求参数
     * @return
     */
    @PostMapping("/update/resource")
    public AjaxResult updateResource(@RequestBody @Valid UpdateResourceParam param) {
        roleService.updateResource(param.getRoleId(), param.getResourceIds());
        return AjaxResult.success("更新成功！");
    }

    /**
     * 角色删除接口
     * @param roleId 角色 id
     * @return
     */
    @DeleteMapping("/delete/{roleId}")
    public AjaxResult delete(@PathVariable("roleId") Long roleId) {
        boolean b = roleService.deleteRole(roleId);
        Assert.isTrue(b, "角色删除失败！");
        return AjaxResult.success("删除成功！");
    }

    /**
     * 新增角色接口
     * @param param 请求参数
     * @return
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Valid AddRoleParam param) {
        Role role = Role.builder()
                .roleName(param.getRoleName())
                .roleCode(param.getRoleCode())
                .description(param.getDescription())
                .sort(param.getSort()).build();
        boolean b = roleService.addRole(role);
        Assert.isTrue(b, "新增角色失败！");
        return AjaxResult.success();
    }

    /**
     * 更新角色接口
     * @param param
     * @return
     */
    @PutMapping("/update")
    public AjaxResult update(@RequestBody @Valid UpdateRoleParam param) {
        Role role = Role.builder()
                .id(param.getId())
                .roleName(param.getRoleName())
                .roleCode(param.getRoleCode())
                .description(param.getDescription())
                .sort(param.getSort()).build();
        boolean b = roleService.updateRole(role);
        Assert.isTrue(b, "更新角色失败！");
        return AjaxResult.success();
    }

    /**
     * 角色字典接口
     * @return
     */
    @GetMapping("/getRoleDict")
    public AjaxResult getRoleDict() {
        List<RoleDictDto> roleDict = roleService.getRoleDict();
        return AjaxResult.success(roleDict);
    }

}

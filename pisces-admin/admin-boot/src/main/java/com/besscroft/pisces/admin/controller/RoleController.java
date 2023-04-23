package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.RoleConverterMapper;
import com.besscroft.pisces.admin.domain.dto.RoleDictDto;
import com.besscroft.pisces.admin.domain.param.role.*;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.admin.service.RoleService;
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
 * @Description 角色接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:51
 */
@Slf4j
@Tag(name = "角色接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "角色列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<Role>> list(@RequestBody @Valid RolePageListParam param) {
        List<Role> listPage = roleService.getRoleListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    @Operation(summary = "更改角色可用状态接口")
    @PutMapping("/change")
    public AjaxResult change(@RequestBody @Valid ChangeRoleStatusParam param) {
        roleService.changeStatus(param.getRoleId(), param.getStatus());
        return AjaxResult.success("更改成功！");
    }

    @Operation(summary = "更改角色菜单接口")
    @PostMapping("/update/menu")
    public AjaxResult updateMenu(@RequestBody @Valid UpdateMenuByRoleParam param) {
        roleService.updateMenu(param.getRoleId(), param.getMenuIds());
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "更改角色资源接口")
    @PostMapping("/update/resource")
    public AjaxResult updateResource(@RequestBody @Valid UpdateResourceByRoleParam param) {
        roleService.updateResource(param.getRoleId(), param.getResourceIds());
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "角色删除接口")
    @DeleteMapping("/delete/{roleId:[\\d]+}")
    public AjaxResult delete(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
        return AjaxResult.success("删除成功！");
    }

    @Operation(summary = "新增角色接口")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Valid AddRoleParam param) {
        Role role = RoleConverterMapper.INSTANCE.AddParamToRole(param);
        roleService.addRole(role);
        return AjaxResult.success();
    }

    @Operation(summary = "更新角色接口")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody @Valid UpdateRoleByRoleParam param) {
        Role role = RoleConverterMapper.INSTANCE.UpdateParamToRole(param);
        roleService.updateRole(role);
        return AjaxResult.success();
    }

    @Operation(summary = "角色字典接口")
    @GetMapping("/getRoleDict")
    public CommonResult<List<RoleDictDto>> getRoleDict() {
        List<RoleDictDto> roleDict = roleService.getRoleDict();
        return CommonResult.success(roleDict);
    }

    @Operation(summary = "根据用户 id 获取角色信息接口")
    @GetMapping("/get/{id:[\\d]+}")
    public CommonResult<List<Role>> getRoleById(@PathVariable("id") Long userId) {
        List<Role> role = roleService.getRoleByUserId(userId);
        return CommonResult.success(role);
    }

}

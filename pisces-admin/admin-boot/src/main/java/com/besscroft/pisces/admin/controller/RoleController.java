package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.role.ChangeRoleStatusParam;
import com.besscroft.pisces.admin.domain.param.role.RolePageListParam;
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

}

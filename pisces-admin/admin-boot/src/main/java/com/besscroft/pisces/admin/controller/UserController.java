package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.UserConverterMapper;
import com.besscroft.pisces.admin.domain.dto.UserListDto;
import com.besscroft.pisces.admin.domain.param.LoginParam;
import com.besscroft.pisces.admin.domain.param.user.*;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.service.UserService;
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
import java.util.Map;

/**
 * @Description 用户接口
 * @Author Bess Croft
 * @Date 2022/2/4 19:18
 */
@Slf4j
@Tag(name = "用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Valid LoginParam loginParam) {
        log.info("登录请求:{}", loginParam);
        AjaxResult accessToken = userService.login(loginParam.getUsername(), loginParam.getPassword());
        log.info("登录请求成功:{}", accessToken);
        return accessToken;
    }

    @Operation(summary = "退出登录接口")
    @PostMapping("/loginOut")
    public AjaxResult loginOut() {
        userService.loginOut();
        return AjaxResult.success("退出登录成功！");
    }

    @Operation(summary = "获取认证后用户信息")
    @GetMapping("/info")
    public CommonResult<Map<String, Object>> getInfo() {
        Map<String, Object> userInfo = userService.getUserInfo();
        return CommonResult.success(userInfo);
    }

    @Operation(summary = "用户列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<UserListDto>> list(@RequestBody @Valid UserPageListParam param) {
        List<UserListDto> listPage = userService.getUserListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey(), param.getDepartId());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    @Operation(summary = "用户信息获取接口")
    @GetMapping("/info/{username}")
    public CommonResult<User> get(@PathVariable(name = "username") String username) {
        User user = userService.getUser(username);
        return CommonResult.success(user);
    }

    @Operation(summary = "更改用户可用状态接口")
    @PutMapping("/change")
    public AjaxResult change(@RequestBody @Valid ChangeUserStatusParam param) {
        userService.changeStatus(param.getUserId(), param.getStatus());
        return AjaxResult.success("更改成功！");
    }

    @Operation(summary = "新增用户接口")
    @PostMapping("/add")
    public AjaxResult addUser(@RequestBody @Valid AddUserParam param) {
        User user = UserConverterMapper.INSTANCE.AddParamToUser(param);
        userService.addUser(user);
        return AjaxResult.success("新增成功！");
    }

    @Operation(summary = "更新用户信息接口")
    @PutMapping("/update")
    public AjaxResult updateUser(@RequestBody UpdateUserParam param) {
        User user = UserConverterMapper.INSTANCE.UpdateParamToUser(param);
        userService.updateUser(user);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "根据用户 id 删除用户接口")
    @DeleteMapping("/delete/{userId:[\\d]+}")
    public AjaxResult delete(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return AjaxResult.success("删除成功！");
    }

    @Operation(summary = "更新用户角色接口")
    @PutMapping("/update/role")
    public AjaxResult updateRole(@RequestBody @Valid UpdateRoleByUserParam param) {
        userService.updateRole(param.getUserId(), param.getRoleIds());
        return AjaxResult.success("更新用户角色成功！");
    }

    @Operation(summary = "更新用户部门接口")
    @PutMapping("/update/depart")
    public AjaxResult updateDepart(@RequestBody @Valid UpdateDepartByUserParam param) {
        userService.updateDepart(param.getUserId(), param.getDepartId());
        return AjaxResult.success("更新用户部门成功！");
    }

    @Operation(summary = "用户密码更新接口")
    @PutMapping("/update/password")
    public AjaxResult updatePassword(@RequestBody @Valid UpdatePasswordParam param) {
        userService.updatePassword(param.getOldPassword(), param.getNewPassword());
        return AjaxResult.success("更新密码成功！");
    }

}

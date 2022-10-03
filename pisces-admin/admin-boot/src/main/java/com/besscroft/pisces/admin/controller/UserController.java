package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.converter.UserConverterMapper;
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
import org.springframework.util.Assert;
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

    /**
     * 登录接口
     * @param loginParam 登录参数
     * @return token 信息
     */
    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Valid LoginParam loginParam) {
        log.info("登录请求:{}", loginParam);
        AjaxResult accessToken = userService.login(loginParam.getUsername(), loginParam.getPassword());
        log.info("登录请求成功:{}", accessToken);
        return accessToken;
    }

    /**
     * 退出登录接口
     * @return
     */
    @Operation(summary = "退出登录接口")
    @PostMapping("/loginOut")
    public AjaxResult loginOut() {
        userService.loginOut();
        return AjaxResult.success("退出登录成功！");
    }

    /**
     * 获取认证后用户信息
     * @return 用户信息
     */
    @Operation(summary = "获取认证后用户信息")
    @GetMapping("/info")
    public CommonResult<Map<String, Object>> getInfo() {
        Map<String, Object> userInfo = userService.getUserInfo();
        return CommonResult.success(userInfo);
    }

    /**
     * 用户列表接口（分页）
     * @param param 请求参数
     * @return 用户列表分页数据
     */
    @Operation(summary = "用户列表接口（分页）")
    @PostMapping("/list")
    public CommonResult<CommonPage<User>> list(@RequestBody @Valid UserPageListParam param) {
        List<User> listPage = userService.getUserListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return CommonResult.success(CommonPage.restPage(listPage));
    }

    /**
     * 用户信息获取接口
     * @param username 用户名
     * @return 用户信息
     */
    @Operation(summary = "用户信息获取接口")
    @GetMapping("/info/{username}")
    public CommonResult<User> get(@PathVariable(name = "username") String username) {
        User user = userService.getUser(username);
        return CommonResult.success(user);
    }

    /**
     * 更改用户可用状态接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "更改用户可用状态接口")
    @PutMapping("/change")
    public AjaxResult change(@RequestBody @Valid ChangeUserStatusParam param) {
        boolean b = userService.changeStatus(param.getUserId(), param.getStatus());
        Assert.isTrue(b, "更改用户可用状态失败！");
        return AjaxResult.success("更改成功！");
    }

    /**
     * 新增用户接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "新增用户接口")
    @PostMapping("/add")
    public AjaxResult addUser(@RequestBody @Valid AddUserParam param) {
        User user = UserConverterMapper.INSTANCE.AddParamToUser(param);
        boolean b = userService.addUser(user);
        Assert.isTrue(b, "新增用户失败！");
        return AjaxResult.success("新增成功！");
    }

    /**
     * 更新用户信息接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "更新用户信息接口")
    @PutMapping("/update")
    public AjaxResult updateUser(@RequestBody UpdateUserParam param) {
        User user = UserConverterMapper.INSTANCE.UpdateParamToUser(param);
        boolean b = userService.updateUser(user);
        Assert.isTrue(b, "更新用户失败！");
        return AjaxResult.success("更新成功！");
    }

    /**
     * 根据用户 id 删除用户接口
     * @param userId 用户 id
     * @return
     */
    @Operation(summary = "根据用户 id 删除用户接口")
    @DeleteMapping("/delete/{userId}")
    public AjaxResult delete(@PathVariable(name = "userId") Long userId) {
        boolean b = userService.deleteUser(userId);
        Assert.isTrue(b, "删除失败！");
        return AjaxResult.success("删除成功！");
    }

    /**
     * 更新用户角色接口
     * @param param 请求参数
     * @return
     */
    @Operation(summary = "更新用户角色接口")
    @PutMapping("/update/role")
    public AjaxResult updateRole(@RequestBody @Valid UpdateRoleByUserParam param) {
        boolean b = userService.updateRole(param.getUserId(), param.getRoleIds());
        Assert.isTrue(b, "更新用户角色失败！");
        return AjaxResult.success("更新用户角色成功！");
    }

}

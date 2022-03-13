package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.LoginParam;
import com.besscroft.pisces.admin.domain.param.user.UserPageListParam;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.result.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Description 用户接口
 * @Author Bess Croft
 * @Date 2022/2/4 19:18
 */
@Slf4j
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
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Valid LoginParam loginParam) {
        log.info("登录请求:{}", loginParam);
        AjaxResult accessToken = userService.login(loginParam.getUsername(), loginParam.getPassword());
        log.info("登录请求成功:{}", accessToken);
        return accessToken;
    }

    /**
     * 获取认证后用户信息
     * @return 用户信息
     */
    @GetMapping("/info")
    public AjaxResult getInfo() {
        Map<String, Object> userInfo = userService.getUserInfo();
        return AjaxResult.success(userInfo);
    }

    /**
     * 用户列表接口（分页）
     * @param param 请求参数
     * @return 用户列表分页数据
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody @Valid UserPageListParam param) {
        Page<User> userListPage = userService.getUserListPage(param.getPageNumber(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(userListPage);
    }

    /**
     * 用户信息获取接口
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/info/{username}")
    public AjaxResult get(@PathVariable(name = "username") String username) {
        User user = userService.getUser(username);
        return AjaxResult.success(user);
    }

}

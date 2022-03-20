package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.domain.param.LoginParam;
import com.besscroft.pisces.admin.domain.param.user.AddUserParam;
import com.besscroft.pisces.admin.domain.param.user.ChangeUserStatusParam;
import com.besscroft.pisces.admin.domain.param.user.UpdateUserParam;
import com.besscroft.pisces.admin.domain.param.user.UserPageListParam;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.service.UserService;
import com.besscroft.pisces.admin.util.CommonPage;
import com.besscroft.pisces.result.AjaxResult;
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
     * 退出登录接口
     * @return
     */
    @PostMapping("/loginOut")
    public AjaxResult loginOut() {
        userService.loginOut();
        return AjaxResult.success("退出登录成功！");
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
        List<User> listPage = userService.getUserListPage(param.getPageNum(), param.getPageSize(), param.getQueryKey());
        return AjaxResult.success(CommonPage.restPage(listPage));
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

    /**
     * 更改用户可用状态接口
     * @param param 请求参数
     * @return
     */
    @PutMapping("/change")
    public AjaxResult change(@RequestBody ChangeUserStatusParam param) {
        boolean b = userService.changeStatus(param.getUserId(), param.getStatus());
        if (!b) {
            return AjaxResult.error("更改用户可用状态失败！");
        }
        return AjaxResult.success("更改成功！");
    }

    /**
     * 新增用户接口
     * @param param 请求参数
     * @return
     */
    @PostMapping("/add")
    public AjaxResult addUser(@RequestBody @Valid AddUserParam param) {
        User user = User.builder()
                .username(param.getUsername())
                .password(param.getPassword())
                .avatar(param.getAvatar())
                .email(param.getEmail())
                .name(param.getName())
                .realName(param.getRealName())
                .telephone(param.getTelephone())
                .birthday(param.getBirthday())
                .sex(param.getSex())
                .remark(param.getRemark()).build();
        boolean b = userService.addUser(user);
        if (!b) {
            return AjaxResult.error("新增用户失败！");
        }
        return AjaxResult.success("新增成功！");
    }

    /**
     * 更新用户信息接口
     * @param param 请求参数
     * @return
     */
    @PutMapping("/update")
    public AjaxResult updateUser(@RequestBody UpdateUserParam param) {
        User user = User.builder()
                .id(param.getId())
                .avatar(param.getAvatar())
                .email(param.getEmail())
                .name(param.getName())
                .realName(param.getRealName())
                .telephone(param.getTelephone())
                .birthday(param.getBirthday())
                .sex(param.getSex())
                .remark(param.getRemark()).build();
        boolean b = userService.updateUser(user);
        if (!b) {
            return AjaxResult.error("更新用户失败！");
        }
        return AjaxResult.success("更新成功！");
    }

    /**
     * 根据用户id删除用户接口
     * @param id 用户id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable(name = "id") Long id) {
        boolean b = userService.deleteUser(id);
        if (!b) {
            return AjaxResult.error("删除用户失败！");
        }
        return AjaxResult.success("删除成功！");
    }

}

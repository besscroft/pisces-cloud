package com.besscroft.pisces.admin.param;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Description 客户端登录请求参数
 * @Author Bess Croft
 * @Date 2022/2/4 16:15
 */
@Data
public class LoginParam {

    /** 登录用户名 */
    @NotNull(message = "登录用户名不能为空")
    private String username;

    /** 登录密码 */
    @NotNull(message = "登录密码不能为空")
    private String password;

}

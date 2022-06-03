package com.besscroft.pisces.admin.domain.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Description 客户端登录请求参数
 * @Author Bess Croft
 * @Date 2022/2/4 16:15
 */
@Data
@Schema(title = "客户端登录请求参数")
public class LoginParam {

    /** 登录用户名 */
    @NotNull(message = "登录用户名不能为空")
    @Schema(title = "登录用户名", type = "String", required = true)
    private String username;

    /** 登录密码 */
    @NotNull(message = "登录密码不能为空")
    @Schema(title = "登录密码", type = "String", required = true)
    private String password;

}

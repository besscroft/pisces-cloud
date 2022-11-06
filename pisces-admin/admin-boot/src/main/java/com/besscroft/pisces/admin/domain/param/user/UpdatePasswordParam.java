package com.besscroft.pisces.admin.domain.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description 密码更新接口 请求参数
 * @Author Bess Croft
 * @Date 2022/11/5 20:29
 */
@Data
public class UpdatePasswordParam {

    /** 旧密码 */
    @NotBlank(message = "旧密码不能为空！")
    @Schema(title = "旧密码", type = "String", required = true)
    private String oldPassword;

    /** 新密码 */
    @NotBlank(message = "新密码不能为空！")
    @Schema(title = "新密码", type = "String", required = true)
    private String newPassword;

}

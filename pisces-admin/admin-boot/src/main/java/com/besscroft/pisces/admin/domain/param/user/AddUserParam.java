package com.besscroft.pisces.admin.domain.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @Description 新增用户请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 14:17
 */
@Data
@Schema(title = "新增用户请求参数")
public class AddUserParam {

    /** 用户名 */
    @NotEmpty(message = "用户名不能为空")
    @Schema(title = "用户名", type = "String", required = true)
    private String username;

    /** 密码 */
    @NotEmpty(message = "密码不能为空")
    @Schema(title = "密码", type = "String", required = true)
    private String password;

    /** 头像(地址) */
    @Schema(title = "头像(地址)", type = "String")
    private String avatar;

    /** 邮箱 */
    @Schema(title = "邮箱", type = "String")
    private String email;

    /** 昵称 */
    @Schema(title = "昵称", type = "String")
    private String name;

    /** 真实姓名 */
    @Schema(title = "真实姓名", type = "String")
    private String realName;

    /** 手机 */
    @Schema(title = "手机", type = "String")
    private String telephone;

    /** 生日 */
    @Schema(title = "生日", type = "Date")
    private LocalDateTime birthday;

    /** 性别 */
    @Schema(title = "性别", type = "Integer")
    private Integer sex;

    /** 备注 */
    @Schema(title = "备注", type = "String")
    private String remark;

}

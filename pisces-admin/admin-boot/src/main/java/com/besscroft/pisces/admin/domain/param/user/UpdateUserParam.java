package com.besscroft.pisces.admin.domain.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Description 更新用户请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 16:22
 */
@Data
@Schema(title = "更新用户请求参数")
public class UpdateUserParam {

    /** 用户 id */
    @NotNull(message = "用户 id 不能为空")
    @Schema(title = "用户 id", type = "Long", required = true)
    private Long id;

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

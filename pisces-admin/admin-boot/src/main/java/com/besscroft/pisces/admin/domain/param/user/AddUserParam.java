package com.besscroft.pisces.admin.domain.param.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @Description 新增用户请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 14:17
 */
@Data
public class AddUserParam {

    /** 用户名 */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /** 密码 */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /** 头像(地址) */
    private String avatar;

    /** 邮箱 */
    private String email;

    /** 昵称 */
    private String name;

    /** 真实姓名 */
    private String realName;

    /** 手机 */
    private String telephone;

    /** 生日 */
    private LocalDateTime birthday;

    /** 性别 */
    private Integer sex;

    /** 备注 */
    private String remark;

}

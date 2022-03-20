package com.besscroft.pisces.admin.domain.param.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Description 更新用户请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 16:22
 */
@Data
public class UpdateUserParam {

    /** 用户id */
    @NotNull(message = "用户id不能为空")
    private Long id;

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

package com.besscroft.pisces.admin.domain.param.white;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 更新白名单请求参数
 * @Author Bess Croft
 * @Date 2022/5/14 21:02
 */
@Data
@Schema(title = "客户端登录请求参数")
public class UpdateWhiteParam {

    /** 白名单 id */
    @Schema(title = "白名单 id", type = "String", required = true)
    @NotNull(message = "白名单 id 不能为空")
    private Long whiteId;

    /** 白名单规则名称 */
    @Schema(title = "白名单规则名称", type = "String")
    private String title;

    /** 白名单规则地址 */
    @Schema(title = "白名单规则地址", type = "String")
    private String path;

    /** 备注 */
    @Schema(title = "备注", type = "String")
    private String remark;

}

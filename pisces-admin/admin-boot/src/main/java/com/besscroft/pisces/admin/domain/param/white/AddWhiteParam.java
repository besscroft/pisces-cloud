package com.besscroft.pisces.admin.domain.param.white;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 新增白名单 请求参数
 * @Author Bess Croft
 * @Date 2022/5/14 21:01
 */
@Data
@Schema(title = "客户端登录请求参数")
public class AddWhiteParam {

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

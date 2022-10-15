package com.besscroft.pisces.admin.domain.param.user;

import com.besscroft.pisces.admin.domain.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 用户分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/13 16:35
 */
@Data
@Schema(title = "用户分页列表请求参数")
public class UserPageListParam extends PageParam {

    /** 查询参数 */
    @Schema(title = "查询参数", type = "String")
    private String queryKey;

    /** 部门id */
    @Schema(title = "部门id", type = "Long")
    private Long departId;

}

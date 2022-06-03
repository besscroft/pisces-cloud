package com.besscroft.pisces.admin.domain.param.role;

import com.besscroft.pisces.admin.domain.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 角色分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 19:21
 */
@Data
@Schema(title = "角色分页列表请求参数")
public class RolePageListParam extends PageParam {

    /** 查询参数 */
    @Schema(title = "查询参数", type = "String")
    private String queryKey;

}
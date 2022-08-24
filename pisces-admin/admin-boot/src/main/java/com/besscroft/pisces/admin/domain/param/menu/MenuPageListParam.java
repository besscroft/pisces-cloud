package com.besscroft.pisces.admin.domain.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 菜单分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/24 14:41
 */
@Data
@Schema(title = "菜单分页列表请求参数")
public class MenuPageListParam {

    /** 查询参数 */
    @Schema(title = "查询参数", type = "String")
    private String queryKey;

}

package com.besscroft.pisces.admin.domain.param.white;

import com.besscroft.pisces.admin.domain.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 白名单分页接口 请求参数
 * @Author Bess Croft
 * @Date 2022/5/14 18:28
 */
@Data
@Schema(title = "白名单分页接口请求参数")
@EqualsAndHashCode(callSuper = true)
public class WhitePageListParam extends PageParam {

    /** 查询参数 */
    @Schema(title = "查询参数", type = "String")
    private String queryKey;

}

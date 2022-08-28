package com.besscroft.pisces.admin.domain.param.dict;

import com.besscroft.pisces.admin.domain.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 字典分页接口 请求参数
 * @Author Bess Croft
 * @Date 2022/8/24 16:47
 */
@Data
@Schema(title = "字典分页接口请求参数")
public class DictPageListParam extends PageParam {

    /** 查询参数 */
    @Schema(title = "查询参数", type = "String")
    private String queryKey;

}

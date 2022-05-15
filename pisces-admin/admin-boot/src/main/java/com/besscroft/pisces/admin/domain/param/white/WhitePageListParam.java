package com.besscroft.pisces.admin.domain.param.white;

import com.besscroft.pisces.admin.domain.param.PageParam;
import lombok.Data;

/**
 * @Description 白名单分页接口 请求参数
 * @Author Bess Croft
 * @Date 2022/5/14 18:28
 */
@Data
public class WhitePageListParam extends PageParam {

    /** 查询参数 */
    private String queryKey;

}

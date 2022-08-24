package com.besscroft.pisces.admin.domain.param.dict;

import com.besscroft.pisces.admin.domain.param.PageParam;
import lombok.Data;

/**
 * @Description 字典分页接口 请求参数
 * @Author Bess Croft
 * @Date 2022/8/24 16:47
 */
@Data
public class DictPageListParam extends PageParam {

    /** 查询条件 */
    private String queryKey;

}

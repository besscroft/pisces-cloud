package com.besscroft.pisces.admin.domain.param.depart;

import com.besscroft.pisces.admin.domain.param.PageParam;
import lombok.Data;

/**
 * @Description 部门/组织 分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/24 17:17
 */
@Data
public class DepartPageListParam extends PageParam {

    /** 查询参数 */
    private String queryKey;

}

package com.besscroft.pisces.admin.domain.param.role;

import com.besscroft.pisces.admin.domain.param.PageParam;
import lombok.Data;

/**
 * @Description 角色分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 19:21
 */
@Data
public class RolePageListParam extends PageParam {

    /** 查询参数 */
    private String queryKey;

}
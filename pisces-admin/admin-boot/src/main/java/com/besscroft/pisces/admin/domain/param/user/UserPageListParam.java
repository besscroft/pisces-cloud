package com.besscroft.pisces.admin.domain.param.user;

import com.besscroft.pisces.admin.domain.param.PageParam;
import lombok.Data;

/**
 * @Description 用户分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/13 16:35
 */
@Data
public class UserPageListParam extends PageParam {

    /** 查询参数 */
    private String queryKey;

}

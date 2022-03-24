package com.besscroft.pisces.admin.domain.param.menu;

import com.besscroft.pisces.admin.domain.param.PageParam;
import lombok.Data;

/**
 * @Description 菜单分页列表 请求参数
 * @Author Bess Croft
 * @Date 2022/3/24 14:41
 */
@Data
public class MenuPageListParam extends PageParam {

    /** 查询参数 */
    private String queryKey;

}

package com.besscroft.pisces.admin.domain.param.menu;

import lombok.Data;

/**
 * @Description 菜单可用状态更改 请求参数
 * @Author Bess Croft
 * @Date 2022/3/24 15:16
 */
@Data
public class ChangeMenuStatusParam {

    /** 菜单 id */
    private Long menuId;

    /** 菜单可用状态 */
    private Boolean hidden;

}

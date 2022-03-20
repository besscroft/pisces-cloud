package com.besscroft.pisces.admin.domain.param.user;

import lombok.Data;

/**
 * @Description 用户可用状态更改 请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 11:34
 */
@Data
public class ChangeUserStatusParam {

    /** 用户 id */
    private Long userId;

    /** 用户可用状态 */
    private Boolean status;

}

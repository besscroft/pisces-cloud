package com.besscroft.pisces.admin.domain.param.white;

import lombok.Data;

/**
 * @Description 更新白名单请求参数
 * @Author Bess Croft
 * @Date 2022/5/14 21:02
 */
@Data
public class UpdateWhiteParam {

    /** 白名单 id */
    private Long whiteId;

    /** 白名单规则名称 */
    private String title;

    /** 白名单规则地址 */
    private String path;

    /** 备注 */
    private String remark;

}

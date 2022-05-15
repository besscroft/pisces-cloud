package com.besscroft.pisces.admin.domain.param.white;

import lombok.Data;

/**
 * @Description 新增白名单 请求参数
 * @Author Bess Croft
 * @Date 2022/5/14 21:01
 */
@Data
public class AddWhiteParam {

    /** 白名单规则名称 */
    private String title;

    /** 白名单规则地址 */
    private String path;

    /** 备注 */
    private String remark;

}

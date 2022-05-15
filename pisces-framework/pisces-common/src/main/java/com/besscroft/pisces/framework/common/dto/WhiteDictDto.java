package com.besscroft.pisces.framework.common.dto;

import lombok.Data;

/**
 * @Description 白名单字典 封装对象
 * @Author Bess Croft
 * @Date 2022/5/14 22:01
 */
@Data
public class WhiteDictDto {

    /** 白名单规则名称 */
    private String title;

    /** 白名单规则地址 */
    private String path;

}

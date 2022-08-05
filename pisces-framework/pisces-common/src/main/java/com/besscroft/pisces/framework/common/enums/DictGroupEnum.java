package com.besscroft.pisces.framework.common.enums;

/**
 * @Description 字典分组枚举
 * @Author Bess Croft
 * @Date 2022/8/5 14:25
 */
public enum DictGroupEnum {

    RESOURCE("资源", "RESOURCE");

    private final String code;
    private final String value;

    DictGroupEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

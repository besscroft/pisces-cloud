package com.besscroft.pisces.admin.domain.vo;

import lombok.Data;

/**
 * @Description 路由 meta 信息
 * @Author Bess Croft
 * @Date 2022/2/5 13:16
 */
@Data
public class MetaVo {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /** 是否外链 */
    private String isLink;

    /** 是否隐藏 */
    private Boolean isHide;

    /** 是否全屏 */
    private Boolean isFull;

    /** 是否固定在 tabs nav */
    private Boolean isAffix;

    /** 是否缓存 */
    private Boolean isKeepAlive;

    public MetaVo() {
    }

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaVo(String title, String icon, String isLink, Boolean isHide, Boolean isFull, Boolean isAffix, Boolean isKeepAlive) {
        this.title = title;
        this.icon = icon;
        this.isLink = isLink;
        this.isHide = isHide;
        this.isFull = isFull;
        this.isAffix = isAffix;
        this.isKeepAlive = isKeepAlive;
    }
}

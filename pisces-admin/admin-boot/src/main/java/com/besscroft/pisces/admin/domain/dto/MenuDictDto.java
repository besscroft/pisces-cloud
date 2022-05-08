package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description 菜单字典 封装对象
 * @Author Bess Croft
 * @Date 2022/5/4 18:43
 */
@Data
public class MenuDictDto {

    /** 菜单 id */
    private Long value;

    /** 菜单标题 */
    private String label;

    List<MenuDictDto> children;

}

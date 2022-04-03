package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description 资源树封装对象
 * @Author Bess Croft
 * @Date 2022/4/3 11:21
 */
@Data
public class ResourceDto {

    private Long id;

    /** 资源名称 */
    private String name;

    /** 资源路径 */
    private String url;

    /** 资源描述 */
    private String description;

    /** 子资源 **/
    private List<ResourceDto> children;

}

package com.besscroft.pisces.admin.domain.param.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description 更新字典接口——请求参数
 * @Author Bess Croft
 * @Date 2022/8/28 12:59
 */
@Data
@Schema(title = "更新字典接口请求参数")
public class UpdateDictParam {

    @Schema(title = "字典 id", type = "Long")
    private Long id;

    /** 字典分组 */
    @Schema(title = "字典分组", type = "String")
    @NotBlank(message = "字典分组不能为空")
    private String groupName;

    /** 字典 key */
    @Schema(title = "字典 key", type = "String")
    @NotBlank(message = "字典 key不能为空")
    private String key;

    /** 字典值 */
    @Schema(title = "字典值", type = "String")
    @NotBlank(message = "字典值不能为空")
    private String value;

    /** 备注 */
    @Schema(title = "备注", type = "String")
    private String remark;

}

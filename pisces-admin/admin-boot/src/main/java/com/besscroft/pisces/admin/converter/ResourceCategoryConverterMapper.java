package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.param.resourceCategory.AddResourceCategoryParam;
import com.besscroft.pisces.admin.domain.param.resourceCategory.UpdateResourceCategoryParam;
import com.besscroft.pisces.framework.common.entity.ResourceCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Description 资源类别对象转换器
 * @Author Bess Croft
 * @Date 2022/8/28 15:08
 */
@Mapper(componentModel = "spring")
public interface ResourceCategoryConverterMapper {

    ResourceCategoryConverterMapper INSTANCE = Mappers.getMapper(ResourceCategoryConverterMapper.class);

    ResourceCategory AddParamToResourceCategory(AddResourceCategoryParam param);

    @Mapping(source = "param.resourceCategoryId", target = "id")
    ResourceCategory UpdateParamToResourceCategory(UpdateResourceCategoryParam param);

}

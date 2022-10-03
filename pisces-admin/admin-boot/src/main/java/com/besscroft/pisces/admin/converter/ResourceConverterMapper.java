package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.param.resource.AddResourceParam;
import com.besscroft.pisces.admin.domain.param.resource.UpdateResourceParam;
import com.besscroft.pisces.framework.common.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Description 资源对象转换器
 * @Author Bess Croft
 * @Date 2022/4/30 14:33
 */
@Mapper(componentModel = "spring")
public interface ResourceConverterMapper {

    ResourceConverterMapper INSTANCE = Mappers.getMapper(ResourceConverterMapper.class);

    Resource AddParamToResource(AddResourceParam param);

    @Mapping(source = "param.resourceId", target = "id")
    Resource UpdateParamToResource(UpdateResourceParam param);

}

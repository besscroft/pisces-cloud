package com.besscroft.pisces.admin.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 资源对象转换器
 * @Author Bess Croft
 * @Date 2022/4/30 14:33
 */
@Mapper(componentModel = "spring")
public interface ResourceConverterMapper {

    ResourceConverterMapper INSTANCE = Mappers.getMapper(ResourceConverterMapper.class);

}

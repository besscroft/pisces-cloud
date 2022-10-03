package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.param.white.AddWhiteParam;
import com.besscroft.pisces.admin.domain.param.white.UpdateWhiteParam;
import com.besscroft.pisces.framework.common.entity.White;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Description 白名单对象转换器
 * @Author Bess Croft
 * @Date 2022/8/28 15:09
 */
@Mapper(componentModel = "spring")
public interface WhiteConverterMapper {

    WhiteConverterMapper INSTANCE = Mappers.getMapper(WhiteConverterMapper.class);

    White AddParamToWhite(AddWhiteParam param);

    @Mapping(source = "param.whiteId", target = "id")
    White UpdateParamToWhile(UpdateWhiteParam param);

}

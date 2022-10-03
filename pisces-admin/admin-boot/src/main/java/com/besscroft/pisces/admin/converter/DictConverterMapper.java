package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.param.dict.AddDictParam;
import com.besscroft.pisces.admin.domain.param.dict.UpdateDictParam;
import com.besscroft.pisces.framework.common.entity.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 字典对象转换器
 * @Author Bess Croft
 * @Date 2022/8/28 13:31
 */
@Mapper(componentModel = "spring")
public interface DictConverterMapper {

    DictConverterMapper INSTANCE = Mappers.getMapper(DictConverterMapper.class);

    Dict AddParamToDict(AddDictParam param);

    Dict UpdateParamToDict(UpdateDictParam param);

}

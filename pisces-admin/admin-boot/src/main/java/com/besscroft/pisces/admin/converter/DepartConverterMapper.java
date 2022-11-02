package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.dto.DepartDto;
import com.besscroft.pisces.admin.domain.dto.DepartTreeDto;
import com.besscroft.pisces.admin.domain.param.depart.AddDepartParam;
import com.besscroft.pisces.admin.domain.param.depart.UpdateDepartParam;
import com.besscroft.pisces.framework.common.entity.Depart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description 部门对象转换器
 * @Author Bess Croft
 * @Date 2022/4/30 17:02
 */
@Mapper(componentModel = "spring")
public interface DepartConverterMapper {

    DepartConverterMapper INSTANCE = Mappers.getMapper(DepartConverterMapper.class);

    Depart AddParamToDepart(AddDepartParam param);

    @Mappings({
            @Mapping(source = "param.departId", target = "id"),
    })
    Depart UpdateParamToDepart(UpdateDepartParam param);

    DepartDto DepartToDepartDto(Depart depart);

    List<DepartDto> DepartToDepartDtoList(List<Depart> departList);

    @Mappings({
            @Mapping(source = "departDto.id", target = "departId"),
            @Mapping(source = "departDto.name", target = "departName")
    })
    DepartTreeDto DepartDtoToDepartTreeDto(DepartDto departDto);

    List<DepartTreeDto> DepartDtoToDepartTreeDtoList(List<DepartDto> departDtoList);

}

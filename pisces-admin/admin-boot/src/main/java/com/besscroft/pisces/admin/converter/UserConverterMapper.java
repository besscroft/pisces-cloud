package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.param.user.AddUserParam;
import com.besscroft.pisces.admin.domain.param.user.UpdateUserParam;
import com.besscroft.pisces.framework.common.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 用户对象转换器
 * @Author Bess Croft
 * @Date 2022/8/28 15:09
 */
@Mapper(componentModel = "spring")
public interface UserConverterMapper {

    UserConverterMapper INSTANCE = Mappers.getMapper(UserConverterMapper.class);

    User AddParamToUser(AddUserParam param);

    User UpdateParamToUser(UpdateUserParam param);

}

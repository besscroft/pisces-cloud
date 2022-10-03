package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.param.role.AddRoleParam;
import com.besscroft.pisces.admin.domain.param.role.UpdateRoleByRoleParam;
import com.besscroft.pisces.framework.common.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 角色对象转换器
 * @Author Bess Croft
 * @Date 2022/8/28 15:09
 */
@Mapper(componentModel = "spring")
public interface RoleConverterMapper {

    RoleConverterMapper INSTANCE = Mappers.getMapper(RoleConverterMapper.class);

    Role AddParamToRole(AddRoleParam param);

    Role UpdateParamToRole(UpdateRoleByRoleParam param);

}

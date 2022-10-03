package com.besscroft.pisces.admin.converter;

import com.besscroft.pisces.admin.domain.dto.MenuDto;
import com.besscroft.pisces.admin.domain.param.menu.AddMenuParam;
import com.besscroft.pisces.admin.domain.param.menu.UpdateMenuByMenuParam;
import com.besscroft.pisces.framework.common.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description 菜单对象转换器
 * @Author Bess Croft
 * @Date 2022/2/5 13:19
 */
@Mapper(componentModel = "spring")
public interface MenuConverterMapper {

    MenuConverterMapper INSTANCE = Mappers.getMapper(MenuConverterMapper.class);

    Menu AddParamToMenu(AddMenuParam param);

    Menu UpdateParamToMenu(UpdateMenuByMenuParam param);

    MenuDto MenuToMenuDto(Menu menu);

    List<MenuDto> MenuToMenuDtoList(List<Menu> menuList);

}

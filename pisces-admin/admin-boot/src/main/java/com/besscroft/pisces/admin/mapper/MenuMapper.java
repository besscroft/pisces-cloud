package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.Menu;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户 id 查询所有菜单
     * @param userId 用户 id
     * @return 用户的菜单集合
     */
    List<Menu> getAllByUserId(@Param("userId") Long userId);

}

package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.framework.common.entity.Menu;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据关键词查询菜单信息
     * @param queryKey 关键词
     * @return 菜单信息
     */
    List<Menu> selectAllByQueryKey(@Param("queryKey") String queryKey);

    /**
     * 修改菜单可用状态
     * @param menuId 菜单id
     * @param isHide 可用状态
     * @return 生效行数
     */
    int updateStatusById(@Param("menuId") Long menuId,
                         @Param("isHide") Integer isHide);

    /**
     * 根据角色id查询所有菜单
     * @param roleId 角色id
     * @return
     */
    List<Menu> findAllByRoleId(@Param("roleId") Long roleId);

}

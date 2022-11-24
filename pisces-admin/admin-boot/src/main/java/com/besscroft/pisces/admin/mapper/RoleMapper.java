package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.domain.dto.RoleResourceRelationDto;
import com.besscroft.pisces.framework.common.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取角色资源关系
     * @return 角色资源关系
     */
    List<RoleResourceRelationDto> findRoleResourceRelation();

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findAllByUserId(@Param("userId") Long userId);

    /**
     * 根据关键词查询角色信息
     * @param queryKey 关键词
     * @return 角色信息
     */
    List<Role> selectAllByQueryKey(@Param("queryKey") String queryKey);

    /**
     * 修改角色可用状态
     * @param roleId 角色id
     * @param status 可用状态
     * @return 生效行数
     */
    int updateStatusById(@Param("roleId") Long roleId,
                         @Param("status") Integer status);

    /**
     * 根据角色 id 删除角色菜单关系
     * @param roleId 角色 id
     * @return
     */
    int deleteMenuByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色 id，插入角色菜单关系
     * @param roleId 角色 id
     * @param menuIds 菜单 id 列表
     * @return
     */
    int insertMenuByRoleId(@Param("roleId") Long roleId,
                           @Param("menuIds") Set<Long> menuIds);

    /**
     * 根据角色 id 删除角色资源关系
     * @param roleId 角色 id
     * @return
     */
    int deleteResourceByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色 id，插入角色资源关系
     * @param roleId 角色 id
     * @param resourceIds 资源 id 列表
     * @return
     */
    int insertResourceByRoleId(@Param("roleId") Long roleId,
                               @Param("resourceIds") Set<Long> resourceIds);

}

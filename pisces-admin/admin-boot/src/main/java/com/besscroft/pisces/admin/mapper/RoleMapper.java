package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.domain.dto.RoleResourceRelationDto;
import com.besscroft.pisces.admin.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

}

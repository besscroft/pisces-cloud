package com.besscroft.pisces.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.auth.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findAllByUserId(@Param("userId") Long userId);

}

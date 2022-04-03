package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据关键词查询资源信息
     * @param queryKey 关键词
     * @return 资源信息
     */
    List<Resource> selectAllByQueryKey(@Param("queryKey") String queryKey);

    /**
     * 根据角色id查询所有资源
     * @param roleId 角色id
     * @return
     */
    List<Resource> findAllByRoleId(@Param("roleId") Long roleId);

}

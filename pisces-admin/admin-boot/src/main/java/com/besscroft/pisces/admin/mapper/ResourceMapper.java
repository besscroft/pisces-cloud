package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.framework.common.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 资源 Mapper 接口
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
     * 根据角色 id 查询所有资源
     * @param roleId 角色 id
     * @return 角色的资源集合
     */
    List<Resource> findAllByRoleId(@Param("roleId") Long roleId);

}

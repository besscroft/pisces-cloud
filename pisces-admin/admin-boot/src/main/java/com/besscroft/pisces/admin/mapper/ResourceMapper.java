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
     * 根据角色 id 查询所有资源
     * @param roleId 角色 id
     * @return
     */
    List<Resource> findAllByRoleId(@Param("roleId") Long roleId);

    /**
     * 更新资源删除状态
     * @param resourceId 资源 id
     * @return
     */
    int updateDelById(@Param("resourceId") Long resourceId);

}

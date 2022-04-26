package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface ResourceCategoryMapper extends BaseMapper<ResourceCategory> {

    /**
     * 根据关键词查询资源类别信息
     * @param queryKey 关键词
     * @return 资源类别
     */
    List<ResourceCategory> selectAllByQueryKey(@Param("queryKey") String queryKey);

    /**
     * 更新资源类别删除状态
     * @param resourceCategoryId 资源类别 id
     * @return
     */
    int updateDelById(@Param("resourceCategoryId") Long resourceCategoryId);

}

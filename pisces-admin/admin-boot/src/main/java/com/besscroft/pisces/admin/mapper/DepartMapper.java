package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.framework.common.entity.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:36
 */
public interface DepartMapper extends BaseMapper<Depart> {

    /**
     * 根据关键词查询部门信息
     * @param queryKey 关键词
     * @return 部门信息
     */
    List<Depart> selectAllByQueryKey(@Param("queryKey") String queryKey);

    /**
     * 更新部门/组织删除状态
     * @param departId 部门/组织 id
     * @return
     */
    int updateDelById(@Param("departId") Long departId);

}

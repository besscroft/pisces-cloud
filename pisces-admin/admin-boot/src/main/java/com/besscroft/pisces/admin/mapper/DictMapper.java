package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.admin.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/5 14:20
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据字典分组查询字典列表
     * @param groupName 字典分组名称
     * @return
     */
    List<Dict> selectAllByGroup(@Param("groupName") String groupName);

    /**
     * 根据查询参数查询所有数据
     * @param queryKey 查询参数
     * @return
     */
    List<Dict> selectAllByQueryKey(@Param("queryKey") String queryKey);

    /**
     * 根据字典 id 修改删除状态
     * @param id 字典 id
     * @return
     */
    int updateDelById(@Param("id") Long id);

}

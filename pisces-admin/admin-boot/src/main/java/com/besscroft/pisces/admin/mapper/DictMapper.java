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
     * @param group 字典分组
     * @return
     */
    List<Dict> selectAllByGroup(@Param("group") String group);

}

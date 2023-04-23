package com.besscroft.pisces.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.besscroft.pisces.framework.common.entity.White;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 白名单 Mapper 接口
 * @Author Bess Croft
 * @Date 2022/5/14 18:20
 */
public interface WhiteMapper extends BaseMapper<White> {

    /**
     * 根据关键字查询白名单信息
     * @param queryKey 关键字
     * @return 白名单信息
     */
    List<White> selectAllByQueryKey(@Param("queryKey") String queryKey);

}

package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.framework.common.entity.White;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/5/14 18:21
 */
public interface WhiteService extends IService<White> {

    /**
     * 获取白名单列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return 白名单分页列表
     */
    List<White> getWhiteListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 新增白名单
     * @param white 白名单实体
     */
    void addWhite(White white);

    /**
     * 更新白名单
     * @param white 白名单实体
     */
    void updateWhite(White white);

    /**
     * 白名单逻辑删除
     * @param whiteId 白名单 id
     */
    void deleteWhite(Long whiteId);

    /**
     * 获取白名单字典
     * @return 白名单字典
     */
    List<WhiteDictDto> getWhiteDict();

}

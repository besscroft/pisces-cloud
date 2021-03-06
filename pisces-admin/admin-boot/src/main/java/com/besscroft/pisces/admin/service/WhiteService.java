package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.admin.entity.White;

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
     * @return
     */
    List<White> getWhiteListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 新增白名单
     * @param white 白名单实体
     * @return
     */
    boolean addWhite(White white);

    /**
     * 更新白名单
     * @param white 白名单实体
     * @return
     */
    boolean updateWhite(White white);

    /**
     * 删除白名单(软删除)
     * @param whiteId 白名单 id
     * @return
     */
    boolean deleteWhite(Long whiteId);

    /**
     * 白名单字典
     * @return
     */
    List<WhiteDictDto> getWhiteDict();

}

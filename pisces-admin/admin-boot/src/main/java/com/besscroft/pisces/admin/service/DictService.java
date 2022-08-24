package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.entity.Dict;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/5 14:21
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据字典分组获取所有字典
     * @param groupName 字典分组名称
     * @return
     */
    List<Dict> queryAllByGroup(String groupName);

    /**
     * 字典分页
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return
     */
    List<Dict> pageList(Integer pageNum, Integer pageSize, String queryKey);

}

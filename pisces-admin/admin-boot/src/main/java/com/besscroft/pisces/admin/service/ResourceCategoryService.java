package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.admin.entity.ResourceCategory;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
public interface ResourceCategoryService extends IService<ResourceCategory> {

    /**
     * 获取资源类别列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return 资源类别列表分页对象
     */
    List<ResourceCategory> getResourceCategoryListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 根据资源类别 id 进行假删除
     * @param resourceCategoryId 资源类别 id
     * @return
     */
    boolean deleteResourceCategory(Long resourceCategoryId);

    /**
     * 获取资源类别字典
     * @return
     */
    List<ResourceCategoryDictDto> getResourceCategoryDict();

    /**
     * 新增资源类别
     * @param resourceCategory 资源类别实体
     * @return
     */
    boolean addResourceCategory(ResourceCategory resourceCategory);

    /**
     * 更新资源类别
     * @param resourceCategory 资源类别实体
     * @return
     */
    boolean updateResourceCategory(ResourceCategory resourceCategory);

}

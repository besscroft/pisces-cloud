package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.mapper.ResourceCategoryMapper;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements ResourceCategoryService {

    @Override
    public List<ResourceCategory> getResourceCategoryListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteResourceCategory(Long resourceCategoryId) {
        return this.baseMapper.updateDelById(resourceCategoryId) > 0;
    }

}

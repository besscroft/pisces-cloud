package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.mapper.ResourceCategoryMapper;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements ResourceCategoryService {
}

package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.framework.common.entity.ResourceCategory;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.admin.mapper.ResourceCategoryMapper;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 资源类别服务实现类
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
@Service
@RequiredArgsConstructor
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements ResourceCategoryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<ResourceCategory> getResourceCategoryListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteResourceCategory(@NonNull Long resourceCategoryId) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.RESOURCE_CATEGORY));
        Assert.isTrue(this.baseMapper.deleteById(resourceCategoryId) > 0, "资源类别删除失败！");
    }

    @Override
    public List<ResourceCategoryDictDto> getResourceCategoryDict() {
        List<ResourceCategoryDictDto> resourceCategoryDictDtoList = (List<ResourceCategoryDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.RESOURCE_CATEGORY);
        if (CollectionUtils.isEmpty(resourceCategoryDictDtoList)) {
            synchronized (this) {
                resourceCategoryDictDtoList = (List<ResourceCategoryDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.RESOURCE_CATEGORY);
                if (CollectionUtils.isEmpty(resourceCategoryDictDtoList)) {
                    List<ResourceCategory> resourceCategoryList = this.list();
                    if (CollectionUtils.isEmpty(resourceCategoryList)) return resourceCategoryDictDtoList;
                    resourceCategoryDictDtoList = resourceCategoryList.stream().map(resourceCategory -> {
                        ResourceCategoryDictDto categoryDto = new ResourceCategoryDictDto();
                        categoryDto.setResourceCategoryId(resourceCategory.getId());
                        categoryDto.setCategoryName(resourceCategory.getCategoryName());
                        return categoryDto;
                    }).collect(Collectors.toList());
                    redisTemplate.opsForValue().set(SystemDictConstants.RESOURCE_CATEGORY, resourceCategoryDictDtoList);
                }
            }
        }
        return resourceCategoryDictDtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addResourceCategory(@NonNull ResourceCategory resourceCategory) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.RESOURCE_CATEGORY));
        Assert.isTrue(this.baseMapper.insert(resourceCategory) > 0, "新增资源类别失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResourceCategory(@NonNull ResourceCategory resourceCategory) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.RESOURCE_CATEGORY));
        Assert.isTrue(this.baseMapper.updateById(resourceCategory) > 0, "更新资源类别失败！");
    }

}

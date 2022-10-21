package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.framework.common.entity.ResourceCategory;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.admin.mapper.ResourceCategoryMapper;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
@Service
@RequiredArgsConstructor
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements ResourceCategoryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SecurityUtils securityUtils;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<ResourceCategory> getResourceCategoryListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteResourceCategory(@NonNull Long resourceCategoryId) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.RESOURCE_CATEGORY));
        return this.baseMapper.updateDelById(resourceCategoryId) > 0;
    }

    @Override
    public List<ResourceCategoryDictDto> getResourceCategoryDict() {
        List<ResourceCategoryDictDto> resourceCategoryDictDtoList = (List<ResourceCategoryDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.RESOURCE_CATEGORY);
        if (CollectionUtils.isEmpty(resourceCategoryDictDtoList)) {
            synchronized (this) {
                resourceCategoryDictDtoList = (List<ResourceCategoryDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.RESOURCE_CATEGORY);
                if (CollectionUtils.isEmpty(resourceCategoryDictDtoList)) {
                    QueryWrapper<ResourceCategory> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("del", 1);
                    List<ResourceCategory> resourceCategoryList = this.baseMapper.selectList(queryWrapper);
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
    public boolean addResourceCategory(@NonNull ResourceCategory resourceCategory) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        resourceCategory.setCreator(currentAdmin.getUsername());
        resourceCategory.setUpdater(currentAdmin.getUsername());
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.RESOURCE_CATEGORY));
        return this.baseMapper.insert(resourceCategory) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateResourceCategory(@NonNull ResourceCategory resourceCategory) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        resourceCategory.setUpdater(currentAdmin.getUsername());
        resourceCategory.setUpdateTime(LocalDateTime.now());
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.RESOURCE_CATEGORY));
        return this.baseMapper.updateById(resourceCategory) > 0;
    }

}

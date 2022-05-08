package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.domain.dto.ResourceCategoryDictDto;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.mapper.ResourceCategoryMapper;
import com.besscroft.pisces.admin.service.ResourceCategoryService;
import com.besscroft.pisces.admin.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Override
    public List<ResourceCategory> getResourceCategoryListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteResourceCategory(Long resourceCategoryId) {
        redisTemplate.delete(SystemDictConstants.RESOURCE_CATEGORY);
        return this.baseMapper.updateDelById(resourceCategoryId) > 0;
    }

    @Override
    public List<ResourceCategoryDictDto> getResourceCategoryDict() {
        List<ResourceCategoryDictDto> resourceCategoryDictDtoList = (List<ResourceCategoryDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.RESOURCE_CATEGORY);
        if (!CollectionUtils.isEmpty(resourceCategoryDictDtoList)) {
            return resourceCategoryDictDtoList;
        }
        synchronized (this) {
            List<ResourceCategory> resourceCategoryList = this.baseMapper.selectList(new QueryWrapper<>());
            resourceCategoryDictDtoList = resourceCategoryList.stream().map(resourceCategory -> {
                ResourceCategoryDictDto categoryDto = new ResourceCategoryDictDto();
                categoryDto.setResourceCategoryId(resourceCategory.getId());
                categoryDto.setCategoryName(resourceCategory.getCategoryName());
                return categoryDto;
            }).collect(Collectors.toList());
            redisTemplate.opsForValue().set(SystemDictConstants.RESOURCE_CATEGORY, resourceCategoryDictDtoList);
            return resourceCategoryDictDtoList;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addResourceCategory(ResourceCategory resourceCategory) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        resourceCategory.setCreator(currentAdmin.getUsername());
        resourceCategory.setUpdater(currentAdmin.getUsername());
        return this.baseMapper.insert(resourceCategory) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateResourceCategory(ResourceCategory resourceCategory) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        resourceCategory.setUpdater(currentAdmin.getUsername());
        resourceCategory.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(resourceCategory) > 0;
    }

}

package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.domain.dto.ResourceDto;
import com.besscroft.pisces.admin.domain.dto.RoleResourceRelationDto;
import com.besscroft.pisces.admin.entity.Resource;
import com.besscroft.pisces.admin.entity.ResourceCategory;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.mapper.ResourceCategoryMapper;
import com.besscroft.pisces.admin.mapper.ResourceMapper;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.service.ResourceService;
import com.besscroft.pisces.admin.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RoleMapper roleMapper;
    private final ResourceCategoryMapper resourceCategoryMapper;
    private final SecurityUtils securityUtils;
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    @SneakyThrows
    public Map<String, List<String>> initRoleResourceMap() {
        Map<String,List<String>> RoleResourceMap = new TreeMap<>();
        List<Resource> resourceList = this.list();
        List<Role> roleList = roleMapper.selectList(new QueryWrapper<>());
        List<RoleResourceRelationDto> roleResourceRelationList = roleMapper.findRoleResourceRelation();
        for (Resource resource: resourceList) {
            Set<Long> roleIds = roleResourceRelationList.stream().filter(item ->
                            item.getResourceId().equals(resource.getId())
                    ).map(RoleResourceRelationDto::getRoleId)
                    .collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item ->
                    roleIds.contains(item.getId())
            ).map(item ->
                    // ??????:ROLE_{roleId}_{roleName}
                    AuthConstants.AUTHORITY_PREFIX + item.getId() + "_" + item.getRoleName()
            ).collect(Collectors.toList());
            // key???????????????/???????????????value?????????
            RoleResourceMap.put("/" + applicationName + resource.getUrl(), roleNames);
        }
        redisTemplate.delete(AuthConstants.PERMISSION_RULES_KEY);
        redisTemplate.opsForHash().putAll(AuthConstants.PERMISSION_RULES_KEY, RoleResourceMap);
        log.info("?????????????????????.[RoleResourceMap={}]", objectMapper.writeValueAsString(RoleResourceMap));
        return RoleResourceMap;
    }

    @Override
    public List<Resource> getResourceListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    public List<ResourceDto> getAll() {
        QueryWrapper<ResourceCategory> resourceCategoryWrapper = new QueryWrapper<>();
        resourceCategoryWrapper.eq("del", 1);
        List<ResourceCategory> resourceCategoryList = resourceCategoryMapper.selectList(resourceCategoryWrapper);
        QueryWrapper<Resource> resourceWrapper = new QueryWrapper<>();
        resourceWrapper.eq("del", 1);
        List<Resource> resourceList = this.baseMapper.selectList(resourceWrapper);
        return getResourceDto(resourceCategoryList, resourceList);
    }

    @Override
    public Set<Long> getIdsByRoleId(Long roleId) {
        List<Resource> resourceList = this.baseMapper.findAllByRoleId(roleId);
        return resourceList.stream().map(Resource::getId).collect(Collectors.toSet());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteResource(Long resourceId) {
        return this.baseMapper.updateDelById(resourceId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addResource(Resource resource) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        resource.setCreator(currentAdmin.getUsername());
        resource.setUpdater(currentAdmin.getUsername());
        return this.baseMapper.insert(resource) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateResource(Resource resource) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        resource.setUpdater(currentAdmin.getUsername());
        resource.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(resource) > 0;
    }

    /**
     * ???????????????
     * @param categoryList ??????????????????
     * @param resourceList ????????????
     * @return ?????????
     */
    private List<ResourceDto> getResourceDto(List<ResourceCategory> categoryList, List<Resource> resourceList) {
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        categoryList.forEach(resourceCategory -> {
            ResourceDto dto = new ResourceDto();
            dto.setName(resourceCategory.getCategoryName());
            dto.setDescription(resourceCategory.getDescription());
            // ??????????????????
            List<ResourceDto> resources = resourceList.stream()
                    .filter(resource -> resource.getCategoryId() == resourceCategory.getId())
                    .map(resource -> {
                        ResourceDto resourceDto = new ResourceDto();
                        resourceDto.setId(resource.getId());
                        resourceDto.setUrl(resource.getUrl());
                        resourceDto.setName(resource.getName());
                        resourceDto.setDescription(resource.getDescription());
                        return resourceDto;
                    })
                    .collect(Collectors.toList());
            dto.setChildren(resources);
            resourceDtoList.add(dto);
        });
        return resourceDtoList;
    }

}

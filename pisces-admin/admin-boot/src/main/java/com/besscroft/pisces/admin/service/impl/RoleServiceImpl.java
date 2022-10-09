package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.domain.dto.RoleDictDto;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.service.RoleService;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:37
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final SecurityUtils securityUtils;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<Role> getRoleListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(@NonNull Long roleId, @NonNull Boolean status) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        return this.baseMapper.updateStatusById(roleId, status ? 1 : 0) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(@NonNull Long roleId, @NonNull Set<Long> menuIds) {
        this.baseMapper.deleteMenuByRoleId(roleId);
        this.baseMapper.insertMenuByRoleId(roleId, menuIds);
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResource(@NonNull Long roleId, @NonNull Set<Long> resourceIds) {
        this.baseMapper.deleteResourceByRoleId(roleId);
        this.baseMapper.insertResourceByRoleId(roleId, resourceIds);
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        eventPublisher.publishEvent(new ClearCacheEvent(AuthConstants.PERMISSION_RULES_KEY));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(@NonNull Long roleId) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        return this.baseMapper.updateDelById(roleId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(@NonNull Role role) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        role.setCreator(currentAdmin.getUsername());
        role.setCreateTime(LocalDateTime.now());
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        return this.baseMapper.insert(role) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(@NonNull Role role) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        role.setUpdater(currentAdmin.getUsername());
        role.setUpdateTime(LocalDateTime.now());
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        return this.baseMapper.updateById(role) > 0;
    }

    @Override
    public List<RoleDictDto> getRoleDict() {
        List<RoleDictDto> roleDictDtoList = (List<RoleDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.ROLE);
        if (!CollectionUtils.isEmpty(roleDictDtoList)) {
            return roleDictDtoList;
        }
        synchronized (this) {
            List<Role> roleList = this.baseMapper.selectList(new QueryWrapper<>());
            if (CollectionUtils.isEmpty(roleList)) return roleDictDtoList;
            roleDictDtoList = roleList.stream().map(role -> {
                RoleDictDto dto = new RoleDictDto();
                dto.setRoleId(role.getId());
                dto.setRoleName(role.getRoleName());
                dto.setRoleCode(role.getRoleCode());
                return dto;
            }).collect(Collectors.toList());
            redisTemplate.opsForValue().set(SystemDictConstants.ROLE, roleDictDtoList);
        }
        return roleDictDtoList;
    }

    @Override
    public List<Role> getRoleByUserId(@NonNull Long userId) {
        return this.baseMapper.findAllByUserId(userId);
    }

}

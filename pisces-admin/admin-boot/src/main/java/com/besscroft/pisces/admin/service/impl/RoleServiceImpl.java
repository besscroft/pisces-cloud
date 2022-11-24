package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.domain.dto.RoleDictDto;
import com.besscroft.pisces.framework.common.entity.Role;
import com.besscroft.pisces.admin.event.ClearCacheEvent;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.service.RoleService;
import com.besscroft.pisces.framework.common.constant.AuthConstants;
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

    private final RedisTemplate<String, Object> redisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<Role> getRoleListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeStatus(@NonNull Long roleId, @NonNull Boolean status) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        Assert.isTrue(this.baseMapper.updateStatusById(roleId, status ? 1 : 0) > 0, "更改角色可用状态失败！");
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
    public void deleteRole(@NonNull Long roleId) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        Assert.isTrue(this.baseMapper.deleteById(roleId) > 0, "删除角色失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(@NonNull Role role) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        Assert.isTrue(this.baseMapper.insert(role) > 0, "新增角色失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(@NonNull Role role) {
        eventPublisher.publishEvent(new ClearCacheEvent(SystemDictConstants.ROLE));
        Assert.isTrue(this.baseMapper.updateById(role) > 0, "更新角色失败！");
    }

    @Override
    public List<RoleDictDto> getRoleDict() {
        List<RoleDictDto> roleDictDtoList = (List<RoleDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.ROLE);
        if (CollectionUtils.isEmpty(roleDictDtoList)) {
            synchronized (this) {
                roleDictDtoList = (List<RoleDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.ROLE);
                if (CollectionUtils.isEmpty(roleDictDtoList)) {
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
            }
        }
        return roleDictDtoList;
    }

    @Override
    public List<Role> getRoleByUserId(@NonNull Long userId) {
        return this.baseMapper.findAllByUserId(userId);
    }

}

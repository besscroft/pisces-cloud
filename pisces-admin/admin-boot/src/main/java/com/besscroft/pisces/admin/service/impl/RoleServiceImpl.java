package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.entity.User;
import com.besscroft.pisces.admin.mapper.RoleMapper;
import com.besscroft.pisces.admin.service.RoleService;
import com.besscroft.pisces.admin.util.SecurityUtils;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:37
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final SecurityUtils securityUtils;

    @Override
    public List<Role> getRoleListPage(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(Long roleId, Boolean status) {
        return this.baseMapper.updateStatusById(roleId, status ? 1 : 0) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(Long roleId, Set<Long> menuIds) {
        this.baseMapper.deleteMenuByRoleId(roleId);
        this.baseMapper.insertMenuByRoleId(roleId, menuIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResource(Long roleId, Set<Long> resourceIds) {
        this.baseMapper.deleteResourceByRoleId(roleId);
        this.baseMapper.insertResourceByRoleId(roleId, resourceIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Long roleId) {
        return this.baseMapper.updateDelById(roleId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean AddRole(Role role) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        role.setCreator(currentAdmin.getUsername());
        role.setCreateTime(LocalDateTime.now());
        return this.baseMapper.insert(role) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean UpdateRole(Role role) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        role.setUpdater(currentAdmin.getUsername());
        role.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(role) > 0;
    }

}

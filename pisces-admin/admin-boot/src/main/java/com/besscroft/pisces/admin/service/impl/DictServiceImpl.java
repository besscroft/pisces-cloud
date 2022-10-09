package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.framework.common.entity.Dict;
import com.besscroft.pisces.framework.common.entity.User;
import com.besscroft.pisces.admin.mapper.DictMapper;
import com.besscroft.pisces.admin.service.DictService;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/5 14:21
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    private final SecurityUtils securityUtils;

    @Override
    public List<Dict> queryAllByGroup(@NonNull String groupName) {
        return this.baseMapper.selectAllByGroup(groupName);
    }

    @Override
    public List<Dict> pageList(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDict(@NonNull Dict dict) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        dict.setCreator(currentAdmin.getUsername());
        return this.baseMapper.insert(dict) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDict(@NonNull Dict dict) {
        User currentAdmin = securityUtils.getCurrentAdmin();
        dict.setUpdater(currentAdmin.getUpdater());
        return this.baseMapper.updateById(dict) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDict(@NonNull Long dictId) {
        return this.baseMapper.updateDelById(dictId) > 0;
    }

}

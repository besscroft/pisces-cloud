package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.framework.common.entity.Dict;
import com.besscroft.pisces.admin.mapper.DictMapper;
import com.besscroft.pisces.admin.service.DictService;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/5 14:21
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

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
    public void addDict(@NonNull Dict dict) {
        Assert.isTrue(this.baseMapper.insert(dict) > 0, "新增字典失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(@NonNull Dict dict) {
        Assert.isTrue(this.baseMapper.updateById(dict) > 0, "更新字典失败！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDict(@NonNull Long dictId) {
        Assert.isTrue(this.baseMapper.deleteById(dictId) > 0, "删除字典失败！");
    }

}

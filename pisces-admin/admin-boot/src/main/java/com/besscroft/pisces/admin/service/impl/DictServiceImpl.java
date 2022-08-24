package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.entity.Dict;
import com.besscroft.pisces.admin.mapper.DictMapper;
import com.besscroft.pisces.admin.service.DictService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/5 14:21
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> queryAllByGroup(String groupName) {
        return this.baseMapper.selectAllByGroup(groupName);
    }

    @Override
    public List<Dict> pageList(Integer pageNum, Integer pageSize, String queryKey) {
        PageHelper.startPage(pageNum, pageSize);
        return this.baseMapper.selectAllByQueryKey(queryKey);
    }

}

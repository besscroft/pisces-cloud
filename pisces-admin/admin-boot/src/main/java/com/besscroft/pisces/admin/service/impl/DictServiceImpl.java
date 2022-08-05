package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.admin.entity.Dict;
import com.besscroft.pisces.admin.mapper.DictMapper;
import com.besscroft.pisces.admin.service.DictService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/8/5 14:21
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
}

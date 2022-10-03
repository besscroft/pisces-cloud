package com.besscroft.pisces.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.besscroft.pisces.framework.common.entity.Log;
import com.besscroft.pisces.admin.mapper.LogMapper;
import com.besscroft.pisces.admin.service.LogService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:39
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}

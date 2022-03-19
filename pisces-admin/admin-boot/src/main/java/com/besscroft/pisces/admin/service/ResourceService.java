package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.entity.Resource;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 初始化资源角色规则
     * @return 资源角色规则
     */
    Map<String, List<String>> initRoleResourceMap();

}

package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.domain.dto.ResourceDto;
import com.besscroft.pisces.admin.entity.Resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * 获取资源列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return 资源列表分页对象
     */
    List<Resource> getResourceListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 获取所有资源树
     * @return
     */
    List<ResourceDto> getAll();

    /**
     * 根据角色 id 获取资源 id 列表
     * @param roleId 角色 id
     * @return 资源 id 列表
     */
    Set<Long> getIdsByRoleId(Long roleId);

}

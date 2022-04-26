package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.entity.Depart;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:39
 */
public interface DepartService extends IService<Depart> {

    /**
     * 获取部门/组织列表（分页）
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param queryKey 查询参数
     * @return 部门/组织列表分页对象
     */
    List<Depart> getDepartListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 部门/组织软删除
     * @param departId 部门/组织 id
     * @return
     */
    boolean deleteDepart(Long departId);

}

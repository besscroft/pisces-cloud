package com.besscroft.pisces.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.besscroft.pisces.admin.domain.dto.DepartDictDto;
import com.besscroft.pisces.admin.domain.dto.DepartDto;
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
    List<DepartDto> getDepartListPage(Integer pageNum, Integer pageSize, String queryKey);

    /**
     * 部门/组织软删除
     * @param departId 部门/组织 id
     * @return
     */
    boolean deleteDepart(Long departId);

    /**
     * 新增部门
     * @param depart 部门实体
     * @return
     */
    boolean addDepart(Depart depart);

    /**
     * 更新部门
     * @param depart
     * @return
     */
    boolean updateDepart(Depart depart);

    /**
     * 获取部门字典
     * @return 部门字典
     */
    List<DepartDictDto> getDepartDict();

}

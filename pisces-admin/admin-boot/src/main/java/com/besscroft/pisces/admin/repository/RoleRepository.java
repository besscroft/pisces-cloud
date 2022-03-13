package com.besscroft.pisces.admin.repository;

import com.besscroft.pisces.admin.domain.dto.RoleResourceRelationDto;
import com.besscroft.pisces.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:35
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 获取角色资源关系
     * @return
     */
    @Query(value = "SELECT " +
                "   id, role_id AS roleId, resource_id AS resourceId " +
                "FROM " +
                "   pisces_auth_role_resource ", nativeQuery = true)
    List<RoleResourceRelationDto> findRoleResourceRelation();

}

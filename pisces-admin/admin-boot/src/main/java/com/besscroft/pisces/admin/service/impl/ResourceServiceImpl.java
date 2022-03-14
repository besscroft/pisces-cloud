package com.besscroft.pisces.admin.service.impl;

import com.besscroft.pisces.admin.domain.dto.RoleResourceRelationDto;
import com.besscroft.pisces.admin.entity.Resource;
import com.besscroft.pisces.admin.entity.Role;
import com.besscroft.pisces.admin.repository.ResourceRepository;
import com.besscroft.pisces.admin.repository.RoleRepository;
import com.besscroft.pisces.admin.service.ResourceService;
import com.besscroft.pisces.constant.AuthConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ResourceRepository resourceRepository;
    private final RoleRepository roleRepository;
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    @SneakyThrows
    public Map<String, List<String>> initRoleResourceMap() {
        Map<String,List<String>> RoleResourceMap = new TreeMap<>();
        List<Resource> resourceList = resourceRepository.findAll();
        List<Role> roleList = roleRepository.findAll();
        List<RoleResourceRelationDto> roleResourceRelationList = roleRepository.findRoleResourceRelation();
        for (Resource resource: resourceList) {
            Set<Long> roleIds = roleResourceRelationList.stream().filter(item ->
                            item.getResourceId().equals(resource.getId())
                    ).map(RoleResourceRelationDto::getRoleId)
                    .collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item ->
                    roleIds.contains(item.getId())
            ).map(item ->
                    // 格式:ROLE_{roleId}_{roleName}
                    AuthConstants.AUTHORITY_PREFIX + item.getId() + "_" + item.getRoleName()
            ).collect(Collectors.toList());
            // key为访问路径/资源路径，value为角色
            RoleResourceMap.put("/" + applicationName + resource.getUrl(), roleNames);
        }
        redisTemplate.delete(AuthConstants.PERMISSION_RULES_KEY);
        redisTemplate.opsForHash().putAll(AuthConstants.PERMISSION_RULES_KEY, RoleResourceMap);
        log.info("权限初始化成功.[RoleResourceMap={}]", objectMapper.writeValueAsString(RoleResourceMap));
        return RoleResourceMap;
    }

}

package com.besscroft.pisces.admin.component;

import com.besscroft.pisces.admin.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description 角色资源规则初始化 处理
 * @Author Bess Croft
 * @Date 2022/3/4 21:41
 */
@Component
@RequiredArgsConstructor
public class RoleResourceRulesHolder {

    private final ResourceService resourceService;

    @PostConstruct
    public void initRoleResourceMap() {
        resourceService.initRoleResourceMap();
    }

}

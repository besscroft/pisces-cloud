package com.besscroft.pisces.admin.component;

import com.besscroft.pisces.admin.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/3/4 21:41
 */
@Component
public class RoleResourceRulesHolder {

    @Autowired
    private ResourceService resourceService;

    @PostConstruct
    public void initRoleResourceMap() {
        resourceService.initRoleResourceMap();
    }

}

package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 角色接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:51
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

}

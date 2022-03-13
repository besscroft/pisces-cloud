package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 菜单接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

}

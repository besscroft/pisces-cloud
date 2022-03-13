package com.besscroft.pisces.admin.controller;

import com.besscroft.pisces.admin.service.ResourceCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 资源类别接口
 * @Author Bess Croft
 * @Date 2022/3/13 19:52
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource/category")
public class ResourceCategoryController {

    private final ResourceCategoryService resourceCategoryService;

}

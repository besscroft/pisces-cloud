package com.besscroft.pisces.admin.service;

import java.util.Map;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:38
 */
public interface MenuService {

    /**
     * 获取当前用户菜单动态路由
     * @param userId 用户 id
     * @return 当前用户的所有动态路由
     */
    Map<String, Object> getTreeListById(Long userId);

}

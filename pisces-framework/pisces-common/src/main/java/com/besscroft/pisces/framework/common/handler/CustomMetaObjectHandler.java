package com.besscroft.pisces.framework.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.besscroft.pisces.framework.common.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description Mybatis-Plus 自动填充自定义实现类
 * @Author Bess Croft
 * @Date 2022/10/19 11:51
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomMetaObjectHandler implements MetaObjectHandler {

    private final SecurityUtils securityUtils;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("username:{}", securityUtils.getCurrentAdmin().getUsername());
        String username = securityUtils.getCurrentAdmin().getUsername();
        log.info("username:{}", securityUtils.getCurrentAdmin().getUsername());
        this.strictInsertFill(metaObject, "creator", String.class, username);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updater", String.class, username);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updater", () -> securityUtils.getCurrentAdmin().getUsername(), String.class);
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

}

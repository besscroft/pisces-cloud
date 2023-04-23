package com.besscroft.pisces.gateway.retry;

/**
 * @Description 异步远程调用——处理载入权限缓存——重试方法
 * @Author Bess Croft
 * @Date 2022/9/16 21:25
 */
public interface AuthRetry {

    /**
     * 权限关系列表重试方法
     * @return 是否成功
     */
    boolean retryAuthTask();

}

package com.besscroft.pisces.runnable;

import org.slf4j.MDC;

import java.util.Map;

/**
 * @Description MDC 运行线程类
 * @Author Bess Croft
 * @Date 2022/2/2 21:58
 */
public class MDCRunnable implements Runnable {

    private final Runnable runnable;

    private final Map<String, String> map;

    public MDCRunnable(Runnable runnable) {
        this.runnable = runnable;
        // 保存当前线程的 MDC 值
        this.map = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
        // 传入已保存的 MDC 值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
        // 装饰器模式，执行 run 方法
        runnable.run();
        // 移除已保存的 MDC 值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.remove(entry.getKey());
        }
    }

}

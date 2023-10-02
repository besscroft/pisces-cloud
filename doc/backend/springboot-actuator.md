---
icon: creative
category: Pisces
---

# SpringBoot Actuator 应用监控接口支持

SpringBoot 中内置的监控叫做 `Actuator`，使用它可以添加如下依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Actuator 默认把所有访问点暴露给 JMX，但处于安全原因，只有 `health` 和 `info` 会暴露给 Web。如果你需要暴露全部访问点，或者是其它访问点，可以在对应的 yaml 文件中进行配置。

| 地址            | 描述                                              |
| --------------- | ------------------------------------------------- |
| /beans          | 显示所有的 `Spring bean` 列表                       |
| /caches         | 显示所有的缓存相关信息                            |
| /scheduledtasks | 显示所有的定时任务相关信息                        |
| /loggers        | 显示所有的日志相关信息                            |
| /configprops    | 显示所有的配置信息                                |
| /env            | 显示所有的环境变量信息                            |
| /mappings       | 显示所有控制器相关信息                            |
| /info           | 显示自定义用户信息配置                            |
| /metrics        | 显示应用指标相关信息                              |
| /health         | 显示健康检查状态信息，`up` 表示成功 `down` 表示失败 |
| /threaddump     | 显示程序线程的信息                                |

默认启动后是无法访问的，你需要单独给每一项配置白名单才可在**外部**访问。一般我们只会暴露 `gateway` 到公网上，所以需要把对应服务的访问点添加到白名单。

例如需要在外部访问 `pisces-admin` 服务的 `/actuator/health` 访问点，需要配置白名单路径为 `/pisces-admin/actuator/health`，所有对应的白名单默认没加，你需要什么加上什么即可！当然，这里只是拿原项目举例，你自己可以自定义路径。如果你是从内部访问，那么直接用 `http://127.0.0.1:<port>/actuator/health` 就可以了，其它的访问点也是一样。如果你需要用 Spring Security 鉴权的话，可以使用下面的代码案例放行：

```java
package com.besscroft.pisces.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/4 16:34
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/publicKey/get", "/oauth/token").permitAll()
                // 下面这行是 SpringBoot Actuator 放行，如果不想用，可以注释掉
                .and().authorizeRequests().antMatchers("/actuator/**").anonymous()
                .anyRequest().authenticated();
    }

}
```

> 要特别注意暴露的 URL 的安全性，例如，`/actuator/env` 可以获取当前机器的所有环境变量，不要暴露给公网。

如果你新建了模块的话，可以添加依赖来支持监控接口：

```xml
<!-- SpringBoot Actuator 支持 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

- 配置

```yaml
# Actuator 配置
management:
  endpoints:
    web:
      exposure:
        # 监控项配置
        include: info, health
  endpoint:
    health:
      # 开启显示全部细节
      show-details: always
    shutdown:
      # 启用接口关闭 SpringBoot
      enabled: false
```
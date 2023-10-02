---
icon: creative
category: Pisces
---

# 项目介绍

[![](https://img.shields.io/badge/%E5%BC%80%E5%8F%91%E8%BF%9B%E5%BA%A6-%E5%BC%80%E5%8F%91%E4%B8%AD-brightgreen?style=flat-square)]() [![](https://img.shields.io/github/license/besscroft/pisces-cloud?style=flat-square)](https://github.com/besscroft/pisces-cloud/blob/master/LICENSE) ![GitHub repo size](https://img.shields.io/github/repo-size/besscroft/pisces-cloud?style=flat-square&color=328657)

一站式微服务解决方案，基于 Spring Cloud 2021.0.1 & Alibaba 2021.0.1.0 + Spring Security OAuth2 + PostgreSQL + Mybatis-Plus 构建。项目的进度可以查看下方的看板了解！

[pisces-cloud 看板](https://github.com/users/besscroft/projects/1)

## 预览

[Pisces Cloud](https://pisces.besscroft.com/)

## 项目结构

```
├── .github    # GitHub Actions
│   ├── workflows # 代码检查作业
├── doc    # 文档
│   ├── config # 项目需要的 nacos 配置
│   └── sql # 项目需要的 SQL 脚本
├── pisces-admin    # 系统支撑服务
│   ├── admin-api # 接口
│   └── admin-boot # 服务
├── pisces-amqp    # 消息中心服务
├── pisces-api    # 通用接口支撑服务
│   └── pisces-file # 分布式文件中心服务
├── pisces-auth    # 认证服务
├── pisces-framework # 核心模块
│   ├── pisces-cache # 公共缓存模块
│   └── pisces-common # 公共核心模块
└──pisces-gateway    # 网关服务
```




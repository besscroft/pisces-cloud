---
icon: creative
category: Pisces
---

# 前端项目介绍

[![](https://img.shields.io/badge/%E5%BC%80%E5%8F%91%E8%BF%9B%E5%BA%A6-%E5%BC%80%E5%8F%91%E4%B8%AD-brightgreen?style=flat-square)]() [![](https://img.shields.io/badge/license-GPL%20v3.0-green?style=flat-square)](https://github.com/besscroft/pisces-cloud-web/blob/master/LICENSE) ![GitHub repo size](https://img.shields.io/github/repo-size/besscroft/pisces-cloud-web?style=flat-square&color=328657)

Pisces-Cloud 的前端，基于 Vue3 + Vite + Pinia + Vue Router + TypeScript + Element-PLUS 构建。

## 项目结构

> 已重构，文档待更新！

```
├── dist                       # 构建后的源码
├── public                     # 静态资源
│   │── favicon.ico            # favicon图标
├── src                        # 源代码
│   ├── @types                 # 类型文件
│   ├── api                    # 所有请求
│   ├── assets                 # 主题 字体等静态资源
│   ├── components             # 全局公用组件
│   ├── config                 # 配置文件夹
│   ├── constant               # 常量
│   ├── icons                  # 图标
│   ├── layout                 # 全局 layout
|   ├── plugins                # 插件
│   ├── router                 # 路由
│   ├── store                  # 全局 store 管理
│   ├── styles                 # 全局样式
│   ├── types                  # 接口、页面ts类型约束
│   ├── utils                  # 全局公用方法
│   ├── views                  # views 所有页面
│   ├── App.vue                # 入口页面
│   ├── main.ts                # 入口文件 加载组件 初始化等
│   └── shims-vue.d.ts         # vue 类型约束
├── .browserslistrc            # browserslist 配置文件
├── .editorconfig              # editorconfig 配置文件
├── .env.xxx                   # 环境变量配置
├── .gitignore                 # gitignore 配置文件
├── auto-imports.d.ts          # 自动导入配置文件
├── babel.config.js            # babel 配置文件
├── components.d.ts            # 组件配置文件
├── index.html                 # html模板
├── LICENSE                    # 许可证
├── package.json               # package.json
├── README.md                  # README.md
├── tsconfig.json              # ts配置文件
└── vite.config.ts             # vite配置文件
```




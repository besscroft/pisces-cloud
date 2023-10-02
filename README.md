# Pisces-Cloud

### 简介

> 似乎没什么要写的了，项目进行归档。本项目适合大学期末作业的参考，以及适合刚步入工作学习 Spring 微服务。
> 没什么太复杂的东西，就是集成了工作中常见的框架和场景，实现了 RBAC 模型的权限管理，没有什么多余的业务。
> 这个项目也是我在工作初期写的学习项目，Learning by doing，最重要的是去做，自己从零开始一个，哪怕很多东西你明白，
> 但是你依旧会踩很多坑，它会帮助你更好地理解 Spring 微服务。最后，希望这个学习项目也有能帮到他人的时候。

一站式微服务解决方案，基于 Spring Cloud 2021.0.1 & Alibaba 2021.0.1.0 + Spring Security OAuth2 + PostgreSQL + Mybatis-Plus 构建。
只实现基础框架能力，不涉及具体业务。

[pisces-cloud 看板](https://github.com/users/besscroft/projects/1)

[![](https://img.shields.io/badge/%E5%BC%80%E5%8F%91%E8%BF%9B%E5%BA%A6-%E5%BC%80%E5%8F%91%E4%B8%AD-brightgreen?style=flat-square)]() [![](https://img.shields.io/github/license/besscroft/pisces-cloud?style=flat-square)](https://github.com/besscroft/pisces-cloud/blob/master/LICENSE) ![GitHub repo size](https://img.shields.io/github/repo-size/besscroft/pisces-cloud?style=flat-square&color=328657)

## 开发者中心

> 我们安装，我们更新，我们开发

### 预览

> 未来某一天，服务器不再维护后，可能会无法预览，请见谅！

[Pisces Cloud](https://pisces.besscroft.com/)

[SkyWalking](https://skywalking.besscroft.com/)

[Sentinel 控制台](https://sentinel.besscroft.com/) 账号：sentinel 密码：sentinel

### 文档

[pisces-cloud 的文档](https://github.com/besscroft/pisces-cloud/doc/readme.md) ，在这里你可以找到大部分问题的解答。

## 环境搭建

### 开发环境

pisces-cloud 的需要以下程序才能正常的安装和运行：

- Git
- PostgreSQL 14(兼容低版本，不保证)
- openJDK 17(兼容低版本，不保证)
- nacos 1.4.1+
- redis 5.0+
- SkyWalking 9.x(兼容8.x)
- sentinel 1.8.x(兼容低版本，不保证)

pisces-cloud 支持安装在 LNMP、宝塔面板 等集成环境中, Docker、HeroKu 等容器环境以及 Kubernetes 中，支持大部分能够运行 Java 的平台。

### 行为准则

我们有一份 [行为准则](https://github.com/besscroft/pisces-cloud/blob/main/CODE_OF_CONDUCT.md) ，希望所有的贡献者都能遵守，请花时间阅读一遍全文以确保你能明白哪些是可以做的，哪些是不可以做的。

### 代码贡献

[提出新想法 & 提交 Bug](https://github.com/besscroft/pisces-cloud/issues/new) | [Fork & Pull Request](https://github.com/besscroft/pisces-cloud/fork)

pisces-cloud 欢迎各种贡献，包括但不限于改进，新功能，文档和代码改进，问题和错误报告。

我们有一份 [开源代码贡献小册](https://github.com/besscroft/pisces-cloud/blob/main/fork-and-push.md) ，如果你还不会，可以看一下大概的思路，如果你已经会了，那么更好。

### 在线开发

你可以使用 Gitpod 进行在线开发：

<p><a href="https://gitpod.io/#https://github.com/besscroft/pisces-cloud" rel="nofollow"><img src="https://camo.githubusercontent.com/1eb1ddfea6092593649f0117f7262ffa8fbd3017/68747470733a2f2f676974706f642e696f2f627574746f6e2f6f70656e2d696e2d676974706f642e737667" alt="Open in Gitpod" data-canonical-src="https://gitpod.io/button/open-in-gitpod.svg" style="max-width:100%;"></a></p>

或者克隆到本地开发:

```shell
git clone https://github.com/besscroft/pisces-cloud.git
```
### 前端项目

[pisces-web](https://github.com/besscroft/pisces-web)

如果您有任何建议，欢迎反馈！

您可以将服务部署在 `DigitalOcean` ，如果你愿意走我的邀请链接注册，可以获得100美元的信用额度。

<a href="https://www.digitalocean.com/?refcode=6841be7284cc&utm_campaign=Referral_Invite&utm_medium=Referral_Program&utm_source=badge"><img src="https://web-platforms.sfo2.cdn.digitaloceanspaces.com/WWW/Badge%201.svg" alt="DigitalOcean Referral Badge" /></a>

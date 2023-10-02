---
icon: creative
category: Pisces
---

# 开箱即用

开发环境
pisces-cloud 的需要以下程序才能正常的安装和运行：

```
PostgreSQL (建议 14.x 版本，其它版本不保证函数兼容性)
open/oracleJDK1.8+
nacos 1.4.1+
redis 6.0+
maven 3.6.x+
Node 14.x+
```

pisces-cloud 支持安装在 LNMP、宝塔面板 等集成环境中, Docker、HeroKu 等容器环境中, 支持 K8S 以及大部分能够运行 Java 的平台。

## 在线开发

你可以使用 Gitpod 进行在线开发：

<p><a href="https://gitpod.io/#https://github.com/besscroft/pisces-cloud" rel="nofollow"><img src="https://camo.githubusercontent.com/1eb1ddfea6092593649f0117f7262ffa8fbd3017/68747470733a2f2f676974706f642e696f2f627574746f6e2f6f70656e2d696e2d676974706f642e737667" alt="Open in Gitpod" data-canonical-src="https://gitpod.io/button/open-in-gitpod.svg" style="max-width:100%;"></a></p>

或者克隆到本地开发:

```shell
git clone https://github.com/besscroft/pisces-cloud.git
```

## 本地开发环境准备(后端)

新建数据库 `pisces-cloud` ，导入 `doc/sql` 文件夹下的 `pisces-cloud.sql` 文件进数据库。(默认账号 root、密码 666666，你可以自己改)

> 初次打开项目，idea会自动加载 Maven 依赖，根据自身网络和配置等因素，加载时长不确定。

下载 [Naocs](https://github.com/alibaba/nacos/releases) 二进制包。

然后新建数据库 `nacos` ，导入 `nacos/conf` 文件夹下的 `nacos-mysql.sql` 文件进数据库。(默认账号nacos、密码nacos，你可以自己改，但注意和下面保持一致)

配置好 `nacos/conf` 文件夹下的 `application.properties` 文件之后运行：`startup.cmd -m standalone` ，主要配置信息如下：

```
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=666666
```

然后你可以通过访问 http://localhost:8848/nacos 查看。

启动你本地的 `Redis` ，密码默认为 `password` ，你可以在 Nacos 上面，每个服务的 `${server.name}-dev.yml` 配置文件中修改为你自己的密码。

然后你现在就可以运行项目中的每一个服务了，不过别忘了，如果你的环境配置信息跟项目默认的不同，别忘记修改！

> 如果你已经有 nacos 环境了，那么你只需要新建一个值为 `daa4bab0-c137-42a8-86dd-406bb603dee1` 的 namespace，然后导入 `doc/config` 文件夹下的 yaml 文件即可。

> 现在你已经成功的运行了后端的服务，但是并没有出现静态页面，不过别担心，咱们继续往下看！

## 本地开发环境准备(前端)

你可能需要先安装 [nodejs](https://nodejs.org/zh-cn/download/)

推荐使用 pnpm

```bash
# 克隆项目
git https://github.com/besscroft/pisces-web.git

# 进入项目
cd pisces-web

# 安装依赖
pnpm i

# 本地开发 启动项目
pnpm dev
```

> 不出意外的话，应该会直接弹出浏览器，你也可以手动输入 http://localhost:3000 进行访问，默认账户密码为 `admin/666666` 。

以上就是在本地开发/调式的大致内容！后续内容编写中！

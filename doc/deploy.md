---
icon: creative
category: Pisces
---

# 部署

## Docker 部署 Java 服务

你可以用我打包好的 Docker 镜像进行部署，`latest` 版本对应于 `main` 分支最新代码。

我们假设这时你已经在服务器上，配置好了 Naocs、PostgreSQL、Redis，那么请继续往下看。

> 注意，你的 nacos 服务，除了 8848 端口外，可能还需要确保应用程序能够访问到它的 9848 和 9849 端口！

- 命令模板：

```shell
docker run -d --name <容器名> \
  --net <网络类型> \
  -p <主机端口>:<容器端口> \
  -v <宿主机目录>:<容器目录> \
  <镜像名>:<版本号>
```

> 请注意，容器内连接主机端口，可以使用 ip `172.17.0.1`。

- Piaces-Gateway 示例命令：

```shell
docker run -d --name pisces-gateway \
  -p 8000:8000 \
  -e JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8" \
  -e SPRING_CONFIG="--spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=http://127.0.0.1:8848" \
  besscroft/pisces-gateway:latest
```

- Piaces-Auth 示例命令：

```shell
docker run -d --name pisces-auth \
  -p 22333:22333 \
  -e JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8" \
  -e SPRING_CONFIG="--spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=http://127.0.0.1:8848" \
  besscroft/pisces-auth:latest
```

- Pisces-Admin 示例命令：

```shell
docker run -d --name pisces-admin \
  -p 20100:20100 \
  -e JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8" \
  -e SPRING_CONFIG="--spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=http://127.0.0.1:8848" \
  besscroft/pisces-admin:latest
```

- Pisces-File 示例命令：

```shell
docker run -d --name pisces-file \
  -p 20890:20890 \
  -e JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8" \
  -e SPRING_CONFIG="--spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=http://127.0.0.1:8848" \
  besscroft/pisces-file:latest
```

- Pisces-AMQP 示例命令：

```shell
docker run -d --name pisces-amqp \
  -p 20990:20990 \
  -e JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8" \
  -e SPRING_CONFIG="--spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=http://127.0.0.1:8848" \
  besscroft/pisces-amqp:latest
```

发现了吗？启动参数支持定制化，你可以根据自己的需求来调整！

> JAVA_OPTS 主要用来设置 JVM 参数。
> SPRING_CONFIG 主要用来设置 Spring 应用的配置文件，当然，不指定文件也可以，比如说数据库连接地址，你可以直接用参数指定，来覆盖掉 yml 中的默认配置！

如果你需要了解支持 `SkyWalking Agent` 的部署方式，可以查看 <a href="./backend/apm-agent#部署带有-skywalking-agent-支持的-docker-镜像">部署带有-skywalking-agent-支持的-docker-镜像</a>

## Nginx 部署前端页面

进入 `pisces-cloud-web` 项目目录，执行如下命令：

```shell
pnpm build:vite
```

如果你需要自定义标识，可以打开 `.env.production` 文件编辑 `VUE_APP_BASE_API`。

打包完成后，将 `dist` 文件夹的内容部署到 nginx 即可。

最后，在你的 nginx 配置文件中，加入如下配置。

```
# 代理转发请求至网关，pisces 标识解决跨域问题
location /pisces/ {
	proxy_pass http://127.0.0.1:8000/;
}
```

> 上面的 `pisces`，就是在 `.env.production` 配置的。而 8000 端口，则为后端网关服务的端口，如果你没有配置，默认为 8000。

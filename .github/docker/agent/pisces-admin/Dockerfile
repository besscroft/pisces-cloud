# 该镜像需要依赖的基础镜像
FROM apache/skywalking-java-agent:8.13.0-java17
# 设置环境变量
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m -Dfile.encoding=utf-8" SPRING_CONFIG="--spring.profiles.active=prod"
# 设置 agent 配置信息
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES="-Dskywalking.collector.backend_service=127.0.0.1:11800" \
    SW_AGENT_NAME="-Dskywalking.agent.service_name=Pisces-Cloud::pisces-admin"
# 设置时区
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone; \
    cp /skywalking/agent/optional-plugins/apm-sentinel-1.x-plugin-8.13.0.jar /skywalking/agent/plugins/apm-sentinel-1.x-plugin-8.13.0.jar; \
    cp /skywalking/agent/optional-plugins/apm-mybatis-3.x-plugin-8.13.0.jar /skywalking/agent/plugins/apm-mybatis-3.x-plugin-8.13.0.jar; \
    cp /skywalking/agent/optional-plugins/apm-fastjson-1.x-plugin-8.13.0.jar /skywalking/agent/plugins/apm-fastjson-1.x-plugin-8.13.0.jar
# 拷贝jar包，并重命名
COPY ./pisces-admin/admin-boot/target/pisces-admin.jar /pisces-admin.jar
# 指定docker容器启动时运行jar包
ENTRYPOINT exec java ${JAVA_OPTS} -javaagent:/skywalking/agent/skywalking-agent.jar ${SW_AGENT_COLLECTOR_BACKEND_SERVICES} ${SW_AGENT_NAME} -jar /pisces-admin.jar ${SPRING_CONFIG}
# 指定维护者的名字
MAINTAINER besscroft

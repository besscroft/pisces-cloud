# 该镜像需要依赖的基础镜像
FROM apache/skywalking-java-agent:8.10.0-java8
# 设置环境变量
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m -Dfile.encoding=utf-8" SPRING_CONFIG="--spring.profiles.active=prod"
# 设置 agent 配置信息
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES="-Dskywalking.collector.backend_service=127.0.0.1:11800" \
    SW_AGENT_NAME="-Dskywalking.agent.service_name=Pisces-Cloud::pisces-auth"
# 设置时区
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone
# 拷贝jar包，并重命名
COPY ./pisces-auth/target/pisces-auth.jar /pisces-auth.jar
# 指定docker容器启动时运行jar包
ENTRYPOINT exec java ${JAVA_OPTS} -javaagent:/skywalking/agent/skywalking-agent.jar ${SW_AGENT_COLLECTOR_BACKEND_SERVICES} ${SW_AGENT_NAME} -jar /pisces-auth.jar ${SPRING_CONFIG}
# 指定维护者的名字
MAINTAINER besscroft
# 该镜像需要依赖的基础镜像
FROM eclipse-temurin:17.0.4.1_1-jre-jammy
# 设置环境变量
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m -Dfile.encoding=utf-8" SPRING_CONFIG="--spring.profiles.active=prod"
# 设置时区
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone
# 拷贝jar包，并重命名
COPY ./pisces-gateway/target/pisces-gateway.jar /pisces-gateway.jar
# 指定docker容器启动时运行jar包
ENTRYPOINT exec java ${JAVA_OPTS} -jar /pisces-gateway.jar ${SPRING_CONFIG}
# 指定维护者的名字
MAINTAINER besscroft

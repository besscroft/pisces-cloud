---
icon: creative
category: Pisces
---

# Kubernetes 集群部署支持

> 本项目将会提供 K8S 的优先支持，如果你本身已经知道如何使用 K8S 的情况下，需要帮助可以私信我！

## 部署准备

如果你要部署在 K8S 集群上，需要先准备好以下基础设施，才能确保服务正常调度和运行：

```
PostgreSQL (建议 14.x 版本，其它版本不保证函数兼容性)
nacos 1.4.1+ (必须)
redis 5.0+ (必须)
RabbitMQ (必须)
Skywalking (非必须，如果没有 skywalking 的话，用不带 skywalking 的普通镜像即可)
Sentinel (非必须)
Grafana、Prometheus、ELK Stack (可选，正在支持中...)
```

> 注意，你的 nacos 服务，除了 8848 端口外，可能还需要确保应用程序能够访问到它的 9848 和 9849 端口！

在集群中部署服务前，你首先得在 nacos 中配置好各个服务对应的 yaml 配置文件，并确保 pods 能够访问到它。

## 部署 StatefulSet 有状态工作负载

[StatefulSet](https://kubernetes.io/zh-cn/docs/concepts/workloads/controllers/statefulset/) 是用来管理有状态应用的工作负载 API 对象。

### 部署 pisces-gateway

- 创建 pisces-gateway-v1.yaml 文件

```yaml
kind: StatefulSet
apiVersion: apps/v1
metadata:
  name: pisces-gateway-v1 # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-gateway
    app.kubernetes.io/name: pisces-gateway
    app.kubernetes.io/version: v1
    version: v1
  annotations:
    kubesphere.io/creator: admin
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  replicas: 1
  selector:
    matchLabels:
      app: pisces-gateway
      app.kubernetes.io/name: pisces-gateway
      app.kubernetes.io/version: v1
      version: v1
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: pisces-gateway
        app.kubernetes.io/name: pisces-gateway
        app.kubernetes.io/version: v1
        version: v1
      annotations:
        kubesphere.io/restartedAt: '2022-10-21T07:11:38.485Z'
        sidecar.istio.io/inject: 'false'
    spec:
      volumes:
        - name: host-time
          hostPath:
            path: /etc/localtime
            type: ''
      containers:
        - name: container-4hcxyx
          image: 'besscroft/pisces-gateway:v2.0.0-agent' # 镜像
          ports:
            - name: http-0
              containerPort: 8000 # 端口
              protocol: TCP
          env:
            - name: JAVA_OPTS # 环境变量用于指定 jvm 参数
              value: '-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8'
            - name: SPRING_CONFIG # 环境变量用于指定 yml 配置文件
              value: >-
                --spring.profiles.active=prod
                --spring.cloud.nacos.discovery.server-addr=http://10.233.43.50:8848
            - name: SW_AGENT_COLLECTOR_BACKEND_SERVICES # 环境变量用于指定 Skywalking 连接配置
              value: '-Dskywalking.collector.backend_service=10.233.6.115:11800'
            - name: SW_AGENT_NAME # 环境变量用于指定 Skywalking 服务名称
              value: '-Dskywalking.agent.service_name=Pisces-Cloud::pisces-gateway'
          resources: {}
          volumeMounts:
            - name: host-time
              readOnly: true
              mountPath: /etc/localtime
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          imagePullPolicy: Always
      restartPolicy: Always
      terminationGracePeriodSeconds: 30
      dnsPolicy: ClusterFirst
      serviceAccountName: default
      serviceAccount: default
      securityContext: {}
      schedulerName: default-scheduler
  serviceName: pisces-gateway
  podManagementPolicy: OrderedReady
  updateStrategy:
    type: RollingUpdate
    rollingUpdate:
      partition: 0
  revisionHistoryLimit: 10
```

- 使用 kubectl apply 来创建定义在 pisces-gateway-v1.yaml 中的 StatefulSet。

```shell
kubectl apply -f pisces-gateway-v1.yaml
```

- 然后获取 StatefulSet，以验证已成功创建.

```shell
kubectl get statefulset pisces-gateway-v1
```

### 部署 pisces-admin

- 创建 pisces-admin-v1.yaml 文件

```yaml
kind: StatefulSet
apiVersion: apps/v1
metadata:
  name: pisces-admin-v1 # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-admin
    app.kubernetes.io/name: pisces-admin
    app.kubernetes.io/version: v1
    version: v1
  annotations:
    kubesphere.io/creator: admin
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  replicas: 1
  selector:
    matchLabels:
      app: pisces-admin
      app.kubernetes.io/name: pisces-admin
      app.kubernetes.io/version: v1
      version: v1
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: pisces-admin
        app.kubernetes.io/name: pisces-admin
        app.kubernetes.io/version: v1
        version: v1
      annotations:
        kubesphere.io/restartedAt: '2022-10-21T07:11:32.741Z'
        sidecar.istio.io/inject: 'false'
    spec:
      volumes:
        - name: host-time
          hostPath:
            path: /etc/localtime
            type: ''
      containers:
        - name: container-g8we70
          image: 'besscroft/pisces-admin:v2.0.0-agent'
          ports:
            - name: http-0
              containerPort: 20100 # 端口
              protocol: TCP
          env:
            - name: JAVA_OPTS # 环境变量用于指定 jvm 参数
              value: '-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8'
            - name: SPRING_CONFIG # 环境变量用于指定 yml 配置文件
              value: >-
                --spring.profiles.active=prod
                --spring.cloud.nacos.discovery.server-addr=http://10.233.43.50:8848
            - name: SW_AGENT_COLLECTOR_BACKEND_SERVICES # 环境变量用于指定 Skywalking 连接配置
              value: '-Dskywalking.collector.backend_service=10.233.6.115:11800'
            - name: SW_AGENT_NAME # 环境变量用于指定 Skywalking 服务名称
              value: '-Dskywalking.agent.service_name=Pisces-Cloud::pisces-admin'
          resources: {}
          volumeMounts:
            - name: host-time
              readOnly: true
              mountPath: /etc/localtime
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          imagePullPolicy: Always
      restartPolicy: Always
      terminationGracePeriodSeconds: 30
      dnsPolicy: ClusterFirst
      serviceAccountName: default
      serviceAccount: default
      securityContext: {}
      schedulerName: default-scheduler
  serviceName: pisces-admin
  podManagementPolicy: OrderedReady
  updateStrategy:
    type: RollingUpdate
    rollingUpdate:
      partition: 0
  revisionHistoryLimit: 10
```

- 使用 kubectl apply 来创建定义在 pisces-admin-v1.yaml 中的 StatefulSet。

```shell
kubectl apply -f pisces-admin-v1.yaml
```

- 然后获取 StatefulSet，以验证已成功创建.

```shell
kubectl get statefulset pisces-admin-v1
```

### 部署 pisces-auth

- 创建 pisces-auth-v1.yaml 文件

```yaml
kind: StatefulSet
apiVersion: apps/v1
metadata:
  name: pisces-auth-v1 # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-auth
    app.kubernetes.io/name: pisces-auth
    app.kubernetes.io/version: v1
    version: v1
  annotations:
    kubesphere.io/creator: admin
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  replicas: 1
  selector:
    matchLabels:
      app: pisces-auth
      app.kubernetes.io/name: pisces-auth
      app.kubernetes.io/version: v1
      version: v1
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: pisces-auth
        app.kubernetes.io/name: pisces-auth
        app.kubernetes.io/version: v1
        version: v1
      annotations:
        kubesphere.io/restartedAt: '2022-10-15T05:27:35.551Z'
        sidecar.istio.io/inject: 'false'
    spec:
      volumes:
        - name: host-time
          hostPath:
            path: /etc/localtime
            type: ''
      containers:
        - name: container-ezmevb
          image: 'besscroft/pisces-auth:v2.0.0-agent'  # 镜像
          ports:
            - name: http-0
              containerPort: 22333 # 端口
              protocol: TCP
          env:
            - name: JAVA_OPTS # 环境变量用于指定 jvm 参数
              value: '-Xms256m -Xmx256m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8'
            - name: SPRING_CONFIG # 环境变量用于指定 yml 配置文件
              value: >-
                --spring.profiles.active=prod
                --spring.cloud.nacos.discovery.server-addr=http://10.233.43.50:8848
            - name: SW_AGENT_COLLECTOR_BACKEND_SERVICES # 环境变量用于指定 Skywalking 连接配置
              value: '-Dskywalking.collector.backend_service=10.233.6.115:11800'
            - name: SW_AGENT_NAME # 环境变量用于指定 Skywalking 服务名称
              value: '-Dskywalking.agent.service_name=Pisces-Cloud::pisces-auth'
          resources: {}
          volumeMounts:
            - name: host-time
              readOnly: true
              mountPath: /etc/localtime
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          imagePullPolicy: Always
      restartPolicy: Always
      terminationGracePeriodSeconds: 30
      dnsPolicy: ClusterFirst
      serviceAccountName: default
      serviceAccount: default
      securityContext: {}
      schedulerName: default-scheduler
  serviceName: pisces-auth
  podManagementPolicy: OrderedReady
  updateStrategy:
    type: RollingUpdate
    rollingUpdate:
      partition: 0
  revisionHistoryLimit: 10
```

- 使用 kubectl apply 来创建定义在 pisces-auth-v1.yaml 中的 StatefulSet。

```shell
kubectl apply -f pisces-auth-v1.yaml
```

- 然后获取 StatefulSet，以验证已成功创建.

```shell
kubectl get statefulset pisces-auth-v1
```

### 部署 pisces-amqp

- 创建 pisces-amqp-v1.yaml 文件

```yaml
kind: StatefulSet
apiVersion: apps/v1
metadata:
  name: pisces-amqp-v1 # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-amqp
    app.kubernetes.io/name: pisces-amqp
    app.kubernetes.io/version: v1
    version: v1
  annotations:
    kubesphere.io/creator: admin
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  replicas: 1
  selector:
    matchLabels:
      app: pisces-amqp
      app.kubernetes.io/name: pisces-amqp
      app.kubernetes.io/version: v1
      version: v1
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: pisces-amqp
        app.kubernetes.io/name: pisces-amqp
        app.kubernetes.io/version: v1
        version: v1
      annotations:
        kubesphere.io/restartedAt: '2022-10-15T05:27:42.611Z'
        sidecar.istio.io/inject: 'false'
    spec:
      volumes:
        - name: host-time
          hostPath:
            path: /etc/localtime
            type: ''
      containers:
        - name: container-cd22yy
          image: 'besscroft/pisces-amqp:v2.0.0-agent'  # 镜像
          ports:
            - name: http-0
              containerPort: 20990 # 端口
              protocol: TCP
          env:
            - name: JAVA_OPTS # 环境变量用于指定 jvm 参数
              value: '-Xms256m -Xmx256m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8'
            - name: SPRING_CONFIG # 环境变量用于指定 yml 配置文件
              value: >-
                --spring.profiles.active=prod
                --spring.cloud.nacos.discovery.server-addr=http://10.233.43.50:8848
            - name: SW_AGENT_COLLECTOR_BACKEND_SERVICES # 环境变量用于指定 Skywalking 连接配置
              value: '-Dskywalking.collector.backend_service=10.233.6.115:11800'
            - name: SW_AGENT_NAME # 环境变量用于指定 Skywalking 服务名称
              value: '-Dskywalking.agent.service_name=Pisces-Cloud::pisces-amqp'
          resources: {}
          volumeMounts:
            - name: host-time
              readOnly: true
              mountPath: /etc/localtime
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          imagePullPolicy: Always
      restartPolicy: Always
      terminationGracePeriodSeconds: 30
      dnsPolicy: ClusterFirst
      serviceAccountName: default
      serviceAccount: default
      securityContext: {}
      schedulerName: default-scheduler
  serviceName: pisces-amqp
  podManagementPolicy: OrderedReady
  updateStrategy:
    type: RollingUpdate
    rollingUpdate:
      partition: 0
  revisionHistoryLimit: 10
```

- 使用 kubectl apply 来创建定义在 pisces-amqp-v1.yaml 中的 StatefulSet。

```shell
kubectl apply -f pisces-amqp-v1.yaml
```

- 然后获取 StatefulSet，以验证已成功创建.

```shell
kubectl get statefulset pisces-amqp-v1
```

### 部署 pisces-file

- 创建 pisces-file-v1.yaml 文件

```yaml
kind: StatefulSet
apiVersion: apps/v1
metadata:
  name: pisces-file-v1 # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-file
    app.kubernetes.io/name: pisces-file
    app.kubernetes.io/version: v1
    version: v1
  annotations:
    kubesphere.io/creator: admin
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  replicas: 1
  selector:
    matchLabels:
      app: pisces-file
      app.kubernetes.io/name: pisces-file
      app.kubernetes.io/version: v1
      version: v1
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: pisces-file
        app.kubernetes.io/name: pisces-file
        app.kubernetes.io/version: v1
        version: v1
      annotations:
        kubesphere.io/restartedAt: '2022-10-15T05:27:50.434Z'
        sidecar.istio.io/inject: 'false'
    spec:
      volumes:
        - name: host-time
          hostPath:
            path: /etc/localtime
            type: ''
      containers:
        - name: container-g6dswl
          image: 'besscroft/pisces-file:v2.0.0-agent'  # 镜像
          ports:
            - name: http-0
              containerPort: 20890 # 端口
              protocol: TCP
          env:
            - name: JAVA_OPTS # 环境变量用于指定 jvm 参数
              value: '-Xms256m -Xmx256m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8'
            - name: SPRING_CONFIG # 环境变量用于指定 yml 配置文件
              value: >-
                --spring.profiles.active=prod
                --spring.cloud.nacos.discovery.server-addr=http://10.233.43.50:8848
            - name: SW_AGENT_COLLECTOR_BACKEND_SERVICES # 环境变量用于指定 Skywalking 连接配置
              value: '-Dskywalking.collector.backend_service=10.233.6.115:11800'
            - name: SW_AGENT_NAME # 环境变量用于指定 Skywalking 服务名称
              value: '-Dskywalking.agent.service_name=Pisces-Cloud::pisces-file'
          resources: {}
          volumeMounts:
            - name: host-time
              readOnly: true
              mountPath: /etc/localtime
          terminationMessagePath: /dev/termination-log
          terminationMessagePolicy: File
          imagePullPolicy: Always
      restartPolicy: Always
      terminationGracePeriodSeconds: 30
      dnsPolicy: ClusterFirst
      serviceAccountName: default
      serviceAccount: default
      securityContext: {}
      schedulerName: default-scheduler
  serviceName: pisces-file
  podManagementPolicy: OrderedReady
  updateStrategy:
    type: RollingUpdate
    rollingUpdate:
      partition: 0
  revisionHistoryLimit: 10
```

- 使用 kubectl apply 来创建定义在 pisces-file-v1.yaml 中的 StatefulSet。

```shell
kubectl apply -f pisces-file-v1.yaml
```

- 然后获取 StatefulSet，以验证已成功创建.

```shell
kubectl get statefulset pisces-file-v1
```

## 部署 Service 有状态服务

[服务（Service）](https://kubernetes.io/zh-cn/docs/concepts/services-networking/service/) 是将运行在一组 [Pods](https://kubernetes.io/zh-cn/docs/concepts/workloads/pods/) 上的应用程序公开为网络服务的抽象方法。

### 创建 pisces-gateway 有状态服务

创建 pisces-gateway-service.yaml 文件

```yaml
kind: Service
apiVersion: v1
metadata:
  name: pisces-gateway # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-gateway
    app.kubernetes.io/name: pisces-gateway
    app.kubernetes.io/version: v1
    micrometer-prometheus-discovery: 'true'
    version: v1
  annotations:
    kubesphere.io/creator: admin
    kubesphere.io/serviceType: statefulservice
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  ports:
    - name: metrics # 端口配置
      protocol: TCP
      port: 8000
      targetPort: 8000
  selector:
    app: pisces-gateway # 对应的 StatefulSet 的 app 名称
    app.kubernetes.io/name: pisces-gateway
    app.kubernetes.io/version: v1
  clusterIP: None
  clusterIPs:
    - None
  type: ClusterIP
  sessionAffinity: None
  ipFamilies:
    - IPv4
  ipFamilyPolicy: SingleStack
  internalTrafficPolicy: Cluster
```

使用 kubectl apply 来创建定义在 pisces-gateway-service.yaml 中的 Headless Service。

```yaml
kubectl apply -f pisces-gateway-service.yaml
```

### 创建 pisces-admin 有状态服务

创建 pisces-admin-service.yaml 文件

```yaml
kind: Service
apiVersion: v1
metadata:
  name: pisces-admin # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-admin
    app.kubernetes.io/name: pisces-admin
    app.kubernetes.io/version: v1
    micrometer-prometheus-discovery: 'true'
    version: v1
  annotations:
    kubesphere.io/creator: admin
    kubesphere.io/serviceType: statefulservice
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  ports:
    - name: metrics # 端口配置
      protocol: TCP
      port: 20100
      targetPort: 20100
  selector:
    app: pisces-admin # 对应的 StatefulSet 的 app 名称
    app.kubernetes.io/name: pisces-admin
    app.kubernetes.io/version: v1
  clusterIP: None
  clusterIPs:
    - None
  type: ClusterIP
  sessionAffinity: None
  ipFamilies:
    - IPv4
  ipFamilyPolicy: SingleStack
  internalTrafficPolicy: Cluster
```

使用 kubectl apply 来创建定义在 pisces-admin-service.yaml 中的 Headless Service。

```yaml
kubectl apply -f pisces-admin-service.yaml
```

### 创建 pisces-auth 有状态服务

创建 pisces-auth-service.yaml 文件

```yaml
kind: Service
apiVersion: v1
metadata:
  name: pisces-auth # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-auth
    app.kubernetes.io/name: pisces-auth
    app.kubernetes.io/version: v1
    micrometer-prometheus-discovery: 'true'
    version: v1
  annotations:
    kubesphere.io/creator: admin
    kubesphere.io/serviceType: statefulservice
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  ports:
    - name: metrics # 端口配置
      protocol: TCP
      port: 22333
      targetPort: 22333
  selector:
    app: pisces-auth # 对应的 StatefulSet 的 app 名称
    app.kubernetes.io/name: pisces-auth
    app.kubernetes.io/version: v1
  clusterIP: None
  clusterIPs:
    - None
  type: ClusterIP
  sessionAffinity: None
  ipFamilies:
    - IPv4
  ipFamilyPolicy: SingleStack
  internalTrafficPolicy: Cluster
```

使用 kubectl apply 来创建定义在 pisces-auth-service.yaml 中的 Headless Service。

```yaml
kubectl apply -f pisces-auth-service.yaml
```

### 创建 pisces-amqp 有状态服务

创建 pisces-amqp-service.yaml 文件

```yaml
kind: Service
apiVersion: v1
metadata:
  name: pisces-amqp # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-amqp
    app.kubernetes.io/name: pisces-amqp
    app.kubernetes.io/version: v1
    micrometer-prometheus-discovery: 'true'
    version: v1
  annotations:
    kubesphere.io/creator: admin
    kubesphere.io/serviceType: statefulservice
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  ports:
    - name: metrics # 端口配置
      protocol: TCP
      port: 20990
      targetPort: 20990
  selector:
    app: pisces-amqp # 对应的 StatefulSet 的 app 名称
    app.kubernetes.io/name: pisces-amqp
    app.kubernetes.io/version: v1
  clusterIP: None
  clusterIPs:
    - None
  type: ClusterIP
  sessionAffinity: None
  ipFamilies:
    - IPv4
  ipFamilyPolicy: SingleStack
  internalTrafficPolicy: Cluster
```

使用 kubectl apply 来创建定义在 pisces-amqp-service.yaml 中的 Headless Service。

```yaml
kubectl apply -f pisces-amqp-service.yaml
```

### 创建 pisces-file 有状态服务

创建 pisces-file-service.yaml 文件

```yaml
kind: Service
apiVersion: v1
metadata:
  name: pisces-file # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-file
    app.kubernetes.io/name: pisces-file
    app.kubernetes.io/version: v1
    micrometer-prometheus-discovery: 'true'
    version: v1
  annotations:
    kubesphere.io/creator: admin
    kubesphere.io/serviceType: statefulservice
    servicemesh.kubesphere.io/enabled: 'false'
spec:
  ports:
    - name: metrics # 端口配置
      protocol: TCP
      port: 20890
      targetPort: 20890
  selector:
    app: pisces-file # 对应的 StatefulSet 的 app 名称
    app.kubernetes.io/name: pisces-file
    app.kubernetes.io/version: v1
  clusterIP: None
  clusterIPs:
    - None
  type: ClusterIP
  sessionAffinity: None
  ipFamilies:
    - IPv4
  ipFamilyPolicy: SingleStack
  internalTrafficPolicy: Cluster
```

使用 kubectl apply 来创建定义在 pisces-file-service.yaml 中的 Headless Service。

```yaml
kubectl apply -f pisces-file-service.yaml
```

## 暴露网关

假设这时候已经全部配置好了，那么我们如果想让 gateway 暴露出来让外部访问，可以通过配置 NodePort 的方式来解决（并非唯一方式），也就是 VirtualIP 方式（咱们默认的是 Headless 有状态服务）。

- 新建 pisces-gateway-svc.yaml 文件

```yaml
kind: Service
apiVersion: v1
metadata:
  name: pisces-gateway-svc # 名称
  namespace: pisces-cloud # 命名空间
  labels:
    app: pisces-gateway-svc
  annotations:
    kubesphere.io/creator: admin
spec:
  ports:
    - name: http-gateway
      protocol: TCP
      port: 8000 # 端口
      targetPort: 8000
      nodePort: 30138 # 给外部的端口
  selector:
    app: pisces-gateway # 对应的 StatefulSet 的 app 名称
    app.kubernetes.io/name: pisces-gateway
    app.kubernetes.io/version: v1
    version: v1
  clusterIP: 10.233.14.213 # 内部访问 IP
  clusterIPs:
    - 10.233.14.213
  type: NodePort
  sessionAffinity: None
  externalTrafficPolicy: Cluster
  ipFamilies:
    - IPv4
  ipFamilyPolicy: SingleStack
  internalTrafficPolicy: Cluster
```

- 使用 kubectl apply 来创建。

```shell
kubectl apply -f pisces-gateway-svc.yaml
```

> 这里 gateway-svc 配置了 NodePort 的形式，假如集群外部有个网关，那么可以做 proxy 访问，对应的地址就为 `10.233.14.213:30138`。
# 介绍

基于Netty构建的基础IM程序，实现了一丁点基础功能。

# 工程介绍

|模块|说明|
| ---- | ---- |
|netty-im-core|包含协议制定、编解码、工具类等核心代码|
|netty-im-client|客户端代码|
|netty-im-server|服务端代码|

# 安装

```shell

# 构建
mvn -DskipTests=true clean package

# 启动服务端，参数为监听端口
java -jar netty-im-server/target/netty-im-server-1.0.0-SNAPSHOT.jar 8080

# 启动客户端，参数为服务端端口
java -jar netty-im-client/target/netty-im-client-1.0.0-SNAPSHOT.jar 8080

```

# 效果图

![WX20210623-221256@2x.png](https://p3.toutiaoimg.com/origin/pgc-image/4edbd025433948ec86c5198e4c4d7821.png)


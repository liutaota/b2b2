# 项目部署

## 部署说明

1. 开发源码编码
2. 根据部署环境调整配置配置文件`application.properties`
5. 项目日志默认只输出到控制台，非开发环境添加配置项`spring.profiles.include=logfile`，默认会输出到`/data/logs`目录下，运行时可通过`-Dlog.path=/data/logs`修改输出目录
3. 基于maven项目源码编译打包，可参考如下
   ```shell
   mvn -Dmaven.test.skip=true package 
   ```
4. 结合`nohup`通过`java -jar`运行项目jar包,可通过命令行覆盖相关的配置项，建议单个应用最大内存限制`1G`到`2G`
   ```shell
   nohup java -Dlog.path=/data/logs -jar /path-to-deploy/app-name.jar --server.port=9000 > /dev/null 2>&1 &
   ```

## 部署目录说明

- 应用发布目录
  ```
  /data/service
  ```
- 应用日志目录
  ```
  /data/logs
  ```
  
- 应用日志文件格式为 `app-name_yyyymmdd_type.log`
  - `app-name` 应用名
  - `yyyymmdd` 日期为年月日
  - `type` 日志类型，其中
    - `CORE` 为核心日志
    - `ERROR` 为错误日志

## 应用端口规则

 - 端口格式说明 【AABBC】
    - AA 实例，建议使用10至30，开发测试使用09
    - BB 应用，定义如下表
    - C  端口类型
         - 0 : http
         - 1 : manager
         - 2 : dubbo
         - 3 : xxl-job
         
## 开发环境

### IP 帐号密码

- IP : 172.168.200.223
- 帐号 : zsyc
- 密码 : zsyc2020
- rsa_key [deploy_rsa](http://172.168.200.6/thirdparty/b2b_doc/-/wikis/uploads/9cd5ebfa7583ef31e3b7105b45b703b5/deploy_rsa)



### 系统环境

Centos7 64位 6核16G

### 运行环境

JRE 1.8.0_191

安装目录 /opt/jdk1.8.0_191/jre

```
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)

``` 

### 中间件使用

#### oracle

| 版本       | IP              | 端口 | 帐号    | 密码    | sid  |
| :--------- | :-------------- | :--- | :------ | :------ | :--- |
| oracle 11G | 172.168.200.221 | 1521 | b2b     | b2b     | orcl |
| oracle 11G | 172.168.200.221 | 1521 | incaerp | incaerp | orcl |

#### zookeeper

| 版本  | 端口 | 安装目录                        |
| :---- | :--- | :------------------------------ |
| 3.6.1 | 2181 | /opt/apache-zookeeper-3.6.1-bin |

#### redis

| 版本         | 端口 | 密码   |
| :----------- | :--- | :----- |
| redis-3.2.12 | 6379 | zt2020 |

#### xxl-job

| admin url                             | 帐号  | 密码   |
| :------------------------------------ | :---- | :----- |
| http://172.16.20.3:8080/xxl-job-admin | admin | 123456 |

#### rabbitmq

| 版本                   | 端口  | 帐号  | 密码   |
| :--------------------- | :---- | :---- | :----- |
| rabbitmq_server-3.6.15 | 5672  | b2b   | 123456 |
| rabbitmq(web)          | 15672 | admin | 123456 |

### 应用部署

| app               | http | manager-port | dubbo-port | xxl-job |
| :---------------- | :--- | :----------- | :--------- | :------ |
| zt-b2b-api        | 9100 | 9101         |            |         |
| zt-b2b-service    |      | 9111         | 9112       | 9113    |
| zt-b2b-oauth      | 9120 | 9121         | 9122       |         |
| zt-inca-service   |      | 9131         | 9132       |         |
| zt-inca-api       | 9140 | 9141         |            |         |
| zt-b2b-fs-service | 9150 | 9151         |            |         |

### 部署监控工具

| app               | http | 用户 | 密码   | 地址                      |
| :---------------- | :--- | :--- | :----- | :------------------------ |
| dubbo-admin       | 9300 | root | root   | http://172.16.20.223:9300 |

## ~~sit环境~~

### IP 帐号密码

- IP : 172.168.200.221
- 帐号 : zsyc
- 密码 : zsyc2020

### 系统环境

Centos7 64位 6核16G

### 运行环境

JRE 1.8.0_191

安装目录 /opt/jdk1.8.0_191/jre

```
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
``` 

## pro环境


### IP 帐号密码

- IP : 172.168.220.227 / 172.16.20.227
- 帐号 : zsyc
- 密码 : zsyc2020

### 系统环境

Centos7 64位 6核16G

### 运行环境

JRE 1.8.0_191

安装目录 /opt/jdk1.8.0_191/jre

```
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
``` 

### 中间件使用

#### oracle

| 版本       | IP              | 端口 | 帐号    | 密码    | sid  |
| :--------- | :-------------- | :--- | :------ | :------ | :--- |
| oracle 11G | 172.168.220.10 | 1521 | b2b     | b2b     | orcl |
| oracle 11G | 172.168.220.10 | 1521 | gdztzs2 | gdztzs2 | orcl |

#### zookeeper

| 版本  | 端口 | 安装目录                        |
| :---- | :--- | :------------------------------ |
| 3.6.1 | 2181 | /opt/apache-zookeeper-3.6.1-bin |

#### redis

| 版本        | 端口 | 密码   |
| :---------- | :--- | :----- |
| redis-6.0.7 | 6379 | zt2020 |

#### xxl-job

| admin url                             | 帐号  | 密码   | 
| :------------------------------------ | :---- | :----- | 
| http://172.168.220.225:8080/xxl-job-admin | admin | 123456 | 

### 应用部署

| app               | http  | manager-port | dubbo-port | xxl-job |
| :---------------- | :---- | :----------- | :--------- | :------ |
| zt-b2b-api        | 10100 | 10101        |            |         |
| zt-b2b-service_0  |       | 10111        | 10112      | 10113   |
| zt-b2b-service_1  |       | 11111        | 11112      | 11113   |
| zt-b2b-oauth      | 10120 | 10121        | 10122      |         |
| zt-inca-service_0 |       | 10131        | 10132      |         |
| zt-inca-service_1 |       | 11131        | 11132      |         |
| zt-b2b-fs-service | 10150 | 10151        |            |         |

### 部署监控工具

| app               | http | 用户 | 密码   | 地址                      |
| :---------------- | :--- | :--- | :----- | :------------------------ |
| dubbo-admin       | 9300 | root | root   | http://172.16.20.227:9300 |
| spring-boot-admin | 9310 | zt   | zt2020 | http://172.16.20.227:9310 |

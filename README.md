# Spring Cloud 开发框架使用指南

## 组件及工具使用版本说明

*组件*| *版本*|*必要性*
---|---|---
JDK|1.8.222|✓
Maven|3.6.1|✓
Spring Cloud|Greenwich SR3|✓
Oracle DB|11.2.0.1.0|✓
Redis|4.0.14|✓
RabbitMQ|3.7.19|✓
Docker|18.06+|
Docker-compose|1.24.1|
Docker-compose File|3.7|

## 开发环境配置
- 开发环境必须具备以下基本工具依赖
   - JDK及其命令行工具
   - Maven命令行工具
   - Maven依赖源（私库、镜像源、本地源）
   - IDE（推荐Jetbrains IDEA）
   - Docker（如果不准备使用，那么基于Docker的一键开发依赖组件配置就无法使用，需要手工提供开发依赖环境）
   
- Oracle数据库驱动
    1. 说明
        - ojdbc?.jar Oracle数据库客户端驱动
        - ucp.jar Oracle数据库连接池驱动
    
    2. 注册Oracle驱动到本地库    
        
        在开发机上执行scripts/oracle/11.2目录下install脚本来注册ojdbc和ucp到本地库
        
        备注：项目自带驱动支持版本为11.2的Oracle数据库，如果使用其他版本，请自行去[Oracle官方网站](www.oracle.com)下载对应驱动替换ojdbc?.jar和ucp.jar，并修改install脚本中对应的文件名
        
## 项目目录
    
      ├── additional  附加组件，包括Oracle11g2 Java访问组件（jar）及Maven Local Repository注册脚本
      ├── auth-server 基于Spring Security OAuth的SSO单点登录
      ├── common 通用工具类
      ├── config-server 基于Spring Cloud Config的配置管理服务
      ├── eureka-server 基于Spring Cloud Netflex组件的Eureka注册中心
      ├── ops
      │   ├── admin-server Spring Boot Admin JVM服务监控 
      │   ├── hystrix-dashboard Hystrix断路器展示服务
      │   └── turbine-server Hystrix断路器汇聚服务
      ├── scripts 数据库创建脚本（在docker-compose.yaml文件中被引用）
      └── service 业务服务（所有业务开发服务请放置于该目录下）
      │   ├── user-info 样例用户服务 
      │   └── zuul Api Gateway网关服务
      ├── .gitignore 
      ├── docker-compose.yaml 基于Docker Compose的开发依赖组件配置文件
      ├── Oracle DB docker image guide.md
      ├── README.md
      └── pom.xml
      
## 快速启动

### 数据库初始化

- 修改数据库连接参数(需要数据库访问的项目根目录下pom.xml文件)
  ```xml	
  <properties>
    <jdbcDriver>oracle.jdbc.OracleDriver</jdbcDriver>
    <!-- TIPS: 该处使用了XML CDATA来处理数据库连接字符串中的特殊符号 -->
    <jdbcURL><![CDATA[jdbc:oracle:thin:@localhost:1521:XE]]></jdbcURL> 
    <jdbcUserId>newtouch</jdbcUserId>
    <jdbcPassword>123456</jdbcPassword>
  </properties>
  ```
- 执行数据库环境初始化命令
  ```bash
  cd [子项目根目录]
  mvn flyway:migrate
  ```
  如果执行失败可以先使用以下命令进行环境清空，然后重新执行初始化命令
  ```bash
  mvn flyway:clean
  ```
  
### 编译打包
- 执行编译打包命令
  ```bash
  cd [项目根目录]
  mvn clean package -Dmaven.test.skip=true
  ```
  指定项目编译
  ```bash
  cd [项目根目录]
  mvn clean package -Dmaven.test.skip=true --projects :common,:user-info # user-info子项目依赖子项目common
  ```
  全量项目镜像打包
  ```bash
  cd [项目根目录]
  mvn dockerfile:build
  ```
  指定项目镜像打包
  ```bash
  cd [项目根目录]
  mvn dockerfile:build --projects :user-info,:zuul
  ```
### 启动及测试
- （可选）启动依赖环境组件（MySQL、ORACLE、REDIS、RabbitMQ、Spring Boot Admin、Eureka、Turbine、Hystrix Dashboard、Zipkin）
  ```bash
  cd [项目根目录]
  docker-compose -f docker-compose.yaml up -d
  ```
- 顺序启动子项目
  ```bash

  ```
## 特性说明

### Maven插件

#### 数据库版本管理插件 flyway-maven-plugin

#### Docker镜像打包插件 dockerfile-maven-plugin

#### 数据库ORM代码自动生成器插件 mybatis-generator-maven-plugin

### 其他特性
#### Oracle数据库
    
    - 添加pom依赖
        
        - 在根项目的pom.xml的<dependencyManagement>段中添加依赖管理
        ```xml
        <dependencies>
            <!-- 省略其他依赖 -->
            <!-- Oracle 11gR2 Jdbc 驱动 -->
            <dependency>
                <groupId>com.oracle</groupId>
                <artifactId>ojdbc6</artifactId>
                <version>11.2.0.4.0</version>
            </dependency>
            <!-- Oracle 11gR2 通用数据库连接池 -->
            <dependency>
                <groupId>com.oracle</groupId>
                <artifactId>ucp</artifactId>
                <version>11.2.0.3.0</version>
            </dependency>
        </dependencies>
        ```
       
        - 在业务服务项目的pom.xml的<dependencies>段中添加依赖，此处不用再指定驱动版本
        ```xml
        <dependencies>
            <!-- 省略其他依赖 -->
            <!-- Oracle 11gR2 Jdbc 驱动 -->
            <dependency>
                <groupId>com.oracle.ojdbc</groupId>
                <artifactId>ojdbc6</artifactId>
                <scope>runtime</scope>
            </dependency>
            <dependency>
                <groupId>com.oracle.ojdbc</groupId>
                <artifactId>ucp</artifactId>
            </dependency>
       </dependencies>
       ```
    - Oracle数据库访问项目配置（样例项目User-Info）
       - 在applicaton-xxx.yaml文件中添加以下内容
       ```yaml
       spring:
         datasource:
           driver-class-name: oracle.jdbc.OracleDriver
           url: jdbc:oracle:thin:@localhost:1521:XE
           username: newtouch
           password: 123456
       ```

#### 数据库ORM组件 MyBatis

#### MyBatis自动分页插件 PageHelper

#### 基于注解的参数校验

#### Spring Cloud特性

##### 注册服务 Eureka

##### 单点登录服务 Security OAuth2 JWT

##### 配置服务 Config

##### 客户端负载均衡 Ribbon

##### 声明式服务调用 Feign

##### 容错保护 Hystrix

##### 服务网关 Zuul

##### 基于消息驱动开发 Stream

##### 分布式链路跟踪 Open Zipkin

##### 服务监控 Admin

##### 集群断路聚合 Turbine

##### 集群断路监控 Turbine Dashboard
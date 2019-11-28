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
MySQL|5.7|✓
Docker|18.06+|
Docker-compose|1.24.1|

## 开发环境说明

- 开发设备需求
   - 内存 16G+ 
   - CPU 4C+ （低于4C容器环境运行缓慢）
   - Ubuntu 16.04+ Or MacOS
   - Windows使用Docker较为麻烦，建议配置以下外部组件依赖环境
       - RabbitMQ
       - Oracle 兼容环境
       - MySQL 兼容环境
       - Redis 兼容环境
    
- 开发环境必须具备以下基本工具依赖
   - JDK及其命令行工具（[JDK环境管理工具](https://sdkman.io/)）
   - JDK的[JCE配置](https://www.oracle.com/technetwork/cn/java/javase/downloads/jce8-download-2133166-zhs.html)
   - Maven命令行工具（[阿里镜像源配置](https://developer.aliyun.com/mirror/maven?spm=a2c6h.13651102.0.0.53322f70zNTCrh)）
   - IDE（推荐[Jetbrains IDEA](https://www.jetbrains.com/)）
   - [Docker](https://www.docker.com/products/docker-desktop) 和 [Docker-compose](https://docs.docker.com/compose/)（如果不准备使用，那么基于Docker的一键开发依赖组件配置就无法使用，需要手工提供开发依赖环境，并修改项目的相关配置参数）
   
- [Oracle数据库驱动](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)
    1. 说明
        - ojdbc?.jar
        - ucp.jar Oracle数据库连接池驱动
    2. 注册Oracle驱动到本地库
        - 在开发机上执行scripts/oracle/11.2目录下install脚本来注册ojdbc和ucp到本地库
        - 项目自带驱动支持版本为11.2的Oracle数据库，如果使用其他版本，请自行去[Oracle官方网站](www.oracle.com)下载对应驱动替换ojdbc?.jar和ucp.jar，并修改install脚本中对应的文件名
        
## 项目目录
    
      ├── additional  附加组件，包括Oracle11g2 Java访问组件（jar）及Maven Local Repository注册脚本
      ├── auth-server 基于Spring Security OAuth的SSO单点登录
      ├── common 通用工具类
      ├── 项目配置库本地目录（git库）
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
      
## 快速启动步骤
  ```bash
  git clone https://github.com/LikeSimple/springcloud-demo-2.git
  cd springcloud-demo-2
  
  # 后续命令如无特殊标注，均指项目根目录下  
  ```
### 1. 全量编译
  ```bash
  mvn clean package -Dmaven.test.skip=true

  ...
  [INFO] Reactor Summary:
  [INFO] 
  [INFO] newtouch-cloud-parent 1.0.0-SNAPSHOT ............... SUCCESS [  0.264 s]
  [INFO] common 1.0.0-SNAPSHOT .............................. SUCCESS [  0.033 s]
  [INFO] service 1.0.0-SNAPSHOT ............................. SUCCESS [  0.003 s]
  [INFO] service-zuul 1.0.0-SNAPSHOT ........................ SUCCESS [  0.083 s]
  [INFO] user-info 1.0.0-SNAPSHOT ........................... SUCCESS [  0.021 s]
  [INFO] ops 1.0.0.RELEASE .................................. SUCCESS [  0.002 s]
  [INFO] admin-server 1.0.0.RELEASE ......................... SUCCESS [  0.016 s]
  [INFO] turbine-server 1.0.0.RELEASE ....................... SUCCESS [  0.018 s]
  [INFO] hystrix-dashboard 1.0.0.RELEASE .................... SUCCESS [  0.016 s]
  [INFO] eureka-server 1.0.0.RELEASE ........................ SUCCESS [  0.012 s]
  [INFO] auth-server 1.0.0-SNAPSHOT ......................... SUCCESS [  0.022 s]
  [INFO] config-server 1.0.0-SNAPSHOT ....................... SUCCESS [  0.019 s]
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time:  1.144 s
  [INFO] Finished at: 2019-11-28T14:53:40+08:00
  ```
### 2. 服务依赖镜像打包
  ```bash
  mvn dockerfile:build --projects :turbine-server,:hystrix-dashboard,:eureka,:admin-server

  ...
  [INFO] Reactor Summary for admin-server 1.0.0.RELEASE:
  [INFO] 
  [INFO] admin-server ....................................... SUCCESS [ 11.167 s]
  [INFO] turbine-server ..................................... SUCCESS [ 10.514 s]
  [INFO] hystrix-dashboard .................................. SUCCESS [  8.823 s]
  [INFO] eureka-server ...................................... SUCCESS [  9.305 s]
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time:  40.462 s
  [INFO] Finished at: 2019-11-28T15:24:19+08:00
  ```
### 3. 依赖环境启动:
  ```bash
  docker-compose up -d

  Creating springcloud-demo-2_redis_1  ... done
  Creating springcloud-demo-2_eureka_1 ... done
  Creating springcloud-demo-2_mq_1     ... done
  Creating springcloud-demo-2_oracle_1 ... done
  Creating springcloud-demo-2_db_1     ... done
  Creating springcloud-demo-2_admin_1  ... done
  Creating springcloud-demo-2_turbine_1           ... done
  Creating springcloud-demo-2_zipkin_1            ... done
  Creating springcloud-demo-2_hystrix-dashboard_1 ... done
  ```

  - 检查服务启动是否成功，启动根据系统处理能力会花费较多时间(一般2-5分钟)
  ```
  docker container list

  ...
  CONTAINER ID        IMAGE                                                                COMMAND                  CREATED              STATUS                                 PORTS                                                                                                      NAMES
  0d86ed451c12        registry.onetest.newtouch.com/demo/hystrix-dashboard:1.0.0.RELEASE   "/bin/sh -c 'exec ja…"   About a minute ago   Up About a minute                      0.0.0.0:7703->7703/tcp, 8090/tcp                                                                           springcloud-demo-2_hystrix-dashboard_1
  4899a5696f69        openzipkin/zipkin:2.18.1                                             "/busybox/sh run.sh"     About a minute ago   Up 25 seconds                          0.0.0.0:9410-9411->9410-9411/tcp                                                                           springcloud-demo-2_zipkin_1
  1bcb4578b397        registry.onetest.newtouch.com/demo/turbine-server:1.0.0.RELEASE      "/bin/sh -c 'exec ja…"   About a minute ago   Up About a minute                      0.0.0.0:7702->7702/tcp, 8090/tcp                                                                           springcloud-demo-2_turbine_1
  2e41f002ec77        registry.onetest.newtouch.com/demo/admin-server:1.0.0.RELEASE        "/bin/sh -c 'exec ja…"   About a minute ago   Up About a minute                      0.0.0.0:7701->7701/tcp, 8090/tcp                                                                           springcloud-demo-2_admin_1
  78f93b977c70        rabbitmq:3.7.19-management-alpine                                    "docker-entrypoint.s…"   About a minute ago   Up About a minute                      0.0.0.0:4369->4369/tcp, 0.0.0.0:5671-5672->5671-5672/tcp, 15671/tcp, 0.0.0.0:15672->15672/tcp, 25672/tcp   springcloud-demo-2_mq_1
  d1ccaf97a2f7        oracle/database:11.2.0.2-xe                                          "/bin/sh -c 'exec $O…"   About a minute ago   Up About a minute (health: starting)   0.0.0.0:1521->1521/tcp, 0.0.0.0:8000->8080/tcp                                                             springcloud-demo-2_oracle_1
  b84a8a33db2f        percona:5.7                                                          "/docker-entrypoint.…"   About a minute ago   Up About a minute                      0.0.0.0:3306->3306/tcp                                                                                     springcloud-demo-2_db_1
  4198acc879d3        registry.onetest.newtouch.com/demo/eureka:1.0.0.RELEASE              "/bin/sh -c 'exec ja…"   About a minute ago   Up About a minute                      8090/tcp, 0.0.0.0:8761->8761/tcp                                                                           springcloud-demo-2_eureka_1
  461d460ebb1a        redis:4.0.14-alpine                                                  "docker-entrypoint.s…"   About a minute ago   Up About a minute                      0.0.0.0:6379->6379/tcp                                                                                     springcloud-demo-2_redis_1  ```
  ```

  执行以下命令可以清理依赖环境
  ```bash
  docker-compose down
  ```

### 4. 数据库初始化


- 执行数据库环境初始化命令
  ```bash
  mvn flyway:migrate --projects :auth-server,:user-info
  ```

  - 执行成功可以看到以下显示
  
  ```bash
  [INFO] Reactor Summary for user-info 1.0.0-SNAPSHOT:
  [INFO] 
  [INFO] user-info .......................................... SUCCESS [  2.040 s]
  [INFO] auth-server ........................................ SUCCESS [  0.748 s]
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time:  3.532 s
  [INFO] Finished at: 2019-11-28T14:31:50+08:00
  [INFO] ------------------------------------------------------------------------
  ```
  - 如果执行失败可以先使用以下命令进行环境清空，然后重新执行初始化命令
  ```bash
  mvn flyway:clean --projects :auth-server,:user-info
  ```
### 5. 项目启动
- 顺序启动各个服务项目（使用命令行启动会阻塞命令界面，需要开启4个分别来启动以下项目）
  ```bash
  mvn spring-boot:run --projects :config-server
  mvn spring-boot:run --projects :auth-server
  mvn spring-boot:run --projects :common,:user-info
  mvn spring-boot:run --projects :zuul
  ```
  或者也可以使用IDE通过Maven Project方式导入项目来启动
  
### 6. 测试
  ```bash
  # 获取访问Token
  curl -X POST http://web_app:web_secret@localhost:7500/auth/oauth/token -F username=test -F password=test -F grant_type=password -F scope=DEMO
  
  {"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzQ5Mjc2OTEsInVzZXJfbmFtZSI6InRlc3QiLCJhdXRob3JpdGllcyI6WyJBVVRIX0FETUlOIl0sImp0aSI6IjBiZTg5YWQ1LTk0YTQtNGJkZC1iZDIxLWE4NDhmMjQ0NWJlMSIsImNsaWVudF9pZCI6IndlYl9hcHAiLCJzY29wZSI6WyJERU1PIl19.oe1RgMF7DGOI5LWhwtRselibBsU1aVQMxuQFCNPcZ775R_sGnNnNmD90WReGkue4EmR3dFPTObPCKHO8LpCJ9EEq1kpT4GDRj35VgeHdJw3nCYIrAJhqY0V_bHwcpTN-8Ys5mvKcepVJBWI1KVqd-KJsHeWwCLtg9e3bwG0gDhX6jCW7ywZlMCi8BKIkOBi6tgvRw9S9bqrd1NBB_dMXOgW_1nz6eeehmmxKlU5_uwDnwUU7vQwiMws_-6xu38yhZy5qBCT5_bPNKZRnJ06NUvNzqsJPsUZat5uWBVIgSqaJe474hDtl1li94j8t2bABuZNF_hweSlQf7u-Oke_gBw","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsiREVNTyJdLCJhdGkiOiIwYmU4OWFkNS05NGE0LTRiZGQtYmQyMS1hODQ4ZjI0NDViZTEiLCJleHAiOjE1Nzc1MTkxOTEsImF1dGhvcml0aWVzIjpbIkFVVEhfQURNSU4iXSwianRpIjoiYWQzNjcyZjctYjJkZS00Y2Y2LWIwOTYtOGY3ODI1OTI1MjI3IiwiY2xpZW50X2lkIjoid2ViX2FwcCJ9.YfV6tWZObypyhXNF1QxGwMDA2QpQuEJWaWCRO9T9U1H7FaCid-Nr5m9OiZFDwV5b4-MdFO8VBIdOD-WJoi7EyudqtisadMtDTbe-J-0NMqgr9wt8vnKiCpQQs75PJKf9FMYOuj3viw9USnP4osnwX6IXwIvUPNukOyOHUCyuZfQ-OL1ksrdfANbgVXLxY_U3Nu-5CRLYHz1_GHgiMonoLZZnFczXRfPqJQJCqVA-Gz5CqQUngXwb0GK5D5dpbd1c14LZEEf2pA4j5ho98_MVD7UWo7h7R-BOjS5UPoWFtugxGd7duSiCoq30b_1JU749Jvs8egNFGv0Cbz09arucVA","expires_in":499,"scope":"DEMO","jti":"0be89ad5-94a4-4bdd-bd21-a848f2445be1"}
  
  配置环境变量（返回Json串中的access_token）
  TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzQ5Mjc2OTEsInVzZXJfbmFtZSI6InRlc3QiLCJhdXRob3JpdGllcyI6WyJBVVRIX0FETUlOIl0sImp0aSI6IjBiZTg5YWQ1LTk0YTQtNGJkZC1iZDIxLWE4NDhmMjQ0NWJlMSIsImNsaWVudF9pZCI6IndlYl9hcHAiLCJzY29wZSI6WyJERU1PIl19.oe1RgMF7DGOI5LWhwtRselibBsU1aVQMxuQFCNPcZ775R_sGnNnNmD90WReGkue4EmR3dFPTObPCKHO8LpCJ9EEq1kpT4GDRj35VgeHdJw3nCYIrAJhqY0V_bHwcpTN-8Ys5mvKcepVJBWI1KVqd-KJsHeWwCLtg9e3bwG0gDhX6jCW7ywZlMCi8BKIkOBi6tgvRw9S9bqrd1NBB_dMXOgW_1nz6eeehmmxKlU5_uwDnwUU7vQwiMws_-6xu38yhZy5qBCT5_bPNKZRnJ06NUvNzqsJPsUZat5uWBVIgSqaJe474hDtl1li94j8t2bABuZNF_hweSlQf7u-Oke_gBw

  访问被保护的服务
  curl -H "Authorization: Bearer $TOKEN" http://localhost:7500/user/whoami
  看到以下返回则成功
  test

  不带TOKEN访问
  curl http://localhost:7500/user/whoami
  返回401未授权标志
  {"timestamp":"2019-11-28 15:51:58","status":401,"error":"Unauthorized","message":"No message available","path":"/user/whoami"}
  # 

  ```
  
## 特性说明

### Spring Cloud 特性

#### 注册服务 Eureka

#### 单点登录服务 Security OAuth2 JWT

#### 配置服务 Config

#### 客户端负载均衡 Ribbon

#### 声明式服务调用 Feign

#### 容错保护 Hystrix

#### 服务网关 Zuul

#### 基于消息驱动开发 Stream

#### 分布式链路跟踪 Open Zipkin

#### 服务监控 Admin

#### 集群断路聚合 Turbine

#### 集群断路监控 Turbine Dashboard

### Spring Boot 特性

### Maven 插件

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


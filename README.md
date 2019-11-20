# Spring Cloud 开发框架使用指南

## 环境版本

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
## 项目架构

## 开发环境配置
- **使用Oracle数据库**

    - 注册Oracle驱动
    
        执行scripts/oracle/11.2目录下install脚本来注册ojdbc和ucp到本地maven repository
    
        项目自带驱动支持版本为11.2的Oracle数据库，如果使用其他版本，请自行去www.oracle.com下载对应驱动替换ojdbc?.jar和ucp.jar，并修改install脚本中对应的文件名
    
    - 添加pom依赖
        
        在项目根目录的<dependencyManagement>段中添加依赖管理
        ```xml
        <dependencies>
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

## 样例项目

## 开发测试


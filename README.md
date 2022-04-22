<p align="center"><img width="100" src="https://dev.java/assets/images/duke-celebrate.png"></p>

<p align="center">
  <a href="https://github.com/flyhero/spring-boot-plus"><img src="https://img.shields.io/badge/JDK-1.8-brightgreen" alt="jdk"></a>
  <a href="https://github.com/flyhero/spring-boot-plus"><img src="https://img.shields.io/badge/license-MIT-blue" alt="license"></a>
  <a href="https://github.com/flyhero/spring-boot-plus"><img src="https://img.shields.io/badge/author-flyhero-green" alt="author"></a>
</p>

<h2 align="center"> spring-boot-plus</h2>
<p align="center"><b> 对一些常用功能的自动配置，省去CV操作。</b></p>

## Introduction - 介绍

### Summary - 概要
springboot代码生成器，主要用于对一些常用功能的自动配置，省去CV操作。

### Features - 特性

- 日志配置
- 异常统一处理
- cors跨域设置
- 序列化配置
- xss防攻击
- mybatis-plus配置
- mybatis-plus代码生成
- README模板生成

## Usage - 用法

在测试类或启动类中进行如下设置：

```java
        PlusConfig.ProjectConfig projectConfig = new PlusConfig.ProjectConfig();
        projectConfig.setGroupId("com.github.flyhero");
        projectConfig.setArtifactId("demo-test");
        projectConfig.setName("demo-test");
        projectConfig.setDescription("desc");

        PlusConfig.DataSourceConfig dataSourceConfig = new PlusConfig.DataSourceConfig();
        dataSourceConfig.setUsername("username");
        dataSourceConfig.setPassword("password");
        dataSourceConfig.setType("Druid");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSourceConfig.setTableNames(new String[]{"test"});

        PlusConfig plusConfig = new PlusConfig();
        plusConfig.setUseCors(true);
        plusConfig.setUseMybatisPlus(true);
        plusConfig.setUseExceptionHandler(true);
        plusConfig.setProjectConfig(projectConfig);
        plusConfig.setDataSourceConfig(dataSourceConfig);

        projectGenerator.doGenerate(plusConfig);
```
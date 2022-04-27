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
springboot代码生成器，适用于前后端分离的单体项目，主要用于对一些常用功能的自动配置，省去CV操作。

### Features - 特性

- 日志配置
- 异常统一处理
- cors跨域设置
- 拦截器
- 序列化配置
- mybatis-plus配置
- mybatis-plus代码生成
- README模板生成
- xss防攻击

TODO：

- 国际化
- util常用类

## Usage - 用法

在测试类或启动类中进行如下设置：

```java
        PlusConfig.ProjectConfig projectConfig = PlusConfig.ProjectConfig.builder().groupId("com.github.flyhero")
        .artifactId("demo-test")
        .name("demo-test")
        .description("desc")
        .build();

        PlusConfig.DataSourceConfig dataSourceConfig = PlusConfig.DataSourceConfig.builder()
        .username("***")
        .password("***")
        .type(PlusConfig.DataSourceConfig.DataSourceType.Druid)
        .url("jdbc:mysql://127.0.0.1:3306/test")
        .tableNames(new String[]{"test"}).build();

        PlusConfig plusConfig = new PlusConfig();
        plusConfig.setUseCors(true);
        plusConfig.setUseMybatisPlus(true);
        plusConfig.setUseExceptionHandler(true);
        plusConfig.setProjectConfig(projectConfig);
        plusConfig.setDataSourceConfig(dataSourceConfig);

        projectGenerator.doGenerate(plusConfig);
```
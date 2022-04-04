server.address=8080
spring.application.name=${name}

<#if hasDbConfig>
### Data source
spring.datasource.url=${url}
spring.datasource.username=${username}
spring.datasource.password=${password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
</#if>

<#if dataSourceType == "Hikari">
### Hikari Datasource
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.pool-name=Hikari
spring.datasource.hikari.connectionTimeout=30000
spring.datasource.hikari.idleTimeout=30000
spring.datasource.hikari.validationTimeout=3000
spring.datasource.hikari.maxLifetime=120000
spring.datasource.hikari.loginTimeout=5
spring.datasource.hikari.minimumIdle=5
spring.datasource.hikari.maximumPoolSize=15
spring.datasource.hikari.connection-test-query=SELECT 1
</#if>
<#if dataSourceType == "Druid">
### Druid Datasource
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.druid.initial-size=5
spring.datasource.druid.min-idle=5
spring.datasource.druid.max-active=20
spring.datasource.druid.max-wait=60000
spring.datasource.druid.time-between-eviction-runs-millis=60000
spring.datasource.druid.min-evictable-idle-time-millis=300000
spring.datasource.druid.validation-query=SELECT 1 FROM DUAL
spring.datasource.druid.test-while-idle=true
spring.datasource.druid.test-on-borrow=false
spring.datasource.druid.test-on-return=false
spring.datasource.druid.pool-prepared-statements=false
spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20
spring.datasource.druid.filters=stat,wall
spring.datasource.druid.connection-properties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

##### WebStatFilter配置
#启用StatFilter
spring.datasource.druid.web-stat-filter.enabled=true
#添加过滤规则
spring.datasource.druid.web-stat-filter.url-pattern=/*
#排除一些不必要的url
spring.datasource.druid.web-stat-filter.exclusions=*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*
#开启session统计功能
spring.datasource.druid.web-stat-filter.session-stat-enable=true
#缺省sessionStatMaxCount是1000个
spring.datasource.druid.web-stat-filter.session-stat-max-count=1000
#spring.datasource.druid.web-stat-filter.principal-session-name=
#spring.datasource.druid.web-stat-filter.principal-cookie-name=
#spring.datasource.druid.web-stat-filter.profile-enable=

##### StatViewServlet配置
#启用内置的监控页面
spring.datasource.druid.stat-view-servlet.enabled=true
#内置监控页面的地址
spring.datasource.druid.stat-view-servlet.url-pattern=/druid/*
#关闭 Reset All 功能
spring.datasource.druid.stat-view-servlet.reset-enable=false
#设置登录用户名
spring.datasource.druid.stat-view-servlet.login-username=admin
#设置登录密码
spring.datasource.druid.stat-view-servlet.login-password=123456
#白名单（如果allow没有配置或者为空，则允许所有访问）
spring.datasource.druid.stat-view-servlet.allow=127.0.0.1
#黑名单（deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝）
spring.datasource.druid.stat-view-servlet.deny=
</#if>

<#if useMybatisPlus>
###  Mybatis-Plus Config
mybatis-plus.global-config.banner=false
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
mybatis-plus.global-config.db-config.logic-delete-field=is_delete
mybatis-plus.global-config.db-config.logic-delete-value=1
mybatis-plus.global-config.db-config.logic-not-delete-value=0
</#if>

<#if logWay == "logback">
logging.file.path=/root/logs
</#if>
<#if logWay == "log4j2">
logging.config=log4j2-spring.xml
logging.level.com.infrastructure.log4j2=true
</#if>
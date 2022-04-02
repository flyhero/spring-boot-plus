server.address=8080
spring.application.name=${name}

<#if hasDbConfig>
### Data source
spring.datasource.url=${url}
spring.datasource.username=${username}
spring.datasource.password=${password}
</#if>

<#if dataSource == "Hikari">
### Hikari Datasource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
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
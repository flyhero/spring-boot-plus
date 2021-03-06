<?xml version="1.0" encoding="UTF-8" ?>
<configuration debug="false">
    <!--定义日志文件的存储地址 application.* 中 logging.file.path=/root/logs -->
    <property name="LOG_HOME"
              value="${r'${LOG_PATH:-.}'}"/>
    <!-- 控制台输出设置 -->
    <!-- 彩色日志格式，magenta：洋红，boldMagenta：粗红，yan：青色，:-->
    <property name="CONSOLE_LOG_PATTERN"
              value="%boldMagenta([%d{yyyy-MM-dd HH:mm:ss.SSS}]) %cyan([%X{requestId}]) %boldMagenta(%-5level) %blue(%logger{15}) %red([%thread]) %magenta(:) %cyan(%msg%n)"/>

    <!-- 控制台 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${r'${CONSOLE_LOG_PATTERN}'}</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>

    <!-- 按天输出日志设置 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 日志文件输出的文件名 -->
            <FileNamePattern>${r'${LOG_HOME}'}/%d{yyyy-MM-dd}_info.%i.log
            </FileNamePattern>
            <!-- 日志文件保留天数 -->
            <MaxHistory>7</MaxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>50MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!-- 格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符 -->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 日志输出级别，OFF level > FATAL > ERROR > WARN > INFO > DEBUG > ALL level -->
    <logger name="com.apache.ibatis" level="INFO"/>
    <logger name="java.sql.Statement" level="INFO"/>
    <logger name="java.sql.Connection" level="INFO"/>
    <logger name="java.sql.PreparedStatement" level="INFO"/>
    <logger name="org.springframework" level="WARN"/>
    <logger name="com.baomidou.mybatisplus" level="WARN"/>
    <!-- 开发环境：打印控制台和输出到文件 -->
    <springProfile name="dev">
        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>
    <!-- 生产环境：打印控制台和输出到文件 -->
    <springProfile name="pro">
        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>
</configuration>
# SpringBoot入门 - 添加Logback日志

---

# 一、Logback日志

## 1、什么是日志

通过日志查看程序的运行过程、运行信息、异常信息等。

## 2、日志级别

日志记录器(Logger)的行为是分等级的。如下表所示：
分为：TRACE,DEBUG,INFO,WARN,ERROR,ALL和OFF
默认情况下，spring boot从控制台打印出来的日志级别只有INFO及以上级别，可以配置日志级别

```yml
## 设置日志级别
logging:
  level:
   root: ERROR
```

这种方式能将ERROR级别以及以上级别的日志输出到控制台上，其他级别将不会输出

## 3、创建日志文件

spring boot内部使用Logback作为日志实现的框架。
先删除前面在application.yml中的日志级别配置
resources 中创建logback-spring.xml（默认日志文件的名字）

## 4、创建测试日志输出

将以下日志输出到任意Controller的方法中即可，例如list方法中。

# **二、基本配置说明**

## **1、configuration**

日志配置的根节点

```xml
<configuration></configuration>
```

## **2、contextName**

`<contextName>`是`<configuration>`的子节点。

每个logger都关联到logger上下文，默认上下文名称为“default”。但可以使用`<contextName>`设置成其他名字，用于区分不同的应用程序。

```xml
<contextName>atguiguSrb</contextName>
```

## **3、property**

`<property>`是`<configuration>`的子节点，用来定义变量。

`<property> `有两个属性，name和value：name的值是变量的名称，value是变量的值。

通过`<property>`定义的值会被插入到logger上下文中。定义变量后，可以使“${}”来使用变量。

```xml
<!-- 日志的输出目录 -->
<property name="log.path" value="D:/project/finance/srb_log/core" />
<!--控制台日志格式：彩色日志-->
<!-- magenta:洋红 -->
<!-- boldMagenta:粗红-->
<!-- cyan:青色 -->
<!-- white:白色 -->
<!-- magenta:洋红 -->
<property name="CONSOLE_LOG_PATTERN"
          value="%yellow(%date{yyyy-MM-dd HH:mm:ss}) %highlight([%-5level]) %green(%logger) %msg%n"/>
<!--文件日志格式-->
<property name="FILE_LOG_PATTERN"
          value="%date{yyyy-MM-dd HH:mm:ss} [%-5level] %thread %file:%line %logger %msg%n" />

<!--编码-->
<property name="ENCODING"
          value="UTF-8" />
```

## **4、appender**

`<appender>`是`<configuration>`的子节点，是负责写日志的组件

`<appender>`有两个必要属性name和class：name指定appender名称，class指定appender的全限定名

`<encoder>`对日志进行格式化

`<pattern>`定义日志的具体输出格式

`<charset>`编码方式

### **控制台日志配置**

```xml
<!-- 控制台日志 -->
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        <charset>${ENCODING}</charset>
    </encoder>
</appender>
```

### **文件日志配置**

`<file>`表示日志文件的位置，如果上级目录不存在会自动创建，没有默认值。

`<append>`默认 true，日志被追加到文件结尾，如果是 false，服务重启后清空现存文件。

```xml
<!-- 文件日志 -->
<appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>${log.path}/log.log</file>
    <append>true</append>
    <encoder>
        <pattern>${FILE_LOG_PATTERN}</pattern>
        <charset>${ENCODING}</charset>
    </encoder>
</appender>
```

## **5、logger**

`<logger>`可以是`<configuration>`的子节点，用来设置某一个包或具体某一个类的日志打印级别、指定<appender>

name：用来指定受此logger约束的某一个包或者具体的某一个类

level：用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF。默认继承上级的级别

`<logger>`可以包含零个或多个`<appender-ref>`元素，标识这个appender将会添加到这个logger

```xml
<!-- 日志记录器  -->
<logger name="com.atguigu" level="INFO">
    <appender-ref ref="CONSOLE" />
    <appender-ref ref="FILE" />
</logger>
```

测试日志记录的控制台输出、文件输出、以及日志级别

## **6、测试**

# **三、多环境配置**

## **springProfile**

在一个基于Spring boot开发的项目里，常常需要有多套环境的配置：开发，测试以及产品。使用springProfile 可以分别配置开发（dev），测试（test）以及生产（prod）等不同的环境

```xml
<!-- 开发环境和测试环境 -->
<springProfile name="dev,test">
    <logger name="com.atguigu" level="INFO">
        <appender-ref ref="CONSOLE" />
    </logger>
</springProfile>
<!-- 生产环境 -->
<springProfile name="prod">
    <logger name="com.atguigu" level="ERROR">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </logger>
</springProfile>
```

# **四、滚动日志**

问题：生产环境下，如果系统长时间运行，那么日志文件会变得越来越大，系统读取和写入日志的时间会越来越慢，严重的情况会耗尽系统内存，导致系统宕机。

解决方案：可以设置滚动日志。

## **1、设置时间滚动策略**

RollingFileAppender是Appender的另一个实现，表示滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将旧日志备份到其他文件

`<rollingPolicy>`是`<appender>`的子节点，用来定义滚动策略。

TimeBasedRollingPolicy：最常用的滚动策略，根据时间来制定滚动策略。

`<fileNamePattern>`：包含文件名及转换符， “%d”可以包含指定的时间格式，如：%d{yyyy-MM-dd}。如果直接使用 %d，默认格式是 yyyy-MM-dd。

`<maxHistory>`：可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每个月滚动，且`<maxHistory>`是6，则只保存最近6个月的文件，删除之前的旧文件。注意，删除旧文件是，那些为了归档而创建的目录也会被删除。

```xml
<appender name="ROLLING_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!--  要区别于其他的appender中的文件名字  -->
    <file>${log.path}/log-rolling.log</file>
    <encoder>
        <pattern>${FILE_LOG_PATTERN}</pattern>
        <charset>${ENCODING}</charset>
    </encoder>
    <!-- 设置滚动日志记录的滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- 日志归档路径以及格式 -->
        <fileNamePattern>${log.path}/info/log-rolling-%d{yyyy-MM-dd}.log</fileNamePattern>
        <!--归档日志文件保留的最大数量-->
        <maxHistory>15</maxHistory>
    </rollingPolicy>
</appender>
```

## **2、设置触发滚动时机**

放在`<rollingPolicy>`的子节点的位置，基于实践策略的触发滚动策略

`<maxFileSize>`设置触发滚动条件：单个文件大于100M时生成新的文件

**注意：修改日志文件名** 此时 <fileNamePattern>${log.path}/info/log-rolling-%d{yyyy-MM-dd}**.%i**.log</fileNamePattern>

```xml
<timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
    <maxFileSize>1KB</maxFileSize>
</timeBasedFileNamingAndTriggeringPolicy>
```

# 五、完整的日志配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 日志存放路径 -->
    <!-- <property name="log.path" value="/home/ruoyi/logs" /> -->
    <property name="log.path" value="/Users/yangzhou/log" />
    <!-- 日志输出格式 -->
    <property name="log.pattern" value="%yellow(%date{yyyy-MM-dd HH:mm:ss}) %highlight([%-5level]) %green(%logger) %msg%n" />

    <!-- 控制台输出 -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${log.pattern}</pattern>
        </encoder>
    </appender>

    <!-- 系统日志输出 -->
    <appender name="file_info" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/sys-info.log</file>
        <!-- 循环政策：基于时间创建日志文件 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 日志文件名格式 -->
            <fileNamePattern>${log.path}/sys-info.%d{yyyy-MM-dd}.log</fileNamePattern>
            <!-- 日志最大的历史 60天 -->
            <maxHistory>60</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${log.pattern}</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 过滤的级别 -->
            <level>INFO</level>
            <!-- 匹配时的操作：接收（记录） -->
            <onMatch>ACCEPT</onMatch>
            <!-- 不匹配时的操作：拒绝（不记录） -->
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <appender name="file_error" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/sys-error.log</file>
        <!-- 循环政策：基于时间创建日志文件 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 日志文件名格式 -->
            <fileNamePattern>${log.path}/sys-error.%d{yyyy-MM-dd}.log</fileNamePattern>
            <!-- 日志最大的历史 60天 -->
            <maxHistory>60</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${log.pattern}</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 过滤的级别 -->
            <level>ERROR</level>
            <!-- 匹配时的操作：接收（记录） -->
            <onMatch>ACCEPT</onMatch>
            <!-- 不匹配时的操作：拒绝（不记录） -->
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!-- 用户访问日志输出  -->
    <appender name="sys-user" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/sys-user.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 按天回滚 daily -->
            <fileNamePattern>${log.path}/sys-user.%d{yyyy-MM-dd}.log</fileNamePattern>
            <!-- 日志最大的历史 60天 -->
            <maxHistory>60</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${log.pattern}</pattern>
        </encoder>
    </appender>

    <!-- 系统模块日志级别控制  -->
    <logger name="com.ruoyi" level="info" />
    <!-- Spring日志级别控制  -->
    <logger name="org.springframework" level="warn" />

    <root level="info">
        <appender-ref ref="console" />
    </root>

    <!--系统操作日志-->
    <root level="info">
        <appender-ref ref="file_info" />
        <appender-ref ref="file_error" />
    </root>

    <!--系统用户操作日志-->
    <logger name="sys-user" level="info">
        <appender-ref ref="sys-user"/>
    </logger>
</configuration>
```
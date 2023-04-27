# SpringBoot集成 - redis

---

# 1.初始Redis

Redis是一种键值对的NoSql数据库，这里有两个关键字：

- 键值型
- NoSql

其中**键值型**，是指Redis中存储的数据都是以key、value对的形式存储，而value的形式各种各样，可以是字符串、数值、甚至json：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106758784-e9a14396-1f39-4bdb-b733-ede56e09da43.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106758784-e9a14396-1f39-4bdb-b733-ede56e09da43.png)

而NoSql则是相对于传统关系型数据库而言，有很大差异的一种数据库。

### 1.1 认识NoSql

**NoSql**可以翻译做Not Only Sql(不仅仅是sql)，或者是No Sql（非Sql的）数据库。是相对于传统关系型数据库而言，有很大差异的一种特殊的数据库，因此也称之为**非关系型数据库**。

### 1.1.1 结构化与非结构化

传统关系型数据库是结构化数据，每一张表都有严格的约束信息：字段名、字段数据类型、字段约束等等信息，插入的数据必须遵守这些约束：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106894586-0347fb21-9192-42f3-840a-268d34d32041.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106894586-0347fb21-9192-42f3-840a-268d34d32041.png)

而NoSql则对数据库格式没有严格约束，往往形式松散，自由。

可以是键值型：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106920218-0ddb66fc-3a1d-477b-959d-c8319e0e3d8d.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106920218-0ddb66fc-3a1d-477b-959d-c8319e0e3d8d.png)

也可以是文档型：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106930243-f2dc19e7-4841-4701-8803-828ebfd044fa.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106930243-f2dc19e7-4841-4701-8803-828ebfd044fa.png)

甚至可以是图格式：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106943612-8e30728d-0654-4837-8520-00b103988f2b.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678106943612-8e30728d-0654-4837-8520-00b103988f2b.png)

### 1.1.2 关联和非关联

传统数据库的表与表之间往往存在关联，例如外键：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107100491-de6ccd4d-e6dc-40ce-9621-ed0ac9151ffc.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107100491-de6ccd4d-e6dc-40ce-9621-ed0ac9151ffc.png)

而非关系型数据库不存在关联关系，要维护关系要么靠代码中的业务逻辑，要么靠数据之间的耦合：

```json
{
  id: 1,
  name: "张三",
  orders: [
    {
       id: 1,
       item: {
	 id: 10, title: "荣耀6", price: 4999
       }
    },
    {
       id: 2,
       item: {
	 id: 20, title: "小米11", price: 3999
       }
    }
  ]
}
```

此处要维护“张三”的订单与商品“荣耀”和“小米11”的关系，不得不冗余的将这两个商品保存在张三的订单文档中，不够优雅。还是建议用业务来维护关联关系。

### 1.1.3 查询方式

传统关系型数据库会基于Sql语句做查询，语法有统一标准；

而不同的非关系数据库查询语法差异极大，五花八门各种各样。

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107145280-50c9da5f-8288-4fa5-a73a-ea2fc4a53e7e.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107145280-50c9da5f-8288-4fa5-a73a-ea2fc4a53e7e.png)

### 1.1.4 事务

传统关系型数据库能满足事务ACID的原则。

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107174173-2ce407ba-1b0c-4652-ab6e-4adc37937bc0.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107174173-2ce407ba-1b0c-4652-ab6e-4adc37937bc0.png)

而非关系型数据库往往不支持事务，或者不能严格保证ACID的特性，只能实现基本的一致性。

### 1.1.5 总结

除了上述四点以外，在存储方式、扩展性、查询性能上关系型与非关系型也都有着显著差异，总结如下：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107202174-c7795176-6dcb-44a6-81b6-98c5fc9dd5fa.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678107202174-c7795176-6dcb-44a6-81b6-98c5fc9dd5fa.png)

- 存储方式
- 关系型数据库基于磁盘进行存储，会有大量的磁盘IO，对性能有一定影响
- 非关系型数据库，他们的操作更多的是依赖于内存来操作，内存的读写速度会非常快，性能自然会好一些
- 扩展性
- 关系型数据库集群模式一般是主从，主从数据一致，起到数据备份的作用，称为垂直扩展。
- 非关系型数据库可以将数据拆分，存储在不同机器上，可以保存海量数据，解决内存大小有限的问题。称为水平扩展。
- 关系型数据库因为表之间存在关联关系，如果做水平扩展会给数据查询带来很多麻烦

### 1.2 认识Redis

Redis诞生于2009年全称是**Re**mote  **D**ictionary **S**erver 远程词典服务器，是一个基于内存的键值型NoSQL数据库。

**特征**：

- 键值（key-value）型，value支持多种不同数据结构，功能丰富
- 单线程，每个命令具备原子性
- 低延迟，速度快（基于内存、IO多路复用、良好的编码）。
- 支持数据持久化
- 支持主从集群、分片集群
- 支持多语言客户端

**作者**：Antirez

Redis的官方网站地址：[https://redis.io/](https://redis.io/)

### 1.3.安装Redis

### Mac 通过 homebrew安装Redis数据库

[https://blog.csdn.net/qq_41855420/article/details/103691030](https://blog.csdn.net/qq_41855420/article/details/103691030)

### 1.4.Redis桌面客户端

安装完成Redis，我们就可以操作Redis，实现数据的CRUD了。这需要用到Redis客户端，包括：

- 命令行客户端
- 图形化桌面客户端
- 编程客户端

### 1.4.1.Redis命令行客户端

Redis安装完成后就自带了命令行客户端：redis-cli，使用方式如下：

```
redis-cli [options] [commonds]
```

其中常见的options有：

- `h 127.0.0.1`：指定要连接的redis节点的IP地址，默认是127.0.0.1
- `p 6379`：指定要连接的redis节点的端口，默认是6379
- `a 123321`：指定redis的访问密码

其中的commonds就是Redis的操作命令，例如：

- `ping`：与redis服务端做心跳测试，服务端正常会返回`pong`

不指定commond时，会进入`redis-cli`的交互控制台：

### 1.4.2.图形化桌面客户端

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678108834762-ede7243a-fa98-408b-bc82-4fdea1463e88.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678108834762-ede7243a-fa98-408b-bc82-4fdea1463e88.png)

### 1.4.3.安装如果通过brew或者dmg安装后无法打开，执行下面命令后再启动即可:sudo xattr -rd com.apple.quarantine /Applications/Another\ Redis\ Desktop\ Manager.app

- 通过 **brew**: brew install --cask another-redis-desktop-manager

# 2.Redis常见命令

Redis是典型的key-value数据库，key一般是字符串，而value包含很多不同的数据类型：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678108933387-6f19ae74-3ad4-41bf-9f20-dad511601732.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678108933387-6f19ae74-3ad4-41bf-9f20-dad511601732.png)

Redis为了方便我们学习，将操作不同数据类型的命令也做了分组，在官网（[https://redis.io/commands](https://redis.io/commands)）可以查看到不同的命令：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678108955902-6ad5b8cd-f83b-4044-9c27-146484630640.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678108955902-6ad5b8cd-f83b-4044-9c27-146484630640.png)

不同类型的命令称为一个group，我们也可以通过help命令来查看各种不同group的命令：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678169638071-119237e6-35e0-4f31-89d8-1a45efecb23e.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678169638071-119237e6-35e0-4f31-89d8-1a45efecb23e.png)

接下来，我们就学习常见的五种基本数据类型的相关命令。

### 2.1.Redis通用命令

通用指令是部分数据类型的，都可以使用的指令，常见的有：

- KEYS：查看符合模板的所有key
- DEL：删除一个指定的key
- EXISTS：判断key是否存在
- EXPIRE：给一个key设置有效期，有效期到期时该key会被自动删除
- TTL：查看一个KEY的剩余有效期

通过help [command] 可以查看一个命令的具体用法，例如：

```
# 查看keys命令的帮助信息：
127.0.0.1:6379> help keys

KEYS pattern
summary: Find all keys matching the given pattern
since: 1.0.0
group: generic
```

### 2.2.String类型

String类型，也就是字符串类型，是Redis中最简单的存储类型。

其value是字符串，不过根据字符串的格式不同，又可以分为3类：

- string：普通字符串
- int：整数类型，可以做自增、自减操作
- float：浮点类型，可以做自增、自减操作

不管是哪种格式，底层都是字节数组形式存储，只不过是编码方式不同。字符串类型的最大空间不能超过512m.

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678169676841-ee0b4bef-d3f5-4b2b-af6f-5b41d057eb07.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678169676841-ee0b4bef-d3f5-4b2b-af6f-5b41d057eb07.png)

### 2.2.1.String的常见命令

String的常见命令有：

- SET：添加或者修改已经存在的一个String类型的键值对
- GET：根据key获取String类型的value
- MSET：批量添加多个String类型的键值对
- MGET：根据多个key获取多个String类型的value

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled.png)

- INCR：让一个整型的key自增1
- INCRBY:让一个整型的key自增并指定步长，例如：incrby num 2 让num值自增2
- INCRBYFLOAT：让一个浮点类型的数字自增并指定步长
- SETNX：添加一个String类型的键值对，前提是这个key不存在，否则不执行
- SETEX：添加一个String类型的键值对，并且指定有效期

### 2.2.2.Key结构

Redis没有类似MySQL中的Table的概念，我们该如何区分不同类型的key呢？

例如，需要存储用户、商品信息到redis，有一个用户id是1，有一个商品id恰好也是1，此时如果使用id作为key，那就会冲突了，该怎么办？

我们可以通过给key添加前缀加以区分，不过这个前缀不是随便加的，有一定的规范：

Redis的key允许有多个单词形成层级结构，多个单词之间用':'隔开，格式如下

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%201.png)

这个格式并非固定，也可以根据自己的需求来删除或添加词条。这样以来，我们就可以把不同类型的数据区分开了。从而避免了key的冲突问题。

例如我们的项目名称叫 heima，有user和product两种不同类型的数据，我们可以这样定义key：

- user相关的key：**heima:user:1**
- product相关的key：**heima:product:1**

如果Value是一个Java对象，例如一个User对象，则可以将对象序列化为JSON字符串后存储：

| KEY | VALUE |
| --- | --- |
| heima:user:1 | {"id":1,  "name": "Jack", "age": 21} |
| heima:product:1 | {"id":1,  "name": "小米11", "price": 4999} |

并且，在Redis的桌面客户端中，还会以相同前缀作为层级结构，让数据看起来层次分明，关系清晰：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174142233-f6f846af-b653-4325-9c78-eae5602161da.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174142233-f6f846af-b653-4325-9c78-eae5602161da.png)

### 2.3.Hash类型

Hash类型，也叫散列，其value是一个无序字典，类似于Java中的HashMap结构。

String结构是将对象序列化为JSON字符串后存储，当需要修改对象某个字段时很不方便：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174194851-5d7a2008-ee5d-4679-ac26-27883f457692.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174194851-5d7a2008-ee5d-4679-ac26-27883f457692.png)

Hash结构可以将对象中的每个字段独立存储，可以针对单个字段做CRUD：

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174186902-0312dccd-c3bc-4e7e-afa5-b4af37a0a703.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174186902-0312dccd-c3bc-4e7e-afa5-b4af37a0a703.png)

Hash的常见命令有：

- HSET key field value：添加或者修改hash类型key的field的值
- HGET key field：获取一个hash类型key的field的值
- HMSET：批量添加多个hash类型key的field的值
- HMGET：批量获取多个hash类型key的field的值

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%202.png)

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%203.png)

- HGETALL：获取一个hash类型的key中的所有的field和value
- HKEYS：获取一个hash类型的key中的所有的field
- HINCRBY:让一个hash类型key的字段值自增并指定步长
- HSETNX：添加一个hash类型的key的field值，前提是这个field不存在，否则不执行

### 2.4.List类型

Redis中的List类型与Java中的LinkedList类似，可以看做是一个双向链表结构。既可以支持正向检索和也可以支持反向检索。

特征也与LinkedList类似：

- 有序
- 元素可以重复
- 插入和删除快
- 查询速度一般

常用来存储一个有序数据，例如：朋友圈点赞列表，评论列表等。

List的常见命令有：

- LPUSH key element ... ：向列表左侧插入一个或多个元素
- LPOP key：移除并返回列表左侧的第一个元素，没有则返回nil
- RPUSH key element ... ：向列表右侧插入一个或多个元素

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%204.png)

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%205.png)

- RPOP key：移除并返回列表右侧的第一个元素
- LRANGE key star end：返回一段角标范围内的所有元素
- BLPOP和BRPOP：与LPOP和RPOP类似，只不过在没有元素时等待指定时间，而不是直接返回nil

### 2.5.Set类型

Redis的Set结构与Java中的HashSet类似，可以看做是一个value为null的HashMap。因为也是一个hash表，因此具备与HashSet类似的特征：

- 无序
- 元素不可重复
- 查找快
- 支持交集、并集、差集等功能

Set的常见命令有：

- SADD key member ... ：向set中添加一个或多个元素
- SREM key member ... : 移除set中的指定元素
- SCARD key： 返回set中元素的个数
- SISMEMBER key member：判断一个元素是否存在于set中
- SMEMBERS：获取set中的所有元素

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%206.png)

- SINTER key1 key2 ... ：求key1与key2的交集

例如两个集合：s1和s2:

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174212078-18d4d196-38a5-44f3-ad5f-bf8f899a3419.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174212078-18d4d196-38a5-44f3-ad5f-bf8f899a3419.png)

求交集：SINTER s1 s2

求s1与s2的不同：SDIFF s1 s2

![https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174221682-81fc44ba-3356-42c5-be74-04eadee4baf5.png](https://cdn.nlark.com/yuque/0/2023/png/12865830/1678174221682-81fc44ba-3356-42c5-be74-04eadee4baf5.png)

练习：

1. 将下列数据用Redis的Set集合来存储：
- 张三的好友有：李四、王五、赵六
- 李四的好友有：王五、麻子、二狗
1. 利用Set的命令实现下列功能：
- 计算张三的好友有几人
- 计算张三和李四有哪些共同好友
- 查询哪些人是张三的好友却不是李四的好友
- 查询张三和李四的好友总共有哪些人
- 判断李四是否是张三的好友
- 判断张三是否是李四的好友
- 将李四从张三的好友列表中移除

### 2.6.SortedSet类型

Redis的SortedSet是一个可排序的set集合，与Java中的TreeSet有些类似，但底层数据结构却差别很大。SortedSet中的每一个元素都带有一个score属性，可以基于score属性对元素排序，底层的实现是一个跳表（SkipList）加 hash表。

SortedSet具备下列特性：

- 可排序
- 元素不重复
- 查询速度快

因为SortedSet的可排序特性，经常被用来实现排行榜这样的功能。

SortedSet的常见命令有：

- ZADD key score member：添加一个或多个元素到sorted set ，如果已经存在则更新其score值
- ZREM key member：删除sorted set中的一个指定元素
- ZSCORE key member : 获取sorted set中的指定元素的score值
- ZRANK key member：获取sorted set 中的指定元素的排名
- ZCARD key：获取sorted set中的元素个数
- ZCOUNT key min max：统计score值在给定范围内的所有元素的个数
- ZINCRBY key increment member：让sorted set中的指定元素自增，步长为指定的increment值
- ZRANGE key min max：按照score排序后，获取指定排名范围内的元素
- ZRANGEBYSCORE key min max：按照score排序后，获取指定score范围内的元素
- ZDIFF、ZINTER、ZUNION：求差集、交集、并集

注意：所有的排名默认都是升序，如果要降序则在命令的Z后面添加REV即可，例如：

- **升序**获取sorted set 中的指定元素的排名：ZRANK key member
- **降序**获取sorted set 中的指定元素的排名：ZREVRANK key memeber

# 3、SpringBoot整合redis

SpringData是Spring中数据操作的模块，包含对各种数据库的集成，其中对Redis的集成模块就叫做SpringDataRedis，官网地址：[https://spring.io/projects/spring-data-redis](https://spring.io/projects/spring-data-redis)

- 提供了对不同Redis客户端的整合（Lettuce和Jedis）
- 提供了RedisTemplate统一API来操作Redis
- 支持Redis的发布订阅模型
- 支持Redis哨兵和Redis集群
- 支持基于Lettuce的响应式编程
- 支持基于JDK、JSON、字符串、Spring对象的数据序列化及反序列化
- 支持基于Redis的JDKCollection实现

SpringDataRedis中提供了RedisTemplate工具类，其中封装了各种对Redis的操作。并且将不同数据类型的操作API封装到了不同的类型中：

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%207.png)

## 3.1、快速入门

SpringBoot已经提供了对SpringDataRedis的支持，使用非常简单。

首先，新建一个maven项目，然后按照下面步骤执行：

**1）引入依赖**

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.8.16</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.fastjson2</groupId>
            <artifactId>fastjson2</artifactId>
            <version>2.0.27</version>
        </dependency>
    </dependencies>
```

**2）配置Redis**

```xml
spring:
  redis:
    host: localhost
    port: 6379
    password:
    lettuce:
      pool:
        max-active: 8 # 最大连接
        max-idle: 8   # 最大空闲连接
        min-idle: 0   # 最小空闲连接
        max-wait: 100 # 连接等待时间
```

**3）注入**stringRedisTemplate

因为有了SpringBoot的自动装配，我们可以拿来就用：

```xml
@Resource
private StringRedisTemplate stringRedisTemplate;
```

**4）编写测试（**写入一条String数据**）**

```java
		@Test
    void contextLoads() {
        // 插入键-name 值-yz
        stringRedisTemplate.opsForValue().set("name", "yz");
        // 获取值，根据键name获取
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
```

**5)执行结果**

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%208.png)

**6）编写测试（写入单个Object）**

```java
@Test
    void testObject(){
        User user = new User();
        user.setName("John");
        user.setSex("男");
        // 1、使用fastjson的序列化，将user对象转换为序列化，存入redis，stringRedisTemplate key 和value都为string类型，所以需要序列化
        String jsonString = JSON.toJSONString(user);
        stringRedisTemplate.opsForValue().set("user:object", jsonString);
        String jsonResult = stringRedisTemplate.opsForValue().get("user:object");
        // 2、反序列化
        User parsedObject = JSON.parseObject(jsonResult, User.class);
        System.out.println("parsedObject = " + parsedObject);
    }
```

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%209.png)

**7)执行结果**

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2010.png)

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2011.png)

8**）编写测试（写入List）**

```java
@Test
    void testInsertArray(){

        List<User> users = userMapper.selectList(null);
        String json = JSON.toJSONString(users);
        stringRedisTemplate.opsForValue().set("user:array",json);

        String user = stringRedisTemplate.opsForValue().get("user:array");
        System.out.println("user = " + user);
        List<User> parsedArray = JSON.parseArray(user, User.class);
        System.out.println("parsedObject = " + parsedArray);

    }
```

**9)执行结果**

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2012.png)

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2013.png)

# 4、SpringBoot整合redis实践-手机号验证码

## 4.1、导入依赖并配置redis

### 4.1.1 导入依赖

```java
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.8.16</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.fastjson2</groupId>
            <artifactId>fastjson2</artifactId>
            <version>2.0.27</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.2</version>
        </dependency>

        <!-- Swagger3依赖 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.github.xiaoymin/knife4j-spring-boot-starter -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>3.0.3</version>
        </dependency>

    </dependencies>
```

### 4.1.2 配置redis

```java
server:
  port: 8888
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot?characterEncoding=utf-8
    username: root
    password: 12345678
  jackson:
    default-property-inclusion: non_null # JSON处理时忽略非空字段
  redis:
    host: localhost
    password:
    lettuce:
      pool:
        max-active: 8 # 最大连接
        max-idle: 8   # 最大空闲连接
        min-idle: 0   # 最小空闲连接
        max-wait: 100 # 连接等待时间

#========Mybatis-plus============
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl   #查看日志

logging:
  level:
    com.yz: debug
```

## 4.2、创建实体类以及

`User、UserDTO、LoginFormDTO`

```java
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 电话
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

@Data
public class UserDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 电话
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;
}

@Data
public class LoginFormDTO {

    /**
     * 电话
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
}
```

## 4.3 service

### 4.3.1 发送验证码

```java
public interface UserService extends IService<User> {

    Result sendCode(String phone);
}
```

### 4.3.2 登录

```java
public interface UserService extends IService<User> {

    Result login(LoginFormDTO loginFormDTO);
}
```

## 4.4 serviceImpl

### 4.4.1 发送验证码

```java

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result sendCode(String phone) {
        // 1、验证手机号
        if (RegexUtils.isPhoneInvalid(phone)){
            // 2、如果不符合，返回错误消息
            return Result.fail().message("手机号格式错误");
        }

        // 3、符合，生成验证码(使用Hutool工具生成6为随机数)
        String code = RandomUtil.randomNumbers(6);

        // 4、保存验证码到redis
        // key + code + time
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone , code , LOGIN_CODE_TTL ,TimeUnit.MINUTES);
        // 5、发送验证码(可使用阿里云等第三方发送给用户，此处模拟就用log打印在控制台)
        log.debug("code="+ code);

        // 返回ok
        return Result.ok();
    }

}
```

### 4.4.2 登录

```java

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Result login(LoginFormDTO loginFormDTO) {
        //1、验证手机号
        String phone = loginFormDTO.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)){
            return Result.fail().message("手机号格式错误");
        }
        //2、校验验证码
        String code = loginFormDTO.getCode();
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        if (cacheCode == null || !cacheCode.equals(code)) {
            //3、不一致，报错
            return Result.fail().message("验证码错误");
        }
        //4、一致，根据手机号查询用户
        User user = query().eq("phone", phone).one();
        //5、判断用户是否存在
        if (user == null) {
            //6、不存在，创建用户
            user = createUserWithPhone(phone);
        }

        //7、保存用户信息到 redis
        //7.1、随机生成token令牌
        String token = UUID.randomUUID().toString(true);

        //7.2、将user对象转为Hash存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        // 对象转为map
        String string = JSON.toJSONString(userDTO);
        //7.3、存储
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForValue().set(tokenKey, string,LOGIN_USER_TTL,TimeUnit.MINUTES);

        String userObject = stringRedisTemplate.opsForValue().get(tokenKey);
        User parsedObject = JSON.parseObject(userObject, User.class);
        return Result.ok(parsedObject);
    }

    private User createUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName("user_" + RandomUtil.randomString(10));
        // 保存用户
        save(user);
        return user;
    }

}
```

## 4.5 controller

### 4.5.1 发送验证码

```java
@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("code")
    public Result code(@RequestParam("phone") String phone){
        // TODO 发送短信验证码并保存验证码
        return userService.sendCode(phone);
    }
}
```

### 4.5.2 登录

```java
@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO){

        return userService.login(loginFormDTO);
    }
}
```

## 4.6 需要用到的工具

- RedisConstants：redis常量

```java
package com.yz.redis.utils;

/**
 * redis常量
 *
 * @author yangzhou
 * @date 2023/04/27
 */
public class RedisConstants {

    /**
     * redis中验证码的key
     */
    public static final String LOGIN_CODE_KEY = "login:code:";
    /**
     * redis中验证码有效期
     */
    public static final Long LOGIN_CODE_TTL = 2L;
    /**
     * redis中用户信息的key
     */
    public static final String LOGIN_USER_KEY = "login:token:";
    /**
     * redis中用户信息的有效期
     */
    public static final Long LOGIN_USER_TTL = 36000L;
}
```

- RegexUtils、RegexPatterns：手机号验证

```java

public class RegexUtils {

    /**
     * 是否是无效手机格式
     * @param phone 要校验的手机号
     * @return true:符合，false：不符合
     */
    public static boolean isPhoneInvalid(String phone){
        return mismatch(phone, RegexPatterns.PHONE_REGEX);
    }

    /**
     * 是否是无效邮箱格式
     * @param email 要校验的邮箱
     * @return true:符合，false：不符合
     */
    public static boolean isEmailInvalid(String email){
        return mismatch(email, RegexPatterns.EMAIL_REGEX);
    }

    /**
     * 是否是无效验证码格式
     * @param code 要校验的验证码
     * @return true:符合，false：不符合
     */
    public static boolean isCodeInvalid(String code){
        return mismatch(code, RegexPatterns.VERIFY_CODE_REGEX);
    }

    // 校验是否不符合正则格式
    private static boolean mismatch(String str, String regex){
        if (StrUtil.isBlank(str)) {
            return true;
        }
        return !str.matches(regex);
    }
}

public abstract class RegexPatterns {
    /**
     * 手机号正则
     */
    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     * 邮箱正则
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 密码正则。4~32位的字母、数字、下划线
     */
    public static final String PASSWORD_REGEX = "^\\w{4,32}$";
    /**
     * 验证码正则, 6位数字或字母
     */
    public static final String VERIFY_CODE_REGEX = "^[a-zA-Z\\d]{6}$";

}
```

## 4.7 测试（主要查看redis）

测试发送验证码接口：

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2014.png)

查看redis：

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2015.png)

测试登录接口：

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2016.png)

查看redis：

![Untitled](SpringBoot%E9%9B%86%E6%88%90%20-%20redis%20a73e4db1b0504b62abfe4acd8f16d3ae/Untitled%2017.png)
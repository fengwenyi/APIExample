<h1 align="center">
    Api Example
</h1>

<p align="center">
    <strong>以API接口开发为例，探索开发的最佳实践</strong>
</p>

## 分支列表

| 分支名称 | 说明 |
| --- | --- |
| api-result-sample | api-result结合Spring Boot使用实例 |

## 默认启用端口

9191

## 功能

- [x] 包结构规划最佳实践

- [x] API接口返回结果封装最佳解决方案

- [x] 使用Swagger实现漂亮、简洁、大方API接口文档

- [x] Swagger添加请求头

- [x] RESTful API规范接口实例（2020.4.12）

- [ ] Swagger添加接口认证

- [ ] MySQL数据库操作实例

- [ ] MongoDB操作实例

- [ ] Redis操作实例

- [ ] Kafka操作实例

- [ ] RabbitMQ操作实例

- [ ] Elasticsearch操作实例


## 项目目录结构

```
APIExample
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── fengwenyi
│   │   │           └── api_example
│   │   │               ├── bean
│   │   │               │   └── PageResultDataBean
│   │   │               ├── config
│   │   │               │   └── Swagger2Config
│   │   │               ├── controller
│   │   │               │   ├── ApiTestResultController
│   │   │               │   └── ApiUserController
│   │   │               ├── data
│   │   │               │   └── UserData
│   │   │               ├── entity
│   │   │               │   └── UserEntity
│   │   │               ├── exceptions
│   │   │               │   └── DataParseException
│   │   │               ├── handler
│   │   │               │   └── GlobalExceptionHandler
│   │   │               ├── util
│   │   │               │   ├── ApiResultUtils
│   │   │               │   ├── PageResultUtils
│   │   │               │   └── ResultUtils
│   │   │               ├── vo
│   │   │               │   └── param
│   │   │               │       ├── UserFilterParamVO
│   │   │               │       └── UserParamVO
│   │   │               └── ApiExampleApplication
│   │   └── resource
│   │       ├── application.yml
│   │       └── banner.txt
│   └── test
│       └── java
│           └── com
│               └── fengwenyi
│                   └── api_example
│                       └── ApiExampleApplicationTests
└── pom.xml
```

## 包

`controller`

`business`

`service`

## 依赖

`默认错误页面`

```xml
<dependency>
    <groupId>com.github.iutil</groupId>
    <artifactId>app-boot-starter</artifactId>
    <version>${app-boot-starter.version}</version>
</dependency>
```

`API接口结果处理解决方案`

```xml
<dependency>
    <groupId>com.github.iutil</groupId>
    <artifactId>api-result</artifactId>
    <version>${api-result.version}</version>
</dependency>
```

`常用工具类`

```xml
<dependency>
    <groupId>com.github.iutil</groupId>
    <artifactId>JavaLib</artifactId>
    <version>${JavaLib.version}</version>
</dependency>
```

`接口文档：Swagger`

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

## 测试接口示例

控制台打印

```
[2019-06-05 10:17:02.516]  api-test -> get
[2019-06-05 10:17:12.900]  api-test -> add
[2019-06-05 10:17:24.037]  api-test -> update
[2019-06-05 10:17:36.409]  api-test -> delete
```

## 包结构

```
controller -> business -> service -> dbBusiness -> dbService
```

## 关于DB

```
dbService 只能由 dbBusiness调用

在 dbService 里处理所有与数据库相关的异常

查询由 find 开头
    携带All：查询所有的数据
    携带Use：查询没有被逻辑删除，且已经发布的数据
    携带Normal：查询没有被逻辑删除的数据
```

## RESTful API

### HTTP动词

对于资源的具体操作类型，由HTTP动词表示。
    
常用的HTTP动词有下面五个（括号里是对应的SQL命令）。
    
```
GET（SELECT）：从服务器取出资源（一项或多项）。
POST（CREATE）：在服务器新建一个资源。
PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
DELETE（DELETE）：从服务器删除资源。
```

还有两个不常用的HTTP动词。

```
HEAD：获取资源的元数据。
OPTIONS：获取信息，关于资源的哪些属性是客户端可以改变的。
```

下面是一些例子。

```
GET /zoos：列出所有动物园
POST /zoos：新建一个动物园
GET /zoos/ID：获取某个指定动物园的信息
PUT /zoos/ID：更新某个指定动物园的信息（提供该动物园的全部信息）
PATCH /zoos/ID：更新某个指定动物园的信息（提供该动物园的部分信息）
DELETE /zoos/ID：删除某个动物园
GET /zoos/ID/animals：列出某个指定动物园的所有动物
DELETE /zoos/ID/animals/ID：删除某个指定动物园的指定动物
```

### 过滤信息（Filtering）
    
如果记录数量很多，服务器不可能都将它们返回给用户。API应该提供参数，过滤返回结果。
    
下面是一些常见的参数。
    
```
?limit=10：指定返回记录的数量
?offset=10：指定返回记录的开始位置。
?page=2&per_page=100：指定第几页，以及每页的记录数。
?sortby=name&order=asc：指定返回结果按照哪个属性排序，以及排序顺序。
?animal_type_id=1：指定筛选条件
```

参数的设计允许存在冗余，即允许API路径和URL参数偶尔有重复。比如，`GET /zoo/ID/animals` 与 `GET /animals?zoo_id=ID` 的含义是相同的。


### 错误处理

如果状态码是4xx，就应该向用户返回出错信息。一般来说，返回的信息中将error作为键名，出错信息作为键值即可。

```
{
    error: "Invalid API key"
}
```

### 返回结果

针对不同操作，服务器向用户返回的结果应该符合以下规范。
    
```
GET /collection：返回资源对象的列表（数组）
GET /collection/resource：返回单个资源对象
POST /collection：返回新生成的资源对象
PUT /collection/resource：返回完整的资源对象
PATCH /collection/resource：返回完整的资源对象
DELETE /collection/resource：返回一个空文档
```

2020.5.11 规划

```
api-example
    api-web
    api-webfluex
    api-swagger
```


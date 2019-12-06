<h1 align="center">
    Api Example
</h1>

<p align="center">
    <strong>以API接口开发为例，探索开发的最佳实践</strong>
</p>

## 默认启用端口

9191

## 功能

- [x] 包结构规划最佳实践

- [x] API接口返回结果封装最佳解决方案

- [x] 使用Swagger实现漂亮、简洁、大方API接口文档

- [x] Swagger添加请求头

- [ ] Swagger添加接口认证

- [ ] MySQL数据库操作实例

- [ ] MongoDB操作实例

- [ ] Redis操作实例

- [ ] Kafka操作实例

- [ ] RabbitMQ操作实例

- [ ] Elasticsearch操作实例


## 目录

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
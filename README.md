## 项目介绍

一个提供API接口供开发者调用的平台

管理员可以在后台对接口进行上线、下线、管理、分析等操作，以及接口调用情况的可视化

普通用户在前台可以浏览接口信息，在注册登录后开通想要调用的接口获取使用次数进行调用

提供有SDK包帮助开发者更方便的调用API接口服务

使用Dubbo进行服务之间的通信，因为项目暂时业务并不复杂，所以核心业务暂未拆分为多服务，统一放在core模块里

| 目录                  | 描述                                                     |
| --------------------- | -------------------------------------------------------- |
| WebOpenApi-core       | 核心业务模块，鉴权、接口处理、接口调用等核心功能都在这里 |
| WebOpenApi-gateway    | 网关模块，负责对core传过来的调用请求进行过滤和转发等操作 |
| WebOpenApi-common     | 项目公共模块，定义了rpc接口，提供服务之间的通信          |
| WebOpenApi-interface  | 模拟接口，真正提供接口服务的地方                         |
| WebOpenApi-frontend   | 前端项目，使用Ant Design Pro脚手架搭建                   |
| WebOpenApi-client-sdk | SDK工具包，提供给开发者使用                              |



> 在线体验：[https://openapi.zhnblog.icu](https://openapi.zhnblog.icu)

## 业务流程

![image-20230721230331445](https://github.com/newzhn/WebOpenApi/blob/master/doc/image6.png)



## 技术栈

前端：

- 开发框架：React、Umi
- 脚手架：Ant Design Pro
- 组件库：Ant Design、Ant Design Components
- 语法扩展：TypeScript、Less
- 打包工具：Webpack
- 代码规范：ESLint、StyleLint、Prettier

后端：

+ 主语言：Java8
+ 开发框架：SpringBoot、Dubbo、Mybatis-plus
+ 网关框架：Spring Cloud Gateway
+ 服务注册：Nacos
+ 数据库：Mysql
+ 中间件：Redis



## 快速上手

### 前端

环境要求：Node.js >= 14

安装依赖：

```
yarn
```

启动：

```
yarn dev
```

部署：

```
yarn build
```

执行命令后会得到 dist 目录，可以放到自己的 web 服务器指定的路径；也可以使用 Docker 容器部署，将 dist、Dockerfile、docker 目录（文件）一起打包即可。

### 后端

首先要在本地运行一个nacos服务，然后在`WebOpenApi-backend`根目录下创建`.env.dev`和`.env`两个环境配置文件

前者是开发环境下环境配置，后者是线上环境配置

`.env.dev`配置参考如下：

```properties
MYSQL_DATABASE=数据库名
MYSQL_USERNAME=数据库登录账户
MYSQL_PASSWORD=数据库密码
REDIS_DATABASE=1
REDIS_PASSWORD=Redis密码
EMAIL_HOST=smtp.qq.com
EMAIL_USERNAME=发送者邮箱
EMAIL_PASSWORD=邮箱密钥
NACOS_ADDRESS=nacos地址，端口默认是8848
```

`.env`配置参考如下：

```properties
MYSQL_HOST=数据库地址
MYSQL_PORT=数据库端口
MYSQL_DATABASE=数据库名
MYSQL_USERNAME=数据库登录账户
MYSQL_PASSWORD=数据库密码
REDIS_DATABASE=1
REDIS_HOST=Redis地址
REDIS_PORT=Redis端口
REDIS_PASSWORD=Redis密码
EMAIL_HOST=smtp.qq.com
EMAIL_USERNAME=发送者邮箱
EMAIL_PASSWORD=邮箱密钥
NACOS_ADDRESS=nacos地址，端口默认是8848
```

上述都配置完之后就可以使用maven导入依赖运行项目了

需要注意的是如果打算注册使用邮箱验证码需要自己选择对应邮箱服务处申请密钥（默认注册未开启邮箱验证码）



## SDK使用

### 说明

提供给开发者在代码层面实现远程调用平台所提供api的能力

除了基本的api客户端，还提供了一些封装好的工具类，便于开发者更好的解析调用结果



### 环境要求

JDK版本要在8以上

SpringBoot版本要求2.x



### Maven安装

```xml
<dependency>
    <groupId>io.github.newzhn</groupId>
    <artifactId>WebOpenApi-client-sdk</artifactId>
    <version>1.0.2</version>
</dependency>
```



### 代码示例

依赖成功引入后，需要在`application.yml`配置文件中进行相关配置

```yml
# 开发者签名认证
web-open-api:
  client:
    access-key: #在WebOpenApi平台个人中心中查看和申请
    secret-key: #在WebOpenApi平台个人中心中查看和申请
```

配置完成后，就可以启动项目使用客户端去调用接口

```java
@SpringBootTest
class SdkTestApplicationTests {
    @Autowired
    ApiClientFacade apiClientFacade;

    @Test
    void test() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name","张三");
        String result = apiClientFacade.invoke("GET", "/api/test/get/{name}", paramMap);
        Map<String, Object> resultMap = JsonUtil.toMap(result);
        System.out.println(resultMap);
    }
}
```

上述就是使用SDK调用接口的一个完整示例，在需要的地方直接引入客户端`ApiClientFacade`，调用invoke方法即可

方法中的三个参数都需要去参照Web平台对应接口的说明文档输入才能保证执行成功

传入参数和返回结果都是`HashMap<String, Object>`这样的一个结构，传入参数参照接口文档，返回结果使用sdk提供的`JsonUtil.toMap(result)`进行转换

返回参数的统一结构：

```json
{
  "code": "响应码",
  "msg": "响应消息",
  "data": "响应结果"
}
```



## 项目展示

![image-20230721225326627](https://github.com/newzhn/WebOpenApi/blob/master/doc/image1.png)

![image-20230721225428031](https://github.com/newzhn/WebOpenApi/blob/master/doc/image2.png)

![image-20230721225509948](https://github.com/newzhn/WebOpenApi/blob/master/doc/image3.png)

![image-20230721225545856](https://github.com/newzhn/WebOpenApi/blob/master/doc/image4.png)

![image-20230721225605155](https://github.com/newzhn/WebOpenApi/blob/master/doc/image5.png)
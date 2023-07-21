## 项目架构

使用Dubbo进行服务之间的通信，因为项目暂时业务并不复杂，所以核心业务暂未拆分，统一放在core模块里

| 目录                 | 描述                                                     |
| -------------------- | -------------------------------------------------------- |
| WebOpenApi-core      | 项目核心代码，鉴权、接口处理、接口调用等核心功能都在这里 |
| WebOpenApi-gateway   | 项目网关，负责对core传过来的调用请求进行过滤和转发等操作 |
| WebOpenApi-common    | 项目公共模块，定义了rpc接口，提供服务之间的通信          |
| WebOpenApi-interface | 项目模拟接口，真正提供接口服务的地方                     |



## 项目启动

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


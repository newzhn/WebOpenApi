## SDK说明

------

提供给开发者在代码层面实现远程调用平台所提供api的能力

除了基本的api客户端，还提供了一些封装好的工具类，便于开发者更好的解析调用结果



## 环境要求

------

JDK版本要在8以上

SpringBoot版本要求2.x



## Maven安装

------

```xml
<!-- https://mvnrepository.com/artifact/io.github.newzhn/WebOpenApi-client-sdk -->
<dependency>
    <groupId>io.github.newzhn</groupId>
    <artifactId>WebOpenApi-client-sdk</artifactId>
    <version>1.0.2</version>
</dependency>
```



## 代码示例

------

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


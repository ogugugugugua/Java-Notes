# SpringCloud

## 简要介绍

版本要求

|     组件      |    Version    |
| :-----------: | :-----------: |
|  Spring boot  | 2.2.2.RELEASE |
| cloud alibaba | 2.1.0.RELEASE |
|     Java      |     Java8     |
|     Maven     |     >=3.5     |
|     Mysql     |     >=5.7     |
| Spring cloud  |  Hoxton.SR1   |

---

相关组件：

|              |                                                  |
| :----------- | :----------------------------------------------: |
| 服务注册中心 |          Zookeeper；Consul；Nacos(推荐)          |
| 服务调用     |              Ribbon；LoadBalancer；              |
| 服务调用2    |                    OpenFeign                     |
| 服务降级熔断 | Resilience4j(国外多)；Alibaba Sentinel(国内多)； |
| 服务网关     |                     gateway                      |
| 服务配置     |                apolo；Nacos(推荐)                |
| 服务总线     |                      Nacos                       |

- 约定>配置>编码

- Maven中Dependency和DependencyManagement的区别：

  DependencyManagement元素提供一种管理依赖版本号的方式，一般DependencyManagement用于最顶层的父工程POM，能让所有的子项目中引用一个依赖而**不用显式列出版本号**。也方便一致性修改。

  但要注意：DependencyManagement只是声明依赖，而**不实现引入**，因此子项目需要显式的声明需要用的依赖。
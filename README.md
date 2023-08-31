## 项目介绍
一个基于事件发布与订阅的轻量级框架，分别使用两种不同的方式去实现：
1. crab4j-event-toolkit：无任何外部依赖的轻量工具，以 toolkit 包的形式提供服务
2. crab4j-spring-boot-starter：充分利用 Spring 框架特性，以 starter 包的形式提供服务

### 项目背景
在工作中，当一个事件的产生，需要去触发很多事件时，我们通常会在事件产生方中分别的去调用那些事件消费方，这样往往是很浪费资源的，
并且事件的产生方与事件的消费方会产生极大的耦合。如果我们要改动某一个事件的消费方，很有可能还要改动事件的产生方，为了解决这个问题，我就编写了 Crab4J 这个
框架。

### 使用场景
在同一个 JVM 中使用异步的方式来发送事件或者触发另外一个动作时，就可以使用 Crab4J 框架。

## 核心功能
- 事件注册
- 事件发布
- 事件订阅
- 事件注销
- 事件广播
- 事件分组

## 项目架构
![crab4j-event-toolkit](docs/imgs/Crab4J.png)

## 许可
Crab4J 基于 [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0) 许可证。

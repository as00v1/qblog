# 一、什么是qblog？
简单来说，qblog是一套博客系统的后端接口集合。

qblog意图打造一个轻量级、松耦合的博客后端接口集合，按照基本的博客业务思路，参照在线接口文档，实现前端展示层即可完成一套属于自己的博客系统。

并且，简化技术开发、部署也是qblog前进的方向，尽最大可能使开发者专注业务逻辑的实现。

# 二、包含模块
* 通用模块
* 账户模块
* 博客模块
* 统计模块

为了尽可能降低模块耦合度，qblog的目标在于项目中每个模块都可以独立拆分、单独运行。

比如，你可以只使用qblog的账户模块来进行账户管理，从而基于账户模块去开发额外的业务；
或者你可以单独使用博客模块来实现一个私有化的博客系统；
甚至你可以将统计模块作为任意系统的页面分析插件。

# 三、如何使用

## 接口文档

<https://www.qiaohx.com/qblog/swagger-ui.html>
## druid监控：

<https://www.qiaohx.com/qblog/druid>

账户：``jiayong``  
密码：``12345678``

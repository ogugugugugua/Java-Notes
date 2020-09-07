# 单页应用SPA

## 构建Web应用程序方式

目前可通过**两种**通用方法来构建 Web 应用程序：

- 在服务器上执行大部分应用程序逻辑的传统 Web 应用程序
- 在 Web 浏览器中执行大部分用户界面逻辑的单页应用程序 (SPA)
- 后者主要使用 Web API 与 Web 服务器通信。 也可以将两种方法混合使用，最简单的方法是在更大型的传统 Web 应用程序中托管一个或多个丰富 SPA 类子应用程序。

何时使用传统 Web 应用程序：

- 应用程序的客户端要求简单，甚至要求只读。
- 应用程序需在不支持 JavaScript 的浏览器中工作。
- 团队不熟悉 JavaScript 或 TypeScript 开发技术。

何时使用 SPA：

- 应用程序必须公开具有许多功能的丰富用户界面。
- 团队熟悉 JavaScript 和/或 TypeScript 开发。
- 应用程序已为其他（内部或公共）客户端公开 API。
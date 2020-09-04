# SpringMVC

一些相互独立的内容：展示给用户的视图View；控制器返回的数据模型Model；定位视图的视图解析器ViewResolver；处理适配器HandlerAdapter，等等。

流程和组件是SpringMVC的核心，流程是围绕DispatcherServlet工作的，所以其实最重要的内容。

## 流程

HTTP Request请求 ---> DispatcherServlet ---> HandlerMapping处理器映射(定位控制器响应方法) ---> HandlerExecutionChain处理器执行链(包含处理器及其拦截器) ---> DispatcherServlet ---> HandlerAdapter处理器适配器(运行处理器) ---> ModelAndView模型与视图(用于获取视图和数据模型) ---> DispatcherServlet ---> ViewResolver视图解析器(定位视图资源) ---> DispatcherServlet ---> View视图(将数据模型渲染展示)

不过并不一定要经过全流程，例如当加入@ResponseBody时，是没有经过ViewResolver视图解析器和View视图渲染的。


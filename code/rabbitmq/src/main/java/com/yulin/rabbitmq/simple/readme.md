### 简单模式

> 简单模式是最简单的消息模式，它包含一个生产者、一个消费者和一个队列。生产者向队列里发送消息，消费者从队列中获取消息并消费。

![](https://mmbiz.qpic.cn/mmbiz_png/CKvMdchsUwnv70NjgrxuUOeAzNicr0UmT1ELp5BFIYBmOPElmfr8B6AogW6QF7cLLMc0fn1PO1jLe4GTV4htXicw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

- SimpleRabbitConfig中添加`简单模式`相关Java配置，创建一个名为`simple.hello`的队列、一个生产者和一个消费者

- 生产者通过`send方法`向队列`simple.hello`中发送消息

- 消费者从队列`simple.hello`中获取消息

- 在controller中添加测试接口，调用该接口开始发送消息

  ---

- 运行后结果如下，可以发现生产者往队列中发送消息，消费者从队列中获取消息并消费
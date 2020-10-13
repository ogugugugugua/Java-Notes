### 工作模式

> 工作模式是指向多个互相竞争的消费者发送消息的模式，它包含一个生产者、两个消费者和一个队列。两个消费者同时绑定到一个队列上去，当消费者获取消息处理耗时任务时，空闲的消费者从队列中获取并消费消息。

![](https://mmbiz.qpic.cn/mmbiz_png/CKvMdchsUwnv70NjgrxuUOeAzNicr0UmTMAiaUsehmU2DMsbybDzIytME2lhUZNtNeb5vJhOZuuyw5Vx7WZib1Smg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

- 在WorkRabbitConfig 中 添加`工作模式`相关Java配置，创建一个名为`work`的队列、一个生产者和两个消费者

- 生产者通过`send方法`向队列`work`中发送消息，包含不同数目的字符

- 两个消费者从队列`work`中获取消息，名称分别为`instance 1`和`instance 2`，消息中包含字符数目越多，耗时越长

- 在controller中添加测试接口，调用该接口开始发送消息

  ---

  运行后结果如下，可以发现生产者往队列中发送包含不同数量`.`号的消息，`instance 1`和`instance 2`消费者互相竞争，分别消费了一部分消息
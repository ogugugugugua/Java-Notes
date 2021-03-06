# MySQL调优

`show profiles`语句可以查看过去多条历史记录的执行时间。

`show profile; `可以查看历史记录中最后一个语句在执行过程中各个具体步骤所用的时间，便于后面的优化。具体的[文档](https://dev.mysql.com/doc/refman/5.6/en/show-profile.html)可以在官网找到。

也可以使用 `show profile for query X`的方式制定查询历史记录中的第几条语句的执行具体情况。

在新版中建议使用更强大的Performance Schema功能。"It is a feature for monitoring MySQL Server execution at a low level"。收集到的事件数据存储在performance_schema数据库的表中。这些数据不会持久化存储到磁盘中，而是保存在内存里。

`show create table setup_consumers;`可以查看建表语句对应的表结构。

两个需要理解的概念：

- instruments可以理解为生产者；用于采集mysql中各种的操作产生的事件信息，对应配置表中的配置项我们可以成为监控采集配置项。
- consumers可以理解为消费者；对应的消费者表用于存储来自instruments采集的数据，对应配置表中的配置项我们可以成为消费存储配置项。

相关的Performance Schema具体使用方法可以参照老师写的文档。其更加详尽，自然也会更加复杂。

---

show processlist可以查看链接的线程个数，来观察是否有大量线程处于不正常的状态或者具有其他不正常的特征。

数据类型尽量选择占空间比较小的。

char, varchar, text, blob：一般用varchar，是可变长度的。

datetime, timestamp, date：

- datetime精确到毫秒，范围1000-9999年，占用位置最大。
- timestamp精确到秒，范围是1970-2038年，占用位置中等，使用频率通常最大。
- date精确到天，范围是1000-9999年，占用位置最小。
- 不用用字符串的形式来存储日期，因为这样会占用更多的空间，还会使得日期操作变得复杂。

枚举类型enum在实现上用的是整型数来进行存储，能够有效地将以往[底层存整型数+上层做转换]的操作进行简化，直接在数据库层面写好对应的枚举值。这对于很多应用里面需要固定的枚举值来说是比较友好的。

---

# MySQL索引

## 索引目的

一般的应用系统，读写比例在10:1左右，而且插入操作和一般的更新操作很少出现性能问题，遇到最多的，也是最容易出问题的，还是一些复杂的查询操作，所以查询语句的优化显然是重中之重，MySQL索引存在的意义是优化查询速度。

## 索引原理

可以概述为将整体的数据进行分片，然后分片地查询。

显然如果使用数组来进行分片是没有意义的；

使用搜索树的平均复杂度是lgN，具有不错的查询性能，但是由于数据是存储在磁盘上的，每次进行此磁盘IO的时候都会有一个预读的操作，即每次IO都会读出一个4K大小的片段，所以我们其实可以使用一个经过改进的搜索树，即：

## B+树

我们希望每次查找数据时把磁盘IO次数控制在一个很小的数量级，最好是常数数量级，所以我们需要一个高度可控的多路搜索树来达到需求。

![B+树 ](https://user-images.githubusercontent.com/17522733/99814541-65797e00-2b49-11eb-910e-0dfa083f76a9.jpg)

如上图所示是一棵B+树，可以看到每个磁盘块包含几个数据项（绿色）和几个指针（红色）。

非叶子节点不存储真实的数据，只存储指引搜索方向是数据项，如28,39,56并不真实存在于数据表中。真实数据存在于叶子节点中比如3,5,9,10,12,13,15,28,29,30,35,36,60,65,75。

### 查找过程

![](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2014/7af22798.jpg)

如图所示，如果要查找数据项29，那么首先会把磁盘块1由磁盘加载到内存，此时发生一次IO，在内存中用二分查找确定29在17和35之间，锁定磁盘块1的P2指针，内存时间因为非常短（相比磁盘的IO）可以忽略不计，通过磁盘块1的P2指针的磁盘地址把磁盘块3由磁盘加载到内存，发生第二次IO，29在26和30之间，锁定磁盘块3的P2指针，通过指针加载磁盘块8到内存，发生第三次IO，同时内存中做二分查找找到29，结束查询，总计三次IO。真实的情况是，3层的b+树可以表示上百万的数据，如果上百万的数据查找只需要三次IO，性能提高将是巨大的，如果没有索引，每个数据项都要发生一次IO，那么总共需要百万次的IO，显然成本非常非常高。

## B+树性质

1. IO次数取决于b+数的高度h，假设当前数据表的数据为N，每个磁盘块的数据项的数量是m，则有h=㏒(m+1)N，当数据量N一定的情况下，m越大，h越小；而m = 磁盘块的大小 / 数据项的大小，磁盘块的大小也就是一个数据页的大小，是固定的，如果数据项占的空间越小，数据项的数量越多，树的高度越低。这就是为什么每个数据项，即索引字段要尽量的小，比如int占4字节，要比bigint8字节少一半。所以如果有两个不同的列都可以做索引并且最终效果一致，我们应该选择字段占空间更小的那个列来做索引。
2. **最左匹配特性**：当b+树的数据项是复合的数据结构，比如(name,age,sex)的时候，b+数是按照从左到右的顺序来建立搜索树的，比如当(张三,20,F)这样的数据来检索的时候，b+树会优先比较name来确定下一步的所搜方向，如果name相同再依次比较age和sex，最后得到检索的数据；但当(20,F)这样的没有name的数据来的时候，b+树就不知道下一步该查哪个节点，因为建立搜索树的时候name就是第一个比较因子，必须要先根据name来搜索才能知道下一步去哪里查询。比如当(张三,F)这样的数据来检索时，b+树可以用name来指定搜索方向，但下一个字段age的缺失，所以只能把名字等于张三的数据都找到，然后再匹配性别是F的数据了


## Tmall项目

### 产品图片管理

有一点需要注意就是：
在com.yulin.tmall.pojo.Product中我们增加的ProductImage firstProductImage数据成员，有自动生成的getter和setter。
这个setter方法会在ProductServiceImpl中被调用。

在interface ProductService中有一个看起来反人类的方法：`void setFirstProductImage(Product p);`，这样做的原因是：
1、先调用`List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);`方法得到一个列表的产品图片，2、在判断不为空的情况下再取出第一张，然后调用POJO中的setter方法，给Product类中的firstProductImage进行赋值。

我们貌似并没有办法直接传入一个ProductImage对象来对Product类中的ProductImage成员进行赋值，所以只能多走一步。
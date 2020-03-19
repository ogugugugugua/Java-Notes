## Tmall项目

### 搜索页面
这里直接利用到了数据库中的like关键词来进行搜索，应该会有更加好的办法吧？后续有待查找

```java
ProductExample example = new ProductExample();
example.createCriteria().andNameLike("%" + keyword + "%");
example.setOrderByClause("id desc");
List<Product> productList = productMapper.selectByExample(example);
```

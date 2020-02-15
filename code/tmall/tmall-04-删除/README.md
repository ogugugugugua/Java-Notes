## Tmall项目

### 删除


连续删除两条项目之后：

log:
```text
DEBUG [http-bio-8080-exec-3] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@6b35c20]
DEBUG [http-bio-8080-exec-3] - ==>  Preparing: delete from category where id=? 
DEBUG [http-bio-8080-exec-3] - ==> Parameters: 22(Integer)
DEBUG [http-bio-8080-exec-3] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@6b35c20]
DEBUG [http-bio-8080-exec-3] - ==>  Preparing: select * from category order by id desc limit ?,? 
DEBUG [http-bio-8080-exec-3] - ==> Parameters: 0(Integer), 5(Integer)
TRACE [http-bio-8080-exec-3] - <==    Columns: id, name
TRACE [http-bio-8080-exec-3] - <==        Row: 24, 测试3
TRACE [http-bio-8080-exec-3] - <==        Row: 23, ??2
TRACE [http-bio-8080-exec-3] - <==        Row: 21, ????11
TRACE [http-bio-8080-exec-3] - <==        Row: 18, hey
TRACE [http-bio-8080-exec-3] - <==        Row: 17, 测试分类10
DEBUG [http-bio-8080-exec-3] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@6b35c20]
DEBUG [http-bio-8080-exec-3] - ==>  Preparing: select count(*) from category; 
DEBUG [http-bio-8080-exec-3] - ==> Parameters: 
TRACE [http-bio-8080-exec-3] - <==    Columns: count(*)
TRACE [http-bio-8080-exec-3] - <==        Row: 19



DEBUG [http-bio-8080-exec-3] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@6b35c20]
DEBUG [http-bio-8080-exec-3] - ==>  Preparing: delete from category where id=? 
DEBUG [http-bio-8080-exec-3] - ==> Parameters: 21(Integer)
DEBUG [http-bio-8080-exec-3] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@6b35c20]
DEBUG [http-bio-8080-exec-3] - ==>  Preparing: select * from category order by id desc limit ?,? 
DEBUG [http-bio-8080-exec-3] - ==> Parameters: 0(Integer), 5(Integer)
TRACE [http-bio-8080-exec-3] - <==    Columns: id, name
TRACE [http-bio-8080-exec-3] - <==        Row: 24, 测试3
TRACE [http-bio-8080-exec-3] - <==        Row: 23, ??2
TRACE [http-bio-8080-exec-3] - <==        Row: 18, hey
TRACE [http-bio-8080-exec-3] - <==        Row: 17, 测试分类10
TRACE [http-bio-8080-exec-3] - <==        Row: 16, 测试分类9
DEBUG [http-bio-8080-exec-3] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@6b35c20]
DEBUG [http-bio-8080-exec-3] - ==>  Preparing: select count(*) from category; 
DEBUG [http-bio-8080-exec-3] - ==> Parameters: 
TRACE [http-bio-8080-exec-3] - <==    Columns: count(*)
TRACE [http-bio-8080-exec-3] - <==        Row: 18
```

pass!
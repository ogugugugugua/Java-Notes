## Tmall项目

JDBC配置BUG：jdbc.properties 中如果 `jdbc.url=jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8`这一行被分成了两个部分，那么就会出现无法将中文字符写入mysql的情况。
因此需要保证这句话不要被分开成两行。

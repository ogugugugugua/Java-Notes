1. ## mapper.xml中返回列表数据类型

   当我们使用Springboot+MyBatis来对数据库进行访问时，可以使用XML文件进行SQL语句的编写。如果需要返回的是列表的数据类型，比如返回`List<User>`类型，则`resultType`应该直接写成`User`，比如：

   ```xml
   <select id="findUsersByName" parameterType="String" resultType="User">
           SELECT * FROM user WHERE name = #{name}
   </select>
   ```

   对应的`UserDao.java`则应该写成这样，来规定真正返回的类型是一个List：

   ```java
   List<User> findUsersByName(String name);
   ```

   **原理：**

   在`mapper.xml`中我们制定的是返回内容的具体类型。resultType是sql映射文件中定义返回值类型，返回值有基本类型，对象类型，List类型，Map类型等。

   即：

   resultType:

   1、基本类型  ：resultType=基本类型

   2、List类型：  resultType=List中元素的类型

   3、Map类型   resultType =map


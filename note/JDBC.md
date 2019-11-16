<center>

# JDBC

Yulin XIE
</center>

#### 1.增删改
```java
public static void execute(String sql){
    //To import relevant .jar
    try{
        Class.forName("com.mysql.jdbc.Driver");
    }catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    //Use this kind of try block to enable automatic shut down of connection after manipulation
    try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
            "root","admin");
        Statement s = c.createStatement())
        {
          s.execute(sql);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

//...
public static void main(String args[]){
    //Samples:
    String sql = "update hero set name = 'change2' where id = 12";
    String sql1 = "insert into hero values(null,'hero1',200,100)"
    execute(sql);
    execute(sql1);
}
```

#### 2. 查
Here we use ResultSet to get the returned result.
```java
public static void executeQuery(String sql){
    try{
        Class.forName("com.mysql.jdbc.Driver");
    }catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
            "root","admin");
        Statement s = c.createStatement())
        {
          ResultSet resultSet = s.executeQuery(sql);
          while (resultSet.next()){
              int id = resultSet.getInt("id");
              String name = resultSet.getString(2);
              float hp = resultSet.getFloat("hp");
              int damage = resultSet.getInt(4);
              System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
          }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//...
public static void main(String args[]){
    //Samples:
      String sql = "select * from hero";
      executeQuery(sql);
}
```
#### 3. list data of limit amount
This can avoid wasting out all the memory the JVM. And facilitate the query speed by reducing the amount of data required, at the other hand, it is useful for inquerying data in a web page because each page can only contain limit amount of data presented.
```java
public static void list(int start, int count){
    try{
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
            "root","admin");
        Statement s = c.createStatement()) {
        String sql = "select * from hero limit " + start + ','+ count;
        ResultSet resultSet = s.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString(2);
            float hp = resultSet.getFloat("hp");
            int damage = resultSet.getInt(4);
            System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//...
public static void main(String[] args) {
      list(10,5);
}

```
#### 4. PreparedStatement
Advantages:
- Readable, difficult to make mistakes
- Compile only once, therefore faster
- Avoid SQL Injection attack
```java
public static void insert(String name, float hp, int damage){
    try{
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    String sql = "insert into hero values(null,?,?,?)";

    try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8","root","admin");
        PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setString(1,name);
        ps.setFloat(2,hp);
        ps.setInt(3,damage);
        ps.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//...
public static void main(String[] args) {
      insert("insertFunction",250,100);
}
```

#### 5. Difference between `Statement.execute` and `Statement.executeUpdate`
```java
// 不同1：execute可以执行查询语句, executeUpdate不可以查询
// 然后通过getResultSet，把结果集取出来
String sqlSelect = "select * from hero";
s.execute(sqlSelect);
ResultSet rs = s.getResultSet();
```
```java
// 不同2:
// execute返回boolean类型，true表示执行的是查询语句，false表示执行的是insert,delete,update等等
boolean isSelect = s.execute(sqlSelect);
System.out.println(isSelect);

// executeUpdate返回的是int，表示有多少条数据受到了影响
String sqlUpdate = "update Hero set hp = 300 where id < 100";
int number = s.executeUpdate(sqlUpdate);
System.out.println(number);
```

#### 6. Get AUTO_INCREMENT id
We use `PreparedStatement` and
```java
try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
    PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  //Pay attention here
    ) {

//manipulations...

// 执行插入语句
ps.execute();

// 在执行完插入语句后，MySQL会为新插入的数据分配一个自增长id
// JDBC通过getGeneratedKeys获取该id
ResultSet rs = ps.getGeneratedKeys();
if (rs.next()) {
    int id = rs.getInt(1);
    System.out.println(id);
}
```

#### 7. `Commit` and `AutoCommit`

在事务中的多个操作，要么都成功，要么都失败。
通过 c.setAutoCommit(false); 关闭自动提交。
使用 c.commit(); 进行手动提交。
对于多个数据库操作，若处于同一个事务当中，要么都成功，要么都失败
所以，虽然第一条SQL语句是可以执行的，但是第二条SQL语句有错误，其结果就是两条SQL语句都没有被提交。 除非两条SQL语句都是正确的。
```java
c.setAutoCommit(false);

// 加血的SQL
String sql1 = "update hero set hp = hp +1 where id = 22";
s.execute(sql1);

// 减血的SQL
// 不小心写错写成了 updata(而非update)

String sql2 = "updata hero set hp = hp -1 where id = 22";
s.execute(sql2);

// 手动提交
c.commit();
```
For the situation aboved, we cannot commit the two commands because one of them is grammatically wrong.

#### 8. Object Relationship Database Mapping (ORM)
对象和关系数据库的映射。
简单说，一个对象，对应数据库里的一条记录。
这是一种思想，在java代码中创建一个相应的数据类型，然后将其与数据库中的数据关联起来。可以编写诸如get,add,delete,list等函数。在这些函数中搭配PreparedStatement就会很好用。
```java
class Hero{
    int id;
    String name;
    float hp;
    int damage;
    public Hero(String name,float hp,int damage){
        this.hp = hp;
        this.damage = damage;
        this.name = name;
    }
}
//----------------------------------
public static void insert(Hero h){
    try{
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    String sql = "insert into hero values(null,?,?,?)";

    try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8", "root", "admin");
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, h.name);
        ps.setFloat(2, h.hp);
        ps.setInt(3, h.damage);
        ps.execute();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//--------------------------------------
public static void main(String[] args) {
    insert(new Hero("TryHero",200,30));
}
```

#### 9. Data Access Object (DAO)
数据访问对象。
实际上就是运用了练习-ORM中的思路，把数据库相关的操作都封装在这个类里面，其他地方看不到JDBC的代码。定义这么一个接口：
```java 
public interface DAO{
    //增加
    public void add(Hero hero);
    //修改
    public void update(Hero hero);
    //删除
    public void delete(int id);
    //获取
    public Hero get(int id);
    //查询
    public List<Hero> list();
    //分页查询
    public List<Hero> list(int start, int count);
}
```

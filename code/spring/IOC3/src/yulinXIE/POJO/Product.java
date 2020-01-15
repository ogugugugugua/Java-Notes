package yulinXIE.POJO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("p")
public class Product {
    private String name="Pro2";
    private int id=2;

//    @Resource(type = Category.class)  // == //@Resource(name = "c")
//    如果Spring只管理有一个Category对象，则上述两种写法都是正确的。
//    然而如果Spring管理了多个对象，类型都是Category的话，就会抛出如下错误：
//    No unique bean of type [yulinXIE.POJO.Category] is defined: expected single matching bean but found 3: [c, category1, category2]
//    大概是因为如果根据类型注入的话，由于找到多个同样类型的，没法正确注入，所以报错了。
    @Resource(name = "category2")
//    推荐使用：@Resource注解在字段上，这样就不用写setter方法了，并且这个注解是属于J2EE的，减少了与spring的耦合。这样代码看起就比较优雅。
    private Category category;

    public Category getCategory(){
        return this.category;
    }

/*
*    @Autowired按byType自动注入，而@Resource默认按 byName自动注入
*    @Autowired默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，如果要允许null值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用
*/

//    @Autowired
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

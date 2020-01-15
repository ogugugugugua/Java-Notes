package yulinXIE.test;
import yulinXIE.POJO.Category;
import yulinXIE.POJO.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        Product product = (Product) context.getBean("p");
        System.out.println(product.getName());
        System.out.println(product.getId());
        System.out.println(product.getCategory().getName());
        System.out.println(product.getCategory().getId());


        System.out.println("------------------------");
        Product product1 = (Product) context.getBean("p");//Duplication of Product with the same name/id
        System.out.println(product1.getName());

        System.out.println("------------------------");
        Category category1 = (Category) context.getBean("category1");//create a Category according to configuration in .xml file
        System.out.println(category1.getName());
    }

}

package com.yulin.test;

import com.yulin.POJO.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
/*这种写法仅仅是在使用当前这种JUNIT测试的情况下的写法，方便运行测试代码的时候定位applicationContext.xml。运行正常业务代码不需要这样写的*/
@ContextConfiguration("classpath:applicationContext.xml")
public class NewTestSpring {
    @Autowired
    Category c;

    @Test
    public void test(){
        /*这样子写，Category.getName()函数的调用依然可以唤起LoggerAspect切面的两个服务函数*/
        System.out.println(c.getName());
    }
}

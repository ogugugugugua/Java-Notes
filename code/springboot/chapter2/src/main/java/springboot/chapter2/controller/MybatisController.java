package springboot.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.chapter2.pojo.UserForMybatis;
import springboot.chapter2.service.MybatisUserService;

import java.util.List;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    MybatisUserService mybatisUserService;

    @RequestMapping("/findall")
    @ResponseBody
    public List<UserForMybatis> findall() {
        return mybatisUserService.findall();
    }

}

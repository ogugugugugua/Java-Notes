package springboot.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.chapter2.aspect.UserValidator;
import springboot.chapter2.pojo.User;
import springboot.chapter2.service.Impl.UserServiceImpl;

@Controller
public class IndexController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/testAspect/{id}/{name}")
    @ResponseBody
    public User testAspect(@PathVariable("id") Long id, @PathVariable("name") String name, @RequestParam(value = "note", required = true) String note) {
        User user = new User(id, name, note);
        userService.print(user);
        return user;
    }

    @RequestMapping("/vp")
    @ResponseBody
    public User validate(Long id, String name, String note) {
        User user = new User(id, name, note);
        UserValidator userValidator = (UserValidator) userService;
        if (userValidator.validate(user)) {
            userService.print(user);
        }
        return user;
    }
}

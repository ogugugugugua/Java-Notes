package yulin.murailleinformation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {
    @RequestMapping("page")
    public String page(){
        return "page";
    }
}

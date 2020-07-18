package naonedcars.yulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPageController {
    @RequestMapping("adminPage")
    public String admin() {
        return "admin/adminPage";
    }

    @RequestMapping("chaqueVoiture")
    public String chaqueVoiture(){
        return "admin/chaqueVoiture";
    }

    @RequestMapping("updateVoiture")
    public String updateVoiture(){
        return "admin/updateVoiture";
    }

    @RequestMapping("addPhoto")
    public String addPhoto(){return "admin/addPhoto";}
}

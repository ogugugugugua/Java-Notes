package springboot.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.chapter2.dao.JpaUserForTestRepository;
import springboot.chapter2.pojo.User;
import springboot.chapter2.pojo.UserForJpa;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private JpaUserForTestRepository userForTestRepository = null;

    @RequestMapping("/getUserForTest")
    @ResponseBody
    public UserForJpa getUserForTest(Long id) {
        UserForJpa userForJpa = userForTestRepository.findById(id).get();
        return userForJpa;
    }

    @RequestMapping("getUsers")
    public String getUsers(Model model) {
        model.addAttribute("name", "n1");
        model.addAttribute("age", "18");

        List<User> users = new ArrayList<>();
        users.add(new User((long) 1, "nn1", "no1"));
        users.add(new User((long) 2, "nn2", "no2"));
        users.add(new User((long) 3, "nn3", "no3"));

        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping("testMoreJpa")
    @ResponseBody
    public List<UserForJpa> testMoreJpa(String like) {
        List<UserForJpa> byNameLike = userForTestRepository.findByNameLike("%" + like + "%");
        System.out.println(byNameLike);
        return byNameLike;
    }
}

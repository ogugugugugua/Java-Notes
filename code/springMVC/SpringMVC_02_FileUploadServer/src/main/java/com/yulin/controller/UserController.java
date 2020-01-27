package com.yulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 传统方式上传文件(略复杂)
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload1")
    public String fileupload1() {
        System.out.println("fileupload1");
        return "success";
    }

}

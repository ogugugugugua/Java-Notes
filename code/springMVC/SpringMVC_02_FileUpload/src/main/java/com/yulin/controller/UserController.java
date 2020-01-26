package com.yulin.controller;

import com.yulin.domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 传统方式上传文件(略复杂)
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("fileupload1");

        //使用fileload组件完成文件上传

        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);

        //判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);

        //遍历
        for(FileItem item: fileItems){
            if(item.isFormField()){
                //普通表单项
            }else {
                //上传文件项
                //获取上传文件名称
                String filename = item.getName();
                //把文件名称设置成唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid+"_"+filename;
                System.out.println(filename);
                //完成文件上传
                item.write(new File(path, filename));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }

    /**
     * SpringMVC方式上传文件(稍简单)
     * @return
     */
    @RequestMapping("fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("SpringMVC file upload");

        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);

        //判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"_"+filename;

        upload.transferTo(new File(path, filename));
        return "success";
    }

}

package com.yulin.tmall.controller;
 
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.service.CategoryService;
import com.yulin.tmall.util.ImageUtil;
import com.yulin.tmall.util.Page;
import com.yulin.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
 
@Controller /*声明当前类是一个控制器*/
@RequestMapping("") /*表示访问的时候无需额外的地址*/
public class CategoryController{

    @Autowired /*把CategoryServiceImpl自动装配进了CategoryService接口*/
    CategoryService categoryService;

    /**
     *
     * @param model
     * @param page 这里进来的Page使用默认值，不传参数
     *             即最开始时 start =0；count=5；total =0；param=null;
     *             在函数体里page.setTotal(total);再设置total这个值
     * @return
     */
    @RequestMapping("admin_category_list") /*映射admin_category_list路径的访问*/
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list(); /*获取所有的Category对象并放在cs中*/

        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page",page);
        return "admin/listCategory"; /*服务器跳转到admin/listCategory视图*/
        /*admin/Category会根据后续的springMVC.xml配置文件，跳转到WEB-INF/jsp/admin/listCategory.jsp文件*/
    }

    /**
     *
     * @param c 参数 Category c接受页面提交的分类名称
     * @param session 参数 session 用于在后续获取当前应用的路径
     * @param uploadedImageFile UploadedImageFile 用于接受上传的图片
     * @return
     * @throws IOException
     */
    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(c.getId());
        //通过categoryService保存c对象
        categoryService.add(c);
        System.out.println(c.getId());

        //通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        //根据分类id创建文件名
        File file = new File(imageFolder, c.getId() + ".jpg");
        //如果/img/category目录不存在，则创建该目录，否则后续保存浏览器传过来图片，会提示无法保存
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);

        //通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
        uploadedImageFile.getImage().transferTo(file);
        //通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);

        //客户端跳转到admin_category_list
        return "redirect:/admin_category_list";
    }

    /**
     * @param id      提供参数接受id注入
     * @param session 提供session参数，用于后续定位文件位置
     * @return
     */
    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) {
        //通过categoryService删除数据
        categoryService.delete(id);

        //通过session获取ControllerContext然后获取分类图片位置，接着删除分类图片
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();

        //客户端跳转到 admin_category_list
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String getByID(int id, Model model) {
        Category category = categoryService.getByID(id);
        model.addAttribute("c",category);
        return "admin/editCategory";
    }


    /**
     *
     * @param category 接受页面提交的分类名称
     * @param session 用于在后续获取当前应用的路径
     * @param uploadedImageFile 用于接受上传的图片
     * @return "redirect:/admin_category_list" 通过这个controller获取数据库数据并展示
     * @throws IOException
     */
    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        //通过categoryService更新category对象
        categoryService.update(category);

        MultipartFile image = uploadedImageFile.getImage();

        //首先判断是否有上传图片，如果有上传，那么通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。
        if(null!=image && !image.isEmpty()){
            //通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            //根据分类id创建文件名
            File file = new File(imageFolder, category.getId() + ".jpg");
            //如果/img/category目录不存在，则创建该目录，否则后续保存浏览器传过来图片，会提示无法保存
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();

            image.transferTo(file);

            //通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list"; //(√)
//        return "admin/listCategory";          //(X)
//        不能直接return "admin/listCategory"
//        因为  return "admin/listCategory" 的意思是服务端跳转到 listCategory.jsp 页面。 而此时服务端跳转过去又没有带数据，所以就报错了
    }
}

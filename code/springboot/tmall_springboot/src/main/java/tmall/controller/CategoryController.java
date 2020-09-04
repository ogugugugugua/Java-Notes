package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tmall.pojo.Category;
import tmall.service.CategoryService;
import tmall.util.ImageUtil;
import tmall.util.Page4Navigator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController//专门用来提供 RESTFUL 服务器的控制器,对每个方法的返回值都会直接转换为json数据格式
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 因为声明为RestController，所以返回的集合数据会被自动转换成json数组抛给浏览器
     *
     * @return json数组
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        Page4Navigator<Category> list = categoryService.list(start, size, 5);
        return list;
    }

    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, bean.getId() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        return null;
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) {
        return categoryService.get(id);
    }

    @PutMapping("/categories/{id}")
    public Object update(Category category, MultipartFile image, HttpServletRequest request) throws IOException {
        System.out.println("-------------------------------");
        System.out.println(category);
        System.out.println("-------------------------------");
        category.setName(request.getParameter("name"));
        categoryService.update(category);
        if (image != null) {
            saveOrUpdateImageFile(category, image, request);
        }
        return category;
    }
}

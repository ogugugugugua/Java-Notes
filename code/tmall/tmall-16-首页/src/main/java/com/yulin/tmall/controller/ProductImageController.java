package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulin.tmall.pojo.Product;
import com.yulin.tmall.pojo.ProductImage;
import com.yulin.tmall.service.ProductImageService;
import com.yulin.tmall.service.ProductService;
import com.yulin.tmall.util.ImageUtil;
import com.yulin.tmall.util.Page;
import com.yulin.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    /**
     * 点击删除超链，进入ProductImageController的delete方法
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("admin_productImage_delete")
    public String delete(int id, HttpSession session){
        ProductImage productImage = productImageService.get(id);                                    //根据id获取ProductImage 对象pi

        String fileName = productImage.getId()+".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;

        if(ProductImageService.type_single.equals(productImage.getType())){                         //单个图片: 那么删除3张正常，中等，小号图片
            imageFolder= session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small= session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle= session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder,fileName);
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle,fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
        }
        else{                                                                                       //详情图片: 那么删除一张图片
            imageFolder= session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();
        }

        productImageService.delete(id);                                                             //借助productImageService，删除数据

        return "redirect:admin_productImage_list?pid="+productImage.getPid();
    }

    /**
     *
     * @param productImage 通过productImage对象接受type和pid的注入
     * @param session
     * @param uploadedImageFile
     * @return
     */
    @RequestMapping("admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile){
        productImageService.add(productImage);                                                          //借助productImageService，向数据库中插入数据。

        String fileName = productImage.getId()+".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(ProductImageService.type_single.equals(productImage.getType())){
            imageFolder= session.getServletContext().getRealPath("img/productSingle");                  //定位到存放单个产品图片的目录
            imageFolder_small= session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle= session.getServletContext().getRealPath("img/productSingle_middle");
        }else{
            imageFolder= session.getServletContext().getRealPath("img/productDetail");
        }

        File f = new File(imageFolder,fileName);
        f.getParentFile().mkdirs();
        try{
            uploadedImageFile.getImage().transferTo(f);                                                 //通过uploadedImageFile保存文件
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img,"jpg",f);                                                     //详情图片做的是一样的事情，区别在于复制到目录productDetail下，并且不需要改变大小

            if(ProductImageService.type_single.equals(productImage.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);

                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:admin_productImage_list?pid="+productImage.getPid();
    }


    /**
     * 通过产品页面的图片管理访问ProductImageController的list()方法
     * @param model
     * @param page
     * @param pid 获取参数pid
     * @return
     */
    @RequestMapping("admin_productImage_list")
    public String list(Model model, Page page, int pid){
        List<ProductImage> pisSingle = productImageService.list(pid, ProductImageService.type_single);      //根据pid对象获取单个图片的集合pisSingle
        List<ProductImage> pisDetail = productImageService.list(pid, ProductImageService.type_detail);      //根据pid对象获取详情图片的集合pisDetail

        Product product = productService.get(pid);                                                          //根据pid获取Product对象

        /*在listProductImage.jsp，使用c:forEach 遍历pisSingle和pisDetail*/
        model.addAttribute("p", product);                                                     //把product放在model上
        model.addAttribute("pisSingle", pisSingle);                                           //把pisSingle放在model上
        model.addAttribute("pisDetail", pisDetail);                                           //把pisDetail放在model上
        return "admin/listProductImage";
    }
}

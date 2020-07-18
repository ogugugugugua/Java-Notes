package naonedcars.yulin.controller;

import naonedcars.yulin.pojo.Result;
import naonedcars.yulin.pojo.temp;
import naonedcars.yulin.pojo.voiture;
import naonedcars.yulin.service.testService;
import naonedcars.yulin.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class testController {
    @Autowired
    private testService service;

    @RequestMapping(value = "/getAllVoitures",method = RequestMethod.GET)
    public Result getAllVoitures(){
        try{
            return Result.success(service.findAll(),"success");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/Voiture/{id}",method = RequestMethod.GET)
    public Result getVoitureById(@PathVariable("id")int id){
        try {
            voiture result = service.getVoitureById(id);
            return Result.success(result, "success");
        } catch (Exception e) {
            System.out.println("exception: "+e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public Result getResult(){
        return Result.success(service.getVoitureById(1), "message_success");
    }

//    @Deprecated
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public Result testAddVoiture(String miseEnCirculation, String annéeModèle){
//        try {
//            Date MiseEnCirculation= new SimpleDateFormat("yyyy-MM-dd").parse(miseEnCirculation);
//            Date AnnéeModèle= new SimpleDateFormat("yyyy").parse(annéeModèle);
//            service.addVoiture("Titre 4 DONGFENG Car", "dongfeng4", "21", AnnéeModèle, MiseEnCirculation, 100000, "diesel", "automatique", "citadine", 5, "1233456546234", "tres bonne voiture", "rouge", 4, 4, 1444, 1, 0);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return Result.success("Add success");
//    }

//    @Deprecated
//    @RequestMapping(value = "/update2", method = RequestMethod.POST)
//    public Result testUpdateVoiture2(@RequestBody temp object) {
//        System.out.println(object);
//        return Result.success("upload success");
//    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result testUpdateVoiture(@RequestBody voiture v) {
        System.out.println(v);
        service.addVoiture(v);
        v.setId(service.getMaxId());
        return Result.success("upload success, information: " + v);
    }

    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    public Result addPhoto(MultipartFile image, int id, HttpServletRequest request){
        try{
            service.addPhoto(image, id);
            return Result.success("add photo success");
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/addPhotos", method = RequestMethod.POST)
    public Result addPhotos(MultipartFile[] images, int id, HttpServletRequest request){
        try{
            service.addPhotos(images, id);
            return Result.success("addPhotos success");
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
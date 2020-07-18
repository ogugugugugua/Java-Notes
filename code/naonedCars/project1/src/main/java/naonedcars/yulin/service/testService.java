package naonedcars.yulin.service;

import naonedcars.yulin.mapper.testMapper;
import naonedcars.yulin.pojo.Result;
import naonedcars.yulin.pojo.voiture;
import naonedcars.yulin.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class testService {
    @Autowired
    private testMapper mapper;
    @Value("${file.uploadFilePath}")
    private String uploadFilePath;

    public List<voiture> findAll(){
//        System.out.println(mapper.findAll().toString());
        return mapper.findAll();
    }

    public voiture getVoitureById(int id) throws NullPointerException{
        System.out.println("In service: "+mapper.getVoitureById(id));
        if (mapper.getVoitureById(id) == null) {
            throw new NullPointerException("No car found by this ID");
        }
        voiture result = mapper.getVoitureById(id);
        try {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date MiseEnCirculation = format1.parse(format1.format(result.getMiseEnCirculation()));
            result.setMiseEnCirculation(MiseEnCirculation);

            DateFormat format2 = new SimpleDateFormat("yyyy");
            Date AnnéeModèle = format2.parse(format2.format(result.getAnnéeModèle()));
            result.setAnnéeModèle(AnnéeModèle);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addVoiture(String Titre,String Marque,String Modèle,Date AnnéeModèle,
                              Date MiseEnCirculation, Integer Kilométrage,String Carburant,
                              String BoîteDeVitesse, String TypeDeVéhicule,Integer PuissanceFiscale,
                              String Référence,String Description,String Couleur,Integer NombreDePlace,
                              Integer NombreDePortes,Integer PuissanceDIN,Integer Permis,Integer SoumisàLOALLD){
        try{
            mapper.addVoiture(Titre, Marque, Modèle, AnnéeModèle, MiseEnCirculation,
                    Kilométrage, Carburant, BoîteDeVitesse, TypeDeVéhicule,
                    PuissanceFiscale, Référence, Description, Couleur, NombreDePlace,
                    NombreDePortes, PuissanceDIN, Permis, SoumisàLOALLD);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addVoiture(voiture voiture){
        try{
            mapper.addVoiture(voiture.getTitre(), voiture.getMarque(), voiture.getModèle(),
                    voiture.getAnnéeModèle(), voiture.getMiseEnCirculation(), voiture.getKilométrage(),
                    voiture.getCarburant(), voiture.getBoîteDeVitesse(), voiture.getTypeDeVéhicule(),
                    voiture.getPuissanceFiscale(), voiture.getRéférence(), voiture.getDescription(),
                    voiture.getCouleur(), voiture.getNombreDePlace(), voiture.getNombreDePortes(),
                    voiture.getPuissanceDIN(), voiture.getPermis(), voiture.getSoumisàLOALLD());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getMaxId(){
        return mapper.getMaxId();
    }

    public boolean deleteById(int id){
        return mapper.deleteById(id);
    }

    public boolean deleteByIds(int[] ids) {
        try {
            for (int id : ids) {
                mapper.deleteById(id);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<voiture> getVoitureByMarque(String marque){
        return mapper.getVoitureByMarque(marque);
    }

    public boolean addPhoto(MultipartFile image, int id) throws Exception{
        File imageFolder = new File(this.uploadFilePath);
        File file = new File(imageFolder, id + "_0.jpg");
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        assert img != null;
        ImageIO.write(img,"jpg",file);
        System.out.println(file);
        return true;
    }

    public boolean addPhotos(MultipartFile[] images, int id) throws Exception{
        File imageFolder = new File(this.uploadFilePath);
        if (images.length == 0) {
            throw new Exception("images.length == 0");
        }
        for (int i = 0; i < images.length; i++) {
            File file = new File(imageFolder, id + "_" + (i+1) + ".jpg");
            images[i].transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            assert img != null;
            ImageIO.write(img,"jpg",file);
            System.out.println(file);
        }
        return true;
    }


}

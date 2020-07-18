package naonedcars.yulin;

import naonedcars.yulin.service.testService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class YulinApplicationTests {
    @Autowired
    testService service;

    @Test
    void contextLoads() {
        try {
            String miseEnCirculation = "1989-01-02";
            String annéeModèle = "1988";
            Date MiseEnCirculation = new SimpleDateFormat("yyyy-MM-dd").parse(miseEnCirculation);
            Date AnnéeModèle = new SimpleDateFormat("yyyy").parse(annéeModèle);
            service.addVoiture("Titre 4 DONGFENG Car", "dongfeng4", "21", AnnéeModèle, MiseEnCirculation, null, "diesel", "automatique", "citadine", 5, "1233456546234", "tres bonne voiture", "rouge", 4, 4, 1444, 1, 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetMaxId(){
        System.out.println(service.getMaxId());
    }

    @Test
    void deleteById(){
        service.deleteById(service.getMaxId());
    }

    @Test
    void deleteByIds(){
        int[] ids = {1, 3, 4};
        service.deleteByIds(ids);
    }

    @Test
    void getVoitureByMarque(){
        System.out.println(service.getVoitureByMarque("dongfeng4"));
    }
}

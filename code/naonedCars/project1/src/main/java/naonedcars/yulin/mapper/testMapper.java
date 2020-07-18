package naonedcars.yulin.mapper;

import naonedcars.yulin.pojo.voiture;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface testMapper {
    @Select("SELECT * FROM voiture")
    List<voiture> findAll();

    @Select("SELECT * FROM voiture where id = #{id}")
    voiture getVoitureById(@Param("id") int id);

//    @Select("SELECT * FROM voiture where Marque like CONCAT ('%',#{marque},'%')")
//    voiture getVoitureByMarque(@Param("marque") String marque);

    @Select("SELECT * FROM voiture where Marque like CONCAT ('%',#{marque},'%')")
    List<voiture> getVoitureByMarque(@Param("marque") String marque);

    @Insert("insert into voiture " +
            "(Titre, Marque, Modèle, AnnéeModèle, MiseEnCirculation, " +
            "Kilométrage, Carburant, BoîteDeVitesse, TypeDeVéhicule, " +
            "PuissanceFiscale, Référence, Description, Couleur, NombreDePlace, " +
            "NombreDePortes, PuissanceDIN, Permis, SoumisàLOALLD) " +
            "VALUE " +
            "(#{Titre},#{Marque},#{Modèle},#{AnnéeModèle},#{MiseEnCirculation}," +
            "#{Kilométrage},#{Carburant},#{BoîteDeVitesse},#{TypeDeVéhicule}," +
            "#{PuissanceFiscale},#{Référence},#{Description},#{Couleur},#{NombreDePlace}," +
            "#{NombreDePortes},#{PuissanceDIN},#{Permis},#{SoumisàLOALLD})")
    boolean addVoiture(@Param("Titre")String Titre,
                       @Param("Marque")String Marque,
                       @Param("Modèle")String Modèle,
                       @Param("AnnéeModèle") Date AnnéeModèle,
                       @Param("MiseEnCirculation") Date MiseEnCirculation,
                       @Param("Kilométrage") Integer Kilométrage,
                       @Param("Carburant") String Carburant,
                       @Param("BoîteDeVitesse") String BoîteDeVitesse,
                       @Param("TypeDeVéhicule") String TypeDeVéhicule,
                       @Param("PuissanceFiscale") Integer PuissanceFiscale,
                       @Param("Référence") String Référence,
                       @Param("Description") String Description,
                       @Param("Couleur") String Couleur,
                       @Param("NombreDePlace") Integer NombreDePlace,
                       @Param("NombreDePortes") Integer NombreDePortes,
                       @Param("PuissanceDIN") Integer PuissanceDIN,
                       @Param("Permis") Integer Permis,
                       @Param("SoumisàLOALLD") Integer SoumisàLOALLD
                       );

    @Select("SELECT id FROM voiture order by id desc limit 1")
    int getMaxId();

    @Delete("DELETE FROM voiture where id = #{id}")
    boolean deleteById(@Param("id") int id);


}

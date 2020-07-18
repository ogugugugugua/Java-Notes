package naonedcars.yulin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
@Component
@Table(name = "voiture")
public class voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Titre")
    private String Titre;

    @Column(name = "Marque")
    private String Marque;

    @Column(name = "Modèle")
    private String Modèle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "AnnéeModèle")
    private Date AnnéeModèle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "MiseEnCirculation")
    private Date MiseEnCirculation;

    @Column(name = "Kilométrage")
    private Integer Kilométrage;

    @Column(name = "Carburant")
    private String Carburant;

    @Column(name = "BoîteDeVitesse")
    private String BoîteDeVitesse;

    @Column(name = "TypeDeVéhicule")
    private String TypeDeVéhicule;

    @Column(name = "PuissanceFiscale")
    private Integer PuissanceFiscale;

    @Column(name = "Référence")
    private String Référence;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Couleur")
    private String Couleur;

    @Column(name = "NombreDePlace")
    private Integer NombreDePlace;

    @Column(name = "NombreDePortes")
    private Integer NombreDePortes;

    @Column(name = "PuissanceDIN")
    private Integer PuissanceDIN;

    @Column(name = "Permis")
    private Integer Permis;

    @Column(name = "SoumisàLOALLD")
    private Integer SoumisàLOALLD;

    public voiture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getModèle() {
        return Modèle;
    }

    public void setModèle(String modèle) {
        Modèle = modèle;
    }

    public Date getAnnéeModèle() {

        return AnnéeModèle;
    }

    public String getAnnéeModèleYear(){
        DateFormat df = new SimpleDateFormat("yyyy");
        return df.format(this.AnnéeModèle);
    }

    public void setAnnéeModèle(Date annéeModèle) {
        AnnéeModèle = annéeModèle;
    }

    public Date getMiseEnCirculation() {
        return MiseEnCirculation;
    }

    public String getMiseEnCirculationYear(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.MiseEnCirculation);
    }

    public void setMiseEnCirculation(Date miseEnCirculation) {
        MiseEnCirculation = miseEnCirculation;
    }

    public Integer getKilométrage() {
        return Kilométrage;
    }

    public void setKilométrage(Integer kilométrage) {
        Kilométrage = kilométrage;
    }

    public String getCarburant() {
        return Carburant;
    }

    public void setCarburant(String carburant) {
        Carburant = carburant;
    }

    public String getBoîteDeVitesse() {
        return BoîteDeVitesse;
    }

    public void setBoîteDeVitesse(String boîteDeVitesse) {
        BoîteDeVitesse = boîteDeVitesse;
    }

    public String getTypeDeVéhicule() {
        return TypeDeVéhicule;
    }

    public void setTypeDeVéhicule(String typeDeVéhicule) {
        TypeDeVéhicule = typeDeVéhicule;
    }

    public Integer getPuissanceFiscale() {
        return PuissanceFiscale;
    }

    public void setPuissanceFiscale(Integer puissanceFiscale) {
        PuissanceFiscale = puissanceFiscale;
    }

    public String getRéférence() {
        return Référence;
    }

    public void setRéférence(String référence) {
        Référence = référence;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String couleur) {
        Couleur = couleur;
    }

    public Integer getNombreDePlace() {
        return NombreDePlace;
    }

    public void setNombreDePlace(Integer nombreDePlace) {
        NombreDePlace = nombreDePlace;
    }

    public Integer getNombreDePortes() {
        return NombreDePortes;
    }

    public void setNombreDePortes(Integer nombreDePortes) {
        NombreDePortes = nombreDePortes;
    }

    public Integer getPuissanceDIN() {
        return PuissanceDIN;
    }

    public void setPuissanceDIN(Integer puissanceDIN) {
        PuissanceDIN = puissanceDIN;
    }

    public Integer getPermis() {
        return Permis;
    }

    public void setPermis(Integer permis) {
        Permis = permis;
    }

    public Integer getSoumisàLOALLD() {
        return SoumisàLOALLD;
    }

    public void setSoumisàLOALLD(Integer soumisàLOALLD) {
        SoumisàLOALLD = soumisàLOALLD;
    }



    @Override
    public String toString() {
        return "voiture{" +
                "id=" + id +
                ", Titre='" + Titre + '\'' +
                ", Marque='" + Marque + '\'' +
                ", Modèle='" + Modèle + '\'' +
                ", AnnéeModèle=" + this.getAnnéeModèle() +
                ", MiseEnCirculation=" + this.getMiseEnCirculation() +
                ", Kilométrage=" + Kilométrage +
                ", Carburant='" + Carburant + '\'' +
                ", BoîteDeVitesse='" + BoîteDeVitesse + '\'' +
                ", TypeDeVéhicule='" + TypeDeVéhicule + '\'' +
                ", PuissanceFiscale=" + PuissanceFiscale +
                ", Référence='" + Référence + '\'' +
                ", Description='" + Description + '\'' +
                ", Couleur='" + Couleur + '\'' +
                ", NombreDePlace=" + NombreDePlace +
                ", NombreDePortes=" + NombreDePortes +
                ", PuissanceDIN=" + PuissanceDIN +
                ", Permis=" + Permis +
                ", SoumisàLOA=" + SoumisàLOALLD +
                '}';
    }
}

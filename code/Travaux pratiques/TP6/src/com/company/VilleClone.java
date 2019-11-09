package com.company;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;

public class VilleClone {
    private String nom;
    public VilleClone(String nom){
        this.nom = nom;
    }
    public String toString(){
        return nom;
    }
    public static void main(String args[]){
        VilleClone Brest = new VilleClone("Brest");
        VilleClone Rennes = new VilleClone("Rennes");
        VilleClone Rouen = new VilleClone("Rouen");
        VilleClone Paris = new VilleClone("Paris");
        VilleClone Le_Mans = new VilleClone("Le Mans");
        VilleClone Angers = new VilleClone("Angers");
        VilleClone Tours = new VilleClone("Tours");
        VilleClone Poitiers = new VilleClone("Poitiers");
        VilleClone Nantes = new VilleClone("Nantes");

        VillePair vp_Brest_Rennes = new VillePair(Brest,Rennes);
        VillePair vp_Rouen_Rennes = new VillePair(Rouen,Rennes);
        VillePair vp_LeMans_Rennes = new VillePair(Le_Mans,Rennes);
        VillePair vp_Nantes_Rennes = new VillePair(Nantes,Rennes);
        VillePair vp_Rouen_Paris = new VillePair(Rouen,Paris);
        VillePair vp_LeMans_Rouen = new VillePair(Le_Mans,Rouen);
        VillePair vp_LeMans_Paris = new VillePair(Le_Mans,Paris);
        VillePair vp_LeMans_Tours = new VillePair(Le_Mans,Tours);
        VillePair vp_LeMans_Angers = new VillePair(Le_Mans,Angers);
        VillePair vp_Angers_Tours = new VillePair(Angers,Tours);
        VillePair vp_Poitiers_Tours = new VillePair(Poitiers,Tours);
        VillePair vp_Poitiers_Nantes = new VillePair(Poitiers,Nantes);
        VillePair vp_Angers_Nantes = new VillePair(Angers,Nantes);

        Graphe graphe = new Graphe();
        graphe.ajouteVoisin(vp_Angers_Nantes, 0.95);
        graphe.ajouteVoisin(vp_Angers_Tours,1.2);
        graphe.ajouteVoisin(vp_Brest_Rennes,2.4);
        graphe.ajouteVoisin(vp_LeMans_Angers,0.95);
        graphe.ajouteVoisin(vp_LeMans_Paris,1.8);
        graphe.ajouteVoisin(vp_LeMans_Rennes,0.9);
        graphe.ajouteVoisin(vp_LeMans_Rouen,1.9);
        graphe.ajouteVoisin(vp_LeMans_Tours,1.1);
        graphe.ajouteVoisin(vp_Nantes_Rennes,1.05);
        graphe.ajouteVoisin(vp_Poitiers_Nantes,2.1);
        graphe.ajouteVoisin(vp_Poitiers_Tours,0.8);
        graphe.ajouteVoisin(vp_Rouen_Paris,1.2);
        graphe.ajouteVoisin(vp_Rouen_Rennes,2.8);

        System.out.println(graphe.graphe.size());
        System.out.println("Original "+graphe);
        Graphe clone = graphe.clonage();
        graphe.graphe.replace(vp_Rouen_Rennes,2.8,100000.0);
        System.out.println("==================================\nModified "+graphe);
        System.out.println("==================================\nClone "+clone);
        System.out.println(clone.graphe.size());
    }
}
class VillePair{
    public VilleClone v1;
    public VilleClone v2;
    public VillePair(VilleClone v1,VilleClone v2){
        this.v1 = v1;
        this.v2 = v2;
    }
    public VillePair getVillePair(){
        return this;
    }
    public VillePair getVillePairInverse(){
        return new VillePair(v2,v1);
    }
    public String toString(){
        return v1.toString()+"--"+v2.toString()+"--";
    }
}

class Graphe{
    public HashMap<VillePair,Double> graphe;
    public Graphe(){
        this.graphe = new HashMap<>();//一定要在这里进行初始化啊，不然的话就会出现nullPointerException！！！
    }
    public void ajouteVoisin(VillePair villePair,Double distance){
        try{
            if(!graphe.containsKey(villePair)){
                this.graphe.put(villePair.getVillePair(), distance);
                this.graphe.put(villePair.getVillePairInverse(),distance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public String toString(){
        String g = "Graphe: \n--------------\n";
        for (VillePair villePair: graphe.keySet()){
            String nom_villePair = villePair.toString();
            String distance = graphe.get(villePair).toString();
            g += villePair.toString()+distance.toString()+"\n";
        }
        return g;
    }
    public Graphe clonage(){
        Graphe clone = new Graphe();
        try{
            for(VillePair villePair:graphe.keySet()){
                String v1 = villePair.v1.toString();
                String v2 = villePair.v2.toString();
                Double distance = graphe.get(villePair);
                VillePair tempVillePair = new VillePair(new VilleClone(v1),new VilleClone(v2));
                clone.graphe.put(tempVillePair, distance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return clone;
    }
}
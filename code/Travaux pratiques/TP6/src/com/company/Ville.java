package com.company;

import java.util.HashMap;

public class Ville {
    private String nom;
    public HashMap<Ville,Double> voisins;
    public Ville(String nom){
        this.nom = nom;
        this.voisins = new HashMap<Ville, Double>();
    }
    public void ajouteVoisin(Ville ville,double distance){
        if(!voisins.containsKey(ville)){
            this.voisins.put(ville,distance);
            ville.ajouteVoisin(this,distance);
        }
    }
    public String toString(){
        return nom;
    }
    public String print_voisin(){
        String voisin = "";
        for (Ville ville: voisins.keySet()){
            String nom_ville = ville.toString();
            String distance = voisins.get(ville).toString();
            voisin += nom + "--" + distance + "--" + nom_ville + "\n";
        }
        return voisin;
    }
    public Double getDistance(Ville voisin){
        return this.voisins.get(voisin);
    }
    public static void main(String args[]){
        Ville Brest = new Ville("Brest");
        Ville Rennes = new Ville("Rennes");
        Ville Rouen = new Ville("Rouen");
        Ville Paris = new Ville("Paris");
        Ville Le_Mans = new Ville("Le Mans");
        Ville Angers = new Ville("Angers");
        Brest.ajouteVoisin(Rennes,2.4);
        Rennes.ajouteVoisin(Rouen,2.8);
        Rouen.ajouteVoisin(Paris,1.2);
        Paris.ajouteVoisin(Le_Mans,1.8);
        System.out.println(Brest.print_voisin());
    }
}

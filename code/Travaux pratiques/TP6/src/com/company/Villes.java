package com.company;

import java.util.ArrayList;
import java.util.List;

public class Villes {
    private List<Ville> listVilles;
    public Villes(){
        this.listVilles = new ArrayList<>();
    }
    public void addVille(Ville ville){
        this.listVilles.add(ville);
    }
    public String toString(){
        String graphe = "Graphe: \n";
        for(Ville ville:listVilles){
            graphe += ville.print_voisin();
        }
        return graphe;
    }

//    public Villes clonage(){
//        List<Ville> newListVilles = new ArrayList<>();
//        for(Ville ville:listVilles){
//
//        }
//    }
//    public static void main(String args[]){
//        Ville Brest = new Ville("Brest");
//        Ville Rennes = new Ville("Rennes");
//        Ville Rouen = new Ville("Rouen");
//        Ville Paris = new Ville("Paris");
//        Ville Le_Mans = new Ville("Le Mans");
//        Ville Angers = new Ville("Angers");
//        Ville Tours = new Ville("Tours");
//        Ville Poitiers = new Ville("Poitiers");
//        Ville Nantes = new Ville("Nantes");
//
//        Brest.ajouteVoisin(Rennes,2.4);
//        Rennes.ajouteVoisin(Rouen,2.8);
//        Rennes.ajouteVoisin(Le_Mans,0.9);
//        Rennes.ajouteVoisin(Nantes,1.05);
//        Rouen.ajouteVoisin(Paris,1.2);
//        Rouen.ajouteVoisin(Le_Mans,1.9);
//        Paris.ajouteVoisin(Le_Mans,1.8);
//        Le_Mans.ajouteVoisin(Rouen,1.2);
//        Tours.ajouteVoisin(Le_Mans,1.1);
//        Tours.ajouteVoisin(Poitiers,0.8);
//        Angers.ajouteVoisin(Tours,1.2);
//        Angers.ajouteVoisin(Le_Mans,0.95);
//        Nantes.ajouteVoisin(Angers,0.95);
//        Nantes.ajouteVoisin(Poitiers,2.1);
//        Nantes.ajouteVoisin(Rennes,0.15);
//
//
//        Villes listVilles = new Villes();
//        listVilles.addVille(Brest);
//        listVilles.addVille(Rennes);
//        listVilles.addVille(Rouen);
//        listVilles.addVille(Paris);
//        listVilles.addVille(Le_Mans);
//        listVilles.addVille(Angers);
//        listVilles.addVille(Tours);
//        listVilles.addVille(Poitiers);
//        listVilles.addVille(Nantes);
//
////        listVilles.completerGraphe();
//        System.out.println(listVilles);
//        System.out.println("-----------------------");
//    }
}

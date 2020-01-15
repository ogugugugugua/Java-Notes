package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.traitement.Visiteur;

public class GoPolytech extends Camera {

	public enum Couleur {Blanc, Noir};
	public enum Definition {High, Normal, Low};
	
	protected Couleur couleur;
	protected Definition definition;
	protected int prix;
	
	public GoPolytech(int prix, Couleur couleur, Definition definition) {
		this.couleur = couleur;
		this.prix = prix;
		this.definition = definition;
	}
	
	public int getPrix() {
		return prix;
	}

	public Definition getDefinition() {
		return definition;
	}

	public Couleur getCouleur() {
		return couleur;
	}
	
	@Override
	public void accept(Visiteur visiteur) {
		visiteur.visit(this);
	}
}

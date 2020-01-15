package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.GoPolytech.GoPolytech.Couleur;
import examCorrection_2014_2015.GoPolytech.GoPolytech.Definition;
import examCorrection_2014_2015.traitement.Visiteur;


public abstract class Camera {
	
	protected int prix;
	
	public abstract int getPrix();

	public abstract Definition getDefinition();

	public abstract Couleur getCouleur();
	
	public abstract void accept(Visiteur visiteur);
		
	@Override
	public String toString() {
		return "Prix: "+this.getPrix()+
				"\nDefinition: "+
				this.getDefinition()+
				"\nCouleur: "+
				this.getCouleur();
	}
}

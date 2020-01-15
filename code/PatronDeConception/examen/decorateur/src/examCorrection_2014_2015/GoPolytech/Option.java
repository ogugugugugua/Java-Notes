package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.GoPolytech.GoPolytech.Couleur;
import examCorrection_2014_2015.GoPolytech.GoPolytech.Definition;

public abstract class Option extends Camera {
	
	private Camera option;
	
	public Option(Camera option, int prix){
		this.option = option;
		this.prix = prix;
	}

	@Override
	public int getPrix() {
		return this.option.getPrix();
	}

	@Override
	public Definition getDefinition() {
		return this.option.getDefinition();
	}

	@Override
	public Couleur getCouleur() {
		return this.option.getCouleur();
	}

	public Camera getOption() {
		return option;
	}

	public void setOption(Camera option) {
		this.option = option;
	}

}

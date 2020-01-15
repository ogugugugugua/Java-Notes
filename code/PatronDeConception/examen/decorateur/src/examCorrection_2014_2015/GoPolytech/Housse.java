package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.traitement.Visiteur;

public class Housse extends Option{
	
	public Housse(Camera option, int prix) {
		super(option, prix);
	}
	
	@Override
	public void accept(Visiteur visiteur) {
		visiteur.visit(this);
	}

}

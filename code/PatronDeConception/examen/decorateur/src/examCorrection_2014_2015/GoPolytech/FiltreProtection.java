package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.traitement.Visiteur;

public class FiltreProtection extends Option{

	public FiltreProtection(Camera option, int prix) {
		super(option, prix);
	}

	@Override
	public void accept(Visiteur visiteur) {
		visiteur.visit(this);
	}

}

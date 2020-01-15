package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.GoPolytech.GoPolytech.Couleur;
import examCorrection_2014_2015.GoPolytech.GoPolytech.Definition;

public class Factory {

	public static Camera getGoldCamera(){
		return new Housse(
				new Eclairage(
						new Lentille(
								new Housse(
										new GoPolytech(1000, Couleur.Blanc, Definition.High), 10), 10), 10), 2000);
	}

	public static Camera getSilverCamera(){
		return new Eclairage(
						new CaissonEtanche(
								new GoPolytech(1000, Couleur.Blanc, Definition.High), 100), 100);
	}

	public static Camera getBronzeCamera(){
		return new Eclairage(
				new CaissonEtanche(
						new GoPolytech(1000, Couleur.Blanc, Definition.High), 100), 50);
	}
}

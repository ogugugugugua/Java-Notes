package examCorrection_2014_2015.GoPolytech;

import examCorrection_2014_2015.GoPolytech.GoPolytech.Couleur;
import examCorrection_2014_2015.GoPolytech.GoPolytech.Definition;

public class Test {

	public static void main(String[] args) {
		
		Camera c1 = new Lentille(new Housse(new GoPolytech(1000, Couleur.Blanc, Definition.High),500), 100);
		
		System.out.println(c1);

		Camera gold = Factory.getGoldCamera();
		Camera silver = Factory.getSilverCamera();
		Camera bronze = Factory.getBronzeCamera();
		
		System.out.println();
	}

}

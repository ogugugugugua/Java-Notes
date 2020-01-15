package examCorrection_2014_2015.traitement;

import examCorrection_2014_2015.GoPolytech.Camera;
import examCorrection_2014_2015.GoPolytech.Factory;

public class Test {
	public static void main(String[] args) {
		
		Camera c1 = Factory.getGoldCamera();
		Camera c2 = Factory.getSilverCamera();
		Camera c3 = Factory.getBronzeCamera();
		
		Visiteur v1 = new VisiteurSecurise();
		Visiteur v2 = new VisiteurVerification();
		Visiteur v3 = new VisiteurPrix();
		
		c1.accept(v1);
		System.out.println(v1);
		
		c1.accept(v2);
		System.out.println(v2);
		
		c1.accept(v3);
		System.out.println(v3);
		
		v1.init();
		v2.init();
		v3.init();
		
		c2.accept(v1);
		System.out.println(v1);
		
		c2.accept(v2);
		System.out.println(v2);
		
		c2.accept(v3);
		System.out.println(v3);
		
		v1.init();
		v2.init();
		v3.init();
		
		c3.accept(v1);
		System.out.println(v1);
		
		c3.accept(v2);
		System.out.println(v2);
		
		c3.accept(v3);
		System.out.println(v3);
		
	}
}

package examCorrection_2014_2015.state;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FonctionnementCamera camera = new FonctionnementCamera();
		
		camera.demarrageOn();
		camera.enregistrementOn();
		camera.enregistrementOff();
		camera.veilleOn();
		camera.veilleOff();
		camera.demarrageOff();
	}

}

package examCorrection_2014_2015.state;

public abstract class Etat {

	protected FonctionnementCamera camera;

	public Etat(FonctionnementCamera camera){
		this.camera = camera;
	}

	public void demarrageOn() {
		System.out.println("Sans Action");
	}

	public void demarrageOff() {
		System.out.println("Sans Action");
	}

	public void veilleOn() {
		System.out.println("Sans Action");
	}

	public void veilleOff() {
		System.out.println("Sans Action");
	}

	public void enregistrementOn() {
		System.out.println("Sans Action");
	}
	
	public void enregistrementOff() {
		System.out.println("Sans Action");
	}
}

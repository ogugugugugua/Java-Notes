package examCorrection_2014_2015.state;

public class FonctionnementCamera {

	private Etat courant;
	
	public FonctionnementCamera(){
		this.courant = new EtatOff(this);
	}

	public Etat getCourant() {
		return courant;
	}

	public void setCourant(Etat courant) {
		this.courant = courant;
	}

	public void demarrageOn() {
		this.courant.demarrageOn();
	}

	public void demarrageOff() {
		this.courant.demarrageOff();
	}

	public void veilleOn() {
		this.courant.veilleOn();
	}

	public void veilleOff() {
		this.courant.veilleOff();
	}

	public void enregistrementOn() {
		this.courant.enregistrementOn();
	}
	
	public void enregistrementOff() {
		this.courant.enregistrementOff();
	}
	
}

package examCorrection_2014_2015.state;

public class EtatOn extends Etat {

	public EtatOn(FonctionnementCamera camera) {
		super(camera);
	}

	@Override
	public void demarrageOff() {
		System.out.println("Etat On -> Etat Off");
		this.camera.setCourant(new EtatOff(this.camera));
	}

	@Override
	public void veilleOn() {
		System.out.println("Etat On -> Etat Veille On");
		this.camera.setCourant(new EtatVeille(this.camera));
	}

	@Override
	public void enregistrementOn() {
		System.out.println("Etat On -> Etat Enregistrement On");
		this.camera.setCourant(new EtatEnregistrement(this.camera));
	}

}

package examCorrection_2014_2015.state;

public class EtatOff extends Etat {

	public EtatOff(FonctionnementCamera camera) {
		super(camera);
	}

	@Override
	public void demarrageOn() {
		System.out.println("Etat Off -> Etat On");
		this.camera.setCourant(new EtatOn(this.camera));
	}

}

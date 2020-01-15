package examCorrection_2014_2015.state;

public class EtatVeille extends Etat {

	public EtatVeille(FonctionnementCamera camera) {
		super(camera);
	}

	@Override
	public void veilleOff() {
		System.out.println("Etat Veille On -> Etat On");
		this.camera.setCourant(new EtatOn(this.camera));
	}
}

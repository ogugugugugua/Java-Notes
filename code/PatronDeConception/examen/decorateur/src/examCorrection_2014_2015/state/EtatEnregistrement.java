package examCorrection_2014_2015.state;

public class EtatEnregistrement extends Etat {

	public EtatEnregistrement(FonctionnementCamera camera) {
		super(camera);
	}

	@Override
	public void enregistrementOff() {
		System.out.println("Etat Enregistrement -> Etat On");
		this.camera.setCourant(new EtatOn(this.camera));
	}

}

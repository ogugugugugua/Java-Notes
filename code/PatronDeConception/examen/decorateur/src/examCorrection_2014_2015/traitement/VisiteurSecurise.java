package examCorrection_2014_2015.traitement;

import examCorrection_2014_2015.GoPolytech.CaissonEtanche;
import examCorrection_2014_2015.GoPolytech.Eclairage;
import examCorrection_2014_2015.GoPolytech.FiltreProtection;
import examCorrection_2014_2015.GoPolytech.Housse;
import examCorrection_2014_2015.GoPolytech.Lentille;
import examCorrection_2014_2015.GoPolytech.GoPolytech;

public class VisiteurSecurise extends Visiteur {
	
	private boolean isSecurise;

	@Override
	public void visit(GoPolytech camera) {
	}

	@Override
	public void visit(Lentille camera) {
		camera.getOption().accept(this);
	}

	@Override
	public void visit(Housse camera) {
		camera.getOption().accept(this);
		this.isSecurise = true;
	}

	@Override
	public void visit(Eclairage camera) {
		camera.getOption().accept(this);
	}

	@Override
	public void visit(CaissonEtanche camera) {
		camera.getOption().accept(this);
	}

	@Override
	public String toString() {
		return "VisiteurSecurise [isSecurise=" + isSecurise + "]";
	}

	@Override
	public void visit(FiltreProtection camera) {
		camera.getOption().accept(this);
	}

	@Override
	public void init() {
		this.isSecurise = false;
	}
	
	
}

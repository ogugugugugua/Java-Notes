package examCorrection_2014_2015.traitement;

import examCorrection_2014_2015.GoPolytech.CaissonEtanche;
import examCorrection_2014_2015.GoPolytech.Eclairage;
import examCorrection_2014_2015.GoPolytech.FiltreProtection;
import examCorrection_2014_2015.GoPolytech.Housse;
import examCorrection_2014_2015.GoPolytech.Lentille;
import examCorrection_2014_2015.GoPolytech.GoPolytech;

public class VisiteurVerification extends Visiteur {
	
	private boolean isCorrect;
	
	private int nbrLentille;
	private int nbrHousse;
	private int nbrCaisson;
	private int nbrFiltre;
	private int nbrEclairage;
	
	@Override
	public void visit(Lentille camera) {
		this.nbrLentille++;
		camera.getOption().accept(this);
	}
	
	@Override
	public void visit(Housse camera) {
		this.nbrHousse++;
		camera.getOption().accept(this);
	}
	
	@Override
	public void visit(Eclairage camera) {
		this.nbrEclairage++;
		camera.getOption().accept(this);
	}
	
	@Override
	public void visit(CaissonEtanche camera) {
		this.nbrCaisson++;
		camera.getOption().accept(this);
	}
	
	@Override
	public void visit(GoPolytech camera) {
		// si deux classes de bases, alors mauvaise solution, plutot le faire dans une methode dédiée
		this.isCorrect = (this.nbrCaisson<2 && this.nbrEclairage<2 && this.nbrFiltre<2 && this.nbrHousse<2 && this.nbrLentille<2);
	}
	
	@Override
	public void visit(FiltreProtection camera) {
		this.nbrFiltre++;
		camera.getOption().accept(this);
	}
		
	@Override
	public String toString() {
		return "isCorrect: " + isCorrect;
	}

	@Override
	public void init() {
		this.isCorrect = false;
		this.nbrCaisson = 0;
		this.nbrEclairage = 0;
		this.nbrFiltre = 0;
		this.nbrHousse = 0;
		this.nbrLentille = 0;
	}

}

package examCorrection_2014_2015.traitement;

import examCorrection_2014_2015.GoPolytech.CaissonEtanche;
import examCorrection_2014_2015.GoPolytech.Eclairage;
import examCorrection_2014_2015.GoPolytech.FiltreProtection;
import examCorrection_2014_2015.GoPolytech.GoPolytech;
import examCorrection_2014_2015.GoPolytech.Housse;
import examCorrection_2014_2015.GoPolytech.Lentille;

public class VisiteurPrix extends Visiteur {
	
	private double prix;

	private int nbrElement;
	
	private boolean isHousse;
	
	
	@Override
	public void visit(GoPolytech camera) {
		this.prix += camera.getPrix();
		this.computeRemise();
	}

	@Override
	public void visit(Lentille camera) {
		nbrElement++;
		this.prix += camera.getPrix();
		camera.getOption().accept(this);
	}

	@Override
	public void visit(Housse camera) {
		isHousse = true;
		nbrElement++;
		this.prix += camera.getPrix();
		camera.getOption().accept(this);
	}

	@Override
	public void visit(Eclairage camera) {
		nbrElement++;
		this.prix += camera.getPrix();
		camera.getOption().accept(this);
	}

	@Override
	public void visit(CaissonEtanche camera) {
		nbrElement++;
		this.prix += camera.getPrix();
		camera.getOption().accept(this);
	}

	@Override
	public String toString() {
		return "VisiteurPrix [prix=" + prix + "]";
	}

	public void computeRemise(){
		if(this.nbrElement > 3){
			this.prix = this.prix - this.prix/(double)100*20;
		}else if(this.nbrElement > 5){
			this.prix = this.prix - this.prix/(double)100*30;
		}
	}
	
	@Override
	public void visit(FiltreProtection camera) {
		nbrElement++;
		this.prix += camera.getPrix();
		camera.getOption().accept(this);
	}

	@Override
	public void init() {
		this.isHousse = false;
		this.nbrElement = 0;
		this.prix = 0;
		
	}
}

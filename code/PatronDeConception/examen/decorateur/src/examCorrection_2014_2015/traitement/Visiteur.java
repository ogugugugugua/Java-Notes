package examCorrection_2014_2015.traitement;

import examCorrection_2014_2015.GoPolytech.CaissonEtanche;
import examCorrection_2014_2015.GoPolytech.Eclairage;
import examCorrection_2014_2015.GoPolytech.FiltreProtection;
import examCorrection_2014_2015.GoPolytech.Housse;
import examCorrection_2014_2015.GoPolytech.Lentille;
import examCorrection_2014_2015.GoPolytech.GoPolytech;

public abstract class Visiteur {
	
	public abstract void visit(GoPolytech camera);
	public abstract void visit(Lentille camera);
	public abstract void visit(Housse camera);
	public abstract void visit(Eclairage camera);
	public abstract void visit(FiltreProtection camera);
	public abstract void visit(CaissonEtanche camera);
	
	public abstract void init();
}

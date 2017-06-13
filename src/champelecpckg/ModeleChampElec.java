package champelecpckg;

import java.util.ArrayList;
import java.util.HashSet;

import basepckg.Forme;
import basepckg.Modele;
import basepckg.Particule;

public class ModeleChampElec extends Modele {
	
	public double[] E;

	public ModeleChampElec(Forme forme, double[] E) {
		super(forme);
		this.E = E;
	}

	public void interaction(Particule p1, HashSet<Particule> liste_particule, ArrayList<Forme> liste_forme) {
		
	}

	@Override
	public void actionP(Particule p) {
		Electron e = (Electron) p;
		double[] a = e.getA();
		a[0] = e.getAttributs().get("charge") * E[0] / e.getAttributs().get("masse");
		a[1] = e.getAttributs().get("charge") * E[1] / e.getAttributs().get("masse");
		p.setA(a);
	}
}

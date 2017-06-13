package gravitypckg;

import java.util.ArrayList;

import basepckg.Forme;
import basepckg.Modele;
import basepckg.Particule;

public class Newton extends Modele {
	
	public Newton(Forme forme) {
		super(forme);
	}

	double G = 6.67e-4;

	public void interaction(Particule p1, ArrayList<Particule> liste_particule, ArrayList<Forme> liste_forme) {
		double Fx = 0;
		double Fy = 0;
		for (Particule p2 : liste_particule) {
			if (p1 != p2 && forme.inForme(p2.getPos())) {
				double dist = p1.distance(p2);
				double cos = p1.cos_alpha(p2);
				double sin = p1.sin_alpha(p2);

				Fx += G * p2.getAttributs().get("masse") * cos / dist / dist;
				Fy += G * p2.getAttributs().get("masse") * sin / dist / dist;
			}
		}
		p1.setA(p1.getA()[0] + Fx, p1.getA()[1] + Fy);
	}

	@Override
	public void actionP(Particule p) {

	}
}

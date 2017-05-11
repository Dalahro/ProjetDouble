package gravitypckg;

import java.util.ArrayList;

import basepckg.Modele;
import basepckg.Particule;

public class Newton extends Modele {

	double G = 6.67;

	@Override
	public void interaction(Particule p1, ArrayList<Particule> liste_particule) {
		double Fx = 0;
		double Fy = 0;
		for (Particule p2 : liste_particule) {
			if (p1 != p2) {
				double dist = p1.distance(p2);
				double cos = p1.cos_alpha(p2);
				double sin = p1.sin_alpha(p2);

				Fx += G * p2.getM() * cos / dist / dist;
				Fy += G * p2.getM() * sin / dist / dist;
			}
		}
		System.out.println(Fx);
		p1.setA(p1.getA()[0] + Fx, p1.getA()[1] + Fy);
	}

	@Override
	public void interaction2P(Particule p1, Particule p2) {
	}

	@Override
	public void actionP(Particule p) {

	}

}

package bibliopckg;

import java.util.ArrayList;
import java.util.HashSet;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Modele;
import basepckg.Pair;
import basepckg.Particule;
import basepckg.Simulation;

public class CollisionForme extends Modele {

	public CollisionForme(Forme forme) {
		super(forme);
	}

	public void interaction(Particule p1, HashSet<Particule> liste_particule, ArrayList<Forme> liste_forme) {
		double[] vit = p1.getV();
		for (Forme f : liste_forme) {
			Pair<Double, Object> pair = f.hitBorder(p1); // si defini collision sur la forme
			double time_min = pair.getFirst();
			double[] v_min = (double[]) pair.getSecond();
			if (time_min < Simulation.h) {
				p1.update_pos(time_min);
				double[] vect_norm = Maths.normalize(Maths.vectNorm(v_min));
				double[] vect_tan = Maths.vectTan(vect_norm);

				double vit_n = -Maths.prodScalaire(vect_norm, vit);
				double vit_t = Maths.prodScalaire(vect_tan, vit);

				double[] vitn_p = Maths.multScalaire(vect_norm, vit_n);
				double[] vitt_p = Maths.multScalaire(vect_tan, vit_t);

				p1.setV(Maths.addVect(vitn_p, vitt_p));
				p1.update_pos(Simulation.h - time_min);
			}
		}
	}

	public void actionP(Particule p) {

	}
}

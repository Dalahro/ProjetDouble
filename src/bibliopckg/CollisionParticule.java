package bibliopckg;

import java.util.ArrayList;
import java.util.HashSet;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Modele;
import basepckg.Particule;
import basepckg.Simulation;

public class CollisionParticule extends Modele {

	private double R = 1;
	private static final double INFINITY = Double.POSITIVE_INFINITY;

	public CollisionParticule(double R, Forme forme){
		super(forme);
		this.R = R;
	}
	public void interaction(Particule p1, HashSet<Particule> liste_particule, ArrayList<Forme> liste_forme) {
		Particule p_plusproche = null;
		double t_min = INFINITY;
		double t = 0;
		for (Particule p2 : liste_particule) {
			if (p2 != p1 && forme.inForme(p2.getPos())) {
				t = timeToHit(p1, p2);
				// System.out.println(t);
				if (t < t_min) {
					t_min = t;
					p_plusproche = p2;
				}
			}
		}
		if (t_min < Simulation.h) {
			p1.update_pos(t_min);
			hit(p1, p_plusproche);
			p1.update_pos(Simulation.h - t_min);
		}
	}

	public void hit(Particule p1, Particule p2) {
		double[] pos1 = p1.getPos();
		double[] pos2 = p2.getPos();
		double[] v1 = p1.getV();
		double[] v2 = p2.getV();
		double m1 = p1.getAttributs().get("masse");
		double m2 = p2.getAttributs().get("masse");

		double[] vect_norm = Maths.normalize(Maths.subVect(pos2, pos1));
		double[] vect_tan = Maths.vectTan(vect_norm);

		double v1n = Maths.prodScalaire(vect_norm, v1);
		double v1t = Maths.prodScalaire(vect_tan, v1);
		double v2n = Maths.prodScalaire(vect_norm, v2);
		double v2t = Maths.prodScalaire(vect_tan, v2);

		double v1t_ps = v1t;
		double v2t_ps = v2t;

		double v1n_ps = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
		double v2n_ps = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

		double[] v1n_p = Maths.multScalaire(vect_norm, v1n_ps);
		double[] v1t_p = Maths.multScalaire(vect_tan, v1t_ps);
		double[] v2n_p = Maths.multScalaire(vect_norm, v2n_ps);
		double[] v2t_p = Maths.multScalaire(vect_tan, v2t_ps);

		double[] v1_p = Maths.addVect(v1n_p, v1t_p);
		double[] v2_p = Maths.addVect(v2n_p, v2t_p);

		// Collision inélastique mais marche pas trop
		double vx_cm = (m1 * v1[0] + m2 * v2[0]) / (m1 + m2);
		double vy_cm = (m1 * v1[1] + m2 * v2[1]) / (m1 + m2);

		v1_p[0] = (v1_p[0] - vx_cm) * R + vx_cm;
		v1_p[1] = (v1_p[1] - vy_cm) * R + vy_cm;
		v2_p[0] = (v2_p[0] - vx_cm) * R + vx_cm;
		v2_p[1] = (v2_p[1] - vy_cm) * R + vy_cm;

		p1.setV(v1_p);
		p2.setV(v2_p);
	}

	public void actionP(Particule p) {

	}

	public double timeToHit(Particule p1, Particule p2) {
		double dx = p2.getPos()[0] - p1.getPos()[0];
		double dy = p2.getPos()[1] - p1.getPos()[1];
		double dvx = p2.getV()[0] - p1.getV()[0];
		double dvy = p2.getV()[1] - p1.getV()[1];
		double dvdr = dx * dvx + dy * dvy;
		if (dvdr > 0) {
			return INFINITY;
		}
		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = p1.getRadius() + p2.getRadius();
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		/*
		 * if (drdr < sigma * sigma) { System.out.println(
		 * "overlapping particles" + drdr); }
		 */
		if (d < 0) {
			return INFINITY;
		}
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

}

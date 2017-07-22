package bibliopckg;

import java.util.List;
import java.util.Set;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Modele;
import basepckg.Particule;
import basepckg.Simulation;

public class CollisionParticule extends Modele {

	private double r = 1;
	private static final double INFINITY = Double.POSITIVE_INFINITY;

	public CollisionParticule(double r, Forme forme){
		super(forme);
		this.r = r;
	}
	public void interaction(Particule p1, Set<Particule> listeParticule, List<Forme> listeForme) {
		Particule partPlusProche = null;
		double tMin = INFINITY;
		double t = 0;
		for (Particule p2 : listeParticule) {
			if (p2 != p1 && forme.inForme(p2.getPos())) {
				t = timeToHit(p1, p2);
				if (t < tMin) {
					tMin = t;
					partPlusProche = p2;
				}
			}
		}
		if (tMin < Simulation.H) {
			p1.updatePos(tMin);
			hit(p1, partPlusProche);
			p1.updatePos(Simulation.H - tMin);
		}
	}

	public void hit(Particule p1, Particule p2) {
		double[] pos1 = p1.getPos();
		double[] pos2 = p2.getPos();
		double[] v1 = p1.getV();
		double[] v2 = p2.getV();
		double m1 = p1.getAttributs().get("masse");
		double m2 = p2.getAttributs().get("masse");

		double[] vectNorm = Maths.normalize(Maths.subVect(pos2, pos1));
		double[] vectTan = Maths.vectTan(vectNorm);

		double v1n = Maths.prodScalaire(vectNorm, v1);
		double v1t = Maths.prodScalaire(vectTan, v1);
		double v2n = Maths.prodScalaire(vectNorm, v2);
		double v2t = Maths.prodScalaire(vectTan, v2);

		double v1tPs = v1t;
		double v2tPs = v2t;

		double v1nPs = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
		double v2nPs = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

		double[] v1nP = Maths.multScalaire(vectNorm, v1nPs);
		double[] v1tP = Maths.multScalaire(vectTan, v1tPs);
		double[] v2nP = Maths.multScalaire(vectNorm, v2nPs);
		double[] v2tP = Maths.multScalaire(vectTan, v2tPs);

		double[] v1P = Maths.addVect(v1nP, v1tP);
		double[] v2P = Maths.addVect(v2nP, v2tP);

		//Collision inélastique
		double vxCm = (m1 * v1[0] + m2 * v2[0]) / (m1 + m2);
		double vyCm = (m1 * v1[1] + m2 * v2[1]) / (m1 + m2);

		v1P[0] = (v1P[0] - vxCm) * r + vxCm;
		v1P[1] = (v1P[1] - vyCm) * r + vyCm;
		v2P[0] = (v2P[0] - vxCm) * r + vxCm;
		v2P[1] = (v2P[1] - vyCm) * r + vyCm;

		p1.setV(v1P);
		p2.setV(v2P);
	}

	public void actionP(Particule p) {
		//no action here
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
		
		if (d < 0) {
			return INFINITY;
		}
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

}

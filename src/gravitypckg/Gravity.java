package gravitypckg;

import java.awt.Color;
import java.util.ArrayList;

import basepckg.Cercle;
import basepckg.Forme;
import basepckg.Milieu;
import basepckg.Particule;
import basepckg.Polygone;
import basepckg.Simulation;

public class Gravity extends Simulation {

	private double G = 6.67;

	public Gravity() {
		super();
		R = 0.5;
		simuler();
	}

	@Override
	public double[][] interraction2P(Particule p1, Particule p2) {
		if (!(p1.equals(p2))) {
			double dist = p1.distance(p2);
			double cos = p1.cos_alpha(p2);
			double sin = p1.sin_alpha(p2);

			double Fx = G * p1.getM() * p2.getM() * cos / dist / dist;
			double Fy = G * p1.getM() * p2.getM() * sin / dist / dist;

			double[][] d = { { 0, 0 }, { 0, 0 }, { Fx, Fy } };
			return d;
		} else {
			double[][] d = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
			return d;
		}
	}

	@Override
	public void createDefaultMilieu() {
		ArrayList<double[]> points0 = new ArrayList<double[]>();
		double[] d0 = { 0, 0 };
		double[] d01 = { Simulation.WIDTH, 0 };
		double[] d02 = { Simulation.WIDTH, Simulation.HEIGHT };
		double[] d03 = { 0, Simulation.HEIGHT };
		points0.add(d0);
		points0.add(d01);
		points0.add(d02);
		points0.add(d03);
		double[] Kpos0 = { 0, 0 };
		double[] Kv0 = { 0, 0 };
		double[] Ka0 = { 0, 0 };
		m0 = new Milieu(new Polygone(points0), Kpos0, Kv0, Ka0);
		m0.setColor(Color.gray);
		liste_milieu.add(m0);
	}

	@Override
	public void createMilieux() {
		/*ArrayList<double[]> points = new ArrayList<double[]>();
		double[] d0 = { 0, 250 };
		double[] d01 = { Simulation.WIDTH, 250 };
		double[] d02 = { Simulation.WIDTH, 300 };
		double[] d03 = { 0, 300 };
		points.add(d0);
		points.add(d01);
		points.add(d02);
		points.add(d03);
		double[] Kpos0 = { 0, 0 };
		double[] Kv0 = { 0, 0 };
		double[] Ka0 = { 0, 0 };
		Milieu m = new Milieu(new Polygone(points), Kpos0, Kv0, Ka0);
		m.setColor(Color.black);
		liste_milieu.add(m);*/

		double[] Kpos1 = { 10, 10 };
		double[] Kv1 = { 0, 0 };
		double[] Ka1 = { -10*250, -10*250 };
		Milieu m1 = new Milieu(new Cercle(100, 250, 250), Kpos1, Kv1, Ka1);
		m1.setColor(Color.red);
		liste_milieu.add(m1);

	}

	@Override
	public void createParticule() {
		for (int i = 0; i < 20; i++) {
			ajouterParticule(new Particule());
		}
	}

}

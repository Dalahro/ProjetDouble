package gravitypckg;

import java.awt.Color;
import java.util.ArrayList;

import basepckg.Milieu;
import basepckg.Particule;
import basepckg.Simulation;
import bibliopckg.Cercle;
import bibliopckg.CollisionParticule;
import bibliopckg.Polygone;

public class Gravity extends Simulation {

	public Gravity() {
		super();
		simuler();
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
		m0 = new Milieu(new Polygone(points0));
		m0.setColor(Color.gray);
		m0.liste_modele.add(new CollisionParticule(0));
		m0.liste_modele.add(new Newton());
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

		Milieu m1 = new Milieu(new Cercle(100, 250, 250));
		m1.setColor(Color.red);
		liste_milieu.add(m1);

	}

	@Override
	public void createParticule() {
		for (int i = 0; i < 10; i++) {
			ajouterParticule(new Particule());
		}
	}

}

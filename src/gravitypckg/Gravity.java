package gravitypckg;

import java.awt.Color;
import java.util.ArrayList;

import basepckg.Milieu;
import basepckg.Simulation;
import bibliopckg.Cercle;
import bibliopckg.CollisionParticule;
import bibliopckg.GenerateurUniform;
import bibliopckg.ModeleDisparition;
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
		Milieu m0 = new Milieu(new Polygone(points0));
		m0.setColor(Color.gray);
		m0.liste_modele.add(new CollisionParticule(0.1, m0.getForme()));
		m0.liste_modele.add(new ModeleDisparition(m0.getForme(), this));
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
		Polygone poly = new Polygone(points);
		Milieu m = new Milieu(poly);
		m.setColor(Color.black);
		m.liste_modele.add(new CollisionForme(m.getForme()));
		m.liste_modele.add(new ModeleDisparition(m.getForme(), this));
		liste_milieu.add(m);*/
		

		Milieu m1 = new Milieu(new Cercle(200, 250, 250));
		m1.liste_modele.add(new Newton(m1.getForme()));
		m1.setColor(new Color(0, 0, 255, 50));
		liste_milieu.add(m1);

		FactoryPlanete facto = new FactoryPlanete();
		liste_generateur.add(new GenerateurUniform(m1.getForme(), facto, 0, 2000));		

		m1.liste_modele.add(new ModeleDisparition(m1.getForme(), this));
	}

	@Override
	public void createParticule() {	
		ajouterParticule(new Planete(250, 250 , 0, 0, 10000000));
	}

}

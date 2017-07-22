package gravitypckg;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import basepckg.Milieu;
import basepckg.Simulation;
import bibliopckg.Cercle;
import bibliopckg.CollisionForme;
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
		List<double[]> points0 = new ArrayList<>();
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
		m0.getListeModele().add(new CollisionParticule(0.1, m0.getForme()));
		m0.getListeModele().add(new ModeleDisparition(m0.getForme(), this));
		listeMilieu.add(m0);
	}

	@Override
	public void createMilieux() {
		Milieu m1 = new Milieu(new Cercle(200, 250, 250));
		m1.getListeModele().add(new Newton(m1.getForme()));
		m1.getListeModele().add(new CollisionForme(m1.getForme()));
		m1.setColor(new Color(0, 0, 255, 50));
		listeMilieu.add(m1);

		FactoryPlanete facto = new FactoryPlanete();
		listeGenerateur.add(new GenerateurUniform(m1.getForme(), facto, 500, 20));		

		m1.getListeModele().add(new ModeleDisparition(m1.getForme(), this));
	}

	@Override
	public void createParticule() {	
		ajouterParticule(new Planete(250, 250 , 0, 0, 10000000));
	}

}

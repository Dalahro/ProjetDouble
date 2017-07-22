package champelecpckg;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import basepckg.Milieu;
import basepckg.Simulation;
import bibliopckg.CollisionParticule;
import bibliopckg.ModeleDisparition;
import bibliopckg.Polygone;

public class ChampElec extends Simulation {

	public ChampElec() {
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
		List<double[]> points = new ArrayList<>();
		double[] d = { 100, 0 };
		double[] d1 = { Simulation.WIDTH - 100, 0 };
		double[] d2 = { Simulation.WIDTH - 100, Simulation.HEIGHT };
		double[] d3 = { 100, Simulation.HEIGHT };
		points.add(d);
		points.add(d1);
		points.add(d2);
		points.add(d3);
		Polygone poly = new Polygone(points);
		Milieu m = new Milieu(poly);
		m.setColor(new Color(0, 170, 0, 120));
		double[] e = {0, 5e-12};
		m.getListeModele().add(new Lorentz(m.getForme(), e));
		listeMilieu.add(m);

		List<double[]> points1 = new ArrayList<>();
		double[] d10 = { 0, 225 };
		double[] d11 = { 50, 225 };
		double[] d12 = { 50, 275 };
		double[] d13 = { 0, 275 };
		points1.add(d10);
		points1.add(d11);
		points1.add(d12);
		points1.add(d13);
		Polygone poly1 = new Polygone(points1);
		Milieu m1 = new Milieu(poly1);
		m1.setColor(new Color(255, 0, 0, 120));
		listeMilieu.add(m1);

		FactoryElectron facto = new FactoryElectron();
		listeGenerateur.add(new GenerateurLigne(m1.getForme(), facto, 500, 200, 50, 250));

	}

	@Override
	public void createParticule() {
		//nothing here
	}

}

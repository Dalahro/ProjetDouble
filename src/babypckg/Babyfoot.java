package babypckg;

import java.awt.Color;
import java.util.ArrayList;

import basepckg.Milieu;
import basepckg.Simulation;
import bibliopckg.CollisionForme;
import bibliopckg.Polygone;

public class Babyfoot extends Simulation {

	public Babyfoot() {
		super();
		simuler();
	}

	@Override
	public void createDefaultMilieu() {
		ArrayList<double[]> points0 = new ArrayList<double[]>();
		double[] d0 = { 100, 0 };
		double[] d01 = { 400, 0 };
		double[] d02 = { 400, Simulation.HEIGHT };
		double[] d03 = { 100, Simulation.HEIGHT };
		points0.add(d0);
		points0.add(d01);
		points0.add(d02);
		points0.add(d03);
		Milieu m0 = new Milieu(new Polygone(points0));
		m0.setColor(new Color(0, 180, 0, 150));
		m0.liste_modele.add(new CollisionForme(m0.getForme()));
		liste_milieu.add(m0);
	}

	@Override
	public void createMilieux() {
		Joueur j1 = new Joueur(245, 10, 1);
		liste_milieu.add(j1.m);
		
		Joueur j21 = new Joueur(195, 73, 1);
		liste_milieu.add(j21.m);
		Joueur j22 = new Joueur(295, 73, 1);
		liste_milieu.add(j22.m);
		
		Joueur j31 = new Joueur(170, 142, 2);
		liste_milieu.add(j31.m);
		Joueur j32 = new Joueur(245, 142, 2);
		liste_milieu.add(j32.m);
		Joueur j33 = new Joueur(320, 142, 2);
		liste_milieu.add(j33.m);		

		
		Joueur j51 = new Joueur(145, 209, 1);
		liste_milieu.add(j51.m);
		Joueur j52 = new Joueur(195, 209, 1);
		liste_milieu.add(j52.m);
		Joueur j53 = new Joueur(245, 209, 1);
		liste_milieu.add(j53.m);
		Joueur j54 = new Joueur(295, 209, 1);
		liste_milieu.add(j54.m);
		Joueur j55 = new Joueur(345, 209, 1);
		liste_milieu.add(j55.m);


		Joueur j1b = new Joueur(245, 480, 2);
		liste_milieu.add(j1b.m);
		
		Joueur j21b = new Joueur(195, 417, 2);
		liste_milieu.add(j21b.m);
		Joueur j22b = new Joueur(295, 417, 2);
		liste_milieu.add(j22b.m);
		
		Joueur j31b = new Joueur(170, 348, 1);
		liste_milieu.add(j31b.m);
		Joueur j32b = new Joueur(245, 348, 1);
		liste_milieu.add(j32b.m);
		Joueur j33b = new Joueur(320, 348, 1);
		liste_milieu.add(j33b.m);		

		
		Joueur j51b = new Joueur(145, 281, 2);
		liste_milieu.add(j51b.m);
		Joueur j52b = new Joueur(195, 281, 2);
		liste_milieu.add(j52b.m);
		Joueur j53b = new Joueur(245, 281, 2);
		liste_milieu.add(j53b.m);
		Joueur j54b = new Joueur(295, 281, 2);
		liste_milieu.add(j54b.m);
		Joueur j55b = new Joueur(345, 281, 2);
		liste_milieu.add(j55b.m);
		
		But but1 = new But(215, 0, this);
		liste_milieu.add(but1.m);
		But but2 = new But(215, 490, this);
		liste_milieu.add(but2.m);
		
	

		FactoryBalle facto = new FactoryBalle();
		liste_generateur.add(new GenerateurTir(but1.m.getForme(), facto, 500, 30, 250, 450));		

		//m1.liste_modele.add(new ModeleDisparition(m1.getForme(), this));
	}

	@Override
	public void createParticule() {	
	}

}

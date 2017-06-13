package babypckg;

import java.awt.Color;
import java.util.ArrayList;

import basepckg.Milieu;
import bibliopckg.CollisionForme;
import bibliopckg.Polygone;

public class Joueur {
	Milieu m = null;

	public Joueur(int x, int y, int equipe) {
		ArrayList<double[]> points0 = new ArrayList<double[]>();
		double[] d0 = { x, y };
		double[] d01 = { x + 10, y };
		double[] d02 = { x + 10, y + 10 };
		double[] d03 = { x, y + 10 };
		points0.add(d0);
		points0.add(d01);
		points0.add(d02);
		points0.add(d03);
		Polygone poly0 = new Polygone(points0);
		m = new Milieu(poly0);
		if (equipe == 1) {
			m.setColor(Color.blue);
		} else {
			m.setColor(Color.red);
		}
		m.liste_modele.add(new CollisionForme(m.getForme()));

	}
}

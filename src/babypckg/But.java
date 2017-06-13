package babypckg;

import java.awt.Color;
import java.util.ArrayList;

import basepckg.Milieu;
import basepckg.Simulation;
import bibliopckg.ModeleDisparition;
import bibliopckg.Polygone;

public class But {
	Milieu m = null;

	public But(int x, int y, Simulation simulation) {
		ArrayList<double[]> points0 = new ArrayList<double[]>();
		double[] d0 = { x, y };
		double[] d01 = { x + 70, y };
		double[] d02 = { x + 70, y + 10 };
		double[] d03 = { x, y + 10 };
		points0.add(d0);
		points0.add(d01);
		points0.add(d02);
		points0.add(d03);
		Polygone poly0 = new Polygone(points0);
		m = new Milieu(poly0);
		m.setColor(Color.black);
		m.liste_modele.add(new ModeleDisparition(m.getForme(), simulation));

	}
}

package bibliopckg;

import java.awt.Graphics;

import basepckg.Forme;
import basepckg.Pair;
import basepckg.Particule;

public class Cercle extends Forme {

	double r;
	double x, y;

	public Cercle(double r, double x, double y) {
		super();
		this.r = r;
		this.x = x;
		this.y = y;
	}

	public boolean inForme(double[] pos) {
		double d = Math.sqrt(Math.pow((x - pos[0]), 2) + Math.pow((y - pos[1]), 2));
		if (d <= r) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		int r1 = 1;
		if (r > 1) {
			r1 = (int) r;
		}
		g.fillOval((int) x - r1, (int) y -r1, r1 * 2, r1 * 2);
	}

	public Pair<Double, Object> hitBorder(Particule p) {
		return null;		
	}

	@Override
	public double[] getEnveloppe() {
		double[] enveloppe = { x - r, y-r, r*2,r*2};
		return enveloppe;
	}

}

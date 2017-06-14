package bibliopckg;

import java.awt.Graphics;

import basepckg.Forme;
import basepckg.Maths;
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
		g.fillOval((int) x - r1, (int) y - r1, r1 * 2, r1 * 2);
	}

	public Pair<Double, Object> hitBorder(Particule p) {
		double[] d = Maths.vectToDroite(p.getV(), p.getPos());
		double[] pos = p.getPos();
		double t_min = Double.POSITIVE_INFINITY;
		double x_inter = 0, y_inter = 0;
		if (d[0] != 0) {
			double A = d[1] * d[1] / d[0] / d[0] + 1;
			double B = 2 * d[1] * d[2] / d[0] / d[0] + 2 * x / d[0] * d[1] - 2 * y;
			double C = d[2] * d[2] / d[0] / d[0] + 2 * x / d[0] * d[2] + y * y - r * r + x * x;

			double delta = B * B - 4 * A * C;
			if (delta == 0) {
				y_inter = -B / 2 / A;
				x_inter = (-d[1] * y_inter - d[2]) / d[0];
				double dist = Math
						.sqrt((pos[0] - x_inter) * (pos[0] - x_inter) + (pos[1] - y_inter) * (pos[1] - y_inter));
				t_min = dist / Maths.norme(p.getV());
			} else if (delta > 0) {
				double y1 = (-B + Math.sqrt(delta)) / 2 / A;
				double y2 = (-B - Math.sqrt(delta)) / 2 / A;
				
				double x1 = (-d[1] * y1 - d[2]) / d[0];
				double x2 = (-d[1] * y2 - d[2]) / d[0];

				double d1 = Math.sqrt((pos[0] - x1) * (pos[0] - x1) + (pos[1] - y1) * (pos[1] - y1)) - p.getRadius();
				double d2 = Math.sqrt((pos[0] - x2) * (pos[0] - x2) + (pos[1] - y2) * (pos[1] - y2)) - p.getRadius();

				double t1 = d1 / Maths.norme(p.getV());
				double t2 = d2 / Maths.norme(p.getV());

				if (t1 < t2) {
					y_inter = y1;
					x_inter = x1;
					t_min = t1;
				} else {
					y_inter = y2;
					x_inter = x2;
					t_min = t2;
				}
			}
		} else {
			if (d[1] != 0) {
				y_inter = -d[2] / d[1];
				x_inter = Math.sqrt(r * r - (y_inter - y) * (y_inter - y)) + x;
				System.out.println(x_inter + " " + y_inter);
				double dist = Math
						.sqrt((pos[0] - x_inter) * (pos[0] - x_inter) + (pos[1] - y_inter) * (pos[1] - y_inter))
						- p.getRadius();
				t_min = dist / Maths.norme(p.getV());
			}
		}
		if (x_inter != 0 || y_inter != 0) {
			double[] v_min = { x - x_inter, y - y_inter };
			// System.out.println(t_min);
			return new Pair<Double, Object>(t_min, Maths.vectTan(v_min));
		} else {
			return new Pair<Double, Object>(t_min, null);
		}
	}

	@Override
	public double[] getEnveloppe() {
		double[] enveloppe = { x - r, y - r, r * 2, r * 2 };
		return enveloppe;
	}

}

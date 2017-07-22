package bibliopckg;

import java.awt.Graphics;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Pair;
import basepckg.Particule;

public class Cercle extends Forme {

	double r;
	double x;
	double y;

	public Cercle(double r, double x, double y) {
		super();
		this.r = r;
		this.x = x;
		this.y = y;
	}

	public boolean inForme(double[] pos) {
		double d = Math.sqrt(Math.pow((x - pos[0]), 2) + Math.pow((y - pos[1]), 2));
		return d <= r;
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
		double tMin = Double.POSITIVE_INFINITY;
		double xInter = 0; 
		double yInter = 0;
		if (d[0] != 0) {
			double a = d[1] * d[1] / d[0] / d[0] + 1;
			double b = 2 * d[1] * d[2] / d[0] / d[0] + 2 * x / d[0] * d[1] - 2 * y;
			double c = d[2] * d[2] / d[0] / d[0] + 2 * x / d[0] * d[2] + y * y - r * r + x * x;

			double delta = b * b - 4 * a * c;
			if (delta == 0) {
				yInter = -b / 2 / a;
				xInter = (-d[1] * yInter - d[2]) / d[0];
				double dist = Math
						.sqrt((pos[0] - xInter) * (pos[0] - xInter) + (pos[1] - yInter) * (pos[1] - yInter));
				tMin = dist / Maths.norme(p.getV());
			} else if (delta > 0) {
				double y1 = (-b + Math.sqrt(delta)) / 2 / a;
				double y2 = (-b - Math.sqrt(delta)) / 2 / a;
				
				double x1 = (-d[1] * y1 - d[2]) / d[0];
				double x2 = (-d[1] * y2 - d[2]) / d[0];

				double d1 = Math.sqrt((pos[0] - x1) * (pos[0] - x1) + (pos[1] - y1) * (pos[1] - y1)) - p.getRadius();
				double d2 = Math.sqrt((pos[0] - x2) * (pos[0] - x2) + (pos[1] - y2) * (pos[1] - y2)) - p.getRadius();

				double t1 = d1 / Maths.norme(p.getV());
				double t2 = d2 / Maths.norme(p.getV());

				if (t1 < t2) {
					yInter = y1;
					xInter = x1;
					tMin = t1;
				} else {
					yInter = y2;
					xInter = x2;
					tMin = t2;
				}
			}
		} else {
			if (d[1] != 0) {
				yInter = -d[2] / d[1];
				xInter = Math.sqrt(r * r - (yInter - y) * (yInter - y)) + x;
				double dist = Math
						.sqrt((pos[0] - xInter) * (pos[0] - xInter) + (pos[1] - yInter) * (pos[1] - yInter))
						- p.getRadius();
				tMin = dist / Maths.norme(p.getV());
			}
		}
		if (xInter != 0 || yInter != 0) {
			double[] vMin = { x - xInter, y - yInter };
			return new Pair<>(tMin, Maths.vectTan(vMin));
		} else {
			return new Pair<>(tMin, null);
		}
	}

	@Override
	public double[] getEnveloppe() {
		return new double[] { x - r, y - r, r * 2, r * 2 };
	}

}

package bibliopckg;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Pair;
import basepckg.Particule;

public class Polygone extends Forme {

	private List<double[]> points = new ArrayList<>();

	public Polygone(List<double[]> points) {
		super();
		this.points = points;
	}

	public boolean inForme(double[] pos) {
		for (int i = 0; i < points.size(); i++) {
			double[] d = points.get(i);
			double[] dip;
			if (i != points.size() - 1) {
				dip = points.get(i + 1);
			} else {
				dip = points.get(0);
			}
			double[] v = Maths.vectTan(Maths.subVect(d, dip));
			double[] vP = Maths.subVect(pos, Maths.multScalaire(Maths.addVect(d, dip), 0.5));
			if (Maths.prodScalaire(v, vP) > 0) {
				return false;
			}
		}
		return true;
	}

	public List<double[]> getPoints() {
		return points;
	}

	public void setPoints(List<double[]> points) {
		this.points = points;
	}

	public int[] getXcoord() {
		int[] x = new int[points.size()];
		for (int i = 0; i < points.size(); i++) {
			x[i] = (int) points.get(i)[0];
		}
		return x;
	}

	public int[] getYcoord() {
		int[] y = new int[points.size()];
		for (int i = 0; i < points.size(); i++) {
			y[i] = (int) points.get(i)[1];
		}
		return y;
	}

	@Override
	public void draw(Graphics g) {
		g.fillPolygon(getXcoord(), getYcoord(), points.size());
	}

	public Pair<Double, Object> hitBorder(Particule p) {
		double timeMin = Double.POSITIVE_INFINITY;
		double[] pos = p.getPos();
		double[] vit = p.getV();
		double[] dip;
		double[] d;
		double time = 0;
		double[] vMin = null;

		for (int i = 0; i < points.size(); i++) {
			d = points.get(i);
			if (i != points.size() - 1) {
				dip = points.get(i + 1);
			} else {
				dip = points.get(0);
			}

			double[] v = Maths.subVect(d, dip);
			double[] d1 = Maths.vectToDroite(v, d);

			double[] d2 = Maths.vectToDroite(vit, pos);

			double x = 0;
			double y = 0;
			if ((Maths.colineaire(vit, v) > 0 && this.inForme(p.getPos()))
					|| (Maths.colineaire(vit, v) < 0 && !this.inForme(p.getPos()))) {

				double[] pInter = Maths.intersectDroite(d1, d2);

				x = pInter[0];
				y = pInter[1];

				double r = ((d[0] - x) * (dip[0] - x) + (d[1] - y) * (dip[1] - y)) / Maths.prodScalaire(v, v);

				if (r <= 1 && r >= -1) {
					time = (Math.sqrt((pos[0] - x) * (pos[0] - x) + (pos[1] - y) * (pos[1] - y)) - p.getRadius())
							/ Maths.norme(vit);
				} else {
					time = Double.POSITIVE_INFINITY;
				}
			} else {
				time = Double.POSITIVE_INFINITY;
			}
			if (time < timeMin) {
				timeMin = time;
				vMin = v;
			}
		}
		return new Pair<>(timeMin, vMin);
	}

	@Override
	public double[] getEnveloppe() {
		double xMin = Double.POSITIVE_INFINITY;
		double xMax = Double.NEGATIVE_INFINITY;
		double yMin = Double.POSITIVE_INFINITY;
		double yMax = Double.NEGATIVE_INFINITY;
		for (double[] d : points) {
			if (d[0] > xMax) {
				xMax = d[0];
			} else if (d[0] < xMin) {
				xMin = d[0];
			}
			if (d[1] < yMin) {
				yMin = d[1];
			} else if (d[1] > yMax) {
				yMax = d[1];
			}
		}
		return new double[] { xMin, yMin, xMax - xMin, yMax - yMin };
	}
}

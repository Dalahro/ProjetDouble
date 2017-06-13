package bibliopckg;

import java.awt.Graphics;
import java.util.ArrayList;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Pair;
import basepckg.Particule;

public class Polygone extends Forme {

	private ArrayList<double[]> points = new ArrayList<double[]>();

	public Polygone(ArrayList<double[]> points) {
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
			double[] v_p = Maths.subVect(pos, Maths.multScalaire(Maths.addVect(d, dip), 0.5));
			if (Maths.prodScalaire(v, v_p) > 0) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<double[]> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<double[]> points) {
		this.points = points;
	}

	public int[] getXcoord() {
		int[] X = new int[points.size()];
		for (int i = 0; i < points.size(); i++) {
			X[i] = (int) points.get(i)[0];
		}
		return X;
	}

	public int[] getYcoord() {
		int[] Y = new int[points.size()];
		for (int i = 0; i < points.size(); i++) {
			Y[i] = (int) points.get(i)[1];
		}
		return Y;
	}

	@Override
	public void draw(Graphics g) {
		g.fillPolygon(getXcoord(), getYcoord(), points.size());
	}

	public Pair<Double, Object> hitBorder(Particule p) {
		double time_min = Double.POSITIVE_INFINITY;
		double[] pos = p.getPos();
		double[] vit = p.getV();
		double[] dip;
		double[] d;
		double time = 0;
		double[] v_min = null;

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

				double[] p_inter = Maths.intersectDroite(d1, d2);

				x = p_inter[0];
				y = p_inter[1];

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
			if (time < time_min) {
				time_min = time;
				v_min = v;
			}
		}
		return new Pair<Double, Object>(time_min, v_min);		
	}

	@Override
	public double[] getEnveloppe() {
		double x_min = Double.POSITIVE_INFINITY, x_max = Double.NEGATIVE_INFINITY;
		double y_min = Double.POSITIVE_INFINITY, y_max = Double.NEGATIVE_INFINITY;
		for (double[] d : points) {
			if (d[0] > x_max) {
				x_max = d[0];
			} else if (d[0] < x_min) {
				x_min = d[0];
			}
			if (d[1] < y_min) {
				y_min = d[1];
			} else if (d[1] > y_max) {
				y_max = d[1];
			}
		}
		double[] enveloppe = { x_min, y_min, x_max - x_min, y_max - y_min };
		return enveloppe;
	}
}

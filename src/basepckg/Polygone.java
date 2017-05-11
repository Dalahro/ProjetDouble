package basepckg;

import java.awt.Graphics;
import java.util.ArrayList;

public class Polygone extends Forme {

	private ArrayList<double[]> points = new ArrayList<double[]>();

	public Polygone(ArrayList<double[]> points) {
		super();
		this.points = points;
	}

	public boolean inForme(Particule p) {
		for (int i = 0; i < points.size(); i++) {
			double[] d = points.get(i);
			double[] dip;
			if (i != points.size() - 1) {
				dip = points.get(i + 1);
			} else {
				dip = points.get(0);
			}
			double[] v = Maths.vectTan(Maths.subVect(d, dip));
			double[] v_p = Maths.subVect(p.getPos(), Maths.multScalaire(Maths.addVect(d, dip), 0.5));
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

}

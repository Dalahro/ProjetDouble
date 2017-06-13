package basepckg;

public class Maths {
	public static double prodScalaire(double[] v1, double[] v2) {
		return v1[0] * v2[0] + v1[1] * v2[1];
	}

	public static double norme(double[] v) {
		return Math.sqrt(prodScalaire(v, v));
	}

	public static double colineaire(double[] v1, double[] v2) {
		return v1[0] * v2[1] - v2[0] * v1[1];
	}

	public static double[] normalize(double[] v) {
		double norme = norme(v);
		double[] vect = new double[v.length];
		for (int i = 0; i < v.length; i++) {
			vect[i] = v[i] / norme;
		}
		return vect;
	}

	public static double[] subVect(double[] v1, double[] v2) {
		double[] vect = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			vect[i] = v2[i] - v1[i];
		}
		return vect;
	}

	public static double[] addVect(double[] v1, double[] v2) {
		double[] vect = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			vect[i] = v1[i] + v2[i];
		}
		return vect;
	}

	public static double[] vectTan(double[] v) {
		double[] vect = new double[2];
		vect[0] = -v[1];
		vect[1] = v[0];
		return vect;
	}

	public static double[] vectNorm(double[] v) {
		double[] vect = new double[2];
		vect[0] = v[1];
		vect[1] = -v[0];
		return vect;
	}

	public static double[] multScalaire(double[] v, double lambda) {
		double[] vect = new double[v.length];
		for (int i = 0; i < v.length; i++) {
			vect[i] = v[i] * lambda;
		}
		return vect;
	}

	public static void raz(double[] v) {
		for (int i = 0; i < v.length; i++) {
			v[i] = 0;
		}
	}

	public static double[] vectToDroite(double[] vect, double[] point) {
		double a = vect[1];
		double b = -vect[0];
		double c = -a * point[0] - b * point[1];

		double[] coef = { a, b, c };

		return coef;
	}

	public static double[] intersectDroite(double[] d1, double[] d2) {
		double x = 0;
		double y = 0;
		if (d1[0] == 0) {
			y = -d1[2] / d1[1];
			x = -(d2[1] * y + d2[2]) / d2[0];
		} else if (d2[0] == 0) {
			y = -d2[2] / d2[1];
			x = -(d1[1] * y + d1[2]) / d1[0];
		} else if (d1[1] == 0) {
			x = -d1[2] / d1[0];
			y = -(d2[0] * x + d2[2]) / d2[1];
		} else if (d2[0] == 0) {
			x = -d2[2] / d2[0];
			y = -(d1[0] * x + d1[2]) / d1[1];
		} else {
			x = (d2[2] / d2[1] - d1[2] / d1[1]) / (d1[0] / d1[1] - d2[0] / d2[1]);
			y = -(d1[0] * x + d1[2]) / d1[1];
		}
		double[] p = {x, y};
		return p;
	}
}

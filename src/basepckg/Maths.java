package basepckg;

public class Maths {
	public static double prodScalaire(double[] v1, double[] v2) {
		return v1[0] * v2[0] + v1[1] * v2[1];
	}

	public static double norme(double[] v) {
		return Math.sqrt(prodScalaire(v, v));
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

	public static double[] multScalaire(double[] v, double lambda) {
		double[] vect = new double[v.length];
		for (int i = 0; i < v.length; i++) {
			vect[i] = v[i] * lambda;
		}
		return vect;
	}
	
	public static void raz(double[] v){
		for(int i = 0; i < v.length; i++){
			v[i] = 0;
		}
	}
}

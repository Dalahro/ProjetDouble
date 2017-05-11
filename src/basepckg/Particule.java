package basepckg;

import java.awt.Color;
import java.util.ArrayList;

public class Particule {

	private static final double INFINITY = Double.POSITIVE_INFINITY;
	private double[] pos = new double[2];
	private double[] v = new double[2];
	private double[] a = new double[2];
	private int casex, casey;
	private double radius = 2;
	private Color color = Color.orange;
	private int m;

	public Particule(double posx, double posy, double vx, double vy, int m) {
		this.m = m;
		this.setPos(posx, posy);
		this.setV(vx, vy);
	}

	public Particule() {
		pos[0] = radius + Math.random() * (Simulation.WIDTH - 2 * radius);
		pos[1] = radius + Math.random() * (Simulation.HEIGHT - 2 * radius);
		v[0] = 0;// Math.random() * 10 - 5;
		v[1] = 0; // Math.random() * 10 - 5;
		radius = 2; // + (int) (Math.random() * 1);
		m = 1 + (int) (Math.random() * 100);
	}

	public void update_pos(double h) {
		hitWallHorizontal(h);
		hitWallVertical(h);

		pos[0] += h * v[0];
		pos[1] += h * v[1];
	}

	public void update_v(double h) {
		v[0] += h * a[0];
		v[1] += h * a[1];
	}

	public void update_a(double[] Kpos, double[] Kv, double[] Ka) {
		a[0] = (Ka[0] + Kv[0] * v[0] + Kpos[0] * pos[0]) / this.m;
		a[1] = (Ka[1] + Kv[1] * v[1] + Kpos[1] * pos[1]) / this.m;
	}

	public double distance(Particule p2) {
		return Maths.norme(Maths.subVect(this.getPos(), p2.getPos()));
	}

	public double cos_alpha(Particule p2) {
		double dist = this.distance(p2);
		return (p2.getPos()[0] - this.getPos()[0]) / dist;
	}

	public double sin_alpha(Particule p2) {
		double dist = this.distance(p2);
		return (p2.getPos()[1] - this.getPos()[1]) / dist;
	}

	public void hitWallVertical(double h) {
		if (pos[0] + v[0] * h - radius < 0) {
			v[0] = -v[0];
			pos[0] = 0 + radius;
		}
		if (pos[0] + v[0] * h + radius > Simulation.HEIGHT) {
			v[0] = -v[0];
			pos[0] = Simulation.HEIGHT - radius;
		}
	}

	public void hitWallHorizontal(double h) {
		if (pos[1] + v[1] * h - radius < 0) {
			v[1] = -v[1];
			pos[1] = 0 + radius;
		}
		if (pos[1] + v[1] * h + radius > Simulation.WIDTH) {
			v[1] = -v[1];
			pos[1] = Simulation.WIDTH - radius;
		}
	}

	public void hit(Particule p2) {
		double[] pos1 = this.getPos();
		double[] pos2 = p2.getPos();
		double[] v1 = this.getV();
		double[] v2 = p2.getV();
		int m1 = this.getM();
		int m2 = p2.getM();

		double[] vect_norm = Maths.normalize(Maths.subVect(pos2, pos1));
		double[] vect_tan = Maths.vectTan(vect_norm);

		double v1n = Maths.prodScalaire(vect_norm, v1);
		double v1t = Maths.prodScalaire(vect_tan, v1);
		double v2n = Maths.prodScalaire(vect_norm, v2);
		double v2t = Maths.prodScalaire(vect_tan, v2);

		double v1t_ps = v1t;
		double v2t_ps = v2t;

		double v1n_ps = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
		double v2n_ps = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

		double[] v1n_p = Maths.multScalaire(vect_norm, v1n_ps);
		double[] v1t_p = Maths.multScalaire(vect_tan, v1t_ps);
		double[] v2n_p = Maths.multScalaire(vect_norm, v2n_ps);
		double[] v2t_p = Maths.multScalaire(vect_tan, v2t_ps);

		double[] v1_p = Maths.addVect(v1n_p, v1t_p);
		double[] v2_p = Maths.addVect(v2n_p, v2t_p);

		// Collision inélastique mais marche pas trop
		double vx_cm = (m1 * v1[0] + m2 * v2[0]) / (m1 + m2);
		double vy_cm = (m1 * v1[1] + m2 * v2[1]) / (m1 + m2);

		v1_p[0] = (v1_p[0] - vx_cm) * Simulation.R + vx_cm;
		v1_p[1] = (v1_p[1] - vy_cm) * Simulation.R + vy_cm;
		v2_p[0] = (v2_p[0] - vx_cm) * Simulation.R + vx_cm;
		v2_p[1] = (v2_p[1] - vy_cm) * Simulation.R + vy_cm;

		this.setV(v1_p);
		p2.setV(v2_p);
	}

	public double timeToHit(Particule p2) {
		double dx = p2.getPos()[0] - this.getPos()[0];
		double dy = p2.getPos()[1] - this.getPos()[1];
		double dvx = p2.getV()[0] - this.getV()[0];
		double dvy = p2.getV()[1] - this.getV()[1];
		double dvdr = dx * dvx + dy * dvy;
		if (dvdr > 0) {
			return INFINITY;
		}
		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + p2.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		/*
		 * if (drdr < sigma * sigma) { System.out.println(
		 * "overlapping particles" + drdr); }
		 */
		if (d < 0) {
			return INFINITY;
		}
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

	public void hitAnyParticule(ArrayList<Particule> liste_p) {
		Particule p_plusproche = null;
		double t_min = INFINITY;
		double t = 0;
		for (Particule p : liste_p) {
			if (p != this) {
				t = timeToHit(p);
				// System.out.println(t);
				if (t < t_min) {
					t_min = t;
					p_plusproche = p;
				}

			}
		}
		if (t_min < Simulation.h) {
			update_pos(t_min);
			hit(p_plusproche);
			update_pos(Simulation.h - t_min);
		} else {
			update_pos(Simulation.h);
		}
	}

	public int getM() {
		return m;
	}

	public double[] getPos() {
		return pos;
	}

	public void setPos(double[] pos) {
		this.pos = pos;
	}

	public void setPos(double posx, double posy) {
		this.pos[0] = posx;
		this.pos[1] = posy;
	}

	public double[] getV() {
		return v;
	}

	public void setV(double[] v) {
		this.v = v;
	}

	public void setV(double vx, double vy) {
		this.v[0] = vx;
		this.v[1] = vy;
	}

	public double[] getA() {
		return a;
	}

	public void setA(double[] a) {
		this.a = a;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getCasex() {
		return casex;
	}

	public void setCasex(int casex) {
		this.casex = casex;
	}

	public int getCasey() {
		return casey;
	}

	public void setCasey(int casey) {
		this.casey = casey;
	}
}

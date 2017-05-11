package basepckg;

import java.awt.Color;

public class Particule {

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
		v[0] = Math.random() * 10 - 5;
		v[1] =  Math.random() * 10 - 5;
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
	
	public void setA(double ax, double ay) {
		this.a[0] = ax;
		this.a[1] = ay;
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

	public void resetA() {
		a[0] = 0;
		a[1] = 0;		
	}
}

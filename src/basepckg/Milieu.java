package basepckg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Milieu {

	double[] Kpos = { 0, 0 };
	double[] Kv = { 0, 0 };
	double[] Ka = { 0, 0 };
	Forme forme = null;
	private ArrayList<Modele> liste_modele = new ArrayList<Modele>();

	public Milieu(Forme forme, double[] Kpos, double[] Kv, double[] Ka) {
		this.forme = forme;
		this.Kpos = Kpos;
		this.Kv = Kv;
		this.Ka = Ka;
	}

	public Milieu(Forme forme) {
		this.forme = forme;
	}

	public double[] getKpos() {
		return Kpos;
	}

	public void setKpos(double[] kpos) {
		Kpos = kpos;
	}

	public double[] getKv() {
		return Kv;
	}

	public void setKv(double[] kv) {
		Kv = kv;
	}

	public double[] getKa() {
		return Ka;
	}

	public void setKa(double[] ka) {
		Ka = ka;
	}

	public Forme getForme() {
		return forme;
	}

	public void setForme(Forme forme) {
		this.forme = forme;
	}

	public boolean inForme(Particule p) {
		return forme.inForme(p);
	}

	public void draw(Graphics g) {
		forme.draw(g);
	}
	
	public Color getColor() {
		return forme.getColor();
	}

	public void setColor(Color color) {
		forme.setColor(color);
	}

}

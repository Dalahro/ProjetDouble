package basepckg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Milieu {

	Forme forme = null;
	public ArrayList<Modele> liste_modele = new ArrayList<Modele>();

	public Milieu(Forme forme) {
		this.forme = forme;
	}

	public Forme getForme() {
		return forme;
	}

	public void setForme(Forme forme) {
		this.forme = forme;
	}

	public boolean inMilieu(double[] pos) {
		return forme.inForme(pos);
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

	public ArrayList<Modele> getListe_modele() {
		return liste_modele;
	}

	public void setListe_modele(ArrayList<Modele> liste_modele) {
		this.liste_modele = liste_modele;
	}

}

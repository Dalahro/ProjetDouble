package basepckg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Milieu {

	Forme forme = null;
	private List<Modele> listeModele = new ArrayList<>();

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

	public List<Modele> getListeModele() {
		return listeModele;
	}

	public void setListeModele(List<Modele> listeModele) {
		this.listeModele = listeModele;
	}

}

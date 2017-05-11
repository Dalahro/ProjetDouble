package basepckg;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Affichage extends JPanel {

	private ArrayList<Milieu> liste_milieu;
	private ArrayList<Particule> liste_particule;

	public Affichage(ArrayList<Milieu> liste_milieu, ArrayList<Particule> liste_particule) {
		this.liste_milieu = liste_milieu;
		this.liste_particule = liste_particule;
	}

	public void paintComponent(Graphics g) {
		for (Milieu m : liste_milieu) {
			g.setColor(m.getColor());
			m.draw(g);
		}

		for (Particule p : liste_particule) {
			g.setColor(p.getColor());
			int r = 1;
			if (p.getRadius() > 1) {
				r = (int) p.getRadius();
			}
			g.fillOval((int) p.getPos()[0] - r, (int) p.getPos()[1] - r, r * 2, r * 2);
		}
	}

	public ArrayList<Milieu> getListe_milieu() {
		return liste_milieu;
	}

	public void setListe_milieu(ArrayList<Milieu> liste_milieu) {
		this.liste_milieu = liste_milieu;
	}

	public ArrayList<Particule> getListe_particule() {
		return liste_particule;
	}

	public void setListe_particule(ArrayList<Particule> liste_particule) {
		this.liste_particule = liste_particule;
	}

}

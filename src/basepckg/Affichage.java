package basepckg;

import java.awt.Color;
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
		g.setColor(Color.white);
		g.fillRect(0, 0, Simulation.WIDTH, Simulation.HEIGHT);
		for (Milieu m : liste_milieu) {
			g.setColor(m.getColor());
			m.draw(g);
		}

		@SuppressWarnings("unchecked")
		ArrayList<Particule> copy_part = (ArrayList<Particule>) liste_particule.clone();
		for (Particule p : copy_part) {
			g.setColor(p.getColor());
			int r = 1;
			if (p.getRadius() > 1) {
				r = (int) p.getRadius();
			}
			g.fillOval((int) p.getPos()[0] - r, (int) p.getPos()[1] - r, r * 2, r * 2);
		}
	}
}

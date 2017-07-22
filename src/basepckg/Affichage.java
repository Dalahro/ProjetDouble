package basepckg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class Affichage extends JPanel {

	private List<Milieu> listeMilieu;
	private Set<Particule> listeParticule;

	public Affichage(List<Milieu> listeMilieu, Set<Particule> listeParticule) {
		this.listeMilieu = listeMilieu;
		this.listeParticule = listeParticule;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Simulation.WIDTH, Simulation.HEIGHT);
		for (Milieu m : listeMilieu) {
			g.setColor(m.getColor());
			m.draw(g);
		}

		@SuppressWarnings("unchecked")
		Set<Particule> copyPart = (Set<Particule>) ((HashSet<Particule>) listeParticule).clone();
		for (Particule p : copyPart) {
			g.setColor(p.getColor());
			int r = 1;
			if (p.getRadius() > 1) {
				r = (int) p.getRadius();
			}
			g.fillOval((int) p.getPos()[0] - r, (int) p.getPos()[1] - r, r * 2, r * 2);
		}
	}
}

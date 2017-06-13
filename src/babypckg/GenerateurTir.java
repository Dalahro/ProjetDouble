package babypckg;

import basepckg.Factory;
import basepckg.Forme;
import basepckg.Generateur;
import basepckg.Particule;
import basepckg.Simulation;

public class GenerateurTir extends Generateur {

	int posx, posy;

	public GenerateurTir(Forme forme, Factory factory, int T, int N, int posx, int posy) {
		super(forme, factory, T, N);
		this.posx = posx;
		this.posy = posy;
	}

	public void generate(Simulation simulation) {
		double vx = Math.random() * 30;
		double vy = -Math.random() * 20;
		Particule p = factory.create(posx, posy);
		p.setV(vx, vy);
		simulation.ajouterParticule(p);
	}
}

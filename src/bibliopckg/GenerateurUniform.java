package bibliopckg;

import basepckg.Factory;
import basepckg.Forme;
import basepckg.Generateur;
import basepckg.Particule;
import basepckg.Simulation;

public class GenerateurUniform extends Generateur {

	public GenerateurUniform(Forme forme, Factory factory, int period, int nombre) {
		super(forme, factory, period, nombre);
	}

	public void generate(Simulation simulation) {
		Particule p = null;
		boolean in = false;
		double[] enveloppe = forme.getEnveloppe();
		while (!in) {
			double posx = enveloppe[0] + Math.random() * enveloppe[2];
			double posy = enveloppe[1] + Math.random() * enveloppe[3];
			p = factory.create(posx, posy);
			in = forme.inForme(p.getPos());
		}
		simulation.ajouterParticule(p);
	}

}

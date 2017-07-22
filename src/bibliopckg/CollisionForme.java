package bibliopckg;

import java.util.List;
import java.util.Set;

import basepckg.Forme;
import basepckg.Maths;
import basepckg.Modele;
import basepckg.Pair;
import basepckg.Particule;
import basepckg.Simulation;

public class CollisionForme extends Modele {

	public CollisionForme(Forme forme) {
		super(forme);
	}

	public void interaction(Particule p1, Set<Particule> listeParticule, List<Forme> listeForme) {
		double[] vit = p1.getV();
		for (Forme f : listeForme) {
			Pair<Double, Object> pair = f.hitBorder(p1); // si defini collision sur la forme
			double timeMin = pair.getFirst();
			double[] vMin = (double[]) pair.getSecond();
			if (timeMin < Simulation.H) {
				p1.updatePos(timeMin);
				double[] vectNorm = Maths.normalize(Maths.vectNorm(vMin));
				double[] vectTan = Maths.vectTan(vectNorm);

				double vitN = -Maths.prodScalaire(vectNorm, vit);
				double vitT = Maths.prodScalaire(vectTan, vit);

				double[] vitNP = Maths.multScalaire(vectNorm, vitN);
				double[] vitTP = Maths.multScalaire(vectTan, vitT);

				p1.setV(Maths.addVect(vitNP, vitTP));
				p1.updatePos(Simulation.H - timeMin);
			}
		}
	}

	public void actionP(Particule p) {
		//no action here
	}
}

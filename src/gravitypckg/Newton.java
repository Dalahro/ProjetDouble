package gravitypckg;

import java.util.List;
import java.util.Set;

import basepckg.Forme;
import basepckg.Modele;
import basepckg.Particule;

public class Newton extends Modele {
	
	public Newton(Forme forme) {
		super(forme);
	}

	double g = 6.67e-4;

	public void interaction(Particule p1, Set<Particule> listeParticule, List<Forme> listeForme) {
		double fX = 0;
		double fY = 0;
		for (Particule p2 : listeParticule) {
			if (p1 != p2 && forme.inForme(p2.getPos())) {
				double dist = p1.distance(p2);
				double cos = p1.cosAlpha(p2);
				double sin = p1.sinAlpha(p2);

				fX += g * p2.getAttributs().get("masse") * cos / dist / dist;
				fY += g * p2.getAttributs().get("masse") * sin / dist / dist;
			}
		}
		p1.setA(p1.getA()[0] + fX, p1.getA()[1] + fY);
	}

	@Override
	public void actionP(Particule p) {
		// no action here
	}
}

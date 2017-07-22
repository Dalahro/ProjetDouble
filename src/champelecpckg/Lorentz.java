package champelecpckg;

import java.util.List;
import java.util.Set;

import basepckg.Forme;
import basepckg.Modele;
import basepckg.Particule;

public class Lorentz extends Modele {

	private double[] e;

	public Lorentz(Forme forme, double[] e) {
		super(forme);
		this.e = e;
	}

	public void interaction(Particule p1, Set<Particule> listeParticule, List<Forme> listeForme) {
		// not interaction here
	}

	@Override
	public void actionP(Particule p) {
		Electron elec = (Electron) p;
		double[] a = elec.getA();
		a[0] = elec.getAttributs().get("charge") * e[0] / elec.getAttributs().get("masse");
		a[1] = elec.getAttributs().get("charge") * e[1] / elec.getAttributs().get("masse");
		p.setA(a);
	}
}

package basepckg;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Modele {

	protected Forme forme;
	public Modele(Forme forme){
		this.forme = forme;
	}
	public abstract void interaction(Particule p1, HashSet<Particule> liste_particule, ArrayList<Forme> liste_forme);

	public abstract void actionP(Particule p);

}

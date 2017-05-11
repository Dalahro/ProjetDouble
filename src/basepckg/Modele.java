package basepckg;

import java.util.ArrayList;

public abstract class Modele {

	public abstract void interaction(Particule p1, ArrayList<Particule> liste_particule);

	public abstract void interaction2P(Particule p1, Particule p2);

	public abstract void actionP(Particule p);

}

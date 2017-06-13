package basepckg;

import java.util.HashSet;

public class Case{

	private HashSet<Particule> liste_particule = new HashSet<Particule>();

	public HashSet<Particule> getListe_particule() {
		return liste_particule;
	}

	public void setListe(HashSet<Particule> liste_particule) {
		this.liste_particule = liste_particule;
	}

}

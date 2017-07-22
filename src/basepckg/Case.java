package basepckg;

import java.util.HashSet;
import java.util.Set;

public class Case{

	private Set<Particule> listeParticule = new HashSet<>();

	public Set<Particule> getListeParticule() {
		return listeParticule;
	}

	public void setListe(Set<Particule> listeParticule) {
		this.listeParticule = listeParticule;
	}

}

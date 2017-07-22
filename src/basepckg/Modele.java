package basepckg;

import java.util.List;
import java.util.Set;

public abstract class Modele {

	protected Forme forme;
	public Modele(Forme forme){
		this.forme = forme;
	}
	public abstract void interaction(Particule p1, Set<Particule> listeParticule, List<Forme> listeForme);
	
	public void interaction(Particule p1, Case[] casesAutour, List<Forme> listeForme){
		for(Case c : casesAutour){
			interaction(p1, c.getListeParticule(), listeForme);
		}
	}

	public abstract void actionP(Particule p);

}

package bibliopckg;

import java.util.ArrayList;
import java.util.HashSet;

import basepckg.Forme;
import basepckg.Modele;
import basepckg.Particule;
import basepckg.Simulation;

public class ModeleDisparition extends Modele{
	
	private Simulation simu;

	public ModeleDisparition(Forme forme, Simulation simu) {
		super(forme);
		this.simu = simu;
	}

	@Override
	public void interaction(Particule p1, HashSet<Particule> liste_particule, ArrayList<Forme> liste_forme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionP(Particule p) {
		
		double[] pos = p.getPos();
		
		pos[0] += Simulation.h * p.getV()[0];
		pos[1] += Simulation.h * p.getV()[1];
		
		
		if(!forme.inForme(pos)){
			simu.retirerParticule(p);
		}
		pos[0] -= Simulation.h * p.getV()[0];
		pos[1] -= Simulation.h * p.getV()[1];
	}

}

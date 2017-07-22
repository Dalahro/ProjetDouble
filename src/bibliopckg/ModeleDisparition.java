package bibliopckg;

import java.util.List;
import java.util.Set;

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
	public void interaction(Particule p1, Set<Particule> listeParticule, List<Forme> listeForme) {
		//no interaction here
	}

	@Override
	public void actionP(Particule p) {
		
		double[] pos = p.getPos();
		
		pos[0] += Simulation.H * p.getV()[0];
		pos[1] += Simulation.H * p.getV()[1];
		
		
		if(!forme.inForme(pos)){
			simu.retirerParticule(p);
		}
		pos[0] -= Simulation.H * p.getV()[0];
		pos[1] -= Simulation.H * p.getV()[1];
	}

}

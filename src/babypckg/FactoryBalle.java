package babypckg;

import basepckg.Factory;
import basepckg.Particule;

public class FactoryBalle extends Factory{

	@Override
	public Particule create(double posx, double posy) {
		Balle p = new Balle(posx, posy, 5, 0);
		p.setRadius(5);
		return p;
	}

}

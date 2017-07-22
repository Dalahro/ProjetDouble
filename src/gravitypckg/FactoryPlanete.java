package gravitypckg;

import basepckg.Factory;
import basepckg.Particule;

public class FactoryPlanete extends Factory{

	@Override
	public Particule create(double posx, double posy) {
		return new Planete(posx, posy, 5, 0, 10);
	}

}

package gravitypckg;

import basepckg.Factory;
import basepckg.Particule;

public class FactoryPlanete extends Factory{

	@Override
	public Particule create(double posx, double posy) {
		Planete p = new Planete(posx, posy, 5, 10, 10);
		return p;
	}

}

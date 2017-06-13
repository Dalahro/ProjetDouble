package gravitypckg;

import basepckg.Particule;

public class Planete  extends Particule{

	public Planete(double posx, double posy, double vx, double vy, double m) {
		super(posx, posy, vx, vy);
		this.attributs.put("masse", m);
	}

}

package champelecpckg;

import basepckg.Particule;

public class Electron extends Particule {

	public Electron(double posx, double posy, double vx, double vy) {
		super(posx, posy, vx, vy);
		this.attributs.put("masse", 9.1e-31);
	}

}

package champelecpckg;

import java.awt.Color;

import basepckg.Factory;
import basepckg.Particule;

public class FactoryElectron extends Factory {

	@Override
	public Particule create(double posx, double posy) {
		Electron e = new Electron(posx, posy, 5, 0);
		double q = 0;
		switch((int) (Math.random() * 2)){
		case 1:
			q = + 1.6e-19;
			e.setColor(Color.blue);
			break;
		case 0 :
			q = - 1.6e-19;
			e.setColor(Color.red);
			break;
		}
		e.getAttributs().put("charge", q);
		return e;
	}

}

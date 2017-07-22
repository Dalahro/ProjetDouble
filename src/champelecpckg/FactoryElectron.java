package champelecpckg;

import java.awt.Color;
import java.util.Random;

import basepckg.Factory;
import basepckg.Particule;

public class FactoryElectron extends Factory {

	@Override
	public Particule create(double posx, double posy) {
		Electron e = new Electron(posx, posy, 5, 0);
		double q = 0;
		Random r = new Random();
		if(r.nextInt(2) == 1){
			q = + 1.6e-19;
			e.setColor(Color.blue);
		} else {	
			q = - 1.6e-19;
			e.setColor(Color.red);
		}
		e.getAttributs().put("charge", q);
		return e;
	}

}

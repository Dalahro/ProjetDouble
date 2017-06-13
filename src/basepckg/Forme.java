package basepckg;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Forme {

	protected Color color = Color.BLACK;
	
	public abstract boolean inForme(double[] pos); 
	
	public abstract Pair<Double, Object> hitBorder(Particule p); 

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void draw(Graphics g);
	
	public abstract double[] getEnveloppe();	
}

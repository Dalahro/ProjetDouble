package basepckg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Forme {

	private Color color = Color.BLACK;

	public Forme(){
		
	}
	
	public abstract boolean inForme(Particule p); 

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void draw(Graphics g);
}

package basepckg;

public abstract class Generateur {

	protected Forme forme;
	protected Factory factory;
	protected int period;
	protected int t = 0;
	protected int nombre;
	protected int n = 0;

	public Generateur(Forme forme, Factory factory, int period, int nombre) {
		super();
		this.forme = forme;
		this.factory = factory;
		this.period = period;
		this.nombre = nombre;
	}

	public void action(int timeBoucle, Simulation simulation) {
		t += timeBoucle;
		if (period == 0 && n != nombre) {
			for (int i = 0; i < nombre; i++) {
				generate(simulation);
			}
			n = nombre;
		} else if (t > period && n < nombre) {
			generate(simulation);
			t -= period;
			n++;
		}
		if (n == nombre) {
			simulation.listeGenerateur.remove(this);
		}
	}

	public abstract void generate(Simulation simulation);
}

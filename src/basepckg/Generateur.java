package basepckg;

public abstract class Generateur {

	protected Forme forme;
	protected Factory factory;
	protected int T;
	protected int t = 0;
	protected int N;
	protected int n = 0;

	public Generateur(Forme forme, Factory factory, int T, int N) {
		super();
		this.forme = forme;
		this.factory = factory;
		this.T = T;
		this.N = N;
	}

	public void action(int time_boucle, Simulation simulation) {
		t += time_boucle;
		if (T == 0 && n != N) {
			for (int i = 0; i < N; i++) {
				generate(simulation);
			}
			n = N;
		} else if (t > T && n < N) {
			generate(simulation);
			t -= T;
			n++;
		}
		if (n == N) {
			simulation.liste_generateur.remove(this);
		}
	}

	public abstract void generate(Simulation simulation);
}

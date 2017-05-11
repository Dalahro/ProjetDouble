package basepckg;

import java.util.ArrayList;

import javax.swing.JFrame;

public abstract class Simulation extends JFrame {

	protected Affichage aff;
	protected ArrayList<Particule> liste_particule;
	protected ArrayList<Milieu> liste_milieu;
	public static double R = 1;
	public static double h = 0.01;
	public static double PAS = 1;
	public static int HEIGHT = 500;
	public static int WIDTH = 500;
	protected Case[][] grille_particule = new Case[(int) Math.ceil(1 / PAS)][(int) Math.ceil(1 / PAS)];

	double[] Kpos = { 0, 0 };
	double[] Kv = { 0, 0 };
	double[] Ka = { 0, 0 };

	protected Milieu m0 = null;

	public Simulation() {
		// Initialisation
		liste_particule = new ArrayList<Particule>();
		liste_milieu = new ArrayList<Milieu>();
		aff = new Affichage(liste_milieu, liste_particule);
		this.setSize(WIDTH + 6, HEIGHT + 28);
		this.setTitle("Simulation");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setContentPane(aff);
		setVisible(true);

		// Initialisation grille
		for (int i = 0; i < grille_particule.length; i++) {
			for (int j = 0; j < grille_particule[0].length; j++) {
				grille_particule[i][j] = new Case();
			}
		}

		// Création des particules
		createParticule();

		// Création du milieu de base
		createDefaultMilieu();

		// Création des milieux
		createMilieux();
	}

	public abstract void createDefaultMilieu();

	public abstract void createMilieux();

	public abstract void createParticule();

	public void ajouterParticule(Particule p) {
		grille_particule[p.getCasex()][p.getCasey()].getListe_particule().add(p);
		liste_particule.add(p);
	}

	public ArrayList<Particule> getListCaseIn(Particule p) {
		ArrayList<Particule> part = grille_particule[p.getCasex()][p.getCasey()].getListe_particule();
		return part;
	}

	public void updateCaseIn() {

		for (Particule p : liste_particule) {
			double[] pos = p.getPos();
			int casex = (int) (pos[0] / (WIDTH * PAS));
			int casey = (int) (pos[1] / (HEIGHT * PAS));

			grille_particule[p.getCasex()][p.getCasey()].getListe_particule().remove(p);

			p.setCasex(casex);
			p.setCasey(casey);

			grille_particule[p.getCasex()][p.getCasey()].getListe_particule().add(p);
		}
	}

	public void echantilloner(int N, Factory facto, Forme f) {
		if (f == null) {
			f = m0.getForme();
		}
		for (int i = 0; i < N; i++) {
			double posx = Math.random() * WIDTH;
			double posy = Math.random() * HEIGHT;
			Particule p = facto.create(posx, posy);
			if (f.inForme(p)) {
				ajouterParticule(p);
			} else {
				i--;
			}
		}
	}

	public void simuler() {
		// Début de la simulation
		while (true) {
			aff.repaint();
			// long t = System.currentTimeMillis();
			// double em=0;
			for (Particule p1 : liste_particule) {
				actualiser(p1);
				// em += 0.5 * p1.getM() * Math.pow(Maths.norme(p1.getV()), 2);
			}
			updateCaseIn();
			// System.out.println(System.currentTimeMillis() - t);
			// System.out.println(em);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void actualiser(Particule p1) {

		// Mise à jour accélération
		Maths.raz(Kpos);
		Maths.raz(Kv);
		Maths.raz(Ka);

		// Somme des forces

		// Forces des formes
		for (Milieu m : liste_milieu) {
			if (m.inForme(p1)) {
				Kpos = Maths.addVect(Kpos, m.getKpos());
				Kv = Maths.addVect(Kv, m.getKv());
				Ka = Maths.addVect(Ka, m.getKa());
			}
		}

		// Forces entre particules
		double[][] K = interraction(p1, getListCaseIn(p1));

		Kpos = Maths.addVect(Kpos, K[0]);
		Kv = Maths.addVect(Kv, K[1]);
		Ka = Maths.addVect(Ka, K[2]);

		p1.update_a(Kpos, Kv, Ka);

		// Mise à jour vitesse et position
		p1.update_v(h);
		
		p1.hitAnyParticule(getListCaseIn(p1));


		// Collisions
		p1.update_pos(h);
	}

	public abstract double[][] interraction2P(Particule p1, Particule p2);

	public double[][] interraction(Particule p1, ArrayList<Particule> liste_particule) {
		double[][] d = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
		for (Particule p : liste_particule) {
			double[][] d1 = interraction2P(p1, p);
			for (int i = 0; i < d.length; i++) {
				for (int j = 0; j < d[0].length; j++) {
					d[i][j] += d1[i][j];
				}
			}
		}
		return d;
	}

	public ArrayList<Particule> getListe_particule() {
		return liste_particule;
	}

	public void setListe_particule(ArrayList<Particule> liste_particule) {
		this.liste_particule = liste_particule;
	}

	public ArrayList<Milieu> getListe_milieu() {
		return liste_milieu;
	}

	public void setListe_milieu(ArrayList<Milieu> liste_forme) {
		this.liste_milieu = liste_forme;
	}
}

package basepckg;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;

public abstract class Simulation extends JFrame {

	protected Affichage aff;
	protected HashSet<Particule> liste_particule;
	protected ArrayList<Milieu> liste_milieu;
	protected ArrayList<Generateur> liste_generateur;
	protected ArrayList<Forme> liste_forme;
	public static double h = 0.01;
	public static double PAS = 1;
	public static int HEIGHT = 500;
	public static int WIDTH = 500;
	protected Case[][] grille_particule = new Case[(int) Math.ceil(1 / PAS)][(int) Math.ceil(1 / PAS)];

	public Simulation() {
		// Initialisation
		liste_particule = new HashSet<Particule>();
		liste_milieu = new ArrayList<Milieu>();
		liste_forme = new ArrayList<Forme>();
		liste_generateur = new ArrayList<Generateur>();
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

		// Initialisation des formes
		for (Milieu m : liste_milieu) {
			liste_forme.add(m.getForme());
		}
	}

	public abstract void createDefaultMilieu();

	public abstract void createMilieux();

	public abstract void createParticule();

	public void ajouterParticule(Particule p) {
		grille_particule[p.getCasex()][p.getCasey()].getListe_particule().add(p);
		liste_particule.add(p);
	}

	public void retirerParticule(Particule p) {
		grille_particule[p.getCasex()][p.getCasey()].getListe_particule().remove(p);
		liste_particule.remove(p);
	}

	public HashSet<Particule> getListCaseIn(Particule p) {
		HashSet<Particule> part = grille_particule[p.getCasex()][p.getCasey()].getListe_particule();
		return part;
	}

	public void updateCaseIn() {
		for (Particule p : liste_particule) {
			if (p.getPos()[0] > 0 && p.getPos()[0] < WIDTH && p.getPos()[1] > 0 && p.getPos()[1] < HEIGHT) {
				double[] pos = p.getPos();
				int casex = (int) (pos[0] / (WIDTH * PAS));
				int casey = (int) (pos[1] / (HEIGHT * PAS));

				grille_particule[p.getCasex()][p.getCasey()].getListe_particule().remove(p);
 
				p.setCasex(casex);
				p.setCasey(casey);

				grille_particule[p.getCasex()][p.getCasey()].getListe_particule().add(p);
			}
		}
	}

	public void simuler() {

		// Début de la simulation
		while (true) {
			aff.repaint();
			long t = System.currentTimeMillis();
			// double em=0;

			@SuppressWarnings("unchecked")
			HashSet<Particule> copy_part = (HashSet<Particule>) liste_particule.clone();
			for (Particule p1 : copy_part) {
				actualiser(p1);
				// em += 0.5 * p1.getM() * Math.pow(Maths.norme(p1.getV()), 2);
			}
			@SuppressWarnings("unchecked")
			ArrayList<Generateur> copy_gen = (ArrayList<Generateur>) liste_generateur.clone();
			for (Generateur gen : copy_gen) {
				gen.action(1, this);
			}

			updateCaseIn();
			System.out.println(System.currentTimeMillis() - t);
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
		p1.resetA();

		// Milieu où est la particule + Action des modèles que si la particule
		// est dans la fenetre
		if (p1.getPos()[0] > 0 && p1.getPos()[0] < WIDTH && p1.getPos()[1] > 0 && p1.getPos()[1] < HEIGHT) {
			HashSet<Particule> caseDedans = getListCaseIn(p1);
			for (Milieu m : liste_milieu) {
				if (m.inMilieu(p1.getPos())) {
					for (Modele mod : m.getListe_modele()) {
						mod.actionP(p1);
						mod.interaction(p1, caseDedans, liste_forme);
					}
				}
			}
		}
		// Mise à jour vitesse et position
		p1.update_v(h);
		p1.update_pos(h);
	}

	public HashSet<Particule> getListe_particule() {
		return liste_particule;
	}

	public void setListe_particule(HashSet<Particule> liste_particule) {
		this.liste_particule = liste_particule;
	}

	public ArrayList<Milieu> getListe_milieu() {
		return liste_milieu;
	}

	public void setListe_milieu(ArrayList<Milieu> liste_forme) {
		this.liste_milieu = liste_forme;
	}
}

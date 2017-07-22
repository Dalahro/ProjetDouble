package basepckg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.JFrame;

public abstract class Simulation extends JFrame {

	protected Affichage aff;
	protected Set<Particule> listeParticule;
	protected List<Milieu> listeMilieu;
	protected List<Generateur> listeGenerateur;
	protected List<Forme> listeForme;
	public static final double H = 0.01;
	public static final double PAS = 0.1;
	public static final int HEIGHT = 500;
	public static final int WIDTH = 500;
	protected Case[][] grilleParticule = new Case[(int) Math.ceil(1 / PAS)][(int) Math.ceil(1 / PAS)];
	
	private static final Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(Simulation.class.getName());
	}

	public Simulation() {
		// Initialisation
		listeParticule = new HashSet<>();
		listeMilieu = new ArrayList<>();
		listeForme = new ArrayList<>();
		listeGenerateur = new ArrayList<>();
		aff = new Affichage(listeMilieu, listeParticule);
		this.setSize(WIDTH + 6, HEIGHT + 28);
		this.setTitle("Simulation");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setContentPane(aff);
		setVisible(true);

		// Initialisation grille
		for (int i = 0; i < grilleParticule.length; i++) {
			for (int j = 0; j < grilleParticule[0].length; j++) {
				grilleParticule[i][j] = new Case();
			}
		}

		// Création des particules
		createParticule();

		// Création du milieu de base
		createDefaultMilieu();

		// Création des milieux
		createMilieux();

		// Initialisation des formes
		for (Milieu m : listeMilieu) {
			listeForme.add(m.getForme());
		}
	}

	public abstract void createDefaultMilieu();

	public abstract void createMilieux();

	public abstract void createParticule();

	public void ajouterParticule(Particule p) {
		grilleParticule[p.getCasex()][p.getCasey()].getListeParticule().add(p);
		listeParticule.add(p);
	}

	public void retirerParticule(Particule p) {
		grilleParticule[p.getCasex()][p.getCasey()].getListeParticule().remove(p);
		listeParticule.remove(p);
	}

	public Case[] getListCaseIn(Particule p) {
		Case[] casesVoisines = new Case[9];

		for (int i = 0; i < casesVoisines.length; i++) {
			casesVoisines[i] = new Case();
		}

		if (p.getCasex() > 0) {
			if (p.getCasey() > 0) {
				casesVoisines[0].setListe(grilleParticule[p.getCasex() - 1][p.getCasey() - 1].getListeParticule());
			}
			casesVoisines[1].setListe(grilleParticule[p.getCasex() - 1][p.getCasey()].getListeParticule());
			if (p.getCasey() < (int) (1 / PAS) - 1) {
				casesVoisines[2].setListe(grilleParticule[p.getCasex() - 1][p.getCasey() + 1].getListeParticule());
			}
		}

		if (p.getCasey() > 0) {
			casesVoisines[3].setListe(grilleParticule[p.getCasex()][p.getCasey() - 1].getListeParticule());
		}
		casesVoisines[4].setListe(grilleParticule[p.getCasex()][p.getCasey()].getListeParticule());
		if (p.getCasey() < (int) (1 / PAS) - 1) {
			casesVoisines[5].setListe(grilleParticule[p.getCasex()][p.getCasey() + 1].getListeParticule());
		}

		if (p.getCasex() < (int) (1 / PAS) - 1) {
			if (p.getCasey() > 0) {
				casesVoisines[6].setListe(grilleParticule[p.getCasex() + 1][p.getCasey() - 1].getListeParticule());
			}
			casesVoisines[7].setListe(grilleParticule[p.getCasex() + 1][p.getCasey()].getListeParticule());
			if (p.getCasey() < (int) (1 / PAS) - 1) {
				casesVoisines[8].setListe(grilleParticule[p.getCasex() + 1][p.getCasey() + 1].getListeParticule());
			}
		}

		return casesVoisines;
	}

	public void updateCaseIn() {
		for (Particule p : listeParticule) {
			if (p.getPos()[0] > 0 && p.getPos()[0] < WIDTH && p.getPos()[1] > 0 && p.getPos()[1] < HEIGHT) {
				double[] pos = p.getPos();
				int casex = (int) (pos[0] / (WIDTH * PAS));
				int casey = (int) (pos[1] / (HEIGHT * PAS));

				if (casex != p.getCasex() && casey != p.getCasey()) {

					grilleParticule[p.getCasex()][p.getCasey()].getListeParticule().remove(p);

					p.setCasex(casex);
					p.setCasey(casey);

					grilleParticule[p.getCasex()][p.getCasey()].getListeParticule().add(p);
				}
			}
		}
	}

	public void simuler() {

		// Début de la simulation
		while (true) {
			aff.repaint();
			long t = System.currentTimeMillis();
			// double em=0

			@SuppressWarnings("unchecked")
			Set<Particule> copyPart = (Set<Particule>) ((HashSet<Particule>) listeParticule).clone();
			for (Particule p1 : copyPart) {
				actualiser(p1);
				// em += 0.5 * p1.getM() * Math.pow(Maths.norme(p1.getV()), 2)
			}
			@SuppressWarnings("unchecked")
			List<Generateur> copyGen = (List<Generateur>) ((ArrayList<Generateur>) listeGenerateur).clone();
			for (Generateur gen : copyGen) {
				gen.action(1, this);
			}

			updateCaseIn();
			System.out.println(System.currentTimeMillis() - t);
			// System.out.println(em)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				LOGGER.severe(e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}

	public void actualiser(Particule p1) {

		// Mise à jour accélération
		p1.resetA();

		// Milieu où est la particule + Action des modèles que si la particule
		// est dans la fenetre
		if (p1.getPos()[0] > 0 && p1.getPos()[0] < WIDTH && p1.getPos()[1] > 0 && p1.getPos()[1] < HEIGHT) {
			Case[] casesAutour = getListCaseIn(p1);
			for (Milieu m : listeMilieu) {
				if (m.inMilieu(p1.getPos())) {
					for (Modele mod : m.getListeModele()) {
						mod.actionP(p1);
						mod.interaction(p1, casesAutour, listeForme);
					}
				}
			}
		}
		// Mise à jour vitesse et position
		p1.updateV(H);
		p1.updatePos(H);
	}

	public Set<Particule> getListeParticule() {
		return listeParticule;
	}

	public void setListeParticule(Set<Particule> listeParticule) {
		this.listeParticule = listeParticule;
	}

	public List<Milieu> getListeMilieu() {
		return listeMilieu;
	}

	public void setListeMilieu(List<Milieu> listeForme) {
		this.listeMilieu = listeForme;
	}
}

import java.util.ArrayList;

import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;

class MainGraphique {

	public static final int tailleCase = 75;

	public static void dessinerPlateau(Fenetre f, Plateau p, char couleurQuiJoue)
			throws ErreurCoordonneesException, CouleurPieceException, RoiNonTrouveException {
		f.effacer();

		// echiquier
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1)
					f.ajouter(new Carre(Couleur.BLANC,
							new Point(i * MainGraphique.tailleCase, j * MainGraphique.tailleCase),
							MainGraphique.tailleCase, true));
				else
					f.ajouter(new Carre(Couleur.GRIS_FONCE,
							new Point(i * MainGraphique.tailleCase, j * MainGraphique.tailleCase),
							MainGraphique.tailleCase, true));
			}

		// Partie haute
		f.ajouter(new Rectangle(new Couleur(68, 158, 255), new Point(0, MainGraphique.tailleCase * 8),
				MainGraphique.tailleCase * 8, MainGraphique.tailleCase * 2, true));
		if (couleurQuiJoue == 'B') {
			f.ajouter(new Texte(Couleur.JAUNE, new String("A blanc de jouer"), new Font("Calibri", Font.TYPE1_FONT, 40),
					new Point(MainGraphique.tailleCase * 4, (int) (MainGraphique.tailleCase * 9.5))));
			f.ajouter(new Cercle(Couleur.BLANC,
					new Point((int) (MainGraphique.tailleCase * 1.5), (int) (MainGraphique.tailleCase * 9.5)),
					(int) (MainGraphique.tailleCase * 0.45), true));
		} else {
			f.ajouter(new Texte(Couleur.JAUNE, new String("A noir de jouer"), new Font("Calibri", Font.TYPE1_FONT, 40),
					new Point(MainGraphique.tailleCase * 4, (int) (MainGraphique.tailleCase * 9.5))));
			f.ajouter(new Cercle(Couleur.NOIR,
					new Point((int) (MainGraphique.tailleCase * 1.5), (int) (MainGraphique.tailleCase * 9.5)),
					(int) (MainGraphique.tailleCase * 0.45), true));
		}

		// pieces banches
		ArrayList<Piece> listeB = p.getPiecesBlanches();

		for (Piece pi : listeB)
			f.ajouter(new Texture("./images/" + pi.getNomLong() + ".png",
					new Point(pi.getPosition().getX() * MainGraphique.tailleCase,
							pi.getPosition().getY() * MainGraphique.tailleCase),
					MainGraphique.tailleCase, MainGraphique.tailleCase));

		// pieces noires
		ArrayList<Piece> listeN = p.getPiecesNoires();

		for (Piece pi : listeN)
			f.ajouter(new Texture("./images/" + pi.getNomLong() + ".png",
					new Point(pi.getPosition().getX() * MainGraphique.tailleCase,
							pi.getPosition().getY() * MainGraphique.tailleCase),
					MainGraphique.tailleCase, MainGraphique.tailleCase));

		// Affichage de la mise en échec
		if (p.estEchec('B')) {
			Position posR = p.getRoiBlanc().getPosition();
			f.ajouter(new Carre(Couleur.ROUGE,
					new Point(posR.getX() * MainGraphique.tailleCase, posR.getY() * MainGraphique.tailleCase),
					MainGraphique.tailleCase, true));
			f.ajouter(new Texture("./images/roi_B.png",
					new Point(posR.getX() * MainGraphique.tailleCase, posR.getY() * MainGraphique.tailleCase),
					MainGraphique.tailleCase, MainGraphique.tailleCase));
			f.ajouter(new Texte(Couleur.ROUGE, new String("Roi blanc échec"), new Font("Calibri", Font.TYPE1_FONT, 40),
					new Point(MainGraphique.tailleCase * 4, (int) (MainGraphique.tailleCase * 8.5))));
		}
		if (p.estEchec('N')) {
			Position posR = p.getRoiNoir().getPosition();
			f.ajouter(new Carre(Couleur.ROUGE,
					new Point(posR.getX() * MainGraphique.tailleCase, posR.getY() * MainGraphique.tailleCase),
					MainGraphique.tailleCase, true));
			f.ajouter(new Texture("./images/roi_N.png",
					new Point(posR.getX() * MainGraphique.tailleCase, posR.getY() * MainGraphique.tailleCase),
					MainGraphique.tailleCase, MainGraphique.tailleCase));
			f.ajouter(new Texte(Couleur.ROUGE, new String("Roi noir échec"), new Font("Calibri", Font.TYPE1_FONT, 40),
					new Point(MainGraphique.tailleCase * 4, (int) (MainGraphique.tailleCase * 8.5))));
		}
	}

	public static void afficheDepPossible(Fenetre f, ArrayList<Position> l) {
		for (Position p : l) {
			int indiceX = p.getX();
			int indiceY = p.getY();
			f.ajouter(new Cercle(Couleur.ROUGE,
					new Point(indiceX * MainGraphique.tailleCase + (MainGraphique.tailleCase / 2),
							indiceY * MainGraphique.tailleCase + (MainGraphique.tailleCase / 2)),
					(int) (MainGraphique.tailleCase * 0.45), false));
		}
	}

	public static void main(String[] args) throws ErreurCoordonneesException, CouleurPieceException, CaseOccupeeException, ErreurDeplacementException, RoiNonTrouveException {
		Fenetre f = new Fenetre("Jeu d'échecs", 8 * 75, 10 * 75);
		Souris souris = f.getSouris();
		
		Plateau p = choixModeJeu(f);

		char couleurQuiJoue = 'B';

		Piece selectionne1;
		Position selectionne2;

		dessinerPlateau(f, p, couleurQuiJoue);
		f.rafraichir();

		selectionne1 = null;
		selectionne2 = null;
		
		while (true) {
			while ((selectionne1 == null)) {
				while (!souris.getClicGauche()) {
					try {
						Thread.sleep(10);
					} catch (Exception e) { }
				}

				int indX = souris.getPosition().getX() / MainGraphique.tailleCase;
				int indY = souris.getPosition().getY() / MainGraphique.tailleCase;
				if ((indX >= 0) && (indX <= 7) && (indY >= 0) && (indY <= 7)) {
					Piece pieceSelectionne = p.getCase(indX, indY);
					if (pieceSelectionne != null) {
						if (pieceSelectionne.getCouleur() == couleurQuiJoue) {
							if (selectionne1 == null)
								selectionne1 = p.getCase(indX, indY);
							else
								selectionne2 = new Position(indX, indY);
						}
					}
				}
			}

			ArrayList<Position> possibilite = selectionne1.getDeplacementPossible(p);
			dessinerPlateau(f, p, couleurQuiJoue);
			afficheDepPossible(f, possibilite);
			f.rafraichir();

			while (selectionne2 == null) {
				while (!souris.getClicGauche()) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				int indX = souris.getPosition().getX() / MainGraphique.tailleCase;
				int indY = souris.getPosition().getY() / MainGraphique.tailleCase;
				// if ((indX >= 0) && (indX <= 7) && (indY >= 0) && (indY <= 7)) {
				// 	selectionne2 = new Position(indX, indY);
				// }
				try {
					selectionne2 = new Position(indX, indY);
				} catch (ErreurCoordonneesException e) {
					System.out.println(e.getMessage());
				}
			}

			if (possibilite.contains(selectionne2)) {
				p.deplacer(selectionne1.getPosition(), selectionne2);

				selectionne1 = null;
				selectionne2 = null;
				if (couleurQuiJoue == 'B')
					couleurQuiJoue = 'N';
				else
					couleurQuiJoue = 'B';
			} else {
				selectionne1 = null;
				selectionne2 = null;
			}
			dessinerPlateau(f, p, couleurQuiJoue);
			f.rafraichir();

		}
	}

	private static Plateau choixModeJeu(Fenetre fenetre) throws ErreurCoordonneesException, CouleurPieceException, CaseOccupeeException {
		fenetre.ajouter(new Texte("A quel mode de jeu voulez-vous jouer ?", new Font("Arial", Font.PLAIN, 28), new Point(300, 400)));
		
		Rectangle noCheat = new Rectangle(Couleur.BLEU, new Point(150, 300), new Point(250, 350), true);
		fenetre.ajouter(noCheat);
		fenetre.ajouter(new Texte("No Cheat", new Font("Arial", Font.PLAIN, 20), new Point(200, 325)));

		Rectangle cheat = new Rectangle(Couleur.ROUGE, new Point(350, 300), new Point(450, 350), true);
		fenetre.ajouter(cheat);
		fenetre.ajouter(new Texte("Cheat", new Font("Arial", Font.PLAIN, 28), new Point(400, 325)));
		
		fenetre.rafraichir();

		Souris souris = fenetre.getSouris();
		Plateau plateau = null;

		while (plateau == null) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}

			int x = souris.getPosition().getX();
			int y = souris.getPosition().getY();

			if (souris.getClicGauche()) {
                if (estDansRectangle(x, y, noCheat)) {
					plateau = new Plateau(false);
                }
                else if (estDansRectangle(x, y, cheat)) {
					plateau = new Plateau(true);
                }
			}
		}

		return plateau;
	}

	private static boolean estDansRectangle(int x, int y, Rectangle rect) {
        int xMin = rect.getA().getX();
        int yMin = rect.getA().getY();
        int xMax = rect.getB().getX();
        int yMax = rect.getB().getY();

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax);
    }

}

import java.util.ArrayList;

class Davalier extends Piece {

	public Davalier() throws CouleurPieceException {
		super('B', new Position());
	}

	public Davalier(char couleur, Position position) throws CouleurPieceException {
		super(couleur, position);
	}

	public String getType() {
		return new String("davalier");
	}

	public ArrayList<Position> getDeplacementPossible(Plateau pl) throws CouleurPieceException, ErreurCoordonneesException {
		// Une dame a les coups possibles de la tour et du fou combinés
		ArrayList<Position> retour = new ArrayList<Position>();

		Dame d = new Dame(this.getCouleur(), this.getPosition());
		Cavalier c = new Cavalier(this.getCouleur(), this.getPosition());

		ArrayList<Position> deplacementPossible = d.getDeplacementPossible(pl);
		for (Position p : deplacementPossible)
			retour.add(p);

		deplacementPossible = c.getDeplacementPossible(pl);
		for (Position p : deplacementPossible)
			retour.add(p);

		return retour;
	}

}

import java.util.ArrayList;

public class Favalier extends Piece{
    
    public Favalier() throws CouleurPieceException {
		super('B', new Position());
	}

	public Favalier(char couleur, Position position) throws CouleurPieceException {
		super(couleur, position);
	}

	public String getType() {
		return new String("favalier");
	}

	public ArrayList<Position> getDeplacementPossible(Plateau pl) throws CouleurPieceException, ErreurCoordonneesException {
		// Un Favalier a les coups possibles du fou et du cavalier combinés
		ArrayList<Position> retour = new ArrayList<Position>();

		Cavalier c = new Cavalier(this.getCouleur(), this.getPosition());
		Fou f = new Fou(this.getCouleur(), this.getPosition());

		ArrayList<Position> deplacementPossible = c.getDeplacementPossible(pl);
		for (Position p : deplacementPossible)
			retour.add(p);

		deplacementPossible = f.getDeplacementPossible(pl);
		for (Position p : deplacementPossible)
			retour.add(p);

		return retour;
	}
}

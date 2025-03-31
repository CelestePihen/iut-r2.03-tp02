import java.util.ArrayList;

class Tavalier extends Piece {

	public Tavalier() throws CouleurPieceException {
		super('B', new Position());
	}

	public Tavalier(char couleur, Position position) throws CouleurPieceException {
		super(couleur, position);
	}

    @Override
    public String getType() {
        return new String("tavalier");
    }

    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau pl)  throws CouleurPieceException, ErreurCoordonneesException  {
        // Un tavalier a les coups possibles de la tour et du cavalier combinés
		ArrayList<Position> retour = new ArrayList<Position>();

		Tour t = new Tour(this.getCouleur(), this.getPosition());
		Cavalier c = new Cavalier(this.getCouleur(), this.getPosition());

		ArrayList<Position> deplacementPossible = t.getDeplacementPossible(pl);
		for (Position p : deplacementPossible)
			retour.add(p);

		deplacementPossible = c.getDeplacementPossible(pl);
		for (Position p : deplacementPossible)
			retour.add(p);

		return retour;
    }

    
    
}

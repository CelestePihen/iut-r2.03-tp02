abstract class Pion extends Piece {

    public Pion() throws CouleurPieceException {
        super('B', new Position());
    }

    public Pion(char couleur, Position position) throws CouleurPieceException {
        super(couleur, position);
    }

    public String getType() {
        return new String("pion");
    }
}

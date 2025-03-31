public class ErreurDeplacementException extends Exception {
    
    private Piece p;
    private Position pos;

    public ErreurDeplacementException(Piece p, Position pos) {
        this.p = p;
        this.pos = pos;
    }

    @Override
    public String getMessage() {
        return "Impossible de déplacer le " + p.getType() + " de " + p.getPosition().toString() + " en " + pos.toString() + "." ;
    }

}

public class CaseOccupeeException extends Exception {
    
    private Piece p1, p2;

    public CaseOccupeeException(Piece p1, Piece p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public String getMessage() {
        return "Impossible de placer le " + p2.toString() + ". Un " + p1.toString() + " est déjà présent.";
    }

}

public class CouleurPieceException extends Exception {

    private char couleur;

    public CouleurPieceException(char couleur) {
        this.couleur = couleur;
    }

    @Override
    public String getMessage() {
        return "Erreur dans la couleur de la pièce : " + couleur + " - La couleur doit être B ou N.";
    }

}

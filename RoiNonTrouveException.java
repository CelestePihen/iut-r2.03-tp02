public class RoiNonTrouveException extends Exception {

    private char couleurRoi;

    public RoiNonTrouveException(char couleurRoi) {
        this.couleurRoi = couleurRoi;
    }

    @Override
    public String getMessage() {
        if (this.couleurRoi == 'B') {
            return "Le roi blanc est introuvable.";
        } else if (this.couleurRoi == 'N') {
            return "Le roi noir est introuvable.";
        }
        return "Le roi " + couleurRoi + " est introuvable.";
    }
    
}
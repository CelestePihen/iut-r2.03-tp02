public class ErreurCoordonneesException extends Exception {
    
    private int x, y;

    public ErreurCoordonneesException(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        if(x < 0 || x > 7){
            return "Erreur dans la position X : " + x + " - Les indices doivent être compris entre 0 et 7.";
		}
        if(y < 0 || y > 7){
			return "Erreur dans la position Y : " + y + " - Les indices doivent être compris entre 0 et 7.";
		}
        return "Erreur de coordonnées ?";
    }

}

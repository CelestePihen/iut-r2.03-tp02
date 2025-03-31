class Position{
    private int x, y;

    public Position(){
	this.x = 0;
	this.y = 0;
    }

    public Position(int x, int y) throws ErreurCoordonneesException {
        /* CAS 1 */
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			throw new ErreurCoordonneesException(x, y);
		}
		this.x = x;
		this.y = y;
    }

    public Position(String position) throws ErreurCoordonneesException {
		/*
		Nous devrions tester la longueur de la chaine *position*
		mais pour une simplification du TP, nous ne faisons pas ce test.
		*/
		
		int x = (int)(position.charAt(0)-'A');
		int y = (int)(position.charAt(1)-'1');
		
		/* CAS 2 */
		if(x < 0 || x > 7 || y < 0 || y > 7){
			throw new ErreurCoordonneesException(x, y);
		}
		
		this.x = x;
		this.y = y;
    }

    public Position(Position position) {
		this.x = position.x;
		this.y = position.y;
    }

    public int getX() {
		return this.x;
    }
 
    public int getY() {
		return this.y;
    }

    public void setX(int x) throws ErreurCoordonneesException {
        /* CAS 3 */
		if(x < 0 || x > 7){
			throw new ErreurCoordonneesException(x, y);
		}
		this.x = x;
    }

    public void setY(int y) throws ErreurCoordonneesException {
        /* CAS 4 */
		if(y < 0 || y > 7){
			throw new ErreurCoordonneesException(x, y);
		}
		this.y = y;
    }


    public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Position)) {
            return false; 
        } 
	
        Position p = (Position) o; 
          
        return (this.x == p.x) && (this.y == p.y);
    }
    

    public String toString() {
		return "("+(char)(x+'A')+""+(y+1)+")";
    }
    
    // public static void main(String[] args) throws ErreurCoordonneesException {
    //     // OK
	// 	Position p = new Position();
	// 	System.out.println(p);
		
	// 	// OK
	// 	p = new Position("F4");
	// 	System.out.println(p);
		
	// 	// Attention ! Erreur
	// 	// x doit être compris entre 0 et 7
	// 	p = new Position(9,5);
	// 	System.out.println(p);
    // }
    
}

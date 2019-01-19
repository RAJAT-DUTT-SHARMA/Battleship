package battleship;


public abstract class Player {

	Board board;
	
	int veselsCellCount[];
	
	
	public Player(Board board) {
		this.board=board;
				veselsCellCount= new int[5];
				for(int i=0;i<5;i++) {
					veselsCellCount[i]=0;
				}
	}

	abstract void placeShips(); //Check validity of ship position
	
}

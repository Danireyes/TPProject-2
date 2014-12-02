package tp.pr2.logic;

import tp.pr2.Util;

public class Connect4Move extends Move { // Connect4Movement is a subclass of Move

	public Connect4Move(int moveColumn, Counter moveColour) {
		
	}
	
	public boolean executeMove(Board board) {
		boolean success = false;
		int h = Util.firstEmptyPosition(board, this.getCol());	
		//Check whether column is valid
		if (!Util.isColumnValid(board, this.getCol()) || 
				(h < Util.ERRORTHRESHOLD)) {
			success = false;
		} else {			
			board.setPosition(this.getCol(), Util.firstEmptyPosition(board, this.getCol()), this.getPlayer());	
			success = true;		
		}
		return success;
	}	
	
	public void undo(Board board) {		
			//Searches desired column from top to bottom
		int h = Util.firstEmptyPosition(board, this.getCol());		
		if (h >= Util.ERRORTHRESHOLD) {
			board.setPosition(this.getCol(), h+1, Counter.EMPTY);
		} 				
	}	
}
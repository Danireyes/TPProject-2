package tp.pr2;

import tp.pr2.logic.Board;
import tp.pr2.logic.Counter;

public class Util {
	
	//Indicates what is the value below which error codes are assigned
	public static final int ERRORTHRESHOLD = 0;

	/** Iterates through the column to find the first empty position at the top
	 * 	Returns the y position of the first empty row
	 *  If an error is found it returns ERRORTHRESHOLD - 1
	 *  If the column is full, it returns 0 */	
	public static int firstEmptyPosition(Board board, int col) {
		int row = board.getHeight();
		//Error-checking
		if ((col < Board.MINWIDTH) || (col > board.getWidth())) {
			//Column is out of bounds
			row = Util.ERRORTHRESHOLD - 1;
		}
		while ((row >= Board.MINHEIGHT) && 
				(board.getPosition(col, row) != Counter.EMPTY)) {			
				row--;
		}
		return row;
	}

}

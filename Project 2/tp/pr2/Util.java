package tp.pr2;

import tp.pr2.logic.Board;
import tp.pr2.logic.Counter;
import tp.pr2.logic.Game;

public class Util {
	
	//Indicates what is the value below which error codes are assigned
	public static final int ERRORTHRESHOLD = 0;

	/** Iterates through the column to find the first empty position at the top
	 * 	Returns the y position of the first empty row
	 *  If an error is found it returns ERRORTHRESHOLD - 1
	 *  If the column is full, it returns 0 */
	/**
	 * @param board
	 * @param col: The column to check for the first empty space
	 * @return 
	 */
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
	
	/**
	 * @param board
	 * @param x
	 * @param y
	 * @param dirx: Direction in which we want to check it
	 * @param diry: Direction in which we want to check it
	 * @return Whether there is a WINCON-length connection starting from
	 * 			the counter at (x,y)
	 */
	public static boolean checkCellInDirection(Board board, int x, int y, DirectionX dirx, DirectionY diry) {
		boolean finished = false;
		int count = 0;
		Counter turn = board.getPosition(x, y);
		//Iterate check if the cell is connected
		while((count < Game.WINCON) && !finished &&
				(x >= Board.MINWIDTH) && (y >= Board.MINHEIGHT) && 
				(x <= board.getWidth()) && (y <= board.getHeight()) &&
				(board.getPosition(x, y) == turn) && (board.getPosition(x, y) != Counter.EMPTY)){
			/**
			 * Change x and y according to the direction, to get the next cell
			 */
			x = x + Util.convertDirX(dirx);
			y = y + Util.convertDirY(diry);
			
			count++;			
		}		
		if (count >= Game.WINCON) {
			finished = true;
		} else {
			count = 0;
		}
		
		return finished;		
	}

	public static int convertDirY(DirectionY diry) {
		int r;
		switch (diry) {
		case DOWN:
			r = 1;
			break;
		case NOTHING:
			r = 0;
			break;
		case UP:
			r = -1;
			break;
		default:
			r = 0;
			break;		
		}
		return r;
	}

	public static int convertDirX(DirectionX dirx) {
		int r;
		switch (dirx) {
		case LEFT:
			r = -1;
			break;
		case NOTHING:
			r = 0;
			break;
		case RIGHT:
			r = 1;
			break;
		default:
			r = 0;
			break;
		
		}
		return r;
	}

}
package tp.pr2.logic;

import tp.pr2.Util;
import tp.pr2.logic.Board;

public class ComplicaMove extends Move {
	
	public ComplicaMove(int moveColumn, Counter moveColour) {
		setCol(moveColumn);
		setPlayer(moveColour);
	}
	
	public boolean executeMove(Board board) {
		boolean success = false;
		int h = Util.firstEmptyPosition(board, this.getCol());	
		//Check whether column is valid
		if (!Util.isColumnValid(board, this.getCol())) {
			success = false;
		} 
		else {
			if (h < Util.ERRORTHRESHOLD) { // if the column is full I move all the Counters one cell down and do the first cell empty
				board = board.moveDownCells(board, this.getCol());
			}
			board.setPosition(this.getCol(), Util.firstEmptyPosition(board, this.getCol()), this.getPlayer());// to put the counter in the first empty position
			success = true;		
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.Move#undo(tp.pr2.logic.Board)
	 */
	@Override
	public void undo(Board board) {
		if (Util.firstEmptyPosition(board, getCol()) < Util.ERRORTHRESHOLD) {
		board = board.moveUpCells(board, this.getCol()); // if the column is full I move the counters one up to put the first counter out of the "printed board" in and make disappear the first counter
		}
		else {
			board.setPosition(this.getCol(), (Util.firstEmptyPosition(board, this.getCol()) + 1), Counter.EMPTY); // the same undo as C4
		}
	}
}

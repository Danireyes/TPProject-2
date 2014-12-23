package tp.pr2.logic;

import tp.pr2.Util;
import tp.pr2.logic.Board;

public class ComplicaMove extends Move {	

	private Counter out;
	
	public ComplicaMove(int moveColumn, Counter moveColour) {
		this.setCol(moveColumn);
		this.setPlayer(moveColour);
		this.out = Counter.EMPTY;
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
				this.setOut(board.getPosition(getCol(), board.getHeight()));
				board = this.moveDownCells(board, this.getCol());
			} else {
				this.setOut(Counter.EMPTY);
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
		if (this.out != Counter.EMPTY) {
			board = this.moveUpCells(board, this.getCol()); // if the column is full I move the counters one up to put the first counter out of the "printed board" in and make disappear the first counter
			board.setPosition(getCol(), board.getHeight(), this.getOut());
		}
		else {
			int h = Util.firstEmptyPosition(board, this.getCol());
			if (h < Util.ERRORTHRESHOLD) {
				h++;
			}
			board.setPosition(this.getCol(), (h + 1), Counter.EMPTY); // the same undo as C4
		}
	}
	
	public Board moveDownCells(Board board, int col) {
		for (int i = board.getHeight() - 1; i >= Board.MINHEIGHT; i--) {
			board.setPosition(col, i + 1, board.getPosition(col, i));
		}
		board.setPosition(col, Board.MINHEIGHT, Counter.EMPTY);
		return board;
	}
	
	public Board moveUpCells(Board board, int col) {
		for (int i = Board.MINHEIGHT; i < (board.getHeight()); i++) {
			board.setPosition(col, i , board.getPosition(col, i + 1));
		}
		board.setPosition(col, board.getHeight(), Counter.EMPTY);
		return board;
	}

	public Counter getOut() {
		return out;
	}

	public void setOut(Counter out) {
		this.out = out;
	}
}

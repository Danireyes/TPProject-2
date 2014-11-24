package tp.pr2.logic;

import tp.pr2.Util;

public class Connect4Movement extends Move { // Connect4Movement is a subclass of Movement
	
	private Game game;
	private UndoStack undoStack;
	private Board board1;
	
	
	public boolean executeMove(Board board) {
		boolean success = false;
		int column = readColumn();
		boolean finished = false;
		boolean full = board1.checkFull();
		//Check whether column is valid
		if (!Util.isColumnValid(board, column) || finished || full) {
			success = false;
		} else {
			//SEARCH the column for first free space, then place the counter
			success = game.undoStack(column);
		}
		return success;
	}
	
	
	public void undo(Board board) {		
			//Searches desired column from top to bottom
		int h = Util.firstEmptyPosition(board, undoStack.getLastElement());		
		if (h > Util.ERRORTHRESHOLD) {
			board.setPosition(undoStack.getLastElement(), h+1, Counter.EMPTY);
			undoStack.pop();	
			game.changeTurn();
		} 				
	}
}

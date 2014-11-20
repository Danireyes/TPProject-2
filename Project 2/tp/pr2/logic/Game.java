package tp.pr2.logic;

import tp.pr2.Util;
import tp.pr2.logic.Board;
import tp.pr2.logic.Counter;
import tp.pr2.logic.Game;

public class Game {
	
	public static final int WINCON = 2;
	public static final int DIMX = 7;
	public static final int DIMY = 6;
	
	//Class Attributes
	private Board board;
	private Counter turn;
	private boolean finished;
	private Counter winner;
	private boolean full;
	private UndoStack undoStack;

	//Constructs a new game.
	public Game() {
		//Initialize game
		this.board = new Board(Game.DIMX, Game.DIMY);
		this.turn = Counter.WHITE;
		this.finished = false;
		this.winner = Counter.EMPTY;
		this.full = false;
		this.undoStack = new UndoStack();
	}
	
	//Restarts the current game. This operation cannot be undone.
	public void reset() {
		this.board.reset();
		this.turn = Counter.WHITE;
		this.finished = false;
		this.winner = Counter.EMPTY;
		this.full = false;
		this.undoStack.clear();
	}
	
	//Executes the move indicated by the column number provided as argument.
	public boolean executeMove(Counter colour, int column) {
		boolean success = false;
		
		//Check whether column is valid
		if (!Util.isColumnValid(this.board, column) ||
				(colour != this.turn) || 
				this.finished || 
				this.full) {
			success = false;
		} else {
			//SEARCH the column for first free space, then place the counter
			int h = Util.firstEmptyPosition(this.board, column);			
			if (h > Util.ERRORTHRESHOLD) {
				this.board.setPosition(column, h, this.turn);					
				success = true;
				this.undoStack.push(column);					
				this.finished = this.board.checkFinished();
				if (this.finished) {
					this.winner = this.turn;
				}
				this.full = this.board.checkFull();
				if (this.full) {
					this.finished = true;
					this.winner = Counter.EMPTY;
				}
				if (!this.finished) {
					this.changeTurn();
				}
			} 
		}
		
		return success;
	}
	
	private void changeTurn() {
		if (this.turn == Counter.WHITE) {
			this.turn = Counter.BLACK;
		} else {
			this.turn = Counter.WHITE;
		}
	}

	//Undo the last movement executed
	public boolean undo() {		
		boolean success = false;
		if (!this.undoStack.isEmpty()) {
			//Searches desired column from top to bottom
			int h = Util.firstEmptyPosition(this.board, this.undoStack.getLastElement());		
			if (h > Util.ERRORTHRESHOLD) {
				this.board.setPosition(this.undoStack.getLastElement(), h+1, Counter.EMPTY);
				success = true;
				this.undoStack.pop();	
				this.changeTurn();
			} 				
		} else {
			System.out.println("Nothing to undo, please try again");
		}
		return success;
	}
	
	//Returns the color of the player whose turn it is.
	public Counter getTurn() {
		return this.turn;
	}
	
	//Returns the color of the winner. It is only valid if the game has finished (isFinished() == true).
	public Counter getWinner() {
		return this.winner;
	}
	
	//Returns true if the game has finished and false otherwise.
	public boolean isFinished() {		
		return this.finished;
	}
	
	//Accessor method for the board.
	public Board getBoard() {
		return this.board;
	}	
	
	public void displayBoard() {
		this.board.printBoard();		
	}	
}
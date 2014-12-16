package tp.pr2.logic;

import tp.pr2.logic.Board;
import tp.pr2.logic.Counter;
import tp.pr2.logic.Game;

public class Game {
	
	public static final int WINCON = 2;
	
	//Class Attributes
	private Board board;
	private Counter turn;// This attribute is public static because we need in Movement.java
	private boolean finished;
	private Counter winner;
	private boolean draw;
	private UndoStack undoStack;
	private GameRules rules;

	//Constructs a new game.
	public Game(GameRules rules) {
		//Initialize game
		this.board = rules.newBoard();
		this.turn = rules.initialPlayer();
		this.finished = false;
		this.winner = Counter.EMPTY;
		this.draw = false;
		this.undoStack = new UndoStack();
		this.rules = rules;
	}
	
	//Restarts the current game. This operation cannot be undone.
	public void reset(GameRules rules) {
		this.board = rules.newBoard();
		this.turn = rules.initialPlayer();
		this.finished = false;
		this.winner = Counter.EMPTY;
		this.draw = false;
		this.undoStack.clear();
		this.rules = rules;
	}

	//Executes the move indicated by the column number provided as argument.
	public boolean executeMove(Move move) {
		boolean success = false;
		
		//Check whether column is valid
		if ((move.getPlayer() != this.turn) || 
				this.winner != Counter.EMPTY || 
				this.draw) {
			success = false;
		} else {	
			success = move.executeMove(this.board);	
			//A complete and correct movement is pushed
			this.undoStack.push(move);	
			//Update the winner
			this.winner = this.rules.winningMove(move, this.board);
			//Check if the game has ended in a draw
			this.draw = this.rules.isDraw(move.getPlayer(), this.board);
			if (this.draw) {					
				this.winner = Counter.EMPTY;
			}
			if (this.winner == Counter.EMPTY && success) {
				this.turn = this.rules.nextTurn(this.turn, this.board);
			}		
		}		
		return success;
	}


	//Undo the last movement executed
	public boolean undo() {		
		boolean success = false;
		if (!this.undoStack.isEmpty()) {
			Move mov = this.undoStack.getLastElement();
			mov.undo(this.board);			
			this.undoStack.pop();	
			this.turn = this.rules.nextTurn(this.turn, this.board);
			success = true;			
		} else {
			System.err.println("Nothing to undo, please try again");
			success = false;
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

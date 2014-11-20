/**
 * 
 */
package tp.pr2.logic;

public interface GameRules {
	
	/** 
	 * Constructs the board that has to be used for the game, 
	 * according to the rules of the game.
	 * @return The board and it's state at the beginning of the game
	 */
	public Board initializeBoard();
	
	/**
	 * Returns the counter of the player starting the game
	 * @return The counter of the player starting the game
	 */
	public Counter initialPlayer();
	
	/**
	 * Checks whether there's a winner in the game.
	 * @param lastMove Last move made
	 * @param b Current board state
	 * @return The counter of the winner (if there is one)
	 */
	public Counter isWinner(Move lastMove, Board b);
	
	/**
	 * Returns whether the game is in a draw
	 * @param lastPlaced Last player to play their counter
	 * @param b Current board state
	 * @return true if the game is ended without a winner
	 */
	public boolean draw(Counter lastPlaced, Board b);
	
	/**
	 * Returns the player that has to play the next counter.
	 * @param lastPlaced Last player to play their counter
	 * @param b Current board state
	 * @return next player to place a counter
	 */
	public Counter nextTurn(Counter lastPlaced, Board b);
}

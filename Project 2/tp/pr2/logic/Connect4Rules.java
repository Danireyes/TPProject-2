package tp.pr2.logic;

import tp.pr2.DirectionX;
import tp.pr2.DirectionY;
import tp.pr2.Util;

public class Connect4Rules implements GameRules {

	private static final int DIMX = 7;
	private static final int DIMY = 6;
	private static final Counter STARTPLAYER = Counter.WHITE;
	
	/* (non-Javadoc)
	 * @see tp.pr2.logic.GameRules#initializeBoard()
	 */
	@Override
	public Board initializeBoard() {
		Board board = new Board(Connect4Rules.DIMX, Connect4Rules.DIMY);
		return board;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.GameRules#initialPlayer()
	 */
	@Override
	public Counter initialPlayer() {
		return Connect4Rules.STARTPLAYER;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.GameRules#isWinner(tp.pr2.logic.Move, tp.pr2.logic.Board)
	 * Traversing the whole board, check whether the game is finished, 
	 * and returns the winner.
	 */
	@Override
	public Counter isWinner(Move lastMove, Board b) {
		boolean end = false;
		int x = Board.MINWIDTH;
		int y = b.getHeight();
		Counter winner = Counter.EMPTY;
		
		while((y >= Board.MINHEIGHT) && !end) {
			while ((x <= b.getWidth() && !end)) {
				//Check up
				end = Util.checkCellInDirection(b, x, y, DirectionX.NOTHING, DirectionY.UP);
				//Check right
				if (!end) end = Util.checkCellInDirection(b, x, y, DirectionX.RIGHT, DirectionY.NOTHING);
				//Check up-left diagonal
				if (!end) end = Util.checkCellInDirection(b, x, y, DirectionX.LEFT, DirectionY.UP);
				//Check up-right diagonal
				if (!end) end = Util.checkCellInDirection(b, x, y, DirectionX.RIGHT, DirectionY.UP);
				//Increase x counter
				if(!end) x++;
			}
			if (end) {
				winner = b.getPosition(x, y);
			}
			x = Board.MINWIDTH;
			y--;
		}
		return winner;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.GameRules#draw(tp.pr2.logic.Counter, tp.pr2.logic.Board)
	 * Traverses the board looking for n empty cell
	 */
	@Override
	public boolean draw(Counter lastPlaced, Board b) {
		boolean full = true;
		int x = Board.MINWIDTH, y = b.getHeight();
		while((y >= Board.MINHEIGHT) && full) {
			while ((x <= b.getWidth() && full)) {				
				if (b.getPosition(x, y) == Counter.EMPTY) {
					full = false;
				}
				x++;
			}
			x = Board.MINWIDTH;
			y--;
		}
		return full;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.GameRules#nextTurn(tp.pr2.logic.Counter, tp.pr2.logic.Board)
	 */
	@Override
	public Counter nextTurn(Counter lastPlaced, Board b) {
		Counter next = Counter.EMPTY;
		if (lastPlaced == Counter.BLACK) {
			next = Counter.WHITE;
		} else if (lastPlaced == Counter.WHITE) {
			next = Counter.BLACK;
		}
		return next;
	}

}

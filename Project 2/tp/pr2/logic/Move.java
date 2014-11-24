package tp.pr2.logic;

public abstract class Move {
	
	private Counter player;
	private int col;
	
	public abstract boolean executeMove(Board board);// this means that every subclass of movement must have a method executeMovement
	public abstract void undo(Board board); // the same as above but with undo
	
	public Counter getPlayer() {
		return this.player;
	}
	public void setPlayer(Counter player) {
		this.player = player;
	}
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
}

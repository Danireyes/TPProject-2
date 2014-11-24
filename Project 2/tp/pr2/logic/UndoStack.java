package tp.pr2.logic;

public class UndoStack {
	
	//Move Stack
	private static final int UNDO_MAX = 10;
	private Move[] undoStack;
	private int numUndo;
	
	/**
	 * @param undoStack Array storing the moves to undo
	 * @param numUndo Counter marking the first empty position in the array
	 */
	public UndoStack() {
		super();
		this.undoStack = new Move[UndoStack.UNDO_MAX];
		this.numUndo = 0;
	}
	//Appends a movement to undo stack
	public void push(Move m) {
		if (this.numUndo == UndoStack.UNDO_MAX) {
			for(int i = 1; i <= this.numUndo - 1; i++) {
				this.undoStack[i - 1] = this.undoStack[i];
			}
			this.numUndo--;
		}
		this.undoStack[this.numUndo] = m;
		if (this.numUndo < UndoStack.UNDO_MAX) {
			this.numUndo++;
		}
	}
	//Pop and element out of the stack
	public void pop() {
		this.numUndo--;
	}
	public void clear() {
		this.numUndo = 0;
	}
	public boolean isEmpty() {
		boolean empty = false;
		if (this.numUndo == 0) {
			empty = true;
		}
		return empty;
	}
	public Move getLastElement() {
		return this.undoStack[this.numUndo - 1];
	}
}

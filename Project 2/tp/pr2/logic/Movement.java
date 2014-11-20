package tp.pr2.logic;

import java.util.Scanner;

public abstract class Movement {
	
	// Return the colour of the player's counter that is in the turn
	
	@SuppressWarnings("null")

	// to read the column where the move have to be done
	public int readColumn() {
		int col;
		Scanner in = null;
		System.out.println("Please provide the column number: ");
		col = in.nextInt();
		in.nextLine();
		return col;		
	}
	
	public abstract boolean executeMove(Board board);// this mean that every subclass of movement must have a method executeMovement
	public abstract void undo(Board board); // the same as above but with undo
	
	
}



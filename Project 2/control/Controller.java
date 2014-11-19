package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logic.Game;


public class Controller {
	
	private Game game;
	private Scanner in;
	
	public Controller(Game g, java.util.Scanner in)  {
		this.game = g;
		this.in = in;
	}

	public void run() {
		Instruction inst = Instruction.ERROR;
		String currentTurn;
		while (!this.game.isFinished() && !(inst.equals(Instruction.EXIT))) {
			this.game.displayBoard();
			System.out.println("");
			currentTurn = this.game.getTurn().toString();
			currentTurn = this.convertTurnFirstUpper(currentTurn);
			System.out.println(currentTurn + " to move.");
			inst = readInstruction(this.in);
			switch (inst) {
			case MOVE:
				this.game.executeMove(this.game.getTurn(), this.readColumn());
				break;
			case UNDO:
				this.game.undo();
				break;
			case RESTART:
				this.game.reset();
				System.out.println("Game restarted.");
				break;
			case ERROR:
				//non existing command
				System.out.println("Invalid move, please try again.");
			case EXIT:
				break;
			default:
				break;			
			}						
		}
		if (this.game.isFinished()) {
			System.out.println("Game over. " + this.convertTurnFirstUpper(this.game.getWinner().toString()) + " wins");
		}
		System.out.println("Closing the game...");	
	}
	
	private String convertTurnFirstUpper(String currentTurn) {
		String out;
		if (currentTurn.equals("WHITE")) {
			out = "White";
		} else {
			out = "Black";
		}
		return out;
	}

	private Instruction readInstruction(java.util.Scanner in) {
		Instruction inst = Instruction.ERROR;
		String instString = "";
		//Start scanner
		System.out.print("Please enter a command: ");		
		instString = in.nextLine();
		instString = instString.toUpperCase();
		if (instString.equals("MAKE A MOVE")) {
			instString = "MOVE";
		}		
		try {
			inst = Instruction.valueOf(instString);
		} catch (IllegalArgumentException e) {
			inst = Instruction.ERROR;
		}
		return inst;		
	}
	
	private int readColumn() {
		int col;
		System.out.println("Please provide the column number: ");
		col = this.in.nextInt();
		this.in.nextLine();
		return col;		
	}

}

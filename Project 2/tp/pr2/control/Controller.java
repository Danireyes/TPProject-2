package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logic.ComplicaMove;
import tp.pr2.logic.Connect4Move;
import tp.pr2.logic.Game;
import tp.pr2.logic.Move;

public class Controller {
	
	private Game game;
	private Scanner in;
	private DifferentRules rules;
	
	public Controller(Game g, java.util.Scanner in)  {
		this.game = g;
		this.in = in;
		this.rules = DifferentRules.CONNECT4;
	}

	public void run() {
		Instruction inst = Instruction.ERROR;
		String currentTurn;
		int moves = 0;
		while (!this.game.isFinished() && !(inst.equals(Instruction.EXIT))) {
			this.game.displayBoard();
			System.out.println("");
			currentTurn = this.game.getTurn().toString();
			currentTurn = this.convertTurnFirstUpper(currentTurn);
			System.out.println(currentTurn + " to move.");
			inst = readInstruction(this.in);
			switch (inst) {
			case MOVE:
				Move mov = null;
				if (this.rules == DifferentRules.CONNECT4) {
					mov = new Connect4Move(readColumn(), this.game.getTurn());
				} 
				else if (this.rules == DifferentRules.COMPLICA) {
					mov = new ComplicaMove(readColumn(), this.game.getTurn());
				}
				moves++;
				this.game.executeMove(mov);
				break;
			case UNDO:
				if (moves > 0){
					this.game.undo();
					moves--;
				}
				else {
					System.out.println("You have to make a move... \n");
				}
				break;
			case RESTART:
				this.game.reset(this.rules.getRules());
				System.out.println("Game restarted.");
				moves = 0;
				break;
			case ERROR:
				//non existing command
				System.out.println("Invalid move, please try again.");
			case EXIT:
				break;
			case PLAY_C4:
				this.rules = DifferentRules.CONNECT4;
				this.game.reset(this.rules.getRules());
				moves = 0;
				break;
			case PLAY_CO:
				this.rules = DifferentRules.COMPLICA;
				this.game.reset(this.rules.getRules());
				moves = 0;
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
		} else if (instString.equals("PLAY C4")){
			instString = "PLAY_C4";
		} else if (instString.equals("PLAY CO")){
			instString = "PLAY_CO";
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

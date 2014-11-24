package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logic.ComplicaMovement;
import tp.pr2.logic.ComplicaRules;
import tp.pr2.logic.Connect4Movement;
import tp.pr2.logic.Connect4Rules;
import tp.pr2.logic.Game;
import tp.pr2.logic.GameRules;
import tp.pr2.logic.Move;

public class Controller {
	
	private Move mov;
	private Game game;
	private Scanner in;
	private GameRules rules;
	
	public Controller(Game g, java.util.Scanner in)  {
		this.game = g;
		this.in = in;
		this.rules = new Connect4Rules();
		this.mov = new Connect4Movement();
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
				this.mov.setCol(this.readColumn());
				this.mov.setPlayer(this.game.getTurn());
				this.game.executeMove(this.mov);
				break;
			case UNDO:
				this.mov.undo(game.getBoard());
				break;
			case RESTART:
				this.game.reset(this.rules);
				System.out.println("Game restarted.");
				break;
			case ERROR:
				//non existing command
				System.out.println("Invalid move, please try again.");
			case EXIT:
				break;
			case PLAY_C4:
				this.rules = new Connect4Rules();
				this.mov = new Connect4Movement();
				this.game.reset(this.rules);
				break;
			case PLAY_CO:
				this.rules = new ComplicaRules();
				this.mov = new ComplicaMovement();
				this.game.reset(this.rules);
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

package tp.pr1;

import tp.pr1.control.Controller;
import tp.pr1.logic.Game;

import java.util.Scanner;

public class Main{
	
	public static void main(java.lang.String[] args) {
		Game game = new Game();
		Scanner in = new java.util.Scanner(System.in);
		Controller controller = new Controller(game, in);
		
		controller.run();
	}

}

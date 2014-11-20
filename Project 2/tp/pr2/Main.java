package tp.pr2;

import tp.pr2.control.Controller;
import tp.pr2.logic.Game;

import java.util.Scanner;

public class Main{
	
	public static void main(java.lang.String[] args) {
		Game game = new Game();
		Scanner in = new java.util.Scanner(System.in);
		Controller controller = new Controller(game, in);
		
		controller.run();
	}

}
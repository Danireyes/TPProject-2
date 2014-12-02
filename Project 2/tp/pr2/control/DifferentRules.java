package tp.pr2.control;

import tp.pr2.logic.ComplicaRules;
import tp.pr2.logic.Connect4Rules;
import tp.pr2.logic.GameRules;
/**
 * This is made to simplify the Controller. 
 * Since the different GameRules are singleton,
 * we might as well have only an enum, and thus
 * avoid creating too many rules objects.
 * 
 */
public enum DifferentRules {
	CONNECT4 (new Connect4Rules()),
	COMPLICA (new ComplicaRules());
	
	private final GameRules rules;
	DifferentRules(GameRules rules) {
		this.rules = rules;
	}
	public GameRules getRules() {
		return rules;
	}
	
	
	
	//toString method
}

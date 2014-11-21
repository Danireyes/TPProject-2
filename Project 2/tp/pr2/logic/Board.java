package tp.pr2.logic;

import tp.pr2.DirectionX;
import tp.pr2.DirectionY;
import tp.pr2.Util;
import tp.pr2.logic.Board;
import tp.pr2.logic.Counter;

public class Board {
	
	public static final int MINWIDTH = 1;
	public static final int MINHEIGHT = 1;
	private int tx, ty;
	private Counter[][] brd;
	
	public Board(int tx, int ty) {
		if (correctWidth(tx) && correctHeight(ty)) {
			this.tx = tx;
			this.ty = ty;
		} else {
			this.tx = Board.MINWIDTH;
			this.ty = Board.MINHEIGHT;
		}
		this.brd = new Counter[this.tx][this.ty];
		reset();
	}
	private boolean correctHeight(int ty2) {
		boolean correct = true;
		if (ty2 < Board.MINHEIGHT) {
			correct = false;
		}
		return correct;
	}
	private boolean correctWidth(int tx2) {
		boolean correct = true;
		if (tx2 < Board.MINWIDTH) {
			correct = false;
		}
		return correct;
	}
	public Board() {
		this.tx = Board.MINWIDTH;
		this.ty = Board.MINHEIGHT;
		this.brd = new Counter[tx][ty];
		reset();
	}
	
	public int getHeight() {
		return this.ty;
	}
	
	public int getWidth() {
		return this.tx;
	}
	
	public Counter getPosition(int x, int y) {	
		Counter ctr = Counter.EMPTY;
		if (checkPosition(x, y)) {
			ctr = this.brd[x-1][y-1];
		}
		return ctr;
	}
	
	public void setPosition (int x, int y, Counter colour) {
		this.brd[x-1][y-1] = colour;
	}
	
	public void reset() {
		for (int i = 0; i < tx; i++) {
			for (int j = 0; j < ty; j++) {
				this.brd[i][j] = Counter.EMPTY;
			}
		}
	}
	
	public String toString() {
		String board = "";
		for (int i = 1; i <= this.ty; i++) {
			board = board + "|";
			for (int j = 1; j <= this.tx; j++) {
				board = board + this.counterToString(getPosition((j), (i)));
			}
			board = board + "|" + "\n";
		}
		board = board + "+";
		for (int h = 1; h <= this.tx; h++){
			board = board + "-";
		}
		board = board + "+" + "\n";
		board = board + " ";
		for (int h = 1; h <= this.tx; h++){
			board = board + h;
		}
		board = board + "\n";
		return board;
	}
	
	public boolean checkPosition(int x, int y) {
		boolean ok = false;
		if ((x >= Board.MINWIDTH) && (y >= Board.MINHEIGHT)){			
			if (x <= this.tx && y <= this.ty) {
				ok = true;
			}
		} else {
			System.out.println("Not valid position");
		}
		return ok;
	}
	
	public void printBoard() {
		System.out.print(this.toString());
	}
	
	public boolean checkFull() {
		boolean full = true;
		int x = Board.MINWIDTH, y = this.getHeight();
		while((y >= Board.MINHEIGHT) && full) {
			while ((x <= this.getWidth() && full)) {				
				if (this.getPosition(x, y) == Counter.EMPTY) {
					full = false;
				}
				x++;
			}
			x = Board.MINWIDTH;
			y--;
		}
		return full;
	}
	public static int getMinwidth() {
		return MINWIDTH;
	}
	public static int getMinheight() {
		return MINHEIGHT;
	}
	
	public String counterToString(Counter counter) {
		String cell = " ";
		switch (counter) {
		case BLACK:
			cell = "X";
			break;
		case WHITE:
			cell = "O";
			break;
		default:
			cell = " ";
		}
		return cell;
	}
	
}
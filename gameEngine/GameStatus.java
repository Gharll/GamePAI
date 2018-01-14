package gameEngine;

import java.io.Serializable;

public abstract class GameStatus implements Serializable {
	
	private static GameStatusFreez freez = new GameStatusFreez();
	private static GameStatusActive active = new GameStatusActive();
	private static GameStatusWin win = new GameStatusWin();
	
	public static GameStatusFreez getStatusFreez(){
		return freez;
	}
	
	public static GameStatusActive getStatusActive(){
		return active;
	}
	
	public static GameStatusWin getStatusWin(){
		return win;
	}
	
	public abstract void getInfo();
}

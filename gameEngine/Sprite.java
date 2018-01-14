package gameEngine;

public class Sprite {
	private char sprite;
	
	public Sprite(char sprite){
		this.sprite = sprite;
	}
	
	public static Sprite getPlayerOneSprite(){
		return new Sprite('o');
	}
	
	public static Sprite getPlayerTwoSprite(){
		return new Sprite('x');
	}
	
	public static Sprite getObstacleSprite(){
		return new Sprite('[');
	}
	
	public static Sprite getEmptyFieldSprite(){
		return new Sprite('W');
	}
	
	public void setChar(char c){
		this.sprite = c;
	}
	
	public char getChar(){
		return sprite;
	}
	
	public static boolean compareSprite(Sprite first, Sprite second){
		if(first.getChar() == second.getChar()){
			return true;
		} else {
			return false;
		}
	}
}

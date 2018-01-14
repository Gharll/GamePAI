package gameEngine;

import java.io.Serializable;

public class Position implements Serializable{
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void iterateHorizontal(){
		this.x += 1;
	}
	
	public void iterateVertical(){
		this.y += 1;
	}
	
	public void iterateRightDiagonally(){
		this.x += 1;
		this.y += 1;
	}
	
	public void iterateRightDiagonallyBack(){
		this.x -= 1;
		this.y -= 1;
	}
	
	public void iterateLeftDiagonally(){		
		this.x -= 1;
		this.y +=1;
	}
	
	public void iterateLeftDiagonallyBack(){		
		this.x += 1;
		this.y -=1;
	}
	
	public void print(){
		System.out.println(this.x + " " + this.y);
	}
	
	public Position getCopy(){
		return new Position(this.x, this.y);
	}
	
	public static boolean compare(Position first, Position second){
		if(first.getX() == second.getX() && first.getY() == second.getY()){
			return true;
		} else {
			return false;
		}
	}
}

package gameEngine;

import java.io.Serializable;

public class Obstacle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position position;
	
	public Obstacle(Position position){
		this.position = position;
	}
	
	public Position getPosition(){
		return position;
	}
}

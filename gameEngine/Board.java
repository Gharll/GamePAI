package gameEngine;

import java.util.ArrayList;

public class Board {
	private int size;
	private int obstaclesQuantity;
	private ArrayList<Row> rows;
	private ObstacleList obstacleList;
	
	public Board(int size){
		this.size = size;
		boardInit();
	}
	
	public Board(int size, int obstaclesQuantity){
		this.size = size;
		this.obstaclesQuantity = obstaclesQuantity;
		boardInit();
		generateObstacles();
	}
	
	
	public void boardInit(){
		rows = new ArrayList<>(size);
		for(int i = 0; i < size; i++){
			rows.add(new Row(size, i));
		}
	}
	
	public void generateObstacles(){
		obstacleList = new ObstacleList(obstaclesQuantity, size);
		for(Obstacle obstacle : obstacleList.getList()){
			Position position = obstacle.getPosition();
			this.setSprite(position, Sprite.getObstacleSprite());
		}
	}
	
	public Row getRow(int y){
		return rows.get(y);
	}
	
	public ArrayList<Field> getColumn(int x){
		ArrayList<Field> column = new ArrayList<>(size);
		Position position = new Position(x, 0);
		while(position.getY() < size){
			column.add(getField(position));
			position.iterateVertical();
		}
		
		return column;
	}
	
	public ArrayList<Field> getRightDiagonally(Position pos){
		Position position = pos.getCopy();
		while(position.getX() > 0 && position.getY() > 0){
			position.iterateRightDiagonallyBack();
		}
		
		int fieldsSize = this.size - position.getY();
		ArrayList<Field> rightDiagonally = new ArrayList<>(fieldsSize);
		while(position.getX() < fieldsSize - 1 && position.getY() < fieldsSize - 1){
			rightDiagonally.add(getField(position));
			position.iterateRightDiagonally();
		}
		
		return rightDiagonally;
	}
	
	public ArrayList<Field> getLeftDiagonally(Position pos){
		Position position = pos.getCopy();
		while(position.getX() < this.size - 1 && position.getY() > 0){
			position.iterateLeftDiagonallyBack();
		}
		
		int fieldsSize = this.size - position.getY();
		ArrayList<Field> rightDiagonally = new ArrayList<>(fieldsSize);
		while(position.getX() > 0 && position.getY() < fieldsSize - 1){
			rightDiagonally.add(getField(position));
			position.iterateLeftDiagonally();
		}
		
		return rightDiagonally;
	}
	
	public ArrayList<Row> getRows(){
		return rows;
	}
	
	public Field getField(Position position){
		return getRow(position.getY()).getField(position.getX());
	}
	
	public void setSprite(Position position, Sprite sprite){
		getField(position).setSprite(sprite);
	}
	
	public int getObstaclesQuantity(){
		return obstaclesQuantity;
	}
	
	public ObstacleList getObstacleList(){
		return obstacleList;
	}
	
}

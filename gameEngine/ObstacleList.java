package gameEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ObstacleList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int listSize;
	private int boardSize;
	private ArrayList <Obstacle> obstacleList;
	
	public ObstacleList(int listSize, int boardSize){
		this.listSize = listSize;
		this.boardSize = boardSize;
		obstacleList = new ArrayList<>(listSize);
		generateObstacles();
	}
	
	public void generateObstacles(){
		if(boardSize*boardSize > listSize){
			for(int i = 0; i < listSize; i++){
				Obstacle obstacle = generateObstacle();
				obstacleList.add(obstacle);
			}
			checkForDublicats();
		}
		
	}
	
	private Obstacle generateObstacle(){
		Random randomGenerator = new Random();
		int generatedX = randomGenerator.nextInt(boardSize);
		int generatedY = randomGenerator.nextInt(boardSize);
		Position position = new Position(generatedX, generatedY);
		Obstacle obstacle = new Obstacle(position);
		return obstacle;
	}
	
	private void checkForDublicats(){
		for(int i = 0; i < listSize - 1; i++){
			Position first = obstacleList.get(i).getPosition();
			Position second = obstacleList.get(i + 1).getPosition();
			while(Position.compare(first, second)){
				Obstacle obstacle = generateObstacle();
				first = obstacle.getPosition();
				obstacleList.set(i, obstacle);
			}
		}
	}
	
	public ArrayList <Obstacle> getList(){
		return obstacleList;
	}
	
	public void print(){
		for(Obstacle o : obstacleList){
			o.getPosition().print();
		}
	}
	
	public static void main(String [] args){
		ObstacleList ol = new ObstacleList(35, 6);
		ol.generateObstacles();
		ol.print();
		
	}
}

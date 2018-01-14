package application.model;

import java.util.ArrayList;

import application.view.FieldController;
import application.view.GameRoomLayoutController;
import gameEngine.Position;
import javafx.scene.layout.GridPane;

public class Board {
	
	public final int CELL_MIN_SIZE = 25;
	public final int CELL_PREF_SIZE = 100;
	public final int CELL_MAX_SIZE = 200;
	
	private int size;
	private ArrayList <FieldController> fields = new ArrayList<>();
	private GridPane gridBoardPane = new GridPane();
	
	
	public Board(int size){
		this.size = size;
	}
	
	public FieldController getFieldController(Position position){
		return fields.get(position.getY() * size + position.getX());
	}
	
	public GridPane getGridBoardPane(){
		return gridBoardPane;
	}
	
	public int getBoardSize(){
		return size;
	}
	
	public ArrayList<FieldController> getFields(){
		return fields;
	}
}

package application.view;

import java.io.IOException;
import java.util.ArrayList;

import application.MainApp;
import application.model.Board;
import application.model.Sprite;
import gameEngine.Config;
import gameEngine.Obstacle;
import gameEngine.ObstacleList;
import gameEngine.Position;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class GameRoomLayoutController {
	
	@FXML
	private BorderPane mainPane;
	@FXML
	private Label roomIdLabel;
	@FXML
	private Label gameStatusLabel;
	@FXML
	private Label userOneLabel;
	@FXML
	private Label userTwoLabel;
	@FXML
	private Label roundNumberLabel;
	@FXML
	private Button disconnectButton;
	
	private int boardSize;
	private BoardController boardController;
	
	public GameRoomLayoutController() {

	}
	
	@FXML
	private void initialize() {		
		
	}

	public void setRoomIdLabel(String id){
		roomIdLabel.setText(id);
	}
	
	// Reference to the main application.
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void createGameBoard(int boardSize){
		this.boardSize = boardSize;
		Board board = new Board(boardSize);
		boardController = new BoardController(board, this);
		mainPane.setCenter(boardController.getBoard().getGridBoardPane());
	}
	
	public void setRoomInfo(ArrayList<String> args){
		roomIdLabel.setText(args.get(0));
		userOneLabel.setText(args.get(1));
		userTwoLabel.setText(args.get(2));
	}
	
	public void setSpriteOnPosition(Position position, Circle circle){
		boardController.getBoard().getFieldController(position).getField().setSprite(circle);
	}
	
	public void setSpriteOnPosition(Position position, Text text){
		boardController.getBoard().getFieldController(position).getField().setSprite(text);
	}
	
	public void nextMoveRequest(Position position) throws IOException{
		mainApp.getClient().nextMove(position);
	}
	
	@FXML
	public void disconnectRequest() throws IOException{
		mainApp.getClient().disconnectRequest();
	}
	
	public void setGameStatus(String status){
		gameStatusLabel.setText(status);
	}

	public void setObstacles(ObstacleList obstacleList){
		for(Obstacle o : obstacleList.getList()){
			boardController.getBoard().getFieldController(o.getPosition()).getField().setSprite(Sprite.getObstacle());
		}
	}
	
	public void setRoundNumber(int roundNumber){
		this.roundNumberLabel.setText(String.valueOf(roundNumber));
	}
	
	
	public Board getBoard(){
		return boardController.getBoard();
	}
	
	public BoardController getBoardController(){
		return boardController;
	}
	
}
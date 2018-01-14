package application.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.MainApp;
import application.model.Sprite;
import gameEngine.ObstacleList;
import gameEngine.Position;
import javafx.application.Platform;
import server.CommandMessage;

public class ClientCommandController {
	private CommandMessage command;
	private GameRoomLayoutController gameRoom;
	private RoomMenuLayoutController roomMenu;
	private RootLayoutController root;
	private MainApp mainApp;
	
	Map <String, Runnable> commandMap = new HashMap<>();
	public ClientCommandController(MainApp mainApp){
		this.mainApp = mainApp;
		this.gameRoom = mainApp.getGameLayoutController();
		this.roomMenu = mainApp.getRoomMenuController();
		this.root = mainApp.getRootLayoutController();
				
		commandMap.put("set user id", () -> setUserId());
		commandMap.put("create new room", () -> createNewRoom());
		commandMap.put("set room info table", () -> setRoomInfoTable());
		commandMap.put("join room", () -> joinRoom());
		commandMap.put("set game room info", () -> setGameRoomInfo());
		commandMap.put("set sprite on position", () -> setSpriteOnPosition());
		commandMap.put("disconnect", () -> disconnect());
		commandMap.put("game status active", () -> setGameStatusActive());
		commandMap.put("game status freez", () -> setGameStatusFreez());
		commandMap.put("game status win", () -> setGameStatusWin());
		commandMap.put("clear board", () -> clearBoard());
		commandMap.put("set obstacles", () -> setObstacles());
		commandMap.put("error", () -> errorHandler());
		commandMap.put("set round number", () -> setRoundNumber());
		commandMap.put("set game board", () -> setGameBoard());
	}

	public synchronized void execute(CommandMessage command){
		this.command = command;
		hideError();
		commandMap.get(command.getKeyWords()).run();
	}
	
	private void setUserId(){
		int id = (int) command.getObjects().get(0);
		Platform.runLater(() -> root.setUserIdLabel(id));

	}
	
	private void createNewRoom(){
		ArrayList<String> args = command.getArgs();
		Platform.runLater(() -> roomMenu.addNewRoom(args));
	}
	
	private void setRoomInfoTable(){
		Platform.runLater(() -> {
			try {
				roomMenu.refreshRoomInfoTable((ArrayList<ArrayList<String>>) command.getObjects().get(0));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void joinRoom(){
		Platform.runLater(() -> roomMenu.joinRoom());
	}
	
	private void setGameRoomInfo() {
		ArrayList<String> args = command.getArgs();
		Platform.runLater(() -> gameRoom.setRoomInfo(args));
	}
	
	private void setSpriteOnPosition(){
		Position position = (Position) command.getObjects().get(0);
		String playerString = (String) command.getObjects().get(1);
		if(playerString.equals("PlayerOne")){
			Platform.runLater(() -> gameRoom.setSpriteOnPosition(position, Sprite.getPlayerOne()));
		}
		else if(playerString.equals("PlayerTwo")){	
			Platform.runLater(() -> gameRoom.setSpriteOnPosition(position, Sprite.getPlayerTwo()));
		}		
	}
	
	private void disconnect(){
		Platform.runLater(() -> mainApp.changeToRoomLayout());
		Platform.runLater(() -> gameRoom.getBoardController().clearGridBoard());
	}
	
	private void setGameStatusActive(){
		String status = (String) command.getObjects().get(0);
		Platform.runLater(() -> gameRoom.setGameStatus(status));
	}
	
	private void setGameStatusFreez(){
		String status = (String) command.getObjects().get(0);
		Platform.runLater(() -> gameRoom.setGameStatus(status));
	}
	
	private void setGameStatusWin(){
		String status = (String) command.getObjects().get(0);
		Platform.runLater(() -> gameRoom.setGameStatus(status));
	}
	
	private void clearBoard(){
		Platform.runLater(() -> gameRoom.getBoardController().clearGridBoard());
	}
	
	private void errorHandler(){
		String error = (String) command.getObjects().get(0);
		Platform.runLater(() -> root.setErrorInfo(error));
	}
	
	private void setObstacles() {
		ObstacleList obstacleList = (ObstacleList) command.getObjects().get(0);
		Platform.runLater(() -> gameRoom.setObstacles(obstacleList));
	}
	
	private void hideError(){
		Platform.runLater(() -> root.setErrorVisible(false));
	}
	
	private void setRoundNumber() {
		int roundNumber = (int) command.getObjects().get(0);
		Platform.runLater(() -> gameRoom.setRoundNumber(roundNumber));
	}
	
	private void setGameBoard(){
		int boardSize = (int) command.getObjects().get(0);
		Platform.runLater(() -> gameRoom.createGameBoard(boardSize));
	}
	
}

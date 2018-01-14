package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import application.model.User;
import gameEngine.GameStatus;
import gameEngine.GameStatusActive;
import gameEngine.GameStatusFreez;
import gameEngine.GameStatusWin;
import gameEngine.Position;

public class ServerCommandController {
	private User user;
	private Room room;
	private ClientHandler clientHandler;
	private CommandMessage command;
	
	public User getUser(){
		return user;
	}
	
	public Room getRoom(){
		return room;
	}
	
	Map <String, Runnable> commandMap = new HashMap<>();
	ServerCommandController(ClientHandler clientHandler){
		this.clientHandler = clientHandler;
		this.user = UserList.createNextUserAndGetInstance(clientHandler);
		commandMap.put("get user id", () -> userIdResponse());
		commandMap.put("create new room", () -> createNewRoomResponse());
		commandMap.put("get room info table", () -> roomInfoTableResponse());
		commandMap.put("join room", () -> joinRoomResponse());
		commandMap.put("next move", () -> nextMoveResponse());
		commandMap.put("disconnect", () -> disconnectResponse());
		commandMap.put("get obstacles", () -> sendObstacles());
	}



	public void execute(CommandMessage command){
		this.command = command;
		commandMap.get(command.getKeyWords()).run();
	}
	
	public void response(CommandMessage commandMessage){
		try {
			clientHandler.getWriter().writeObject(commandMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void responsePlayerOne(CommandMessage message) throws IOException{
		responsePlayerNumber(message, 1);
	}
	
	public void responsePlayerTwo(CommandMessage message) throws IOException{
		responsePlayerNumber(message, 2);
	}
	
	private void responsePlayerNumber(CommandMessage message, int number) throws IOException{
		User user = room.getUser(number);
		if(user != null){
			user.getClientHandler().respone(message);
		}
	}
	
	private void responseErrorMessage(String error){
		CommandMessage errorMessage = new CommandMessage("error");
		errorMessage.addArgumentObject(error);
		response(errorMessage);
	}
	
	private void responseEnemyPlayer(CommandMessage message){
		if(room.getUserOne() == this.user){
			try {
				room.getUserTwo().getClientHandler().respone(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (room.getUserTwo() == this.user){
			try {
				room.getUserOne().getClientHandler().respone(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void userIdResponse(){
		CommandMessage command = new CommandMessage("set user id");
		command.setArgumentObjects(user.getId());
		response(command);
	}
	
	
	public void createNewRoomResponse(){
		CommandMessage command = new CommandMessage("create new room");
		this.room = RoomList.createNewRoom(user);
		command.setArgs(room.getRoomInfoList());
		setGameBoardMessage();
		response(command);
		roomInfoSend();
		gameStatusSend();
		sendObstacles();
		sendRoundNumber();
	}
	
	public void sendObstacles(){
		CommandMessage message = new CommandMessage("set obstacles");
		message.setArgumentObjects(room.getGame().getBoard().getObstacleList());
		response(message);
	}
	
	public void roomInfoTableResponse(){
		CommandMessage message = new CommandMessage("set room info table");
		message.setArgumentObjects(RoomList.getRoomListInfoList());
		response(message);
	}
	
	
	public void joinRoomResponse(){
		int roomId = (int)command.getObjects().get(0);
		boolean isAssign = RoomList.assignUserToRoom(roomId, user);
		if(isAssign){
			this.room = RoomList.getRoom(roomId);
			room.updateGameStatus();
			setGameBoardMessage();
			CommandMessage commandMessage = new CommandMessage("join room");
			commandMessage.setArgumentObjects(roomId);
			response(commandMessage);
			roomInfoSend();
			gameStatusSend();
			sendObstacles();
			sendRoundNumber();
		} else {
			responseErrorMessage("You can't join to this room");
		}
		
	}
	
	public void roomInfoSend(){
		CommandMessage info = new CommandMessage("set game room info");
		info.setArgs(room.getRoomInfoList());
		room.sendMessage(info);
	}
	
	public void nextMoveResponse(){
		Position position = (Position) command.getObjects().get(0);
		room.updateGameStatus();
		if(room.getGame().nextMove(user.getPlayer(), position)){
			CommandMessage command = new CommandMessage("set sprite on position");
			command.addArgumentObject(position);
			command.addArgumentObject(user.getPlayer().getPlayerString());
			room.sendMessage(command);
			gameStatusSend();
			sendRoundNumber();
		} else {
			responseErrorMessage("You can't do this move");
		}
	}
	
	public void disconnectResponse() {
		if(room.unassignUser(this.user)){
			room.updateGameStatus();
			CommandMessage command = new CommandMessage("disconnect");
			response(command);
			roomInfoTableResponse();
			roomInfoSend();
			gameStatusSend();
		}
	}
	
	public void gameStatusSend(){
		GameStatus status = room.getGame().getGameStatus();
		if(status instanceof GameStatusActive){
			try {
				createGameStatusActiveMessage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(status instanceof GameStatusFreez){
			createGameStatusFreezMessage();
		}
		else if(status instanceof GameStatusWin){
			createWinMessage();
		}
	}
	
	private void createGameStatusActiveMessage() throws IOException{
		CommandMessage messageYourTurn = new CommandMessage("game status active");
		messageYourTurn.setArgumentObjects("Your turn");
		CommandMessage messageEnemyTurn = new CommandMessage("game status active");
		messageEnemyTurn.addArgumentObject("Enemy Turn");
		if(room.getGame().getCurrentTurnPlayer() == this.user.getPlayer()){
			this.response(messageYourTurn);
			this.responseEnemyPlayer(messageEnemyTurn);
			
		} else {
			this.response(messageEnemyTurn);
			this.responseEnemyPlayer(messageYourTurn);
		}
		
	}
	
	private void createGameStatusFreezMessage(){
		CommandMessage message = new CommandMessage("game status freez");
		message.addArgumentObject("Freez");
		room.sendMessage(message);
	}
	
	private void createWinMessage(){
		CommandMessage messageWon = new CommandMessage("game status win");
		CommandMessage messageLoose = new CommandMessage("game status win");
		messageWon.setArgumentObjects("You won!");
		messageLoose.setArgumentObjects("You lose!");
		
		if(room.getGame().getCurrentTurnPlayer() == this.user.getPlayer()){
			this.response(messageLoose);
			this.responseEnemyPlayer(messageWon);
		} else {
			this.response(messageWon);
			this.responseEnemyPlayer(messageLoose);
		}
	}
	
	private void sendRoundNumber(){
		CommandMessage message = new CommandMessage("set round number");
		message.setArgumentObjects(room.getGame().getRoundNumber());
		room.sendMessage(message);
	}
	
	private void setGameBoardMessage(){
		CommandMessage message = new CommandMessage("set game board");
		message.setArgumentObjects(room.getGame().getRules().getBoardSize());
		response(message);
	}
}

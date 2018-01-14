package server;

import java.io.IOException;
import java.util.ArrayList;

import application.model.User;
import gameEngine.Config;
import gameEngine.Game;
import gameEngine.Rules;

public class Room {
	
	private int roomId;
	private User userOne;
	private User userTwo;
	private Game game = new Game();
	private Config config = Config.getInstance();
	
	public Room(int id){
		this.roomId = id;
		Rules rules = game.getRules();
		rules.setBoardSize(config.getBoardSize());
		rules.setMatchsToWin(config.getMatchesToWin());
		game.gameInit();
	}
	
	public User getUserOne(){
		return userOne;
	}
	
	public User getUserTwo(){
		return userTwo;
	}
	
	public boolean assignUser(User user){
		if(userOne == null){
			this.userOne = user;
			userOne.isAlreadyAssignToRoom(true);
			user.setPlayer(game.getPlayerOne());
			user.setUserNumberInRoom(1);
			return true;
		}
		else if(userTwo == null){
			this.userTwo = user;
			userTwo.isAlreadyAssignToRoom(true);
			user.setPlayer(game.getPlayerTwo());
			user.setUserNumberInRoom(2);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void unassignUserOne(User user){
		if(user != null){
			this.userOne = null;
		}
		
	}
	
	public void unassignUserTwo(User user){
		this.userTwo = null;
	}
	
	public boolean unassignUser(User user){
		boolean isUnassign = false;
		if(userOne == user){
			userOne = null;
			isUnassign = true;
		} 
		else if (userTwo == user) {
			userTwo = null;
			isUnassign = true;
		}
		
		if(userOne == null && userTwo == null){
			RoomList.removeRoom(this);
		}
		
		return isUnassign;
	}
	
	public boolean isAvaliable(){
		return userOne == null || userTwo == null;
	}
	
	public int getRoomId(){
		return roomId;
	}
	
	public User getUser(int number){
		if(number == 1){
			return userOne;
		}
		else if(number == 2){
			return userTwo;
		} else {
			return null;
		}
	}
	
	public boolean updateGameStatus(){
		if(isAvaliable()){
			game.stopGame();
			return false;
		} else {
			game.startGame();
			return true;
		}
	}
	
	public ArrayList<String> getRoomInfoList(){
		ArrayList <String> argList = new ArrayList<String>();
		argList.add(String.valueOf(roomId));

		if(userOne != null){
			argList.add(String.valueOf(userOne.getNicknameAndId()));
		} else {
			argList.add("Free slot");
		}
		if(userTwo != null){
			argList.add(String.valueOf(userTwo.getNicknameAndId()));
		} else {
			argList.add("Free slot");
		}
		argList.add(String.valueOf(isAvaliable()));

		return argList;
	}
	
	public void sendMessage(CommandMessage commandMessage){
		try{
			if(userOne != null){
				userOne.getClientHandler().respone(commandMessage);
			}
			if(userTwo != null){
				userTwo.getClientHandler().respone(commandMessage);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Game getGame(){
		return game;
	}
}

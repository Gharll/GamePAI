package application.model;

import java.io.Serializable;

import gameEngine.Player;
import server.ClientHandler;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String nickname = "User";
	private int id;
	private boolean isAlreadyAssignToRoom = false;
	private ClientHandler clientHandler;
	private Player player;
	private int userNumberInRoom;
	
	public User(int id, ClientHandler clientHandler){
		this.id = id;
		this.clientHandler = clientHandler;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNicknameAndId(){
		return nickname + "[id:" + id + "]";
	}
	
	public boolean isAlreadyAssignToRoom(){
		return isAlreadyAssignToRoom;
	}
	
	public void isAlreadyAssignToRoom(boolean value){
		isAlreadyAssignToRoom = value;
	}
	
	public ClientHandler getClientHandler(){
		return clientHandler;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void setUserNumberInRoom(int value){
		this.userNumberInRoom = value;
	}
	
	public int getUserNumberInRoom(){
		return userNumberInRoom;
	}
}

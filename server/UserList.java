package server;
import java.util.LinkedList;

import application.model.User;

public class UserList {
	
	private static LinkedList <User> userList = new LinkedList<>();
	private static int idCounter = 0;
	
	private static int assignAndGetNextId(){
		return idCounter++;
	}
	
	public static User createNextUserAndGetInstance(ClientHandler ch){
		User user = new User(assignAndGetNextId(), ch);
		userList.add(user);
		return user;
	}
}

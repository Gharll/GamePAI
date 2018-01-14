package server;

import java.util.ArrayList;
import java.util.LinkedList;

import application.model.User;

public class RoomList {
	
	private static LinkedList <Room> roomList = new LinkedList<>();
	private static int id = 0;
	
	public static LinkedList <Room> getRoomList(){
		return roomList;
	}
	
	private static int getNextId(){
		return id++;
	}
	
	public static Room createNewRoom(User user){
		Room room = new Room(getNextId());
		room.assignUser(user);
		roomList.add(room);
		return room;
	}
	
	public static void removeRoom(Room room){
		roomList.remove(room);
	}
	
	
	public static boolean assignUserToRoom(int roomId, User user){
		for(Room room : roomList){
			if(room.getRoomId() == roomId){
				if(room.assignUser(user)){
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public static ArrayList <ArrayList<String>> getRoomListInfoList(){
		ArrayList <ArrayList<String>> argList = new ArrayList<>();
		for(Room room : roomList){
			argList.add(room.getRoomInfoList());
		}
		return argList;
	}
	
	public static Room getRoom(int id){
		for(Room room : roomList){
			if(room.getRoomId() == id){
				return room;
			}
		}
		return null;
	}
}

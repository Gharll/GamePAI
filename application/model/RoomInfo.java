package application.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomInfo {
	
	private StringProperty roomNumber;
	private StringProperty user1Name;
	private StringProperty user2Name;
	private StringProperty available;
	
	public RoomInfo(String [] args){
		setRoomNumber(Integer.parseInt(args[0]));
		setUser1Name(args[1]);
		setUser2Name(args[2]);
		setAvailable(Boolean.parseBoolean(args[3]));
	}
	
	public RoomInfo(ArrayList <String> roomInfoList){
		setRoomNumber(Integer.parseInt(roomInfoList.get(0)));
		setUser1Name(roomInfoList.get(1));
		setUser2Name(roomInfoList.get(2));
		setAvailable(Boolean.parseBoolean(roomInfoList.get(3)));
	}
	
	public String getRoomNumber(){
		return roomNumber.get();
	}
	
	public void setRoomNumber(int roomNumber){
		this.roomNumber = new SimpleStringProperty("" + roomNumber);
	}
	
    public StringProperty getRoomNumberProperty() {
        return roomNumber;
    }
    
	public String getUser1Name(){
		return user1Name.get();
	}
	
	public void setUser1Name(String user1Name){
		this.user1Name = new SimpleStringProperty(user1Name);
	}
	
    public StringProperty getUser1NameProperty() {
        return user1Name;
    }
    
	public String getUser2Name(){
		return user2Name.get();
	}
	
	public void setUser2Name(String user2Name){
		this.user2Name = new SimpleStringProperty(user2Name);
	}
	
    public StringProperty getUser2NameProperty() {
        return user2Name;
    }
    
	public String getAvailable(){
		return available.get();
	}
	
	public void setAvailable(Boolean available){
		this.available = new SimpleStringProperty(available.toString());
	}
	
    public StringProperty getAvailableProperty() {
        return available;
    }
	
}

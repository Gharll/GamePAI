package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import application.model.User;
import application.view.ClientCommandController;
import gameEngine.Position;

public class Client extends Thread {
	
	private int portNumber;
	private Socket socket;
	private ObjectInputStream serverReader;
	private ObjectOutputStream writer;
	
	private User user;
	
	private ClientCommandController commandController;
	
	public Client(int portNumber, ClientCommandController commandController) {
		this.portNumber = portNumber;
		this.commandController = commandController;
	}
	
	public User getUser(){
		return user;
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket("127.0.0.1", portNumber);
			socketStreamsInit();
			listeningEvent();
			userIdRequest();
			refreshRoomInfoTableRequest();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void socketStreamsInit() throws IOException {
		serverReader = new ObjectInputStream(socket.getInputStream());
		writer = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void userIdRequest(){
		CommandMessage command = new CommandMessage("get user id");
		writeCommand(command);
	}
	
	public void newRoomRequest(){
		CommandMessage command = new CommandMessage("create new room");
		writeCommand(command);
	}
	
	public void refreshRoomInfoTableRequest(){
		CommandMessage command = new CommandMessage("get room info table");
		writeCommand(command);
	}
	
	public void joinRoomRequest(int roomId){
		CommandMessage command = new CommandMessage("join room");
		command.setArgumentObjects(roomId);
		writeCommand(command);
	}
	
	public void nextMove(Position position){
    	CommandMessage command = new CommandMessage("next move");
    	command.setArgumentObjects(position);
    	writeCommand(command);
	}
	
	public void disconnectRequest(){
		CommandMessage command = new CommandMessage("disconnect");
		writeCommand(command);
	}
	
	public void writeCommand(CommandMessage command){
		if(writer != null){
			try {
				writer.writeObject(command);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void listeningEvent() throws IOException, ClassNotFoundException {
	      new Thread(){
	          public void run(){
	        	while(true){
	        		try {
						CommandMessage message = (CommandMessage) serverReader.readObject();
						commandController.execute(message);
					} catch (Exception e) {
						break;
					}
				}
	  			
	          }
	        }.start();  
	}

}
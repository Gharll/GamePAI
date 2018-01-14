package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
	private Socket client;
	private String screenName = "Server";
	
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	
	private ServerCommandController commandController = new ServerCommandController(this);

	ClientHandler(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			socketStreamsInit();
			readExecuteAndRespondingForEver();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				;
			}
		}
	}

	private void socketStreamsInit() throws IOException {
		writer = new ObjectOutputStream(client.getOutputStream());
		reader = new ObjectInputStream(client.getInputStream());
		
	}
	
	private void readExecuteAndRespondingForEver() throws IOException, ClassNotFoundException{
		while(true){
			CommandMessage command = (CommandMessage) reader.readObject();
			commandController.execute(command);
		}
	}
	
	public void respone(CommandMessage commandMessage) throws IOException {
		writer.writeObject(commandMessage);
	}
	
	protected ObjectOutputStream getWriter(){
		return writer;
	}
	
	protected ObjectInputStream getReader(){
		return reader;
	}
	
	public ServerCommandController getServerCommandController(){
		return commandController; 
	}
}

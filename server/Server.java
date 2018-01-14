package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server extends Thread {
	private static final int PORT_NUMBER = 6145;
	private ServerSocket serverSocket;
	private Socket clientSocket;	
	private static LinkedList <ClientHandler> handlers = new LinkedList <>(); 

	public Server(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				System.out.println("user connected");
				ClientHandler handler = new ClientHandler(clientSocket);
				handlers.add(handler);
				handler.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendBroadcastMessage(CommandMessage message){
		for(ClientHandler handler : handlers){
			try {
				handler.respone(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getPortNumber() {
		return PORT_NUMBER;
	}


	public static void main(String[] args) {

		Server server = new Server(Server.getPortNumber());
		server.start();
		System.out.println("Server started");
	}
}
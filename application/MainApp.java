package application;

import java.io.IOException;

import application.model.RoomInfo;
import application.view.ClientCommandController;
import application.view.GameRoomLayoutController;
import application.view.RoomMenuLayoutController;
import application.view.RootLayoutController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.Client;
import server.Server;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane roomMenuLayout;
    private BorderPane gameRoomLayout;
    
  
    
    private RoomMenuLayoutController roomMenuLayoutController;
    private GameRoomLayoutController gameRoomLayoutController;
    private RootLayoutController rootLayoutController;
    private ClientCommandController commandController;
    private Client client;
    
    
    public Client getClient(){
    	return client;
    }
    
    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Go game");
        
        initRootLayout();
        initRoomMenuLayout();
        initGameLayout();
        initClient();
        
        primaryStage.setOnCloseRequest(e -> onClose());
    }

    private void onClose(){
    	client.disconnectRequest();
		Platform.exit();
    }
    
    public void initClient(){
    	commandController = new ClientCommandController(this);
    	this.client = new Client(Server.getPortNumber(), commandController);
        client.start();
    }
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
         
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initRoomMenuLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RoomMenuLayout.fxml"));
            roomMenuLayout = (BorderPane) loader.load();
            
            
			roomMenuLayoutController = loader.getController();
			roomMenuLayoutController.setMainApp(this);
			
			rootLayout.setCenter(roomMenuLayout);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initGameLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GameRoomLayout.fxml"));
            gameRoomLayout = (BorderPane) loader.load();
            
            
			gameRoomLayoutController = loader.getController();
			gameRoomLayoutController.setMainApp(this);
			
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void changeToGameLayout(){
    	rootLayout.setCenter(gameRoomLayout);
    }
    
    public void changeToRoomLayout(){
    	rootLayout.setCenter(roomMenuLayout);
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public RoomMenuLayoutController getRoomMenuController(){
    	return roomMenuLayoutController;
    }
    
    public GameRoomLayoutController getGameLayoutController(){
    	return gameRoomLayoutController;
    }
    
    public RootLayoutController getRootLayoutController(){
    	return rootLayoutController;
    }
   
    public MainApp() {
    	
    }
    

    public static void main(String[] args) {
    	launch(args);
    }
}
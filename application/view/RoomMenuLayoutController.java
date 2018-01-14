package application.view;

import java.io.IOException;
import java.util.ArrayList;

import application.MainApp;
import application.model.RoomInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class RoomMenuLayoutController {
   
	@FXML
    private TableView<RoomInfo> roomInfoTable;
	@FXML
	private TableColumn<RoomInfo, String> roomNumber; 
	@FXML
    private TableColumn<RoomInfo, String> user1Name;
    @FXML
    private TableColumn<RoomInfo, String> user2Name;
    @FXML
    private TableColumn<RoomInfo, String> available;
    @FXML
    private Button addNewRoomButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button joinButton;
    @FXML
    private TextField joinRoomField;
    
    private int roomID = 0;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	private ObservableList<RoomInfo> roomInfoData = FXCollections.observableArrayList();

	public RoomMenuLayoutController() {
	}
	
	@FXML
	private void initialize() {		
		roomNumber.setCellValueFactory(cellData -> cellData.getValue().getRoomNumberProperty());
		user1Name.setCellValueFactory(cellData -> cellData.getValue().getUser1NameProperty());
		user2Name.setCellValueFactory(cellData -> cellData.getValue().getUser2NameProperty());
		available.setCellValueFactory(cellData -> cellData.getValue().getAvailableProperty());
	}

	
	@FXML
	private void addNewRoomRequest() throws ClassNotFoundException, IOException {
		mainApp.getClient().newRoomRequest();
	}
	
	public void addNewRoom(ArrayList<String> roomInfoList){
		RoomInfo roomInfo = new RoomInfo(roomInfoList);
		roomInfoData.add(roomInfo);
	    mainApp.getGameLayoutController().setRoomIdLabel("" + roomInfo.getRoomNumber());
	    mainApp.changeToGameLayout();
	}
	
	@FXML
	private void refreshRoomInfoTableRequest() throws ClassNotFoundException, IOException{
		mainApp.getClient().refreshRoomInfoTableRequest();
	}
	
	public void refreshRoomInfoTable(ArrayList <ArrayList<String>> roomInfoList) throws IOException{
		roomInfoData.clear();
		for(ArrayList<String> room : roomInfoList){
			RoomInfo roomInfo = new RoomInfo(room);
			roomInfoData.add(roomInfo);
		}
	}
	
	@FXML
	private void joinRoomRequest() throws ClassNotFoundException, IOException{
		try{
			int roomId = Integer.parseInt(joinRoomField.getText());
			mainApp.getClient().joinRoomRequest(roomId);
		}catch (NumberFormatException e){
			//obs³uga b³êdu
		}
	} 
	
	public void joinRoom(){
		mainApp.changeToGameLayout();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		roomInfoTable.setItems(roomInfoData);
		
	}

}
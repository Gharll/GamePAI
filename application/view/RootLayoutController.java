package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class RootLayoutController {
	
	@FXML
	private Label errorLabel;
	@FXML
	private Label errorInfoLabel;
	@FXML
	private Label userIdLabel;
	
	// Reference to the main application.
	private MainApp mainApp;

	public RootLayoutController() {

	}
	
	@FXML
	private void initialize() {		
		 
	}

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setErrorInfo(String errorInfo){
		errorInfoLabel.setText(errorInfo);
		setErrorVisible(true);
		
	}
	
	public void setErrorVisible(boolean value){
		errorLabel.setVisible(value);
		errorInfoLabel.setVisible(value);
	}
	
	public void setUserIdLabel(int value){
		userIdLabel.setText(String.valueOf(value));
	}
	
}
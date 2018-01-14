package application.model;

import gameEngine.Position;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Field {
	
	private Position position;
	private StackPane stackPane = new StackPane();
	
	public Field(Position position){
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setSprite(Circle circle) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(circle);
	}
	
	public void setSprite(Text text) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(text);
	}
	
	public StackPane getStackPane(){
		return stackPane;
	}
	
	public void setStackPane(StackPane stackPane){
		this.stackPane = stackPane;
	}
	
}

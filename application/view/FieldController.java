package application.view;


import java.io.IOException;

import application.model.Field;
import gameEngine.Player;
import gameEngine.Position;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import server.Client;
import server.CommandMessage;

public class FieldController {
	
	private Field field;
	private GameRoomLayoutController gameRoom;
	public FieldController(Position position, GameRoomLayoutController gameRoom){
		this.field = new Field(position);
		field.getStackPane().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	try {
		    		gameRoom.nextMoveRequest(position);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
	public Field getField(){
		return field;
	}
	
}

package application.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public interface Sprite {
	
	public static int radius = 8;
	public static Circle getPlayerOne() {
		Circle sprite = new Circle();
		sprite.setRadius(radius);
		sprite.setFill(Color.WHITE);
		sprite.setStrokeWidth(1);
		sprite.setStroke(Color.BLACK);

		return sprite;
	}
	
	public static Circle getPlayerTwo() {
		Circle sprite = new Circle();
		sprite.setRadius(radius);
		sprite.setFill(Color.BLACK);
		sprite.setStrokeWidth(1);
		sprite.setStroke(Color.BLACK);

		return sprite;
	}
	
	public static Text getObstacle(){
		Text sprite = new Text("x");
		sprite.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
		return sprite;
	}
	
}

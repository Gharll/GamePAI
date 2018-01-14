package gameEngine;

public class PlayerOne implements Player {

	Sprite sprite = Sprite.getPlayerOneSprite();
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public String getPlayerString() {
		return "PlayerOne";
	}
	
}

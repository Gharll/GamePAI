package gameEngine;

public class PlayerTwo implements Player {

	Sprite sprite = Sprite.getPlayerTwoSprite();
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public String getPlayerString() {
		return "PlayerTwo";
	}

}

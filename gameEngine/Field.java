package gameEngine;

public class Field {
	private Position position;
	private Sprite sprite;
	
	public Field(Position position){
		this.position = position;
		sprite = Sprite.getEmptyFieldSprite();
	}
	
	public void setPosition(Position position){
		this.position = position;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public static boolean compareField(Field first, Field second){
		if(Sprite.compareSprite(first.getSprite(), second.getSprite())){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		Field field = new Field(new Position(3,4));
		System.out.println(field.getPosition().getX());
		System.out.println(field.getPosition().getY());
	}
}

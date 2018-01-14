package gameEngine;

import java.util.ArrayList;

public class Row {
	
	private int y;
	private ArrayList<Field> fields;
	
	public Row(int size, int y){
		fields = new ArrayList<>(size);
		fieldsInit(size, y);
	}
	
	private void fieldsInit(int size, int y){
		this.y = y;
		for(int i = 0; i < size; i++){
			fields.add(new Field(new Position(i, y)));
		}
	}
	
	public int getY(){
		return y;
	}
	
	public Field getField(int x){
		return fields.get(x);
	}
	
	public ArrayList<Field> getFields(){
		return fields;
	}
	
	public static void main(String[] args){
		Row row = new Row(5, 2);
		for(Field field : row.getFields()){
			System.out.print(field.getPosition().getX() + ", ");
			System.out.print(field.getPosition().getY());
			System.out.println();
		}
	}
}

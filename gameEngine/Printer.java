package gameEngine;

public class Printer {
	private Board board;
	public Printer(Board board){
		this.board = board;
	}
	
	public void printBoard(){
		System.out.println();
		for(Row row : board.getRows()){
			for(Field field : row.getFields()){
				System.out.print(" " + field.getSprite().getChar() + " ");
			}
			System.out.println();
		}
	}
}

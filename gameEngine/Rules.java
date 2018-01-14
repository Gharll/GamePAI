package gameEngine;

import java.util.ArrayList;

public class Rules {
	
	private int boardSize;
	private int matchesToWin;
	private int obstaclesQuantity;
	private boolean isWin = false;
	private Game game;
	private Board board;
	private int matched;
	
	public Rules(Game game){
		Config config = Config.getInstance();
		this.boardSize = config.getBoardSize();
		this.matchesToWin = config.getBoardSize();
		this.obstaclesQuantity = config.getObstaclesQuantity();
		this.game = game;
	}
	
	public void setBoardSize(int size){
		this.boardSize = size;
	}
	
	public int getBoardSize(){
		return boardSize;
	}

	public int getMatchsToWin() {
		return matchesToWin;
	}
	
	public void setObstaclesQuantity(int value){
		obstaclesQuantity = value;
	}
	
	public int getObstaclesQuantity(){
		return obstaclesQuantity;
	}

	public void setMatchsToWin(int matchesToWin) {
		this.matchesToWin = matchesToWin;
	}
	
	public boolean isWin(){
		return isWin;
	}
	
	public boolean isCorrectMove(Player player, Position position){
		if(!isWin && game.getCurrentTurnPlayer() == player){
			Sprite currentSpriteInField = game.getBoard().getField(position).getSprite();
			if(Sprite.compareSprite(currentSpriteInField, Sprite.getEmptyFieldSprite())){
				return true;
			}
		} 
		return false;
	}
	
	public void checkWin(Player player, Position position){
		if(checkWinHorizontal(player, position) 
				|| checkWinVertical(player, position) 
				|| checkWinRightDiagonally(player, position)
				|| checkWinLeftDiagonally(player, position)){
			isWin = true;
		} else {
			isWin = false;
		}
	}
	
	private boolean checkWinHorizontal(Player player, Position position){
		Row searchingRow = game.getBoard().getRow(position.getY());
		return checkWinInFields(searchingRow.getFields(), player);
	}
	
	private boolean checkWinVertical(Player player, Position position){
		ArrayList<Field> searchingColumn = game.getBoard().getColumn(position.getX());
		return checkWinInFields(searchingColumn, player);
	}
	
	private boolean checkWinRightDiagonally(Player player, Position position){
		ArrayList<Field> searchingRightDiagonally = game.getBoard().getRightDiagonally(position);
		return checkWinInFields(searchingRightDiagonally, player);
	}
	
	private boolean checkWinLeftDiagonally(Player player, Position position){
		ArrayList<Field> searchingLeftDiagonally = game.getBoard().getLeftDiagonally(position);
		return checkWinInFields(searchingLeftDiagonally, player);
	}
	
	private boolean checkWinInFields(ArrayList<Field> fields, Player player){
		matched = 0;
		for(Field field : fields){
			checkMatch(field.getSprite(), player.getSprite());
			if(matched >= matchesToWin){
				return true;
			}
		}
		return false;
	}
	
	private void checkMatch(Sprite first, Sprite second){
		if(Sprite.compareSprite(first, second)){
			matched++;
		} else {
			matched = 0;
		}
	}
	
}

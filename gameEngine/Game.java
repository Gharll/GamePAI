package gameEngine;

public class Game {
	private Board board;
	private Printer printer;
	private Player currentTurnPlayer;
	private PlayerOne playerOne = new PlayerOne();
	private PlayerTwo playerTwo = new PlayerTwo();
	private Player playerWon;
	private String gameInfo;
	private Rules rules = new Rules(this);
	private GameStatus gameStatus = GameStatus.getStatusFreez();
	private int roundNumber = 0;
	
	public Game(){
		currentTurnPlayer = playerOne;
	}
		
	public Board getBoard(){
		return board;
	}
	
	public Printer getPrinter(){
		return printer;
	}
	
	public void gameInit(){
		board = new Board(rules.getBoardSize(), rules.getObstaclesQuantity());
		printer = new Printer(board);
	}
	
	public boolean nextMove(Player player, Position position){
		if(rules.isCorrectMove(player, position) && isGameActive(gameStatus)){
			board.setSprite(position, player.getSprite());
			printer.printBoard();
			rules.checkWin(player, position);
			if(rules.isWin()){
				gameStatus = GameStatus.getStatusWin();
				playerWon = player;
			}
			switchToNextPlayer();
			roundNumber++;
			return true;
		} 
		return false;
	}
	
	private boolean isGameActive(GameStatus gameStatus){
		return gameStatus == GameStatus.getStatusActive();
	}
	private void switchToNextPlayer(){
		if(currentTurnPlayer == playerOne){
			currentTurnPlayer = playerTwo;
		} else {
			currentTurnPlayer = playerOne;
		}
	}
	
	public PlayerOne getPlayerOne(){
		return playerOne;
	}
	
	public PlayerTwo getPlayerTwo(){
		return playerTwo;
	}
	
	public Player getCurrentTurnPlayer(){
		return currentTurnPlayer;
	}
		
	public int getCurrentTurnPlayerNumber(){
		if(currentTurnPlayer == playerOne){
			return 1;
		} else if(currentTurnPlayer == playerTwo){
			return 2;
		}
		return -1;
	}
	
	public void setRules(Rules rules){
		this.rules = rules;
	}
	public Rules getRules(){
		return rules;
	}
	
	public GameStatus getGameStatus(){
		return gameStatus;
	}
	
	public void startGame(){
		this.gameStatus = GameStatus.getStatusActive();
	}
	
	public void stopGame(){
		this.gameStatus = GameStatus.getStatusFreez();
	}
	
	public int getRoundNumber(){
		return roundNumber;
	}
	
	public Player getPlayerWon(){
		return playerWon;
	}
	

}

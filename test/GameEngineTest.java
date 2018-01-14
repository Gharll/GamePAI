package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gameEngine.Board;
import gameEngine.Game;
import gameEngine.Player;
import gameEngine.Position;
import gameEngine.Rules;
import gameEngine.Sprite;

public class GameEngineTest {

	private Game game;
	private Board board;
	private Rules rules;
	
	@Before
	public void setup(){
		game = new Game();
		rules = game.getRules();
		rules.setBoardSize(12);
		rules.setMatchsToWin(4);
		rules.setObstaclesQuantity(0);
		game.gameInit();
		board = game.getBoard();
		game.startGame();
	}
	
	@Test
	public void playerSettingSpriteOnBoard(){
		setup();
		Position position = new Position(0,0);
		Player player = game.getCurrentTurnPlayer();
		game.nextMove(player, position);
		Sprite playerSprite = player.getSprite();
		Sprite spriteOnTheField = board.getField(position).getSprite(); 
		assertTrue(Sprite.compareSprite(playerSprite, spriteOnTheField));
	}
	
	@Test
	public void playerOneWinVertical() {
		Position winPosition = new Position(5,3);
		Position otherPlayerPosition = new Position(6,4);
		Player winPlayer = game.getCurrentTurnPlayer();
		Player otherPlayer = getCurrentPlayerWaiting();
		for(int i = 0; i < rules.getMatchsToWin(); i++){
			game.nextMove(winPlayer, winPosition);
			winPosition.iterateVertical();
			game.nextMove(otherPlayer, otherPlayerPosition);
			otherPlayerPosition.iterateVertical();
		}
		
		assertTrue(game.getRules().isWin());
		assertTrue(winPlayer == game.getPlayerWon());
	}
	
	@Test
	public void playerOneWinHorizontal() {
		Position winPosition = new Position(5,3);
		Position otherPlayerPosition = new Position(6,4);
		Player winPlayer = game.getCurrentTurnPlayer();
		Player otherPlayer = getCurrentPlayerWaiting();
		for(int i = 0; i < rules.getMatchsToWin(); i++){
			game.nextMove(winPlayer, winPosition);
			winPosition.iterateHorizontal();
			game.nextMove(otherPlayer, otherPlayerPosition);
			otherPlayerPosition.iterateHorizontal();
		}
		
		assertTrue(game.getRules().isWin());
		assertTrue(winPlayer == game.getPlayerWon());
	}
	
	@Test
	public void playerOneWinRightDiagonally() {
		Position winPosition = new Position(5,3);
		Position otherPlayerPosition = new Position(6,3);
		Player winPlayer = game.getCurrentTurnPlayer();
		Player otherPlayer = getCurrentPlayerWaiting();
		for(int i = 0; i < rules.getMatchsToWin(); i++){
			game.nextMove(winPlayer, winPosition);
			winPosition.iterateRightDiagonally();
			game.nextMove(otherPlayer, otherPlayerPosition);
			otherPlayerPosition.iterateRightDiagonally();
		}
		
		assertTrue(game.getRules().isWin());
		assertTrue(winPlayer == game.getPlayerWon());
	}
	
	@Test
	public void playerOneWinLeftDiagonally() {
		Position winPosition = new Position(5,3);
		Position otherPlayerPosition = new Position(6,3);
		Player winPlayer = game.getCurrentTurnPlayer();
		Player otherPlayer = getCurrentPlayerWaiting();
		for(int i = 0; i < rules.getMatchsToWin(); i++){
			game.nextMove(winPlayer, winPosition);
			winPosition.iterateLeftDiagonally();
			game.nextMove(otherPlayer, otherPlayerPosition);
			otherPlayerPosition.iterateLeftDiagonally();
		}
		
		assertTrue(game.getRules().isWin());
		assertTrue(winPlayer == game.getPlayerWon());
	}
	
	private Player getCurrentPlayerWaiting(){
		if(game.getCurrentTurnPlayer() == game.getPlayerOne()){
			return game.getPlayerTwo();
		} 
		else if(game.getCurrentTurnPlayer() == game.getPlayerTwo()){
			return game.getPlayerTwo();
		}
		return null;
	}

}

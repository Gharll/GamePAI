package gameEngine;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		game.gameInit();
		game.getPrinter().printBoard();
	}

}

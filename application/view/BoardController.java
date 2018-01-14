package application.view;

import application.model.Board;
import gameEngine.Position;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class BoardController {

	private Board board;
	private GameRoomLayoutController gameRoom;

	public BoardController(Board board, GameRoomLayoutController gameRoom) {
		this.board = board;
		this.gameRoom = gameRoom;
		
		initGridBoardPane();
		createFieldsController();
	}

	public void initGridBoardPane(){
		for(int i = 0; i < board.getBoardSize(); i++){
			addRow();
		}
		
		for(int j = 0; j < board.getBoardSize(); j++){
			addColumn();
		}
		
		board.getGridBoardPane().setGridLinesVisible(true);
		fill();
	}
	
	private void fill(){	
		for(int j = 0; j < board.getBoardSize(); j++){
			for(int i = 0; i < board.getBoardSize(); i++){
				FieldController fieldController = new FieldController(new Position(i, j), gameRoom);
				board.getFields().add(fieldController);
			}
		}
	}
	
	private void addColumn() {
		ColumnConstraints column = new ColumnConstraints();
		column.setMinWidth(board.CELL_MIN_SIZE);
		column.setPrefWidth(board.CELL_PREF_SIZE);
		column.setPrefWidth(board.CELL_MAX_SIZE);
		board.getGridBoardPane().getColumnConstraints().add(column);
	}

	private void addRow() {
		RowConstraints row = new RowConstraints();
		row.setMinHeight(board.CELL_MIN_SIZE);
		row.setPrefHeight(board.CELL_PREF_SIZE);
		row.setPrefHeight(board.CELL_MAX_SIZE);
		board.getGridBoardPane().getRowConstraints().add(row);
	}

	private void createFieldsController() {
		for (int j = 0; j < board.getBoardSize(); j++) {
			for (int i = 0; i < board.getBoardSize(); i++) {
				Position position = new Position(i, j);
				board.getGridBoardPane().add(board.getFieldController(position).getField().getStackPane(), i, j);
			}
		}
	}

	public void clearGridBoard() {
		int boardSize = board.getBoardSize();
		for (int j = 0; j < boardSize; j++) {
			for (int i = 0; i < boardSize; i++) {
				Position position = new Position(i, j);
				board.getFieldController(position).getField().getStackPane().getChildren().clear();
			}
		}
	}
	
	public Board getBoard(){
		return board;
	}

}

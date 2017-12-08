package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 3x3 playing field for TicTacToe
 * @author ikoethke
 *
 */
public class Board {
	private int[][] board;

	public Board() {
		board = new int[3][3];
	}
	
	public int[][] getBoard() {
		return board;
	}

	private boolean isXTurn = true;

	/**
	 * Returns a list of all free fields on the board.
	 * The fields are described by 2 numbers one for the column one for the row.
	 * The numbers are modified to represent user input, meaning the first field is 1,1 not 0,0!!!!
	 *
	 * @return
	 */
	public List<int[]> getFreeFieldsMod(){
		List<int[]> freeFields = new ArrayList<>();

		//Used to output free fields via there numeric description within the board array
		int columnNumber = 1;
		for (int columns[]: board) {

			//see columnNumber
			int fieldNumber = 1;
			for (int field: columns) {
				if (field == 0) {
					freeFields.add(new int[]{columnNumber, fieldNumber});
				}
				fieldNumber++;
			}
			columnNumber++;
		}

		return freeFields;
	}

	/**
	 * Marks the field on the board either with X or O depending on the turn.
	 * First starts with X, second O, third X ...
	 * @param selectedField
	 */
	public void markField(int[] selectedField) {
		//one has to be subtrackted in order to match the user input with the array since it startes with 0,0
		int columnNumber = (selectedField[0] - 1);
		int rowNumber = (selectedField[1]- 1);

		if(isXTurn) {
			board[columnNumber][rowNumber] = 1;
			isXTurn = false;
		} else {
			board[columnNumber][rowNumber] = 2;
			isXTurn = true;
		}
	}
	
}

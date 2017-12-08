package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

/**
 * This class represents a simple TicTacToe game. One player can challenge the pc to a one on one game.
 * @author ikoethke
 *
 */
public class TicTacToe {
	private Board board;
	private boolean isPCTurn;

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
	
	public TicTacToe() {
		board = new Board();
		isPCTurn = false;
		printWelcomeMessage();
	}
	
	private void printWelcomeMessage() {
		System.out.println("Willkommen bei TicTacToe.");
		System.out.println("Created by Iven Koethke");
	}
	
	private boolean doesPlayerStart() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public void play() {
		System.out.println("Game started!");
		printBoard();
		
		isPCTurn = !doesPlayerStart();

		boolean gameActive = true;
		while(gameActive) {
			if(isPCTurn) {
				doPCTurn();
				isPCTurn = false;
			}
			else {
				doPlayerTurn();
				isPCTurn = true;
			}
			
			printBoard();
			if(checkForWin()) gameActive = false;
		}
	}
	

	private void doPlayerTurn() {
		System.out.println("Du bist dran! Wähle ein Feld");

		boolean needValidUserInput = true;
		while(needValidUserInput) {
			 int[] userInput = getUserInput();
			 List<int[]> freeFields = board.getFreeFieldsMod();
			 if(detectMatches(userInput, freeFields)) {
				board.markField(userInput);
				needValidUserInput = false;
			 }
		}

		//TODO Check for 0 in userInput


	}

	private boolean detectMatches(int[] playerInput, List<int[]> freeFields) {
		for (int[] field: freeFields) {
			if(field[0] == playerInput[0]) {
				if(field[1] == playerInput[1])
					return true;
			}
		}
		return false;
	}
	
	private void printBoard() {
		System.out.println("    " + "A " + "  B  " + " C");
		int rowNumber = 1;

		for(int[] row: board.getBoard()) {
			String rowInfo = "";
			rowInfo += rowNumber + "   ";
			
			//Create fields
			for(int i = 0; i < row.length; i++) {
				//Detect the last iteration
				if(!(i == (row.length-1))) {
					rowInfo += row[i] + " | ";
				} else {
					rowInfo += row[i];
				}
			}
			System.out.println(rowInfo);
			rowNumber = rowNumber + 1;
		}
	}

	private int[] getUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter row letter");

        int columnNumber = 0;
        int rowNumber = 0;
        try {
			String selecetedColumnRaw = br.readLine();
			columnNumber = parseLetterToInt(selecetedColumnRaw);


		} catch (IOException e) {
			System.err.println("String input could not be read !");
		}
        System.out.print("Enter column number:");
        try{
            rowNumber = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
			System.err.println("Integer input could not be read !");

		}

		return new int[] {columnNumber, rowNumber};
	}

	private int parseLetterToInt(String selecetedColumnRaw) {
		switch (selecetedColumnRaw){
			case "A":
				return 1;
			case "B":
				return 2;
			case "C":
				return 3;
			default:
				System.err.println("Non valid user input cannot be parsed!");
				return 0;
		}
	}

	private void doPCTurn() {
		System.out.println("PC is making his move.");

		List<int[]> freeFields = board.getFreeFieldsMod();
		int amountOfFields = freeFields.size();

		Random random = new Random();
		//the computer chooses a available field by random
		int selectedField = random.nextInt(amountOfFields);
		board.markField(freeFields.get(selectedField));
	}
	

	private boolean checkForWin() {
		// TODO Auto-generated method stub
		
		return false;
		
	}

}

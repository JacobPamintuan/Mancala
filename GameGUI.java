/*
 * GameGUI.java
 * Stones Project
 * ICS3U1
 * Jacob Pamintuan
 * Dec. 29, 2019
 */

package stonesGUI; 

/**
 * The GameGUI class contains the background code for the stones application.
 * It holds the array for the game board as well as controls the movement of each piece. 
 * Methods within this class are instantiated within the StonesGUI class as the validate and game object. 
 */

public class GameGUI {

	private int[][] gameBoard = new int[2][6];
	private int homeBin1, homeBin2;
	private boolean additionalTurn = false;
	private boolean capture = false;
	private int rowEmpty;

	/**
	 * constructor
	 * pre: none
	 * post: Validation object created within StonesGUI class
	 */
	
	public GameGUI() {

	}
	
	/**
	 * constructor
	 * pre: none
	 * post: gameBoard array created with starting pit values and a game object is created
	 */

	public GameGUI(int num) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				gameBoard[i][j] = num;
			}
		}
	}
	
	/**
	 * Validates the number of starting stones inputed by the user.
	 * pre: none
	 * post: returns false if a valid number is entered and initializes both home bins to 0, returns true otherwise. 
	 */

	public boolean validateStart(int num) {
		if (num >= 2 && num <= 5) {
			homeBin1 = 0;
			homeBin2 = 0;
			return false;
		}
		return true;

	}
	
	/**
	 * Moves the stones around the board. 
	 * pre: the num parameter must be valid (not 0), and it must be a player's turn
	 * post: the stones in the pit selected are moved around the board and have been added to home bins if applicable.
	 * The method also determines if the player has an extra move or if stones from the opponent have been captured.
	 * The method calls the getState method to determine whether or not there is a capture, and
	 * sets additional turn to true or false as determined. 
	 */

	public void move(int turn, int num) {
		capture = false;
		int row = turn - 1;
		int column = num - 1;
		boolean empty = false;
		boolean homeBin = false;

		int numStones = gameBoard[row][column];
		gameBoard[row][column] = 0;

		for (int i = numStones; i > 0; i--) {
			if (row == 0) {
				if (column > 0) {
					if (i == 1 && turn == 1)
						empty = getState(row, column - 1);
					add(row, (column - 1));
					column--;
				} else if (column == 0) {
					if (turn == 1) {
						addHomeBin(1,1);
						if (i == 1) {
							homeBin = true;
						}
					} else {
						i++;
					}
					row = 1;
					column = -1;
				}
			} else {
				if (column < 5) {
					if (i == 1 && turn == 2) {
						empty = getState(row, column + 1);
					}
					add(row, column + 1);
					column++;
				} else if (column == 5) {
					if (turn == 2) {
						addHomeBin(2,1);
						if (i == 1) {
							homeBin = true;
						}
					} else {
						i++;
					}
					row = 0;
					column = 6;
				}
			}

			if (empty) {
				capture(row, column, turn);
			}
			if (homeBin) {
				additionalTurn = true;
			} else {
				additionalTurn = false;
			}
		}

	}
	
	/**
	 *  Adds a stone to a specified pit.
	 *  pre: row and column are valid.
	 *  post: a stone is added to the pit specified.
	 */

	private void add(int row, int column) {
		gameBoard[row][column]++;
	}
	
	
	/**
	 * Increases a player's home bin	
	 * pre: A stone lands in the player's home bin or player has captured the opponent's stones. Also a valid home bin must be chosen.
	 * post: the specified player's home bin is increased by the specified amount
	 */
	private void addHomeBin(int bin, int amt) {
		if(bin == 1) {
			homeBin1+=amt;
		} else {
			homeBin2+=amt;
		}
	}
	
	/**
	 * returns the state of the pit specified
	 * pre: row and column are valid.
	 * post: returns true if the pit is empty, returns false otherwise.
	 */

	private boolean getState(int row, int column) {
		if (gameBoard[row][column] == 0)
			return true;
		return false;
	}
	
	/**
	 * Capture's the opponent's stones 
	 * pre: The player's last stone lands on an empty pit on their own side.
	 * post: The player's home bin is increased by the amount of stones on the corresponding opponent's pit.
	 */

	private void capture(int row, int column, int turn) {

		int captureRow, total;
		if (row == 0)
			captureRow = 1;
		else
			captureRow = 0;
		if (gameBoard[captureRow][column] != 0) {
			capture = true;
			total = gameBoard[captureRow][column];
			gameBoard[captureRow][column] = 0;

			addHomeBin(turn, total);
		}
	}
	
	/**
	 * Returns whether or not a capture occurred.
	 * pre: none
	 * post: The boolean value of capture is returned. 
	 */

	public boolean getCapture() {
		return capture;
	}
	
	/**
	 * Returns whether or not the player has an additional turn.
	 * pre: none 
	 * post: The boolean value of additionalTurn is returned.
	 */

	public boolean determineTurn() {
		return additionalTurn;
	}
	
	/**
	 * Determines if either row is empty
	 * pre: none
	 * post: Returns true if a row is empty, returns false otherwise. 
	 */

	public boolean gameOver() {
		int numEmpty1 = 0, numEmpty2 = 0;

		for (int i = 0; i < 6; i++)
			if (gameBoard[0][i] == 0) {
				numEmpty1++;
			}
		for (int i = 0; i < 6; i++)
			if (gameBoard[1][i] == 0) {
				numEmpty2++;
			}
		if (numEmpty1 == 6) {
			rowEmpty = 1;
		} else if (numEmpty2 == 6) {
			rowEmpty = 2;
		}
		
		if (numEmpty1 == 6 || numEmpty2 == 6) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns which row is empty.
	 * pre: game must be over
	 * post: the empty row is returned.
	 */

	public int getEmptyRow() {
		return rowEmpty;
	}
	
	/**
	 * Increases a player's home bin by the remaining number of stones left on the board.
	 * pre: Game must be over (must be an empty row).
	 * post: The player who has the initial empty row's home bin is increased by the number of stones left in the opponents pits
	 */
	public void fullCapture(int player) {
		int loot = 0, rowCaptured;

		if (player == 1) {
			rowCaptured = 1;
		} else {
			rowCaptured = 0;
		}
		
		for (int i = 0; i < 6; i++) {
			loot += gameBoard[rowCaptured][i];
		}
		for (int i = 0; i < 6; i++) {
			gameBoard[rowCaptured][i] = 0;
		}
		
		if (player == 1) {
			addHomeBin(1,loot);
		} else {
			addHomeBin(2,loot);
		}
	}
	
	/**
	 * Returns the number of stones in the pit specified.
	 * pre: none
	 * post: Number of stones in the pit is returned.
	 */

	public int getValue(int row, int column) {
		return gameBoard[row][column];
	}
	
	/**
	 * Returns the number of stones in the specified player's home bin
	 * pre: none
	 * post: the number of stones is returned. 
	 */

	public int getBinValue(int player) {
		if (player == 1) {
			return homeBin1;
		} else {
			return homeBin2;
		}
		
	}
	
	/**
	 * Determines the winner of the game.
	 * pre: all pits empty.
	 * post: An appropriate message is returned based on the results. 
	 */

	public String determineWinner() {
		if (homeBin1 > homeBin2) {
			return ("Player 1 wins!");
		} else if (homeBin1 < homeBin2) {
			return ("Player 2 wins!");
		} else{
			return ("Its a draw!");
		}
	}

}

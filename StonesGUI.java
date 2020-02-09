/*
 * StonesGUI.java
 * Stones Project
 * ICS3U1
 * Jacob Pamintuan
 * Dec. 29 2019
 */

package stonesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The StonesGUI class is the client code for the stones application. GUI
 * components generated in this class.
 */

public class StonesGUI implements ActionListener {
	JFrame startFrame, gameFrame;
	JPanel startPane, gamePane;
	JLabel error;
	JLabel numStonesPrompt, homeBin1, homeBin2;
	JLabel label1, label2, label3, label4, messegeLabel, winnerLabel, gameOverLabel, label8, label9;// label10;
	JButton quit;
	JTextField numStonesText;
	JButton generate;
	JButton R1C1, R1C2, R1C3, R1C4, R1C5, R1C6;
	JButton R2C1, R2C2, R2C3, R2C4, R2C5, R2C6;
	boolean startV = true, gameV = false;
	boolean row1 = true, row2 = false;
	String numBin1 = "Player 1 home bin: 0", numBin2 = "Player 2 home bin: 0";
	static int numStartPits;
	int player, pit;
	static GameGUI game;

	public StonesGUI() {

		/* Create and set up the start frame */
		startFrame = new JFrame("Start");
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create the start content pane with a Grid Layout */
		startPane = new JPanel();
		startPane.setLayout(new GridLayout(2, 2, 10, 10));
		startPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/*
		 * Create and add a prompt for the starting number of stones then a text field
		 */
		numStonesPrompt = new JLabel("Enter the starting number of stones (Between 2 and 5): ");
		startPane.add(numStonesPrompt);
		numStonesText = new JTextField(10);
		startPane.add(numStonesText);

		/* Create and add a button that reveal the second pane */
		generate = new JButton("Start");
		generate.setActionCommand("Start");
		generate.addActionListener(this);
		startPane.add(generate);

		/*
		 * Create to indicate whether the user inputs an invalid number of starting
		 * stones
		 */
		error = new JLabel("");
		startPane.add(error);

		/* Add start content pane to the start frame */
		startFrame.setContentPane(startPane);

		/* Size and then display the start frame */
		startFrame.pack();
		startFrame.setVisible(startV);

		/* Create and set up the game frame */
		gameFrame = new JFrame("Stones");
		gameFrame.setSize(1400, 200);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create the game content pane with a Grid Layout */
		gamePane = new JPanel();
		gamePane.setLayout(new GridLayout(0, 8, 10, 10));
		gamePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/*
		 * Create an empty label and add the first row of pits, then home bins, then the
		 * second row of pits
		 */
		label1 = new JLabel();
		gamePane.add(label1);

		R1C1 = new JButton();
		R1C1.setActionCommand("R1C1");
		R1C1.addActionListener(this);
		gamePane.add(R1C1);

		R1C2 = new JButton();
		R1C2.setActionCommand("R1C2");
		R1C2.addActionListener(this);
		gamePane.add(R1C2);

		R1C3 = new JButton();
		R1C3.setActionCommand("R1C3");
		R1C3.addActionListener(this);
		gamePane.add(R1C3);

		R1C4 = new JButton();
		R1C4.setActionCommand("R1C4");
		R1C4.addActionListener(this);
		gamePane.add(R1C4);

		R1C5 = new JButton();
		R1C5.setActionCommand("R1C5");
		R1C5.addActionListener(this);
		gamePane.add(R1C5);

		R1C6 = new JButton();
		R1C6.setActionCommand("R1C6");
		R1C6.addActionListener(this);
		gamePane.add(R1C6);

		label2 = new JLabel();
		gamePane.add(label2);

		homeBin1 = new JLabel(numBin1);
		homeBin1.setAlignmentY(JLabel.RIGHT_ALIGNMENT);
		gamePane.add(homeBin1);

		label3 = new JLabel();
		label4 = new JLabel();
		messegeLabel = new JLabel();
		winnerLabel = new JLabel();
		gameOverLabel = new JLabel();
		label8 = new JLabel();
		gamePane.add(label3);
		gamePane.add(label4);
		gamePane.add(messegeLabel);
		gamePane.add(winnerLabel);
		gamePane.add(gameOverLabel);
		gamePane.add(label8);

		homeBin2 = new JLabel(numBin2);
		homeBin2.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		gamePane.add(homeBin2);

		label9 = new JLabel();
		gamePane.add(label9);

		R2C1 = new JButton();
		R2C1.setActionCommand("R2C1");
		R2C1.addActionListener(this);
		gamePane.add(R2C1);

		R2C2 = new JButton();
		R2C2.setActionCommand("R2C2");
		R2C2.addActionListener(this);
		gamePane.add(R2C2);

		R2C3 = new JButton();
		R2C3.setActionCommand("R2C3");
		R2C3.addActionListener(this);
		gamePane.add(R2C3);

		R2C4 = new JButton();
		R2C4.setActionCommand("R2C4");
		R2C4.addActionListener(this);
		gamePane.add(R2C4);

		R2C5 = new JButton();
		R2C5.setActionCommand("R2C5");
		R2C5.addActionListener(this);
		gamePane.add(R2C5);

		R2C6 = new JButton();
		R2C6.setActionCommand("R2C6");
		R2C6.addActionListener(this);
		gamePane.add(R2C6);

		/* Create and add a button to quit the application */
		quit = new JButton("Quit");
		quit.setActionCommand("Quit");
		quit.addActionListener(this);
		gamePane.add(quit);

		/* Add game content pane to the game frame */
		gameFrame.setContentPane(gamePane);

		/* Size and then display the game frame */
		gameFrame.setVisible(gameV);

	}

	/*
	 * Change message label pre: none post: change message label to text accepted
	 */
	private void changeLabel(String text) {
		messegeLabel.setText(text);
	}

	/*
	 * Display game over once game is over pre: none post: change the gameOverLabel
	 * to display "game over" when appropriate
	 */
	private void gameOverLabel() {
		gameOverLabel.setText("Game Over!!");
	}

	/*
	 * Display error message for starting number of stones pre: none post: display
	 * the error message when an invalid number of stones is entered
	 */
	private void errorLabel() {
		error.setText("Error: Please Enter a number between 2 and 5");
	}

	/*
	 * Display the winner once the game is over pre: none post: change the
	 * winnerLabel to display the winner
	 */
	private void winnerLabel(String text) {
		winnerLabel.setText(text);
	}

	/**
	 * Create and show the GUI.
	 */
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		StonesGUI app = new StonesGUI();
	}

	/*
	 * Handle button click action event pre: none post: start the game and move the
	 * stones.
	 */
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();

		if (eventName.equals("Start")) {

			GameGUI validate = new GameGUI();
			numStartPits = Integer.parseInt(numStonesText.getText());
			if (!validate.validateStart(numStartPits)) {

				game = new GameGUI(numStartPits);

				R1C1.setText(Integer.toString(game.getValue(0, 0)));
				R1C2.setText(Integer.toString(game.getValue(0, 1)));
				R1C3.setText(Integer.toString(game.getValue(0, 2)));
				R1C4.setText(Integer.toString(game.getValue(0, 3)));
				R1C5.setText(Integer.toString(game.getValue(0, 4)));
				R1C6.setText(Integer.toString(game.getValue(0, 5)));

				R2C1.setText(Integer.toString(game.getValue(1, 0)));
				R2C2.setText(Integer.toString(game.getValue(1, 1)));
				R2C3.setText(Integer.toString(game.getValue(1, 2)));
				R2C4.setText(Integer.toString(game.getValue(1, 3)));
				R2C5.setText(Integer.toString(game.getValue(1, 4)));
				R2C6.setText(Integer.toString(game.getValue(1, 5)));

				R2C1.setEnabled(row2);
				R2C2.setEnabled(row2);
				R2C3.setEnabled(row2);
				R2C4.setEnabled(row2);
				R2C5.setEnabled(row2);
				R2C6.setEnabled(row2);

				startV = false;
				gameV = true;
				startFrame.dispose();
				gameFrame.setVisible(gameV);

			} else {
				errorLabel();
			}
		} else if (eventName.equals("Quit")) {
			gameFrame.dispose();

		} else {

			changeLabel(" ");

			if (eventName.equals("R1C1")) {
				player = 1;
				pit = 1;
			} else if (eventName.equals("R1C2")) {
				player = 1;
				pit = 2;
			} else if (eventName.equals("R1C3")) {
				player = 1;
				pit = 3;
			} else if (eventName.equals("R1C4")) {
				player = 1;
				pit = 4;
			} else if (eventName.equals("R1C5")) {
				player = 1;
				pit = 5;
			} else if (eventName.equals("R1C6")) {
				player = 1;
				pit = 6;
			}

			else if (eventName.equals("R2C1")) {
				player = 2;
				pit = 1;
			} else if (eventName.equals("R2C2")) {
				player = 2;
				pit = 2;
			} else if (eventName.equals("R2C3")) {
				player = 2;
				pit = 3;
			} else if (eventName.equals("R2C4")) {
				player = 2;
				pit = 4;
			} else if (eventName.equals("R2C5")) {
				player = 2;
				pit = 5;
			} else if (eventName.equals("R2C6")) {
				player = 2;
				pit = 6;
			}

			game.move(player, pit);

			R1C1.setText(Integer.toString(game.getValue(0, 0)));
			R1C2.setText(Integer.toString(game.getValue(0, 1)));
			R1C3.setText(Integer.toString(game.getValue(0, 2)));
			R1C4.setText(Integer.toString(game.getValue(0, 3)));
			R1C5.setText(Integer.toString(game.getValue(0, 4)));
			R1C6.setText(Integer.toString(game.getValue(0, 5)));

			R2C1.setText(Integer.toString(game.getValue(1, 0)));
			R2C2.setText(Integer.toString(game.getValue(1, 1)));
			R2C3.setText(Integer.toString(game.getValue(1, 2)));
			R2C4.setText(Integer.toString(game.getValue(1, 3)));
			R2C5.setText(Integer.toString(game.getValue(1, 4)));
			R2C6.setText(Integer.toString(game.getValue(1, 5)));

			if (game.gameOver()) {
				game.fullCapture(game.getEmptyRow());
				winnerLabel(game.determineWinner());
				gameOverLabel();

				R1C1.setEnabled(false);
				R1C2.setEnabled(false);
				R1C3.setEnabled(false);
				R1C4.setEnabled(false);
				R1C5.setEnabled(false);
				R1C6.setEnabled(false);

				R2C1.setEnabled(false);
				R2C2.setEnabled(false);
				R2C3.setEnabled(false);
				R2C4.setEnabled(false);
				R2C5.setEnabled(false);
				R2C6.setEnabled(false);

				R1C1.setText("0");
				R1C2.setText("0");
				R1C3.setText("0");
				R1C4.setText("0");
				R1C5.setText("0");
				R1C6.setText("0");

				R2C1.setText("0");
				R2C2.setText("0");
				R2C3.setText("0");
				R2C4.setText("0");
				R2C5.setText("0");
				R2C6.setText("0");

			} else {

				if (!(game.determineTurn())) {
					if (row1 == false) {
						row1 = true;
					} else {
						row1 = false;
					}
					if (row2 == false) {
						row2 = true;
					} else {
						row2 = false;
					}

				} else {
					changeLabel("Free turn for Player " + player + "!!!!!");
				}

				R1C1.setEnabled(row1);
				R1C2.setEnabled(row1);
				R1C3.setEnabled(row1);
				R1C4.setEnabled(row1);
				R1C5.setEnabled(row1);
				R1C6.setEnabled(row1);

				R2C1.setEnabled(row2);
				R2C2.setEnabled(row2);
				R2C3.setEnabled(row2);
				R2C4.setEnabled(row2);
				R2C5.setEnabled(row2);
				R2C6.setEnabled(row2);

				if (game.getValue(0, 0) == 0) {
					R1C1.setEnabled(false);
				}
				if (game.getValue(0, 1) == 0) {
					R1C2.setEnabled(false);
				}
				if (game.getValue(0, 2) == 0) {
					R1C3.setEnabled(false);
				}
				if (game.getValue(0, 3) == 0) {
					R1C4.setEnabled(false);
				}
				if (game.getValue(0, 4) == 0) {
					R1C5.setEnabled(false);
				}
				if (game.getValue(0, 5) == 0) {
					R1C6.setEnabled(false);
				}

				if (game.getValue(1, 0) == 0) {
					R2C1.setEnabled(false);
				}
				if (game.getValue(1, 1) == 0) {
					R2C2.setEnabled(false);
				}
				if (game.getValue(1, 2) == 0) {
					R2C3.setEnabled(false);
				}
				if (game.getValue(1, 3) == 0) {
					R2C4.setEnabled(false);
				}
				if (game.getValue(1, 4) == 0) {
					R2C5.setEnabled(false);
				}
				if (game.getValue(1, 5) == 0) {
					R2C6.setEnabled(false);
				}

				numBin1 = Integer.toString(game.getBinValue(1));
				numBin2 = Integer.toString(game.getBinValue(2));

				homeBin1.setText("Player 1 home bin: " + numBin1);
				homeBin2.setText("Player 2 home bin: " + numBin2);

				if (game.getCapture()) {
					changeLabel("Capture");
				}
			}
		}
	}

	public static void main(String[] args) {
		/*
		 * Methods that create and show a GUI should be run from an event-dispatching
		 * thread
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
	}
}

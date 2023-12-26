/** 
 * Tamra Tawakol, Amanda Xu, Julia Gu
 * May 28, 2019
 * 
 * This class defines the window and screen changes for a color vision test.
 * 
 * v.1 - skeleton, startScreen & gridScreen (Tamra)
 * v.2 - changeScreen() (Tamra)
 * v.3 - howToScreen (Julia), resultsScreen (Amanda)
 * v.4 - timer (Tamra & Julia)
 * v.5 - pauseButton & pauseScreen (Amanda)
 * v.6 - choiceScreen (Tamra), untimedGame (Julia)
 * v.7 - high score, hint, debugging (Julia)
**/

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JFrame;

public class OculoChroma {

	// colors
	private Color red = new Color(200, 0, 0);
	private Color orange = new Color(200, 100, 0);
//	private Color yellow = new Color(200, 200, 0);
	private Color green = new Color(0, 60, 0);
	private Color blue = new Color(0, 0, 60);
	private Color purple = new Color(80, 0, 100);
	private Color gray = Color.GRAY;
	
	// high score
	private static int timedHighScore = 1;
	private static int untimedHighScore = 1;

	// grid information
	private int countPause = 0;
	private int currentLevel = 1;
	private double colorDifference = 100;
	private SquareGrid squareGrid;

	// timer
	private Timer timer;
	private int secondsLeft = 30;
	private boolean isTimed = true;

	// window
	private Container c;

	// screens
	private Screen startScreen;
	private Screen howToScreen;
	private Screen choiceScreen;
	private Screen gridScreen;
	private Screen pauseScreen;
	private Screen resultsScreen;
//	private Screen currentScreen;

	public static void main(String[] args) {

		OculoChroma test = new OculoChroma();
		
		Button[][] placeHolderButtons = new Button[1][1];
		placeHolderButtons[0][0] = new Button(test, "", null);
		String placeHolderText = " ";
		test.startScreen = new Screen("OculoChroma", "   The Color Vision Test", test.blue, placeHolderButtons);
		test.howToScreen = new Screen("How To", "Click on the square in each grid with a different hue than the others to continue onto the next grid.", test.green, placeHolderButtons);
		test.choiceScreen = new Screen("Choose game", "Choose to play a timed or untimed game", test.purple, placeHolderButtons);
		test.gridScreen = new Screen(" ", "", test.gray, placeHolderButtons);
		test.pauseScreen = new Screen("PAUSED", placeHolderText, test.orange, placeHolderButtons);
		test.resultsScreen = new Screen("Results", placeHolderText, test.red, placeHolderButtons);

		test.updateStartScreen();
		test.updateHowToScreen();
		test.updateChoiceScreen();
		test.updateGridScreen();
		test.updatePauseScreen();
		
		// timer
		test.timer = new Timer(1000, new ActionListener() {
			boolean showedHint = false;
			public void actionPerformed(ActionEvent e) {
				test.secondsLeft--;
				test.gridScreen.setTime(test.secondsLeft);
				if (test.secondsLeft < 0) 
					test.secondsLeft = 0;
				if (test.secondsLeft == 0 && showedHint == false) {
					test.secondsLeft = 0;
					test.squareGrid.getOddSquare().highlight(); 
					showedHint = true;
				}
				else if (showedHint == true) {
					showedHint = false;
					test.getResults();
				}
			}
		});

		// window
		JFrame window = new JFrame("OculoChroma");
		window.setBounds(100, 100, 800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		test.c = window.getContentPane();
//		test.currentScreen = test.startScreen;
		test.changeScreen(test.startScreen);
		window.setVisible(true);

	}

	// switches to specified screen
	public void changeScreen(Screen newScreen) {
		
		// timed?
		if (choiceScreen.getButtons()[0][0].getClicked())
			isTimed = true;
		else if (choiceScreen.getButtons()[0][1].getClicked())
			isTimed = false;

		// timer
		if (newScreen == gridScreen && (isTimed || (secondsLeft == 0 && !isTimed))) {
			gridScreen.setTime(secondsLeft);
			timer.start();
		} else {
			gridScreen.setTime(0);
			timer.stop();
		}

		// special cases
		if (newScreen == startScreen) 
			resetTest();
		else if (newScreen == pauseScreen) 
			updatePauseScreen();
		
		c.removeAll();
		c.add(newScreen);
		c.revalidate();
		c.repaint();
		
//		currentScreen = newScreen;
		
	}

	// decrements timer
	public void addTimePenalty() {
		if (isTimed)
			secondsLeft -= 5;
		else
			secondsLeft -= 30;
		timer.start();
	}

	// changes grid information
	public void incrementLevel() {
		
		if (secondsLeft > 0) {
			
			currentLevel++;
			colorDifference *= 0.9;
			updateGridScreen();
			changeScreen(gridScreen);
			c.repaint();
			if (isTimed && currentLevel > timedHighScore)
				timedHighScore = currentLevel;
			else if (!isTimed && currentLevel > untimedHighScore)
				untimedHighScore = currentLevel;
		
		}
		
			
	}

	// switches to results screen
	public void getResults() {
		
		String results = "";
		if (currentLevel <= 5)
			results += "You may be " + squareGrid.getClosestMainColor() + "-" + squareGrid.getClosestOddColor() + " colorblind. ";
		else if (currentLevel <= 10)
			results += "You have poor " + squareGrid.getClosestMainColor() + "-" + squareGrid.getClosestOddColor() + " color vision. ";
		else if (currentLevel <= 15)
			results += "You have decent color vision. ";
		else if (currentLevel <= 20)
			results += "You have good color vision. ";
		else
			results += "You have sharp color vision. ";
		
		updateResultsScreen(results);
		changeScreen(resultsScreen);

	}

	// resets all grid information
	private void resetTest() {
		
		// reset levels
		currentLevel = 1;
		colorDifference = 100;
		secondsLeft = 30;
		updateGridScreen();
		
		// reset isTimed
		updateChoiceScreen();
		
	}
	
//	public void redrawTime() {
//		gridScreen.setTime(secondsLeft);
//	}

	public Screen getGridScreen() {
		return gridScreen;
	}
	
	private void updateStartScreen() {
		Button[][] startButtons = new Button[1][2];
		startButtons[0][0] = new Button(this, "Start", choiceScreen);
		startButtons[0][1] = new Button(this, "How To", howToScreen);
		startScreen.setButtons(startButtons);
	}
	
	private void updateHowToScreen() {
		Button[][] howToButtons = new Button[1][1];
		howToButtons[0][0] = new Button(this, "Back", startScreen);
		howToScreen.setButtons(howToButtons);
	}
	
	private void updateChoiceScreen() {
		Button[][] choiceButtons = new Button[1][2];
		choiceButtons[0][0] = new Button(this, "Timed", gridScreen);
		choiceButtons[0][1] = new Button(this, "Untimed", gridScreen);
		choiceScreen.setButtons(choiceButtons);
	}
	
	private void updateGridScreen() {
		squareGrid = new SquareGrid(this, currentLevel + 1, colorDifference);
		gridScreen.setTitle("Level " + currentLevel);
		gridScreen.setButtons(squareGrid.getSquares());
		Button pauseButton = new Button(this, "Pause", pauseScreen);
		if(countPause == 0) {
			gridScreen.addButton(pauseButton, 680, 20);
			countPause++;
		}
		
	}

	private void updatePauseScreen() {
		Button[][] continueButtons = new Button[1][2];
		continueButtons[0][0] = new Button(this, "Continue", gridScreen);
		continueButtons[0][1] = new Button(this, "Restart", startScreen);
		pauseScreen.setButtons(continueButtons);
		if (isTimed)
			pauseScreen.setText("Current level: " + currentLevel + "\nTime left: " + secondsLeft);
		else
			pauseScreen.setText("Current level: " + currentLevel);
	}
	
	private void updateResultsScreen(String results) {
		Button[][] restartButtons = new Button[1][1];
		restartButtons[0][0] = new Button(this, "Restart", startScreen);
		if (isTimed)
			resultsScreen.setText("You reached level " + currentLevel + ". " + results + "\n\nHighscore: " + timedHighScore);
		else
			resultsScreen.setText("You reached level " + currentLevel + ". " + results + "\n\nHighscore: " + untimedHighScore);
		resultsScreen.setButtons(restartButtons);
	}

}
/** 
 * Julia Gu
 * May 24, 2019
 * 
 * This class represents a 2D array of colored Squares.
 * 
 * v.1 - rgb colors, grid
 * v.2 - hsb colors (more control over hue, saturation, brightness)
 * v.3 - getClosestColor()s
 */

import java.awt.Color;

public class SquareGrid {

	// fields
	private Square[][] squares;
	private float mainHue;
	private float oddHue;
	private int oddColumn;
	private int oddRow;

	// creates array of Squares of specified dimensions with colors of specified colorDifference
	public SquareGrid(OculoChroma game, int rows, double colorDifference) {
		
		// creating mainColor and oddColor based off of mainColor
		
//			// METHOD 1: RGB
//			mainColor = new Color((int)colorDifference/2 + (int)(Math.random() * (255 - colorDifference)), (int)colorDifference/2 + (int)(Math.random() * (255 - colorDifference)), (int)colorDifference/2 + (int)(Math.random() * (255 - colorDifference)));
//			
//			int oddRed = (int) (mainColor.getRed() + Math.pow(-1, (int)(Math.random()*2)) * colorDifference);
//			int oddGreen = (int) (mainColor.getGreen() + Math.pow(-1, (int)(Math.random()*2)) * colorDifference);
//			int oddBlue = (int) (mainColor.getBlue() + Math.pow(-1, (int)(Math.random()*2)) * colorDifference);
//			
//			if (oddRed < 0) 
//				oddRed = 0;
//			else if (oddRed > 255)
//				oddRed = 255;
//			
//			if (oddGreen < 0) 
//				oddGreen = 0;
//			else if (oddGreen > 255)
//				oddGreen = 255;
//			
//			if (oddBlue < 0) 
//				oddBlue = 0;
//			else if (oddBlue > 255)
//				oddBlue = 255;
//					
//			oddColor = new Color(oddRed, oddGreen, oddBlue);
			
			// METHOD 2: HSB
			mainHue = (float)Math.random();
			oddHue = (float)(colorDifference/255) + mainHue;
			if (oddHue < 0f) 
				oddHue += 1f;
			else if (oddHue > 1f) 
				oddHue -= 1f;
			
			float saturation = (float)Math.random() * 0.7f + 0.3f;
			float brightness = (float)Math.random() * 0.6f + 0.2f;
			
			Color mainColor = Color.getHSBColor(mainHue, saturation, brightness);
			Color oddColor = Color.getHSBColor(oddHue, saturation, brightness);
		
		// determining position of odd Square
		oddColumn = (int)(Math.random() * rows);
		oddRow = (int)(Math.random() * rows); 
		
		// creating and adding Squares to array
		int squareWidth = 400 / rows;
		squares = new Square[rows][rows];
		for (int y = 0; y < rows; y++) {  				// loop through (not yet defined) rows.
			Square[] squaresInRow = new Square[rows];	  // create new row.
			for (int x = 0; x < rows; x++) {			  // loop through columns in row.
				if (x == oddColumn && y == oddRow)		    // if current position IS odd Square's
					squaresInRow[x] = new Square(game, squareWidth, oddColor, true, game.getGridScreen());
				else 									    // if current position ISN'T odd Square's
					squaresInRow[x] = new Square(game, squareWidth, mainColor, false, game.getGridScreen());
			}
			squares[y] = squaresInRow;
		}

	}

	// returns the array of Squares
	public Square[][] getSquares() {
		return squares;
	}
	
	// returns the odd Square
	public Square getOddSquare() {
		return squares[oddRow][oddColumn];
	}
	
	// determines which color category mainColor falls into
	public String getClosestMainColor() {
		
		if (mainHue < 0.05f)
			return "red";
		if (mainHue < 0.1f)
			return "orange";
		if (mainHue < 0.2f)
			return "yellow";
		if (mainHue < 0.45f)
			return "green";
		if (mainHue < 0.5f)
			return "cyan";
		if (mainHue < 0.75f)
			return "blue";
		if (mainHue < 0.8f)
			return "violet";
		if (mainHue < 0.9f)
			return "magenta";
		if (mainHue < 1f)
			return "red";
		return "";
		
	}
	
	// determines which color category oddColor falls into
	public String getClosestOddColor() {
		
		if (oddHue < 0.05f)
			return "red";
		if (oddHue < 0.1f)
			return "orange";
		if (oddHue < 0.2f)
			return "yellow";
		if (oddHue < 0.45f)
			return "green";
		if (oddHue < 0.5f)
			return "cyan";
		if (oddHue < 0.75f)
			return "blue";
		if (oddHue < 0.8f)
			return "violet";
		if (oddHue < 0.9f)
			return "magenta";
		if (oddHue < 1f)
			return "red";
		return "";
		
	}

}
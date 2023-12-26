/**
 * Julia Gu, Amanda Xu 
 * May 16, 2019
 * 
 * This class is a special Button with a color, equal width and height, and no text.
 * 
 * v.1 - constructor, actionPerformed() skeleton (Julia)
 * v.2 - actionPerformed() (Amanda, Julia)
 * v.3 - highlight() (Julia)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Square extends Button {
	
//	private int squareWidth;
//	private Color squareColor;
	private boolean isOddSquare = false;
	private OculoChroma test;

	public Square(OculoChroma test, int width, Color color, Boolean isOdd, Screen screen) {
		
		super(test, "", width, width, screen);
		this.test = test;
//		this.squareWidth = width;
//		this.squareColor = color;
		this.isOddSquare = isOdd;
		this.setBackground(color);
		this.setOpaque(true);
		
	}

	// determines behavior on click
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();
		if (b.equals(this) && isOddSquare) {
			test.incrementLevel();
		} else if (b.equals(this) && (!isOddSquare)) {
			test.addTimePenalty();
		}

	}
	
	// displays * on square
	public void highlight() {
		super.setForeground(Color.WHITE);
		super.setText("*");
	} 
	
}
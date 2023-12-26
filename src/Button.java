/**
 *  Julia Gu
 *  May 16, 2018
 *  
 *  This class customizes the buttons that appear in OculoChroma.
 */

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Button extends JButton implements ActionListener {

	// fields
	private Screen screen;
	private int width;
	private int height;
	private OculoChroma test;
	private boolean isClicked;

	// constructor for Buttons of customizable dimensions
	public Button(OculoChroma test, String name, int width, int height, Screen screen) {
		super(name);
		this.test = test;
		this.screen = screen;
		this.width = width;
		this.height = height;
		this.setBackground(Color.LIGHT_GRAY);
		this.setOpaque(true);	
//		this.setBorderPainted(false);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
		this.setMargin(new Insets(0, 0, 0, 0));
		this.addActionListener(this);
	}

	// constructor for Buttons of width 80 and height 40
	public Button(OculoChroma test, String name, Screen screen) {
		this(test, name, 80, 40, screen);
	}
	
	// returns Button's width
	public int getWidth() {
		return width;
	}
	
	// returns Button's height
	public int getHeight() {
		return height;
	}

	// determines behavior on click
	@Override
	public void actionPerformed(ActionEvent e) {
	    JButton b = (JButton)e.getSource();
	    if (b.equals(this)) {
	    	isClicked = true;
	    	test.changeScreen(screen);
	    }
	}
	
	// returns clicked state
	public boolean getClicked() {
		return isClicked;
	}
	
//	public void setClicked(boolean b) {
//		isClicked = b;
//	}
	
}
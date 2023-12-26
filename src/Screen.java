/**
 *  Julia Gu, Amanda Xu
 *  May 20, 2019
 *  
 *  This class defines the layout of each screen in OculoChroma.
 *  
 *  v.1 - titleLable, textBox, buttonPanel, paint() (Julia)
 *  v.2 - addButton() (Amanda)
 */

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

	// information
	private String title;
	private String text;
	private Button[][] buttons;
	
	// components
	private JLabel timerLabel;
	private JLabel titleLabel;
	private JTextArea textBox;
	private JPanel buttonPanel;
	private Button addedButton;
	private int buttonX;
	private int buttonY;

	public Screen(String title, String text, Color backgroundColor, Button[][] buttons) {
		
		super.setBackground(backgroundColor);
		this.title = title;
		this.text = text;
		this.buttons = buttons;
	
		// timer
		timerLabel = new JLabel("");
		timerLabel.setBounds(20, 10, 80, 40);
		timerLabel.setForeground(Color.WHITE);
		timerLabel.setFont(new Font("serif", Font.BOLD, 16));
		timerLabel.setOpaque(false);
		timerLabel.setVisible(true);
		this.add(timerLabel);
		
		// title
		titleLabel = new JLabel(title, JLabel.CENTER);
		titleLabel.setBounds(this.getWidth()/2-titleLabel.getWidth()/2, this.getHeight()/3-titleLabel.getHeight()/2, 200, 30);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("serif", Font.BOLD, 24));
		titleLabel.setVisible(true);
		this.add(titleLabel);

		// text
		if (!text.equals("")) {
			textBox = new JTextArea(text);
			textBox.setBounds(this.getWidth()/2-textBox.getWidth()/2, this.getHeight()/3-titleLabel.getHeight()/2, 150, 100);
			textBox.setBackground(backgroundColor);
			textBox.setForeground(Color.WHITE);
			textBox.setOpaque(false);
			textBox.setWrapStyleWord(true);
			textBox.setLineWrap(true);
			textBox.setEditable(false);
			textBox.setFocusable(false);
			textBox.setVisible(true);
			this.add(textBox);
		}
		
		// buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(buttons.length, buttons[0].length));
		for (int j = 0; j < buttons.length; j++) {
			for (int i = 0; i < buttons[0].length; i++) {
				Button b = buttons[j][i];
				buttonPanel.add(b);
			}
		}
		buttonPanel.setBounds(this.getWidth()/2-buttons[0].length*buttons[0][0].getWidth()/2, this.getHeight()/2 - buttons.length*buttons[0][0].getHeight()/2, buttons[0].length*buttons[0][0].getWidth() + 20, buttons.length*buttons[0][0].getHeight() + 20);
		buttonPanel.setOpaque(false);
		buttonPanel.setVisible(true);
		this.add(buttonPanel);

		// layout
		this.setLayout(null);
		this.setVisible(true);
	}
	
	// sets title of screen
	public void setTitle(String ttl) {
		titleLabel.setText(ttl);
	}
	
	// redraws time
	public void setTime(int seconds) {
		if (seconds <= 0)
			timerLabel.setText("");
		else 
			timerLabel.setText(seconds + "");
	}
	
	// adds specified button to be placed in specified location
	public void addButton(Button button, int x, int y) {
		addedButton = button;
		buttonX = x;
		buttonY = y;
	}
	
	// sets screen's buttons
	public void setButtons(Button[][] btn) {
		buttonPanel.removeAll();
		buttons = btn;
		buttonPanel.setLayout(new GridLayout(buttons.length, buttons[0].length));
		for (int j = 0; j < buttons.length; j++) {
			for (int i = 0; i < buttons[0].length; i++) {
				Button b = buttons[j][i];
				buttonPanel.add(b);
			}
		}
		buttonPanel.setBounds(this.getWidth()/2-buttons[0].length*buttons[0][0].getWidth()/2, this.getHeight()/2 - buttons.length*buttons[0][0].getHeight()/2, buttons[0].length*buttons[0][0].getWidth() + 20, buttons.length*buttons[0][0].getHeight() + 20);
	}
	
	// returns screen's buttons
	public Button[][] getButtons() {
		return buttons;
	}
	
	// sets screen's text
	public void setText(String txt) {
		textBox.setText(txt);
		textBox.setBounds(this.getWidth()/2-textBox.getWidth()/2, this.getHeight()/3-50, 150, 100);
	}
	
	// repositions components on window rescale
	public void paint(Graphics g) {

		super.paint(g);

		titleLabel.setBounds(this.getWidth()/2-titleLabel.getWidth()/2, this.getHeight()/8-titleLabel.getHeight()/2, 200, 30);
		if (!text.equals("")) 
			textBox.setBounds(this.getWidth()/2-textBox.getWidth()/2, this.getHeight()/3-textBox.getHeight()/2, 150, 100);
		buttonPanel.setBounds(this.getWidth()/2-buttons[0].length*buttons[0][0].getWidth()/2, this.getHeight() * 9/16 - buttons.length*buttons[0][0].getHeight()/2, buttons[0].length*buttons[0][0].getWidth() + 20, buttons.length*buttons[0][0].getHeight() + 20);

		if (addedButton != null) {
			addedButton.setBounds((int)(this.getWidth()/800.0*buttonX), (int)(this.getHeight()/600.0*buttonY), addedButton.getWidth(), addedButton.getHeight());
			addedButton.setVisible(true);
			this.add(addedButton);
			repaint();
		}
		
	}

}
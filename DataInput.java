
//Copyright (C) 2015  AIR
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class for data input purposes. It has a HashMap field which will contain two components, a string, and a JTextfield 
 * (they will be a label and its field to input data). Two panels and a boolean that indicates if the user has clicked the button to
 * hide the window.
 * @author AIR
 * @version 0.0.2a Anvil
 *
 */

public class DataInput extends JFrame{

	public static class AlreadyAddedException extends RuntimeException {private static final long serialVersionUID = 9L;}
	
	private static final long serialVersionUID = 2L;
	private HashMap <String, JTextField> map = new HashMap<String, JTextField>(); //Each label with its value.;
	private JPanel main, form;
	private boolean clickedButton;
	private Color backColor=null;
	private Color fontColor=null;
	
	/**
	 * Creates the window with a title and a "hide" button with the parameters.
	 * @param titulo Title of the window
	 * @param buttonName Text of the "hide" button at the bottom.
	 */
	public DataInput(String titulo, String buttonName) {
		super(titulo);
		clickedButton=false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main = new JPanel();
		main.setLayout(new BorderLayout());
		add(main);
		JButton button = new JButton(buttonName);
		
		button.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				clickedButton = true;
				waitInput();
			}
		});
		form = new JPanel();
		form.setLayout(new GridLayout(0,2));
		form.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		main.add(form, BorderLayout.CENTER);
		main.add(button, BorderLayout.PAGE_END);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	/**
	 * Creates the window with a title, a "hide" button and the desired colors with the parameters.
	 * @param titulo Title of the window
	 * @param buttonName Text of the "hide" button at the bottom.
	 * @param fontColor Desired color of the font.
	 * @param backgroundColor Desired color of the background.
	 */
	public DataInput(String titulo, String buttonName, Color fontColor, Color backgroundColor) {
		super(titulo);
		clickedButton=false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main = new JPanel();
		main.setLayout(new BorderLayout());
		add(main);
		JButton button = new JButton(buttonName);
		
		button.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				clickedButton = true;
				waitInput();
			}
		});
		form = new JPanel();
		form.setLayout(new GridLayout(0,2));
		form.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		main.add(form, BorderLayout.CENTER);
		main.add(button, BorderLayout.PAGE_END);
		changeColors(fontColor,backgroundColor);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	
	/**
	 * Adds a field with a label and a place where the user give the data.
	 * @param label Name of the field (will be the same as the label in the window).
	 * @throws AlreadyAddedException if the Field is already registered.
	 */
	public  void addField(String label) throws AlreadyAddedException{

		//Label->Key
		
		if (map.get(label)!=null) {  // If the field is already added
			throw new AlreadyAddedException();
		}
		
		JLabel lbl = new JLabel(label);
		if (fontColor!=null) {
			lbl.setForeground(fontColor);
		}
		lbl.setPreferredSize(new Dimension(20,10));
		form.add(lbl);
		JTextField text= new JTextField();
		text.setPreferredSize(new Dimension(200,30));
		form.add(text);
		map.put(label,text);
		pack();
		revalidate();
	}
	
	/**
	 * This method must be called after window creation in order to allow the user can write all 
	 * the data, when the user press the "hide" button the method finishes.
	 */
	public void waitInput() {
		while(clickedButton==false){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
		
		
		setVisible(false);

	}
	 
	/**
	 * Returns the content of the JTextfield of a field.
	 * @param label Name of the field to get
	 * @return The content given by the user.
	 */
	public String readField(String label) {
		return map.get(label).getText();
	}
	
	/**
	 * Private method for future implementations.
	 * @return All the Fields [Key (label) , JTextField (Data from the user)]
	 */
	private HashMap<String,JTextField> returnMap() {
		return map;
		
	}
	
	/**
	 * Change the two main colors of the window (font and background color).
	 * @param font Color for changing the font.
	 * @param back Color for changing the background.
	 */
	private void changeColors(Color font, Color back) {
		backColor=back;
		fontColor=font;
		main.setBackground(backColor);
		form.setBackground(backColor);
		revalidate();
	}

}

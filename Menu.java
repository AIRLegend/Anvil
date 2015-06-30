//Copyright (C) 2015  AIR
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//   You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * <p><h1> Menu Class</h1></p>
 * <p> Contains several methods to manage a simple window. It has a JPanel attribute as the main
 * content panel for the components.</p>
 * @author AIR
 * @version 0.0.1 Anvil
 *
 */
public class Menu extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel content;
	/**
	 * Create the window with a title, width and height. 
	 * @param titulo. Title of the window.
	 * @param x Width
	 * @param y Height
	 * @param layoutType. [0] Flow Layout (Default)<br>
	 * 				  	  [1] BoxLayout <br> //TODO
	 * 				  	  [2] GridLayout <br> //TODO
	 */
	public Menu(String titulo, int x, int y, int layoutType) {
		super(titulo);
		setSize(x,y);
		content = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (layoutType==1){
			content.setLayout(new BoxLayout(content,5));
		} else if (layoutType==2){
			content.setLayout(new GridLayout());
		} else {
			content.setLayout(new FlowLayout());
		}
		
		add(content);
		setVisible(true);
	}
	
	/**
	 * Create the window with a title, width and height and FlowLayout. 
	 * @param titulo. Title of the window.
	 * @param x Width
	 * @param y Height
	 */
	public Menu(String titulo, int x, int y) {
		super(titulo);
		setSize(x,y);
		content = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.setLayout(new FlowLayout());
		add(content);
		setVisible(true);
	}

	

	/**
	 * Adds a button to perform an action.
	 * @param text of the button.
	 * @param e Event which will happen after click the button.
	 */
	
	public void addButton(String text, ActionListener e) {
		JButton button = new JButton(text);
		button.addActionListener(e);
		content.add(button);
		revalidate();

	}
		
	
}

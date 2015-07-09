
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


import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * <p><h1> Menu Class</h1></p>
 * <p> Contains several methods to manage a simple window. It has a JPanel attribute as the main
 * content panel for the components.</p>
 * @author AIR
 * @version 1.1.0 Anvil
 *
 */
public class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel content;
	private Color backColor=null;
	private Color fontColor=null;
	
	
	/**
	 * Create the window with a title, width and height. 
	 * @param titulo. Title of the window.
	 * @param x Width
	 * @param y Height
	 * @param layoutType. [0] Flow Layout (Default)<br>
	 * 				  	  [1] BoxLayout <br>
	 * 				  	  [2] GridLayout <br>
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
	 * Creates a Menu window with the given colors.
	 * @param titulo Title of the window
	 * @param x Width
	 * @param y Height
	 * @param layoutType 
	 * @param backColor Color of the background
	 * @param fontColor Color that will be used for the text.
	 */
	
	public Menu(String titulo, int x, int y, int layoutType, Color backColor, Color fontColor) {
		
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
		changeColors(fontColor,backColor);
		
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
	 * Add a text to the window as a Label.
	 * @param textLabel Text to add.
	 */
	public void addText(String textLabel) {
		JLabel text = new JLabel(textLabel);
		
		if(fontColor != null) { // If the window is as default, don't change the font color.
			text.setForeground(fontColor);
		} 
		
		content.add(text);
		validate();		
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
	
	/**
	 * Adds a JLabel with an image.
	 * @param path of the image (needs to be in the same package as the class).
	 * @throws InvalidPathException if the image path is wrong.
	 * @throws IOException 
	 */
	
	public void addImage(String path) throws IOException {
		ImageIcon image = createImageIcon(path,null);

		if (image==null) {
			throw new IOException();
		}
		
		JLabel lbl = new JLabel(image);
		content.add(lbl);
		revalidate();
	}
	
	/**
	 * Adds a frame  which displays an HTML document or a simple web page.
	 * @param url Path (local) of the document  or URL of the web page.
	 * @param x x-position.
	 * @param y y-position.
	 * @param width 
	 * @param height
	 * @param scroll true, if wanted to be scrollable, false if not.
	 * @throws IOException If the HTML document path is wrong or the document is unreadable.
	 * @throws MalformedURLException If the URL to the document (web) is wrong
	 * @see HTMLPanel
 	 */

	public void addHTMLFrame(String url, int x, int y, int width, int height, boolean scroll, boolean editable) throws IOException, MalformedURLException {
		HTMLPanel j = new HTMLPanel(url,editable);
		if (scroll){
			JScrollPane j2 = new JScrollPane(j);
			j2.setBounds(x,y,width,height);
			content.add(j2);
		}else{
			j.setBounds(x,y,width,height);
			content.add(j);
		}
	}
	
	

	/**
	 * Updates the view of the HTMLPanel which has a given URL.
	 * @param oldURL the URL which has the panel to change.
	 * @param newURL new desired URL to show.
	 * @throws IOException If the HTML document path is wrong or the document is unreadable.
	 * @see HTMLPanel
	 */
	public void updateHTMLFrame(String oldURL, String newURL) throws IOException {
		Component components [] = content.getComponents();
		for(int i=0;i<components.length;i++) {
			if (components[i] instanceof HTMLPanel ){
				HTMLPanel temp = (HTMLPanel)(components[i]);
				if (temp.getURL().equals(oldURL)) {
					temp.updateURL(newURL);
					return;
					
				}
			}
		}
	}
	
	
	/**
	 * Private method to verify if an image path is correct.
	 * @param path of the image (needs to be in the same package as the class)
	 * @param description to add to the ImageIcon returned
	 * @return An imageIcon if the path is correct, null if is not.
	 */
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL,description);
		} else {
			return null;
		}
	}
	
	
	/**
	 * Change the two main colors of the window (font and background color).
	 * @param font Color for changing the font.
	 * @param back Color for changing the background.
	 */
	private void changeColors(Color font, Color back) {
		backColor=back;
		fontColor=font;
		content.setBackground(backColor);
		revalidate();
	}	
	
}


//Copyright (C) 2015  AIR
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class for creating window menus from images. It has an attribute  "main" which will be the main panel
 * of the frame, there, all the components will be added.<br>
 * As the frame is undecorated (doesn't have bar), the window will be positioned by dragging it. Also, it's recommended 
 * to add a close button.
 * @author AIR
 * @version 1.0.0
 *
 */
public class WindowFromImage extends JFrame {
	

	private static final long serialVersionUID = 123L;
	
	private ImagePanel main;
	private Point initialClick; 
	
	public WindowFromImage(String path) throws IOException{
		main= new ImagePanel(path);
		main.setLayout(null);
		
		//Add the listeners so the window can be moved.--------------//
		main.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
			}
		});
		
		main.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				//Get the position of the window
				int thisX = getLocation().x;
				int thisY = getLocation().y;
				
				//Calculate how much the mouse has been moved
				
				int xMov = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMov = (thisY + e.getY()) - (thisY+ initialClick.y);
				
				//Move the window to the new position
				int x= thisX+xMov;
				int y= thisY+yMov;
				setLocation(x,y);		
			}	
		});
		//-------------------------------------------------------------//
		
		setSize(main.getWidth(),main.getHeight());
		setUndecorated(true);
		setLocationRelativeTo(null);
		add(main);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Adds a close button on the top-right corner.
	 * @param path Route to the image.
	 * @throws IOException If the path is wrong or the file is not readable.
	 */
	public void addCloseButton(String path) throws IOException{
		
		BufferedImage buttonIco = ImageIO.read(new File(path));
		JButton button = new JButton(new ImageIcon(buttonIco));
		
		button.addActionListener(new ActionListener(){ //Add close action to the button.
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		main.add(button);
		button.setBounds(getWidth()-buttonIco.getWidth()-10, 10, buttonIco.getWidth(), buttonIco.getHeight()); //Positioning it
		repaint();
		revalidate();
		
	}
	
	
	/**
	 * Adds a close button on the desired position.
	 * @param path Route to the image.
	 * @param x Horizontal distance from the beginning of the window,where the button will be placed.
	 * @param y Vertical distance from the beginning of the window,where the button will be placed.
	 * @throws IOException If the path is wrong or the file is not readable.
	 */
	public void addCloseButton(String path, int x, int y) throws IOException{
		
		BufferedImage buttonIco = ImageIO.read(new File(path));
		JButton button = new JButton(new ImageIcon(buttonIco));
		
		button.addActionListener(new ActionListener(){ //Add close action to the button.
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		main.add(button);
		button.setBounds(x, y, buttonIco.getWidth(), buttonIco.getHeight()); //Positioning it
		repaint();
		revalidate();
		
	}
	
	
	/**
	 * Adds a  button on the desired position.
	 * @param path Route to the image.
	 * @param x Horizontal distance from the beginning of the window,where the button will be placed.
	 * @param y Vertical distance from the beginning of the window,where the button will be placed.
	 * @param e Listener which perform the desired action.
	 * @see ActionListener
	 * @throws IOException If the path is wrong or the file is not readable.
	 */
	
	public void addButton(String path, int x, int y, ActionListener e) throws IOException {
		BufferedImage buttonIco = ImageIO.read(new File(path));
		JButton button = new JButton(new ImageIcon(buttonIco));
		button.addActionListener(e);
		main.add(button);
		button.setBounds(x, y, buttonIco.getWidth(), buttonIco.getHeight()); //Positioning it
		repaint();
		revalidate();
	}
}

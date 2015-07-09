
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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Class which creates a Panel to add on a JFrame from an image.
 * @author AIR
 * @version 1.0.1
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 234L;
	private BufferedImage image;
	
	/**
	 * Reads the image given the file-path.
	 * @param path Route of the image.
	 * @throws IOException If the path is wrong or the file is not readable.
	 */
	public ImagePanel(String path) throws IOException {
		image = ImageIO.read(ImagePanel.class.getResource(path));
	}
	
	/**
	 * Paints the image.
	 * @see JPanel
	 */
	@Override
	public void paintComponent (Graphics graphics) {
		graphics.drawImage(image,0,0,null);
		repaint();
	}
	
	public int getWidth(){
		return image.getWidth();
	}
	
	public int getHeight(){
		return image.getHeight();
	}
}


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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JEditorPane;
import javax.swing.text.Document;

/**
 * Class which displays an HTML document  or a simple web page in a Panel.
 * @author AIR
 * @version 1.2.0
 */
public class HTMLPanel extends JEditorPane{

	private static final long serialVersionUID = 345L;
	
	private String url;
	
	/**
	 * Creates the panel from the URL
	 * @param url of the document (local or web).
	 * @param editable true if is wanted the content to be editable, false if not.
	 * @throws MalformedURLException If the URL to the document (web) is wrong.
	 * @throws IOException If the path is wrong or the file is not readable.
	 */
	public HTMLPanel(String url,boolean editable) throws MalformedURLException, IOException {
		super();
		this.url = url;
		if(url.contains("www.")) {
			setPage(url);
		}else {
			setPage(new File(url).toURI().toURL());
		}
		setEditable(editable);
		
	}
	
	public String getURL(){
		return url;
	}
	
	/**
	 * Updates the view of the panel.
	 * @param newURL New URL to show.
	 * @throws IOException If the html document is  or the path is incorrect. 
	 */
	public void updateURL(String newURL) throws IOException {
		if(newURL.equals(url)){ //If the URL is the same, don't update.
			return;
		}
		
		this.url=newURL;
		
		try{
			if(url.contains("www.")) {
				setPage(newURL);
			}else {
				setPage(new File(newURL).toURI().toURL());
			}
			reload();
			
		}catch (MalformedURLException e){
			setPage("<html>Sorry, the page could not be loaded.</html>");
		}
		
	}
	
	/**
	 * Force to reload the content of the panel.
	 * See also: {@linkplain javax.swing.JEditorPane#setPage(URL)}
	 */
	private void reload(){
		Document doc = getDocument();
		doc.putProperty(Document.StreamDescriptionProperty, null);
	}
	

}

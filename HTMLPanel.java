
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JEditorPane;
import javax.swing.text.Document;

/**
 * Class which displays an HTML document  or a simple web page in a Panel.
 * @author AIR
 * @version 1.2.1
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
		if(url.contains("www.") || url.contains("http://www.") || url.contains("https://www.")) {
			setPage(url);
		}else {
			setPage(HTMLPanel.class.getResource(url));
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
			if(url.contains("www.") || newURL.contains("http://www.") || newURL.contains("https://www.")) {
				setPage(newURL);
			}else {
				setContentType("text/html");
				setPage(HTMLPanel.class.getResource(newURL));
			}
			reload();
			
		}catch (MalformedURLException e){
			setPage("<html>Sorry, the page could not be loaded.</html>");
		}catch (IOException e){
			setPage("<html>Sorry, the file could not be found.</html>");
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

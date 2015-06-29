import javax.swing.JOptionPane;

/**
 * Class for displaying some kinds of messages.
 * @author AIR
 * @version 0.0.1 Anvil
 */

public class Message {
	

	/**
	 * Creates a basic message.
	 * @param text Text of the message
	 */
	public Message(String text) {
		
		JOptionPane.showMessageDialog(null, text);
		
	}
	
	/**
	 * Creates several types.
	 * @param type of the message. [1] Error Message [2] Warning Message [3] Plain Message <br> Otherwise: Default message.
	 * @param titulo Title of the message.
	 * @param text Content of the message.
	 */
	public Message(int type, String titulo, String text) {
		
		if(type==1) {
			JOptionPane.showMessageDialog(null,titulo,text,JOptionPane.ERROR_MESSAGE);
		} else if (type==2) {
			JOptionPane.showMessageDialog(null,titulo,text,JOptionPane.WARNING_MESSAGE);
		} else if (type==3) {
			JOptionPane.showMessageDialog(null,titulo,text,JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, text);
		}
		
	}

}

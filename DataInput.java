import java.awt.BorderLayout;
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
 * @version 0.0.1 Anvil
 *
 */

public class DataInput extends JFrame{
	
	private static final long serialVersionUID = 2L;
	private HashMap <String, JTextField> map = new HashMap<String, JTextField>(); //Each label with its value.;
	private JPanel main, form;
	private boolean clickedButton;
	
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
	 * Adds a field with a label and a place where the user give the data.
	 * @param label Name of the field (will be the same as the label in the window).
	 */
	public  void addField(String label) {

		//Label->Key
		JLabel lbl = new JLabel(label);
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

}

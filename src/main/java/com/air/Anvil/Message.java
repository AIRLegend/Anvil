package com.air.Anvil;

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

import javax.swing.JOptionPane;

/**
 * Class for displaying some kinds of messages.
 * @author AIR
 * @version 1.0.0 Anvil
 */

public class Message {

	private String input=null;
	
	/**
	 * Creates a basic message.
	 * @param text Text of the message
	 */
	public Message(String text) {
		
		JOptionPane.showMessageDialog(null, text);
		
	}
	
	/**
	 * Creates several types.
	 * @param type of the message. [1] Error Message [2] Warning Message [3] Plain Message  
	 * 							   [4] Input Message.<br> Otherwise: Default message.
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
		} else if (type==4){
			input=JOptionPane.showInputDialog(null,"Hi"); //Saves the read value.
		}else {
			JOptionPane.showMessageDialog(null, text);
		}
		
	}
	
	public String getInput() {
		return input;
	}
	

}

package com.air.Anvil;

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

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * Class which describes a panel with JCheckBoxes and some methods to manage them.
 * Contains an ArrayList with all the JCheckBoxes added.
 * @author AIR
 * @version 1.0.0
 */
public class DataCheckPanel extends JPanel {
	/**
	 * Thrown when adding a field when it already exists.
	 */
	public static class AlreadyExists extends RuntimeException {
		private static final long serialVersionUID = 1L;
	};
	
	
	private static final long serialVersionUID = 1L;
	private ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	private int maxOptionsChecked = 1;
	private int checkedOptions = 0; //Current checked fields.
	
	/**
	 * Creates the panel with a checkbox.
	 * @param maxOptionsChecked Maximum number of checked fields.
	 * @param name name of the field created (by default).
	 * @param rows rows of the panel.
	 * @param cols columns of the panel.
	 */
	public DataCheckPanel(int maxOptionsChecked, String name, int rows, int cols) {
		super();
		if (maxOptionsChecked >1){
			this.maxOptionsChecked = maxOptionsChecked;
		}
		
		final JCheckBox check = new JCheckBox(name);
		
		check.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				if (check.isSelected() == true) {
					checkedOptions++;
				} else if (check.isSelected() == false) {
					checkedOptions--;
				}
				updateUnusedCheckBoxes();
				
			}
		});
		setLayout(new GridLayout(rows, cols));
		checkboxes.add(check);
		add(check);
	}
	
	
	/**
	 * Adds a checkbox to the panel
	 * @param name Name of the checkbox.
	 */
	public void addCheckBox(String name) {
		final JCheckBox check = new JCheckBox(name);
		
		check.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (check.isSelected() == true) {
					checkedOptions++;
				} else if (check.isSelected() == false) {
					checkedOptions--;
				}
				updateUnusedCheckBoxes();
			}
		});
		checkboxes.add(check);
		add(check);
	}
	
	
	/**
	 * Change the background of the panel.
	 * @param color
	 */
	public void changeBackgroundColor(Color color) {
		setBackground(color);
	}
	
	/**
	 * Returns the value of the selected checkbox.
	 * @param name Name of the checkbox.
	 * @throws RuntimeException if the wanted checkbox doesn't exist.
	 */
	public boolean isSelected(String name) throws RuntimeException {
		for(int i=0; i<checkboxes.size();i++) {
			if(checkboxes.get(i).getText().equals(name)) {
				return checkboxes.get(i).isSelected();
			}
		}
		return false;
	}
	/**
	 * Return the names of all selected checkboxes.
	 * @return An ArrayList with all the names (String) of all the selected checkboxes. 
	 */
	public ArrayList<String> getSelectedCheckboxNames() {
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i<checkboxes.size();i++) {
			if(checkboxes.get(i).isSelected()) {
				result.add(checkboxes.get(i).getText());
			}
		}
		
		if(result.size()==0){
			return null; 	//No checkboxes are selected.
		}
		
		return result;
		
	}
	
	/**
	 * Set editable to false when the maximum name of checked options is reached. Otherwise, set all to editable = true.
	 */
	private void updateUnusedCheckBoxes (){
		if(checkedOptions >= maxOptionsChecked) {
			for(int i=0; i<checkboxes.size();i++){
				if (!checkboxes.get(i).isSelected()) {
					checkboxes.get(i).setEnabled(false);
				}
			}
		}
		
		if(checkedOptions<maxOptionsChecked) {
			for(int i=0; i<checkboxes.size();i++){
				if (!checkboxes.get(i).isSelected()) {
					checkboxes.get(i).setEnabled(true);
				}
			}
		}
	}

}

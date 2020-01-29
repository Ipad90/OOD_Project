package misc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings( { "serial", "unused" } )
public class DynamicRegForm extends RegistrationFormGUI {	
	RegistrationFormGUI formGUIObject;
	Funct F;
	
	public DynamicRegForm() {
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funct.Set_Text();
			}
		});

	}
	
	public static void main(String[] args) {
  		new DynamicRegForm();
  	}
}
package misc;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;


@SuppressWarnings( { "serial", "unused" } )
public class RegistrationFormGUI extends JFrame {   
	static JLabel title;
	static JButton registerButton;
	
	public RegistrationFormGUI() {
		super("REGISTRATION FORM");
		setSize(770, 420);
		getContentPane().setLayout(null);

		title = new JLabel("Registration Form");
		title.setBounds(99, 140, 200, 30);
		getContentPane().add(title);

		registerButton = new JButton("Register");
		registerButton.setBounds(110, 250, 100, 25);
		getContentPane().add(registerButton);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
	}
}
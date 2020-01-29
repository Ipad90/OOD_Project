package view;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;

import controller.*;

@SuppressWarnings( { "unused", "unchecked", "rawtypes" } )

public class V_O_G_Reg_Book {
	Connection conn;
	PreparedStatement ps_0, ps_1, ps_2;
	ResultSet rs_0, rs_1, rs_2;
	ResultSetMetaData rsmd_0;
	
	protected static JFrame FRM_RegBook;
	protected static JTable TBL_DispBook;
	protected static JTextField TF_ID, TF_Name, TF_Genre, TF_Lang, TF_Author, TF_Publisher;
	protected static JButton BTN_DispBook, BTN_RegBook, BTN_DelBook, BTN_ClrFM, BTN_UB, BTN_MM;
	protected static JComboBox Combo_BT;
	protected static JScrollPane SCRL_TBL;
	protected static JLabel L1, L2, L3, L4, L5, L6, L7, L8;
	protected static JPanel P1, P2;

	public V_O_G_Reg_Book() {		
		FRM_RegBook = new JFrame("Library System - Book Registration Form");
		FRM_RegBook.setBounds(100, 100, 1600, 680);
		FRM_RegBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_RegBook.getContentPane().setLayout(null);

		L1 = new JLabel("Book Registration Form");
		L1.setHorizontalAlignment(SwingConstants.CENTER);
		L1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L1.setBounds(10, 10, 280, 25);
		FRM_RegBook.getContentPane().add(L1);
		
		P1 = new JPanel();
		P1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Book Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		P1.setBounds(10, 40, 280, 300);
		FRM_RegBook.getContentPane().add(P1);
		P1.setLayout(null);
		
		L2 = new JLabel("Book ID");
		L2.setHorizontalAlignment(SwingConstants.CENTER);
		L2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L2.setBounds(60, 25, 100, 25);
		P1.add(L2);

		L3 = new JLabel("Book Name");
		L3.setBounds(60, 65, 100, 25);
		L3.setHorizontalAlignment(SwingConstants.CENTER);
		L3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L3);

		L4 = new JLabel("Book Genre");
		L4.setBounds(60, 105, 100, 25);
		L4.setHorizontalAlignment(SwingConstants.CENTER);
		L4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L4);

		L5 = new JLabel("Book Language");
		L5.setBounds(40, 145, 120, 25);
		L5.setHorizontalAlignment(SwingConstants.CENTER);
		L5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L5);
			
		L6 = new JLabel("Book Type");
		L6.setBounds(60, 185, 100, 25);
		L6.setHorizontalAlignment(SwingConstants.CENTER);
		L6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L6);
		
		L7 = new JLabel("Book Author");
		L7.setBounds(60, 225, 100, 25);
		L7.setHorizontalAlignment(SwingConstants.CENTER);
		L7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L7);
		
		L8 = new JLabel("Book Publisher");
		L8.setBounds(40, 265, 120, 25);
		L8.setHorizontalAlignment(SwingConstants.CENTER);
		L8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L8);
		
		TF_ID = new JTextField();
		TF_ID.setEnabled(false);
		TF_ID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_ID.setColumns(10);
		TF_ID.setBounds(170, 25, 100, 25);
		P1.add(TF_ID);

		TF_Name = new JTextField();
		TF_Name.setBounds(170, 65, 100, 25);
		TF_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_Name.setColumns(10);
		P1.add(TF_Name);

		TF_Genre = new JTextField();
		TF_Genre.setBounds(170, 105, 100, 25);
		TF_Genre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_Genre.setColumns(10);
		P1.add(TF_Genre);

		TF_Lang = new JTextField();
		TF_Lang.setBounds(170, 145, 100, 25);
		TF_Lang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_Lang.setColumns(10);
		P1.add(TF_Lang);
		
		TF_Author = new JTextField();
		TF_Author.setBounds(170, 225, 100, 25);
		TF_Author.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_Author.setColumns(10);
		P1.add(TF_Author);
		
		TF_Publisher = new JTextField();
		TF_Publisher.setBounds(170, 265, 100, 25);
		TF_Publisher.setFont(new Font("Tahoma", Font.PLAIN, 14));

		TF_Publisher.setColumns(10);
		P1.add(TF_Publisher);
		
		String Book_Type[] = { "Reference only", "Open shelf" };
		Combo_BT = new JComboBox(Book_Type);
		Combo_BT.setBounds(170, 185, 100, 25);
		P1.add(Combo_BT);
		
		P2 = new JPanel();
		P2.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		P2.setBounds(10, 345, 280, 260);
		FRM_RegBook.getContentPane().add(P2);
		P2.setLayout(null);

		BTN_DispBook = new JButton("Refresh Table");
		BTN_DispBook.setBounds(10, 25, 260, 25);
		P2.add(BTN_DispBook);

		BTN_RegBook = new JButton("Register Book");
		BTN_RegBook.setBounds(10, 105, 260, 25);
		P2.add(BTN_RegBook);
		
		BTN_ClrFM = new JButton("Clear Form");
		BTN_ClrFM.setBounds(10, 65, 260, 25);
		P2.add(BTN_ClrFM);
		
		BTN_DelBook = new JButton("Delete Book");
		BTN_DelBook.setEnabled(false);
		BTN_DelBook.setBounds(10, 145, 260, 25);
		P2.add(BTN_DelBook);

		BTN_UB = new JButton("Update Book");
		BTN_UB.setEnabled(false);
		BTN_UB.setBounds(10, 185, 260, 25);
		P2.add(BTN_UB);
		
		BTN_MM = new JButton("Main Menu");
		BTN_MM.setBounds(10, 225, 260, 25);
		P2.add(BTN_MM);
										
		FRM_RegBook.setVisible(true);
	}
	
	public static void main(String [] args) {
		new V_O_G_Reg_Book();
	}
}
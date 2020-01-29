 package ood_proj;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

@SuppressWarnings( { "unused" } )

public class OOD_GUI_MainMenu {
	Connection conn;
	PreparedStatement ps_0, ps_1, ps_2, ps_3;
	ResultSet rs_0, rs_1;
	
	private JFrame FRM_MM;
	private JButton BTN_Goto_RegMem;
	private JButton BTN_Goto_RegBook;
	private JButton BTN_Goto_Brwr;
	private JButton BTN_Goto_Rtrn;
	private JButton BTN_Goto_RnwMem;
	private JButton BTN_Exit;
	
	private java.sql.Timestamp Cur_Date = new java.sql.Timestamp(new java.util.Date().getTime());
	
	public static void main(String [] args) {
		new OOD_GUI_MainMenu();
	}

	public OOD_GUI_MainMenu() {
		FRM_MM = new JFrame("Library System - Main Menu");
		FRM_MM.setBounds(100, 100, 345, 290);
		FRM_MM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_MM.getContentPane().setLayout(null);
		
		BTN_Goto_RegMem = new JButton("Register Member");
		BTN_Goto_RegMem.setBounds(10, 11, 150, 50);
		BTN_Goto_RegMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_MM.dispose();
				OOD_GUI_RegMem OGRM = new OOD_GUI_RegMem();
			}
		});

		FRM_MM.getContentPane().add(BTN_Goto_RegMem);
		
		BTN_Goto_Brwr = new JButton("Borrow Book");
		BTN_Goto_Brwr.setBounds(10, 133, 150, 50);
		BTN_Goto_Brwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRM_MM.dispose();
				OOD_GUI_Borrow_Book OGBB = new OOD_GUI_Borrow_Book();
			}
		});
		FRM_MM.getContentPane().add(BTN_Goto_Brwr);
		
		BTN_Goto_RegBook = new JButton("Register Book");
		BTN_Goto_RegBook.setBounds(10, 72, 150, 50);
		BTN_Goto_RegBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_MM.dispose();
				OOD_GUI_RegBook OGRB = new OOD_GUI_RegBook();
			}
		});
		FRM_MM.getContentPane().add(BTN_Goto_RegBook);
		
		BTN_Goto_Rtrn = new JButton("Return Book");
		BTN_Goto_Rtrn.setBounds(10, 194, 150, 50);
		BTN_Goto_Rtrn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_MM.dispose();
			}
		});
		FRM_MM.getContentPane().add(BTN_Goto_Rtrn);
			
		BTN_Goto_RnwMem = new JButton("Renew Membership");
		BTN_Goto_RnwMem.setBounds(170, 11, 150, 50);
		BTN_Goto_RnwMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_MM.dispose();
				OOD_GUI_Mem_Pay OGMP = new OOD_GUI_Mem_Pay();
			}
		});
		FRM_MM.getContentPane().add(BTN_Goto_RnwMem);
		
		BTN_Exit = new JButton("Exit");
		BTN_Exit.setBounds(170, 194, 150, 50);
		BTN_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		FRM_MM.getContentPane().add(BTN_Exit);
		
		JButton BTN_DispMsg = new JButton("Display Message");
		BTN_DispMsg.setBounds(170, 72, 150, 50);
		BTN_DispMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check_Overdue_Items();
			}
		});
		FRM_MM.getContentPane().add(BTN_DispMsg);
		
		FRM_MM.setVisible(true);
		Check_Overdue_Items();
	}
	
	public void Connect() {
		String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/library_system?serverTimezone=UTC";
		String DB_User = "root";
		String DB_Pass = "";
		
		try {
			Class.forName(JDBC_Driver);
			conn = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Check_Overdue_Items() {
		String Query_0 = "SELECT * FROM member";
		String Query_1 = "SELECT * FROM borrowing_record";
		String Query_2 = "UPDATE member SET Membership_Status = ? WHERE Member_ID = ?";
		String Query_3 = "UPDATE borrowing_record SET Borrow_Exceed = ?, Days_Exceeded = ? WHERE Borrow_ID = ?";
		
		Calendar CLD_Cur = Calendar.getInstance();
		Calendar CLD_Mem_Ftr = Calendar.getInstance();
		Calendar CLD_Return = Calendar.getInstance();
		
		CLD_Cur.setTime(Cur_Date);
		
		int i = 0;
		int j = 0;
		
		try {
			Connect();
			
			ps_0 = conn.prepareStatement(Query_0);
			ps_1 = conn.prepareStatement(Query_1);

			
			rs_0 = ps_0.executeQuery();
			rs_1 = ps_1.executeQuery();
			
			while (rs_0.next()) {
				java.sql.Timestamp DB_Mem_Date = rs_0.getTimestamp("Membership_Renewed");
				int Mem_ID = rs_0.getInt("Member_ID");
			
				CLD_Mem_Ftr.setTime(DB_Mem_Date);
				CLD_Mem_Ftr.add(Calendar.YEAR, 1);
				
				java.util.Date Date_Of_Mem_Future = CLD_Mem_Ftr.getTime();
			
				if (Cur_Date.after(Date_Of_Mem_Future)) {
					i++;
					ps_2 = conn.prepareStatement(Query_2);
					ps_2.setString(1, "N");
					ps_2.setInt(2, Mem_ID);
					ps_2.execute();
				}
			}
			
			while (rs_1.next()) {
				java.sql.Timestamp DB_Return_Date = rs_1.getTimestamp("Return_Date");
				int Borrow_ID = rs_1.getInt("Borrow_ID");
				
				CLD_Return.setTime(DB_Return_Date);
				
				java.util.Date Date_Of_Return = CLD_Return.getTime();
				
				if (Cur_Date.after(Date_Of_Return)) {
					j++;
					ps_3 = conn.prepareStatement(Query_3);
					ps_3.setString(1, "Y");
					ps_3.setInt(2, 0);
					ps_3.setInt(3, Borrow_ID);
				}
			}
			
			JOptionPane.showMessageDialog(null, i + " member(s) with expired membership.\n" + j + " overdue book(s).");
			rs_0.close();
			rs_1.close();
			ps_0.close();
			ps_1.close();
			ps_2.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
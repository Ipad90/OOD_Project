package ood_proj;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings( { "unused" } )

public class OOD_GUI_Borrow_Book {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	ResultSetMetaData rsmd;
	
	private JFrame FRM_BrwBook;
	private JTextField TF_MemID;
	private JTextField TF_MemName;
	private JTextField TF_BookID;
	private JTextField TF_BookName;
	private JTextField TF_CurDate;
	private JTextField TF_RetDate;
	private JButton BTN_BrwBook;
	private JButton BTN_MM;
	private JButton BTN_ClrFM;
	private JLabel L1, L2, L3, L4, L5, L6, L7;
	private JPanel P1, P2;

	private int Verification_Book = 0;
	private int Verification_Mem = 0;
	
	private SimpleDateFormat Date_Format = new SimpleDateFormat("dd/MM/yyyy");
	
	private java.sql.Timestamp Cur_Date = new java.sql.Timestamp(new java.util.Date().getTime());
	private java.sql.Timestamp Next_Date = new java.sql.Timestamp(new java.util.Date().getTime());
	
	public static void main(String [] args) {
		new OOD_GUI_Borrow_Book();
	}

	public OOD_GUI_Borrow_Book() {
		FRM_BrwBook = new JFrame("Library System - Borrow Book Form");
		FRM_BrwBook.setBounds(100, 100, 1200, 500);
		FRM_BrwBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_BrwBook.getContentPane().setLayout(null);
		
		L1 = new JLabel("Borrow Book Form");
		L1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L1.setHorizontalAlignment(SwingConstants.CENTER);
		L1.setBounds(10, 10, 280, 25);
		FRM_BrwBook.getContentPane().add(L1);
		
		P1 = new JPanel();
		P1.setBorder(new TitledBorder(null, "Borrow Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		P1.setBounds(10, 40, 280, 260);
		FRM_BrwBook.getContentPane().add(P1);
		P1.setLayout(null);
		
		P2 = new JPanel();
		P2.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		P2.setBounds(10, 310, 280, 140);
		FRM_BrwBook.getContentPane().add(P2);
		P2.setLayout(null);
		
		TF_MemID = new JTextField();
		TF_MemID.setBounds(170, 25, 100, 25);	
		TF_MemID.setColumns(10);
		TF_MemID.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				Check_Mem();
			}
		});
		P1.add(TF_MemID);
		
		TF_MemName = new JTextField();
		TF_MemName.setBounds(170, 65, 100, 25);
		TF_MemName.setColumns(10);
		P1.add(TF_MemName);
		
		TF_BookID = new JTextField();
		TF_BookID.setBounds(170, 105, 100, 25);
		TF_BookID.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				Check_Book();
			}
		});
		TF_BookID.setColumns(10);
		P1.add(TF_BookID);
		
		TF_BookName = new JTextField();
		TF_BookName.setBounds(170, 145, 100, 25);
		P1.add(TF_BookName);
		TF_BookName.setColumns(10);
		
		TF_CurDate = new JTextField();
		TF_CurDate.setEditable(false);
		TF_CurDate.setBounds(170, 185, 100, 25);
		P1.add(TF_CurDate);
		TF_CurDate.setColumns(10);
		
		TF_RetDate = new JTextField();
		TF_RetDate.setEditable(false);
		TF_RetDate.setBounds(170, 225, 100, 25);
		P1.add(TF_RetDate);
		TF_RetDate.setColumns(10);
		
		BTN_BrwBook = new JButton("Borrow Book");
		BTN_BrwBook.setBounds(20, 25, 100, 25);
		BTN_BrwBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Verification_Book + Verification_Mem == 2) {
					Borrow();
				}
			}
		});
		P2.add(BTN_BrwBook);
		
		BTN_MM = new JButton("Main Menu");
		BTN_MM.setBounds(20, 65, 100, 25);
		BTN_MM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_BrwBook.dispose();
				OOD_GUI_MainMenu OGMM = new OOD_GUI_MainMenu();
			}
		});
		P2.add(BTN_MM);
		
		BTN_ClrFM = new JButton("Main Menu");
		BTN_ClrFM.setBounds(20, 105, 100, 25);
		BTN_ClrFM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clr_Fm();
			}
		});
		P2.add(BTN_ClrFM);
		
		JButton BTN_ExtendDuration = new JButton("Extend Duration");
		BTN_ExtendDuration.setBounds(181, 26, 100, 25);
		P2.add(BTN_ExtendDuration);
		
		L2 = new JLabel("Member ID");
		L2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L2.setBounds(60, 25, 100, 25);
		P1.add(L2);
		L2.setHorizontalAlignment(SwingConstants.CENTER);
		
		L3 = new JLabel("Member Name");
		L3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L3.setHorizontalAlignment(SwingConstants.CENTER);
		L3.setBounds(60, 65, 100, 25);
		P1.add(L3);
		
		L4 = new JLabel("Book ID");
		L4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L4.setBounds(60, 105, 100, 25);
		P1.add(L4);
		L4.setHorizontalAlignment(SwingConstants.CENTER);
		
		L5 = new JLabel("Book Name");
		L5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L5.setHorizontalAlignment(SwingConstants.CENTER);
		L5.setBounds(60, 145, 100, 25);
		P1.add(L5);
		
		L6 = new JLabel("Borrow Date");
		L6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L6.setHorizontalAlignment(SwingConstants.CENTER);
		L6.setBounds(60, 185, 100, 25);
		P1.add(L6);
		
		L7 = new JLabel("Return Date");
		L7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L7.setHorizontalAlignment(SwingConstants.CENTER);
		L7.setBounds(60, 225, 100, 25);
		P1.add(L7);

		FRM_BrwBook.setVisible(true);
		Set_Dates();
	}
	
	public void Set_Dates() {
		Calendar CLD = Calendar.getInstance();
		
		CLD.setTime(Cur_Date);
		CLD.add(Calendar.DAY_OF_MONTH, 7);
		Next_Date.setTime(CLD.getTime().getTime());
		
		String Now = Date_Format.format(Cur_Date);
		String Ltr = Date_Format.format(Next_Date);
		
		TF_CurDate.setText(Now);
		TF_RetDate.setText(Ltr);
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
	
	public void Check_Fields() {
		
	}
	
	public void Check_Duration() {
		String Query = "SELECT * FROM";
		try {
			Connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Extend_Duration() {
		
	}
	
	public void Check_Book() {
		String Query = "SELECT * FROM books WHERE Book_ID = ?";
		int B_ID = Integer.parseInt(TF_BookID.getText());
		
		try {					
			Connect();			
			ps = conn.prepareStatement(Query);
			ps.setInt(1, B_ID);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String Book_Name = rs.getString("Book_Name");
				TF_BookName.setText(Book_Name);
				Verification_Book = 1;
			} else {
				JOptionPane.showMessageDialog(null, "Book does not exists.");
				TF_BookName.setText("");
				Verification_Book = 0;
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Check_Mem() {
		String Query = "SELECT * FROM member WHERE Member_ID = ?";
		int M_ID = Integer.parseInt(TF_MemID.getText());
		
		try {					
			Connect();			
			ps = conn.prepareStatement(Query);
			ps.setInt(1, M_ID);
			rs = ps.executeQuery();
					
			if (rs.next()) {
				String Mem_Name = rs.getString("Member_Name");
				TF_MemName.setText(Mem_Name);
				Verification_Mem = 1;
			} else {
				JOptionPane.showMessageDialog(null, "Member does not exist.");
				TF_MemName.setText("");
				Verification_Mem = 0;
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Borrow() {
		String Query = "INSERT INTO borrowing_record (Member_ID, Book_ID, Borrow_Date, Return_Date, Borrow_Status, Times_Extended, Borrow_Exceed, Days_Exceeded)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					
		int Member_ID = Integer.parseInt(TF_MemID.getText());
		int Book_ID = Integer.parseInt(TF_BookID.getText());
		
		try {
			Connect();
			ps = conn.prepareStatement(Query);
			ps.setInt(1, Member_ID);
			ps.setInt(2, Book_ID);
			ps.setTimestamp(3, Cur_Date);
			ps.setTimestamp(4, Next_Date);
			ps.setString(5, "B");
			ps.setInt(6, 0);
			ps.setString(7, "N");
			ps.setInt(8, 0);
			
			ps.execute();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void Clr_Fm() {
		TF_MemID.setText("");
		TF_MemName.setText("");
		TF_BookID.setText("");
		TF_BookName.setText("");
		TF_CurDate.setText("");
		TF_RetDate.setText("");
	}
}
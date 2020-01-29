package ood_proj;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings( { "unused", "unchecked", "rawtypes" } )

public class OOD_GUI_Mem_Pay {
	Connection conn;
	PreparedStatement ps, ps_2;
	ResultSet rs;
	ResultSetMetaData rsmd;
	
	private JFrame FRM_PayMem;
	private JTextField TF_MemID;
	private JTextField TF_MemName;
	private JTextField TF_MemAmt;
	private JTextField TF_RnwDt;
	private JButton BTN_PayMem;
	private JButton BTN_MM;
	private JComboBox Combo_MemType;
	private JLabel L1;
	private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	private JLabel L5;
	private JLabel L6;
	private JPanel P1;
	private JPanel P2;
	
	java.sql.Date Cur_Date = new java.sql.Date(new java.util.Date().getTime());
	
	SimpleDateFormat DTF = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		new OOD_GUI_Mem_Pay();
	}

	public OOD_GUI_Mem_Pay() {
		FRM_PayMem = new JFrame("Library System - Membership Payment");
		FRM_PayMem.setBounds(100, 100, 1200, 500);
		FRM_PayMem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_PayMem.getContentPane().setLayout(null);
		
		JPanel P1 = new JPanel();
		P1.setLayout(null);
		P1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Membership Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		P1.setBounds(10, 40, 280, 260);
		FRM_PayMem.getContentPane().add(P1);
		
		L1 = new JLabel("Membership Payment Form");
		L1.setHorizontalAlignment(SwingConstants.CENTER);
		L1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L1.setBounds(10, 10, 280, 25);
		FRM_PayMem.getContentPane().add(L1);
		
		L2 = new JLabel("Member ID");
		L2.setHorizontalAlignment(SwingConstants.CENTER);
		L2.setBounds(60, 25, 100, 25);
		P1.add(L2);
		
		L3 = new JLabel("Member Name");
		L3.setHorizontalAlignment(SwingConstants.CENTER);
		L3.setBounds(60, 65, 100, 25);
		P1.add(L3);
		
		L4 = new JLabel("Membership Type");
		L4.setHorizontalAlignment(SwingConstants.CENTER);
		L4.setBounds(60, 105, 100, 25);
		P1.add(L4);
		
		L5 = new JLabel("Membership Amount");
		L5.setHorizontalAlignment(SwingConstants.CENTER);
		L5.setBounds(40, 145, 120, 25);
		P1.add(L5);
		
		L6 = new JLabel("Renew Date");
		L6.setHorizontalAlignment(SwingConstants.CENTER);
		L6.setBounds(60, 185, 100, 25);
		P1.add(L6);
		
		TF_MemID = new JTextField();
		TF_MemID.setBounds(170, 25, 100, 25);
		TF_MemID.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				Check_Membership();
			}
		});
		TF_MemID.setColumns(10);
		P1.add(TF_MemID);
		
		TF_MemName = new JTextField();
		TF_MemName.setBounds(170, 65, 100, 25);
		TF_MemName.setColumns(10);
		P1.add(TF_MemName);
		
		TF_MemAmt = new JTextField();
		TF_MemAmt.setColumns(10);
		TF_MemAmt.setBounds(170, 145, 100, 25);
		P1.add(TF_MemAmt);
		
		TF_RnwDt = new JTextField();
		TF_RnwDt.setColumns(10);
		TF_RnwDt.setBounds(170, 185, 100, 25);
		P1.add(TF_RnwDt);
		
		String Mem_Type[] = { "Full", "Half" };
		Combo_MemType = new JComboBox(Mem_Type);
		Combo_MemType.setBounds(170, 105, 100, 25);
		Combo_MemType.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }
			public void focusLost(FocusEvent e) {
				Check_Membership();
			}
		});
		P1.add(Combo_MemType);
		
		P2 = new JPanel();
		P2.setLayout(null);
		P2.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		P2.setBounds(10, 310, 280, 140);
		FRM_PayMem.getContentPane().add(P2);
		
		BTN_PayMem = new JButton("Pay Membership");
		BTN_PayMem.setBounds(20, 25, 100, 25);
		P2.add(BTN_PayMem);
		
		BTN_MM = new JButton("Main Menu");
		BTN_MM.setBounds(20, 105, 100, 25);
		BTN_MM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_PayMem.dispose();
				OOD_GUI_MainMenu OGMM = new OOD_GUI_MainMenu();
			}
		});
		P2.add(BTN_MM);
		
		FRM_PayMem.setVisible(true);
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
	
	public void Check_Membership() {
		String Query = "SELECT * FROM member WHERE Member_ID = ?";
		int M_ID = Integer.parseInt(TF_MemID.getText());
		
		Calendar CLD_Cur = Calendar.getInstance();
		Calendar CLD_DB = Calendar.getInstance();
		
		CLD_Cur.setTime(Cur_Date);
		
		try {
			Connect();
			ps = conn.prepareStatement(Query);
			ps.setInt(1, M_ID);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				java.sql.Date Existing_Membership_Date = rs.getDate("Membership_Renewed");
				String Mem_Name = rs.getString("Member_Name");
				CLD_DB.setTime(Existing_Membership_Date);	
				TF_MemName.setText(Mem_Name);
				
				if (CLD_Cur.get(Calendar.YEAR) == CLD_DB.get(Calendar.YEAR) 
						&& CLD_Cur.get(Calendar.MONTH) == CLD_DB.get(Calendar.MONTH)
						&& CLD_Cur.get(Calendar.DAY_OF_MONTH) == CLD_DB.get(Calendar.DAY_OF_MONTH)) {
					JOptionPane.showMessageDialog(null, "Membership not expired.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Member does not exist.");
				TF_MemID.setText("");
				TF_MemName.setText("");
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Pay_Mem() {
		String Query = "UPDATE member SET Membership_Renewed = ?, Membership_Type = ? WHERE Member_ID = ?";
		String Query_2 = "INSERT INTO payment_record (Member_ID, Payment_Date) VALUES (?, ?)";
				
		int M_ID = Integer.parseInt(TF_MemID.getText());
		String Mem_Type = String.valueOf(Combo_MemType.getSelectedItem());
		
		try {
			Connect();
			ps = conn.prepareStatement(Query);
			ps_2 = conn.prepareStatement(Query_2);
			
			ps.setDate(1, Cur_Date);
			ps.setString(2, Mem_Type);
			ps.setInt(3, M_ID);
			
			ps_2.setInt(1, M_ID);
			ps_2.setDate(2, Cur_Date);
			
			ps.execute();
			ps_2.execute();
			ps.close();
			ps_2.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package view;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;

@SuppressWarnings( { "unused", "unchecked", "rawtypes" } )

public class V_O_G_Reg_Mem {
	protected static JFrame FRM_RegMem;
	protected static JTable TBL_DispMem;
	protected static JTextField TF_Name, TF_PNum, TF_HAdd, TF_EAdd, TF_ID;
	protected static JButton BTN_DispMem, BTN_RegMem, BTN_ClrFM, BTN_DelMem, BTN_UM, BTN_MM;
	protected static JComboBox Combo_MemType;
	protected static JScrollPane Scrl_Tbl;
	protected static JLabel L1, L2, L3, L4, L5, L6,L7;
	protected static JPanel P1, P2;
	
	protected static DefaultTableModel DTM;
	
	public V_O_G_Reg_Mem() {
		FRM_RegMem = new JFrame("Library System - Register Member");
		FRM_RegMem.setBounds(100, 100, 1600, 680);
		FRM_RegMem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_RegMem.getContentPane().setLayout(null);

		L1 = new JLabel("Member Registration Form");
		L1.setHorizontalAlignment(SwingConstants.CENTER);
		L1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L1.setBounds(10, 10, 280, 25);
		FRM_RegMem.getContentPane().add(L1);
		
		P1 = new JPanel();
		P1.setBorder(new TitledBorder(null, "Member Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		P1.setBounds(10, 40, 280, 260);
		FRM_RegMem.getContentPane().add(P1);
		P1.setLayout(null);

		L3 = new JLabel("Member Name");
		L3.setBounds(60, 65, 100, 25);
		L3.setHorizontalAlignment(SwingConstants.CENTER);
		L3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L3);

		L4 = new JLabel("Member Phone Number");
		L4.setBounds(10, 105, 150, 25);
		L4.setHorizontalAlignment(SwingConstants.CENTER);
		L4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L4);

		L5 = new JLabel("Member Housing Address");
		L5.setBounds(0, 145, 170, 25);
		L5.setHorizontalAlignment(SwingConstants.CENTER);
		L5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L5);

		L6 = new JLabel("Member Email Address");
		L6.setBounds(10, 185, 150, 25);
		L6.setHorizontalAlignment(SwingConstants.CENTER);
		L6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		P1.add(L6);
		
		L7 = new JLabel("Membership Type");
		L7.setHorizontalAlignment(SwingConstants.CENTER);
		L7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L7.setBounds(10, 225, 150, 25);
		P1.add(L7);
		
		TF_ID = new JTextField();
		TF_ID.setEnabled(false);
		TF_ID.setBounds(170, 25, 100, 25);
		P1.add(TF_ID);
		TF_ID.setColumns(10);
		
		TF_Name = new JTextField();
		TF_Name.setBounds(170, 65, 100, 25);
		TF_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_Name.setColumns(10);
		P1.add(TF_Name);
	
		TF_PNum = new JTextField();
		TF_PNum.setBounds(170, 105, 100, 25);
		TF_PNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_PNum.setColumns(10);
		P1.add(TF_PNum);

		TF_HAdd = new JTextField();
		TF_HAdd.setBounds(170, 145, 100, 25);	
		TF_HAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_HAdd.setColumns(10);
		P1.add(TF_HAdd);
		
		TF_EAdd = new JTextField();
		TF_EAdd.setBounds(170, 185, 100, 25);
		TF_EAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_EAdd.setColumns(10);
		P1.add(TF_EAdd);
		
		String Mem_Type[] = { "Full", "Half" };
		Combo_MemType = new JComboBox(Mem_Type);
		Combo_MemType.setBounds(170, 225, 100, 25);
		P1.add(Combo_MemType);
				
		L2 = new JLabel("Member ID");
		L2.setHorizontalAlignment(SwingConstants.CENTER);
		L2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L2.setBounds(60, 25, 100, 25);
		P1.add(L2);
		
		P2 = new JPanel();
		P2.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		P2.setBounds(10, 310, 280, 260);
		FRM_RegMem.getContentPane().add(P2);
		P2.setLayout(null);

		BTN_DispMem = new JButton("Refresh Table");
		BTN_DispMem.setBounds(10, 25, 260, 25);
		P2.add(BTN_DispMem);

		BTN_RegMem = new JButton("Register Member");
		BTN_RegMem.setBounds(10, 105, 260, 25);
		P2.add(BTN_RegMem);
		
		BTN_ClrFM = new JButton("Clear Form");
		BTN_ClrFM.setBounds(10, 65, 260, 25);
		P2.add(BTN_ClrFM);

		BTN_UM = new JButton("Update Member");
		BTN_UM.setEnabled(false);
		BTN_UM.setBounds(10, 185, 260, 25);
		P2.add(BTN_UM);
		
		BTN_DelMem = new JButton("Delete Member");
		BTN_DelMem.setEnabled(false);
		BTN_DelMem.setBounds(10, 145, 260, 25);
		P2.add(BTN_DelMem);
		
		BTN_MM = new JButton("Main Menu");
		BTN_MM.setBounds(10, 225, 260, 25);
		P2.add(BTN_MM);
		
		FRM_RegMem.setVisible(true);
	}
	
	public static void main(String [] args) {
		new V_O_G_Reg_Mem();
	}
}
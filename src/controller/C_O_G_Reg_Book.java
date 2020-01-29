package controller;

import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import misc.*;
import model.*;
import view.*;

@SuppressWarnings( { "unused" } )

public class C_O_G_Reg_Book extends V_O_G_Reg_Book {
	V_O_G_Reg_Book VOGRB;
	M_O_G_Reg_Book MOGRB;
	
	public C_O_G_Reg_Book() {
		TBL_DispBook = new JTable(M_O_G_Reg_Book.Disp_Book());
		TBL_DispBook.getTableHeader().setReorderingAllowed(false);
		TBL_DispBook.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = TBL_DispBook.getSelectedRow();
				if (row >= 0) {
					TF_ID.setText(String.valueOf(TBL_DispBook.getModel().getValueAt(row, 0)));
					TF_Name.setText(String.valueOf(TBL_DispBook.getModel().getValueAt(row,1)));
					TF_Genre.setText(String.valueOf(TBL_DispBook.getModel().getValueAt(row, 2)));
					TF_Lang.setText(String.valueOf(TBL_DispBook.getModel().getValueAt(row, 3)));
					TF_Author.setText(String.valueOf(TBL_DispBook.getModel().getValueAt(row, 5)));
					TF_Publisher.setText(String.valueOf(TBL_DispBook.getModel().getValueAt(row, 6)));
				}
				BTN_RegBook.setEnabled(false);
				BTN_UB.setEnabled(true);
				BTN_DelBook.setEnabled(true);
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
		});
		SCRL_TBL = new JScrollPane(TBL_DispBook);
		SCRL_TBL.setBounds(300, 10, 1275, 620);
        FRM_RegBook.getContentPane().add(SCRL_TBL);
        
		BTN_ClrFM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Book.Clr_Form();
			}
		});
		
		BTN_RegBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Book.Reg_Book();
			}
		});		
		
		BTN_DispBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Book.Refresh_TBL();
			}
		});
		
		BTN_UB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Book.Updt_Book();
			}
		});
		
		BTN_MM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRM_RegBook.dispose();
				V_O_G_Main_Menu OGMM = new V_O_G_Main_Menu();
			}
		});
		
		TF_Publisher.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				M_O_G_Reg_Book.Check_Book();
			}
		});
	
		TF_Author.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				M_O_G_Reg_Book.Check_Book();
			}
		});
		
		TF_Name.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				M_O_G_Reg_Book.Check_Book();
			}
		});
	}
	
	public static void main(String [] args) {
		new C_O_G_Reg_Book();
	}
}

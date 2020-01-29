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

public class C_O_G_Reg_Mem extends V_O_G_Reg_Mem {
	V_O_G_Reg_Mem VOGRM;
	M_O_G_Reg_Mem MOGRM;
	
	public C_O_G_Reg_Mem() {
		TBL_DispMem = new JTable(M_O_G_Reg_Mem.Disp_Mem());
		TBL_DispMem.getTableHeader().setReorderingAllowed(false);
		TBL_DispMem.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent arg0) {
        		int row = TBL_DispMem.getSelectedRow();
        		if (row >= 0) {
        			TF_ID.setText(String.valueOf(TBL_DispMem.getModel().getValueAt(row, 0)));
		    		TF_Name.setText(String.valueOf(TBL_DispMem.getModel().getValueAt(row, 1)));
		    		TF_PNum.setText(String.valueOf(TBL_DispMem.getModel().getValueAt(row, 2)));
		    		TF_HAdd.setText(String.valueOf(TBL_DispMem.getModel().getValueAt(row, 3)));
		    		TF_EAdd.setText(String.valueOf(TBL_DispMem.getModel().getValueAt(row, 4)));
        		}
        		BTN_RegMem.setEnabled(false);
        		BTN_UM.setEnabled(true);
        		BTN_DelMem.setEnabled(true);
        	}
        	public void mouseReleased(MouseEvent arg0) { }
        	public void mousePressed(MouseEvent arg0) { }
        	public void mouseExited(MouseEvent arg0) { }
        	public void mouseEntered(MouseEvent arg0) { }
        });
        Scrl_Tbl = new JScrollPane(TBL_DispMem);
        Scrl_Tbl.setBounds(300, 10, 1275, 600);
        FRM_RegMem.getContentPane().add(Scrl_Tbl);
		
		TF_Name.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				M_O_G_Reg_Mem.Check_Mem();
			}
		});
		
		BTN_DispMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Mem.Refresh_TBL();
			}
		});
				
		BTN_RegMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Mem.Reg_Mem();
			}
		});
		
		BTN_ClrFM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Mem.Clr_Form();
			}
		});

		BTN_DelMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Mem.Del_Mem();
			}
		});
		
		BTN_UM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_O_G_Reg_Mem.Updt_Mem();
			}
		});
		
		BTN_MM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_RegMem.dispose();
				V_O_G_Main_Menu OGMM = new V_O_G_Main_Menu();
			}
		});
	}
        
	public static void main(String [] args) {
		new C_O_G_Reg_Mem();
	}
}

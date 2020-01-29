package model;

import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import controller.*;
import misc.*;
import model.*;
import view.*;

@SuppressWarnings( { "unused", "serial", "unchecked", "rawtypes" } )

public class M_O_G_Reg_Mem extends C_O_G_Reg_Mem {
	C_O_G_Reg_Mem COGRM;
	
	static Connecting C = new Connecting();
	
	static Connection conn = C.Connect();
	static PreparedStatement ps_0, ps_1, ps_2;
	static ResultSet rs_0, rs_1, rs_2;
	static ResultSetMetaData rsmd_0;
	
	public static TableModel Disp_Mem() {
        Vector<Object> Col_Head = new Vector<Object>();
        Vector<Object> Col_Data = new Vector<Object>();
        
        String Query = "SELECT * FROM member";
        
        Col_Head.add("Member ID");
        Col_Head.add("Member Name");
        Col_Head.add("Member Phone Number");
        Col_Head.add("Member Residential Address");
        Col_Head.add("Member Email Address");
        Col_Head.add("Membership Status");
        Col_Head.add("Member Joined Date");
        Col_Head.add("Membership Type");
        Col_Head.add("Membership Renewed Date");
        
        try {
        	C.Connect();
         
        	ps_0 = conn.prepareStatement(Query);
            rs_0 = ps_0.executeQuery();
            rsmd_0 = rs_0.getMetaData();
            int columns = rsmd_0.getColumnCount();         

            while (rs_0.next()) {
                Vector<Object> row = new Vector<Object>(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs_0.getObject(i));
                }
                Col_Data.addElement(row);
            }

            rs_0.close();
            ps_0.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        DTM = new DefaultTableModel(Col_Data, Col_Head) {

			public Class getColumnClass(int column) {
                for (int row = 0; row < getRowCount(); row++) {
                    Object o = getValueAt(row, column);
                    if (o != null) {
                        return o.getClass();
                    }
                }
                return Object.class;
            }
        };
		return DTM;	
	}
	
	public static void Reg_Mem() {
		String Query_0 = "INSERT INTO member (Member_Name, Member_Phone, Member_Address, Member_Email, Membership_Status, Member_Join, Membership_Type, Membership_Renewed) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String Query_1 = "SELECT * FROM member WHERE Member_Name = ?";
		String Query_2 = "INSERT INTO payment_record (Member_ID, Payment_Date, Payment_Type) VALUES (?, ?, ?)";
		
		java.sql.Timestamp Cur_Date = new java.sql.Timestamp(new java.util.Date().getTime());
		
		String Member_Name = TF_Name.getText();
		String Member_PNum = TF_PNum.getText();
		String Member_Add = TF_HAdd.getText();
		String Member_Email = TF_EAdd.getText();		
		String Member_Type = String.valueOf(Combo_MemType.getSelectedItem());
		
		try {
			C.Connect();
			
			ps_0 = conn.prepareStatement(Query_0);

			ps_0.setString(1, Member_Name);
			ps_0.setString(2, Member_PNum);
			ps_0.setString(3, Member_Add);
			ps_0.setString(4, Member_Email);
			ps_0.setString(5, "Y");
			ps_0.setTimestamp(6, Cur_Date);
			ps_0.setString(7, Member_Type);
			ps_0.setTimestamp(8, Cur_Date);
			ps_0.execute();
			
			ps_1 = conn.prepareStatement(Query_1);
			ps_1.setString(1, Member_Name);
			rs_1 = ps_1.executeQuery();
			
			if (rs_1.next()) {
				int Mem_ID = rs_1.getInt("Member_ID");
				ps_2 = conn.prepareStatement(Query_2);
				ps_2.setInt(1, Mem_ID);
				ps_2.setTimestamp(2, Cur_Date);
				ps_2.setString(3, Member_Type);
				ps_2.execute();

			}
			
			rs_0.close();
			ps_0.close();
			ps_1.close();
			ps_2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Refresh_TBL();
		M_O_G_Reg_Mem.Clr_Form();
	}
	
    public static void Check_Mem() {
    	String Query = "SELECT * FROM member WHERE Member_Name = ?";
    	String M_Name = TF_Name.getText();
    	
    	try {
    		C.Connect();
    		ps_0 = conn.prepareStatement(Query);
    		ps_0.setString(1, M_Name);
    		rs_0 = ps_0.executeQuery();
    		
    		if (rs_0.next()) {
    			String Member_Name = rs_0.getString("Member_Name");
    			JOptionPane.showMessageDialog(null, "Member already exists.");
    			M_O_G_Reg_Mem.Clr_Form();
    		}
    		ps_0.close();
    		rs_0.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void Updt_Mem() {
    	String Query = "UPDATE member SET Member_Name = ?, Member_Phone = ?, Member_Address = ?, Member_Email = ? WHERE Member_ID = ?";
    	
		String Member_Name = TF_Name.getText();
		String Member_PNum = TF_PNum.getText();
		String Member_Add = TF_HAdd.getText();
		String Member_Email = TF_EAdd.getText();
		
		int Member_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		C.Connect();
    	
    		ps_0 = conn.prepareStatement(Query);
    		
    		ps_0.setString(1, Member_Name);
    		ps_0.setString(2, Member_PNum);
    		ps_0.setString(3, Member_Add);
    		ps_0.setString(4, Member_Email);
    		ps_0.setInt(5, Member_ID);
    		
    		ps_0.executeUpdate();
    		ps_0.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_UM.setEnabled(false);
    	BTN_RegMem.setEnabled(true);
    	M_O_G_Reg_Mem.Clr_Form();
    	M_O_G_Reg_Mem.Refresh_TBL();
    }
    
    public static void Del_Mem() {
    	String Query_0 = "DELETE FROM payment_record WHERE Member_ID = ?";
    	String Query_1 = "DELETE FROM borrowing_record WHERE Member_ID = ?";
    	String Query_2 = "DELETE FROM member WHERE Member_ID = ?";
    	
		int Member_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		C.Connect();
    		
    		ps_0 = conn.prepareStatement(Query_0);
    		ps_1 = conn.prepareStatement(Query_1);
    		ps_2 = conn.prepareStatement(Query_2);
    		
    		ps_0.setInt(1, Member_ID);
    		ps_1.setInt(1, Member_ID);
    		ps_2.setInt(1, Member_ID);
    		
    		ps_0.execute();
    		ps_1.execute();
    		ps_2.execute();
    		
    		ps_0.close();
    		ps_1.close();
    		ps_2.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_DelMem.setEnabled(false);
    	BTN_RegMem.setEnabled(true);
    	Refresh_TBL();
    	M_O_G_Reg_Mem.Clr_Form();
    }
    
    public static void Refresh_TBL() {
    	TBL_DispMem.setModel(M_O_G_Reg_Mem.Disp_Mem());
    }
    
	public static void Clr_Form() {
    	TF_ID.setText("");
		TF_Name.setText("");
		TF_PNum.setText("");
		TF_HAdd.setText("");
		TF_EAdd.setText("");
	}
}

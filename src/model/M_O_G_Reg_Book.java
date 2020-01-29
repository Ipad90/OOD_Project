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

public class M_O_G_Reg_Book extends C_O_G_Reg_Book {
	C_O_G_Reg_Book COGRB;
	
	static Connecting C = new Connecting();
	
	static Connection conn = C.Connect();
	static PreparedStatement ps_0, ps_1, ps_2;
	static ResultSet rs_0, rs_1, rs_2;
	static ResultSetMetaData rsmd_0;
	
    public static TableModel Disp_Book() {
        Vector<Object> Col_Head = new Vector<Object>();
        Vector<Object> Col_Data = new Vector<Object>();
        String Query = "SELECT * FROM books";
        
        Col_Head.add("Book ID");
        Col_Head.add("Book Name");
        Col_Head.add("Book Genre");
        Col_Head.add("Book Language");
        Col_Head.add("Book Type");
        Col_Head.add("Book Author");
        Col_Head.add("Book Publisher");
        Col_Head.add("Book Date Added");
        
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
            e.printStackTrace();
        }

        DefaultTableModel DTM = new DefaultTableModel(Col_Data, Col_Head) {

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
	
    public static void Refresh_TBL() {
    	TBL_DispBook.setModel(Disp_Book());
    }
    
    public static void Check_Book() {
    	String Query = "SELECT * FROM books WHERE Book_Name = ? AND Book_Author = ? AND Book_Publisher = ?";
    	
    	String Book_Name = TF_Name.getText();
    	String Book_Author = TF_Author.getText();
		String Book_Publisher = TF_Publisher.getText();	
		
    	try {
    		C.Connect();
    		ps_0 = conn.prepareStatement(Query);
    		ps_0.setString(1, Book_Name);
    		ps_0.setString(2, Book_Author);
    		ps_0.setString(3, Book_Publisher);
    		rs_0 = ps_0.executeQuery();
    		
    		if (rs_0.next()) {
    			JOptionPane.showMessageDialog(null, "Book already exists.");
    			Clr_Form();
    		}
    		ps_0.close();
    		rs_0.close();  		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public static void Reg_Book() {
		String Query_0 = "INSERT INTO books (Book_Name, Book_Genre, Book_Language, Book_Type, Book_Author, Book_Publisher, Book_Added) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		java.sql.Timestamp Cur_Date = new java.sql.Timestamp(new java.util.Date().getTime());
		
		String Book_Name = TF_Name.getText();
		String Book_Genre = TF_Genre.getText();
		String Book_Language = TF_Lang.getText();
		String Book_Type = String.valueOf(Combo_BT.getSelectedItem());
		String Book_Author = TF_Author.getText();
		String Book_Publisher = TF_Publisher.getText();	
		
		try {
			C.Connect();
			ps_0 = conn.prepareStatement(Query_0);
			ps_0.setString(1, Book_Name);
			ps_0.setString(2, Book_Genre);
			ps_0.setString(3, Book_Language);
			ps_0.setString(4, Book_Type);
			ps_0.setString(5, Book_Author);
			ps_0.setString(6, Book_Publisher);
			ps_0.setTimestamp(7, Cur_Date);
			
			ps_0.execute();
			ps_0.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Refresh_TBL();
		Clr_Form();
	}
	
    public static void Updt_Book() {
    	String Query_0 = "UPDATE books SET Book_Name = ?, Book_Genre = ?, Book_Language = ?, Book_Type = ?, Book_Author = ?, Book_Publisher = ? WHERE Member_ID = ?";
    	
		String Book_Name = TF_Name.getText();
		String Book_Genre = TF_Genre.getText();
		String Book_Language = TF_Lang.getText();
		String Book_Type = String.valueOf(Combo_BT.getSelectedItem());
		String Book_Author = TF_Author.getText();
		String Book_Publisher = TF_Publisher.getText();	
		
		int Book_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		C.Connect();
    	
    		ps_0 = conn.prepareStatement(Query_0);
    		
    		ps_0.setString(1, Book_Name);
    		ps_0.setString(2, Book_Genre);
    		ps_0.setString(3, Book_Language);
    		ps_0.setString(4, Book_Type);
    		ps_0.setString(5, Book_Author);
    		ps_0.setString(6, Book_Publisher);
    		ps_0.setInt(7, Book_ID);
    		
    		ps_0.executeUpdate();
    		ps_0.close();    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_UB.setEnabled(false);
    	BTN_RegBook.setEnabled(true);
    	Clr_Form();
    	Refresh_TBL();
    }
    
    public static void Del_Book() {
    	String Query_0 = "DELETE FROM borrowing_record WHERE Book_ID = ?";
    	String Query_1 = "DELETE FROM books WHERE Book_ID = ?";
   	
		int Book_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		C.Connect();
    		
    		ps_0 = conn.prepareStatement(Query_0);
    		ps_1 = conn.prepareStatement(Query_1);
    		
    		ps_0.setInt(1, Book_ID);
    		ps_1.setInt(1, Book_ID);
    		
    		ps_0.execute();
    		ps_1.execute();
    		
    		ps_0.close();
    		ps_1.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_DelBook.setEnabled(false);
    	BTN_RegBook.setEnabled(true);
    	Refresh_TBL();
    	Clr_Form();
    }
    
	public static void Clr_Form() {
		TF_ID.setText("");
		TF_Name.setText("");
		TF_Genre.setText("");
		TF_Lang.setText("");
		TF_Author.setText("");
		TF_Publisher.setText("");
	}
}

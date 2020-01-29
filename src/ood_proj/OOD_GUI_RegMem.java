package ood_proj;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;

@SuppressWarnings( { "unused", "serial", "unchecked", "rawtypes" } )

public class OOD_GUI_RegMem {
	Connection conn;
	PreparedStatement ps_0, ps_1, ps_2;
	ResultSet rs_0, rs_1, rs_2;
	ResultSetMetaData rsmd;
	
	private JFrame FRM_RegMem;
	private JTable TBL_DispMem;
	private JTextField TF_Name, TF_PNum, TF_HAdd, TF_EAdd, TF_ID;
	private JButton BTN_DispMem, BTN_RegMem, BTN_ClrFM, BTN_DelMem, BTN_UM;
	private JComboBox Combo_MemType;
	private JScrollPane Scrl_Tbl;
	private JLabel L1, L2, L3, L4, L5, L6,L7;
	private JPanel P1, P2;
	
	private DefaultTableModel DTM;

	public static void main(String [] args) {
		new OOD_GUI_RegMem();
	}
	
	public OOD_GUI_RegMem() {
		FRM_RegMem = new JFrame("Library System - Register Member");
		FRM_RegMem.setBounds(100, 100, 1600, 680);
		FRM_RegMem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_RegMem.getContentPane().setLayout(null);

		L1 = new JLabel("Member Registration Form");
		L1.setHorizontalAlignment(SwingConstants.CENTER);
		L1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L1.setBounds(10, 10, 280, 25);
		FRM_RegMem.getContentPane().add(L1);
		
		TBL_DispMem = new JTable(Disp_Mem());
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
		TF_Name.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				Check_Mem();
			}
		});
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
		BTN_DispMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Refresh_TBL();
			}
		});
		P2.add(BTN_DispMem);

		BTN_RegMem = new JButton("Register Member");
		BTN_RegMem.setBounds(10, 105, 260, 25);
		BTN_RegMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reg_Mem();
			}
		});
		P2.add(BTN_RegMem);
		
		BTN_ClrFM = new JButton("Clear Form");
		BTN_ClrFM.setBounds(10, 65, 260, 25);
		BTN_ClrFM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clr_Form();
			}
		});
		P2.add(BTN_ClrFM);

		BTN_UM = new JButton("Update Member");
		BTN_UM.setEnabled(false);
		BTN_UM.setBounds(10, 185, 260, 25);
		BTN_UM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Updt_Mem();
			}
		});
		P2.add(BTN_UM);
		
		BTN_DelMem = new JButton("Delete Member");
		BTN_DelMem.setEnabled(false);
		BTN_DelMem.setBounds(10, 145, 260, 25);
		BTN_DelMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Del_Mem();
			}
		});
		P2.add(BTN_DelMem);
	
		JButton BTN_MM = new JButton("Main Menu");
		BTN_MM.setBounds(10, 225, 260, 25);
		BTN_MM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM_RegMem.dispose();
				OOD_GUI_MainMenu OGMM = new OOD_GUI_MainMenu();
			}
		});
		P2.add(BTN_MM);
		
		FRM_RegMem.setVisible(true);
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
	
    public TableModel Disp_Mem() {
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
        	Connect();
         
        	ps_0 = conn.prepareStatement(Query);
            rs_0 = ps_0.executeQuery();
            rsmd = rs_0.getMetaData();
            int columns = rsmd.getColumnCount();         

            while (rs_0.next()) {
                Vector<Object> row = new Vector<Object>(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs_0.getObject(i));
                }
                Col_Data.addElement(row);
            }

            rs_0.close();
            ps_0.close();
            conn.close();
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
    
    public void Refresh_TBL() {
    	TBL_DispMem.setModel(Disp_Mem());
    }
    
    public void Check_Mem() {
    	String Query = "SELECT * FROM member WHERE Member_Name = ?";
    	String M_Name = TF_Name.getText();
    	
    	try {
    		Connect();
    		ps_0 = conn.prepareStatement(Query);
    		ps_0.setString(1, M_Name);
    		rs_0 = ps_0.executeQuery();
    		
    		if (rs_0.next()) {
    			String Member_Name = rs_0.getString("Member_Name");
    			JOptionPane.showMessageDialog(null, "Member already exists.");
    			Clr_Form();
    		}
    		ps_0.close();
    		rs_0.close();
    		conn.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void Reg_Mem() {
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
			Connect();
			
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
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Refresh_TBL();
		Clr_Form();
	}
    
    public void Updt_Mem() {
    	String Query = "UPDATE member SET Member_Name = ?, Member_Phone = ?, Member_Address = ?, Member_Email = ? WHERE Member_ID = ?";
    	
		String Member_Name = TF_Name.getText();
		String Member_PNum = TF_PNum.getText();
		String Member_Add = TF_HAdd.getText();
		String Member_Email = TF_EAdd.getText();
		
		int Member_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		Connect();
    	
    		ps_0 = conn.prepareStatement(Query);
    		
    		ps_0.setString(1, Member_Name);
    		ps_0.setString(2, Member_PNum);
    		ps_0.setString(3, Member_Add);
    		ps_0.setString(4, Member_Email);
    		ps_0.setInt(5, Member_ID);
    		
    		ps_0.executeUpdate();
    		ps_0.close();
    		conn.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_UM.setEnabled(false);
    	BTN_RegMem.setEnabled(true);
    	Clr_Form();
    	Refresh_TBL();
    }
    
    public void Del_Mem() {
    	String Query_0 = "DELETE FROM payment_record WHERE Member_ID = ?";
    	String Query_1 = "DELETE FROM borrowing_record WHERE Member_ID = ?";
    	String Query_2 = "DELETE FROM member WHERE Member_ID = ?";
    	
		int Member_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		Connect();
    		
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
    		conn.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_DelMem.setEnabled(false);
    	BTN_RegMem.setEnabled(true);
    	Refresh_TBL();
    	Clr_Form();
    }
    
    public void Clr_Form() {
    	TF_ID.setText("");
		TF_Name.setText("");
		TF_PNum.setText("");
		TF_HAdd.setText("");
		TF_EAdd.setText("");
	}
}
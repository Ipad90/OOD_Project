package ood_proj;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings( { "unused", "serial", "unchecked", "rawtypes" } )

public class OOD_GUI_RegBook {
	Connection conn;
	PreparedStatement ps_0, ps_1, ps_2;
	ResultSet rs_0, rs_1, rs_2;
	ResultSetMetaData rsmd_0;
	
	private JFrame FRM_RegBook;
	private JTable TBL_DispBook;
	private JTextField TF_ID, TF_Name, TF_Genre, TF_Lang, TF_Author, TF_Publisher;
	private JButton BTN_DispBook, BTN_RegBook, BTN_DelBook, BTN_ClrFM, BTN_UB, BTN_MM;
	private JComboBox Combo_BT;
	private JScrollPane SCRL_TBL;
	private JLabel L1, L2, L3, L4, L5, L6, L7, L8;
	private JPanel P1, P2;

	public static void main(String [] args) {
		new OOD_GUI_RegBook();
	}

	public OOD_GUI_RegBook() {		
		FRM_RegBook = new JFrame("Library System - Book Registration Form");
		FRM_RegBook.setBounds(100, 100, 1600, 680);
		FRM_RegBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRM_RegBook.getContentPane().setLayout(null);

		L1 = new JLabel("Book Registration Form");
		L1.setHorizontalAlignment(SwingConstants.CENTER);
		L1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L1.setBounds(10, 10, 280, 25);
		FRM_RegBook.getContentPane().add(L1);
		
		TBL_DispBook = new JTable(Disp_Book());
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
		TF_Name.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				Check_Book();
			}
		});
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
		TF_Author.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				Check_Book();
			}
		});
		TF_Author.setColumns(10);
		P1.add(TF_Author);
		
		TF_Publisher = new JTextField();
		TF_Publisher.setBounds(170, 265, 100, 25);
		TF_Publisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TF_Publisher.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { }			
			public void focusLost(FocusEvent e) {
				Check_Book();
			}
		});
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
		BTN_DispBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Refresh_TBL();
			}
		});
		P2.add(BTN_DispBook);

		BTN_RegBook = new JButton("Register Book");
		BTN_RegBook.setBounds(10, 105, 260, 25);
		BTN_RegBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reg_Book();
			}
		});
		P2.add(BTN_RegBook);
		
		BTN_ClrFM = new JButton("Clear Form");
		BTN_ClrFM.setBounds(10, 65, 260, 25);
		BTN_ClrFM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clr_Form();
			}
		});
		P2.add(BTN_ClrFM);
		
		BTN_DelBook = new JButton("Delete Book");
		BTN_DelBook.setEnabled(false);
		BTN_DelBook.setBounds(10, 145, 260, 25);
		P2.add(BTN_DelBook);

		BTN_UB = new JButton("Update Book");
		BTN_UB.setEnabled(false);
		BTN_UB.setBounds(10, 185, 260, 25);
		BTN_UB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Updt_Book();
			}
		});
		P2.add(BTN_UB);
		
		BTN_MM = new JButton("Main Menu");
		BTN_MM.setBounds(10, 225, 260, 25);
		BTN_MM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRM_RegBook.dispose();
				OOD_GUI_MainMenu OGMM = new OOD_GUI_MainMenu();
			}
		});
		P2.add(BTN_MM);
										
		FRM_RegBook.setVisible(true);
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
	
    public TableModel Disp_Book() {
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
        	Connect();
         
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
            conn.close();
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
	
    public void Refresh_TBL() {
    	TBL_DispBook.setModel(Disp_Book());
    }
    
    public void Check_Book() {
    	String Query = "SELECT * FROM books WHERE Book_Name = ? AND Book_Author = ? AND Book_Publisher = ?";
    	
    	String Book_Name = TF_Name.getText();
    	String Book_Author = TF_Author.getText();
		String Book_Publisher = TF_Publisher.getText();	
		
    	try {
    		Connect();
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
    		conn.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public void Reg_Book() {
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
			Connect();
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
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Refresh_TBL();
		Clr_Form();
	}
	
    public void Updt_Book() {
    	String Query_0 = "UPDATE books SET Book_Name = ?, Book_Genre = ?, Book_Language = ?, Book_Type = ?, Book_Author = ?, Book_Publisher = ? WHERE Member_ID = ?";
    	
		String Book_Name = TF_Name.getText();
		String Book_Genre = TF_Genre.getText();
		String Book_Language = TF_Lang.getText();
		String Book_Type = String.valueOf(Combo_BT.getSelectedItem());
		String Book_Author = TF_Author.getText();
		String Book_Publisher = TF_Publisher.getText();	
		
		int Book_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		Connect();
    	
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
    		conn.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_UB.setEnabled(false);
    	BTN_RegBook.setEnabled(true);
    	Clr_Form();
    	Refresh_TBL();
    }
    
    public void Del_Book() {
    	String Query_0 = "DELETE FROM borrowing_record WHERE Book_ID = ?";
    	String Query_1 = "DELETE FROM books WHERE Book_ID = ?";
   	
		int Book_ID = Integer.parseInt(TF_ID.getText());
		
    	try {
    		Connect();
    		
    		ps_0 = conn.prepareStatement(Query_0);
    		ps_1 = conn.prepareStatement(Query_1);
    		
    		ps_0.setInt(1, Book_ID);
    		ps_1.setInt(1, Book_ID);
    		
    		ps_0.execute();
    		ps_1.execute();
    		
    		ps_0.close();
    		ps_1.close();
    		conn.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	BTN_DelBook.setEnabled(false);
    	BTN_RegBook.setEnabled(true);
    	Refresh_TBL();
    	Clr_Form();
    }
    
	public void Clr_Form() {
		TF_ID.setText("");
		TF_Name.setText("");
		TF_Genre.setText("");
		TF_Lang.setText("");
		TF_Author.setText("");
		TF_Publisher.setText("");
	}
}
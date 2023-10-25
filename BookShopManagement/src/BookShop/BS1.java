package BookShop;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BS1 {

	private JFrame frame;
	private JTextField txtBNAME;
	private JTextField textISBN;
	private JTextField textEDI;
	private JTextField textPRICE;
	private JTable table_1;
	private JTextField textField;
	
	
	public static void main(String[] args) {
		
	
		EventQueue.invokeLater ( new Runnable() 
		{
			public void run()
			{
				try {
					BS1 window = new BS1();
					window.frame.setVisible(true);
					} catch (Exception e) 
					{
					e.printStackTrace();
					}
			}
		}                     );
	}

	/**
	 * Create the application.
	 */
	public BS1() {
		initialize();
		 connect();
		 tableLoad();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textQuantity;
	
		public void connect()
		{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Books","root","q1w2e3r4t5");
			
			}
			catch(Exception e )
			{
				System.out.println(e);
			}
		
		}
		
		public void tableLoad() 
		{
			try 
			{
			pst = con.prepareStatement("select * from BookManager");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 627, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Management");
		lblNewLabel.setBounds(225, 22, 225, 29);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 57, 289, 203);
		panel.setBorder(new TitledBorder(null, "Regitration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setBounds(24, 29, 104, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ISBN Number");
		lblNewLabel_1_1.setBounds(24, 62, 104, 22);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book Edition");
		lblNewLabel_1_2.setBounds(24, 95, 104, 22);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Price");
		lblNewLabel_1_2_1.setBounds(24, 128, 104, 22);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel_1_2_1);
		
		txtBNAME = new JTextField();
		txtBNAME.setBounds(138, 33, 112, 20);
		panel.add(txtBNAME);
		txtBNAME.setColumns(10);
		
		textISBN = new JTextField();
		textISBN.setColumns(10);
		textISBN.setBounds(138, 66, 112, 20);
		panel.add(textISBN);
		
		textEDI = new JTextField();
		textEDI.setColumns(10);
		textEDI.setBounds(138, 99, 112, 20);
		panel.add(textEDI);
		
		textPRICE = new JTextField();
		textPRICE.setColumns(10);
		textPRICE.setBounds(138, 128, 112, 20);
		panel.add(textPRICE);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Quantity");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2_1_2.setBounds(24, 155, 104, 24);
		panel.add(lblNewLabel_1_2_1_2);
		
		textQuantity = new JTextField();
		textQuantity.setColumns(10);
		textQuantity.setBounds(138, 159, 112, 20);
		panel.add(textQuantity);
		
		JButton btnNewButton = new JButton("Clear");
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				 txtBNAME.setText("");
				 textISBN.setText("");
				 textEDI.setText("");
				 textPRICE.setText("");
				 textQuantity.setText("");
				 txtBNAME.requestFocus();

			}
		}							  );
		
		btnNewButton.setBounds(26, 273, 78, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
			String bookName, ISBN, Edition, Price,quantity;
			bookName = txtBNAME.getText();
			ISBN = textISBN.getText();
			Edition = textEDI.getText();
			Price = textPRICE.getText();
			quantity = textQuantity.getText();
			
				try{
					 pst = con.prepareStatement("insert into BookManager (Title,ISBN_Number,Edition,Price,quantity)values(?,?,?,?,?)");   
					 pst.setString(1, bookName);
					 pst.setString(2, ISBN);
					 pst.setString(3, Edition);
					 pst.setString(4, Price);
					 pst.setString(5, quantity);
					 pst.executeUpdate();
					 JOptionPane.showMessageDialog(null, "Details Added!!!");
					 tableLoad();
					 
					 txtBNAME.setText("");
					 textISBN.setText("");
					 textEDI.setText("");
					 textPRICE.setText("");
					 textQuantity.setText("");
					 txtBNAME.requestFocus();
					 
				    
				
				}catch(Exception ex) 
					{
				    ex.printStackTrace();
				    }
				
			}
		});
		btnSave.setBounds(114, 273, 78, 29);
		frame.getContentPane().add(btnSave);
		
		JButton btnClear = new JButton("Exit");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnClear.setBounds(202, 271, 78, 29);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(325, 62, 257, 198);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(26, 313, 265, 55);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) 
			{
			try 
			{	
				String id = textField.getText();
					pst = con.prepareStatement("select Title,ISBN_Number,Edition,Price,Quantity from BookManager where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() ==true ) 
					{
					String Title = rs.getString(1);
					String Isbn = rs.getString(2);
					String edition = rs.getString(3);
					String price = rs.getString(4);
					String Quantity = rs.getString(5);
					
					txtBNAME.setText(Title);
					 textISBN.setText(Isbn);
					 textEDI.setText(edition);
					 textPRICE.setText(price);
					 textQuantity.setText(Quantity);
					 
					 
					}
					else {
						txtBNAME.setText("");
						 textISBN.setText("");
						 textEDI.setText("");
						 textPRICE.setText("");
						 textQuantity.setText("");
						 }
					
					
					
			}catch(Exception exp) 
			{
				exp.printStackTrace();
			}
				
				
				
			}
		});
		
		textField.setBounds(115, 24, 128, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Enter ID:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2_1_1.setBounds(31, 22, 74, 22);
		panel_1.add(lblNewLabel_1_2_1_1);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				String bookId;
				
				
				bookId = textField.getText();
				
					try{
						 pst = con.prepareStatement("Delete from BookManager where id = ?");   
						
						 pst.setString(1, bookId);
						 pst.executeUpdate();
						 JOptionPane.showMessageDialog(null, "Record Deleted!!!");
						 tableLoad();
						 
						 txtBNAME.setText("");
						 textISBN.setText("");
						 textEDI.setText("");
						 textPRICE.setText("");
						 textQuantity.setText("");
						 txtBNAME.requestFocus();
						 
					    
					
					}catch(Exception ex) 
						{
					    ex.printStackTrace();
					    }	
				
			}
		});
		btnNewButton_1.setBounds(337, 302, 99, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
				try{
					String bName, ISBNno, Edi, Pric, qty, BID;
				
				bName = txtBNAME.getText();
				ISBNno = textISBN.getText();
				 Edi = textEDI.getText();
				Pric = textPRICE.getText();
				qty = textQuantity.getText();
				BID = textField.getText();
				
				pst = con.prepareStatement("update BookManager set Title = ?, ISBN_Number = ?, Edition = ?, Price = ?, Quantity = ? where id = ?");
				pst.setString(1, bName);
				pst.setString(2, ISBNno);
				pst.setString(3, Edi);
				pst.setString(4, Pric);
				pst.setString(5, qty);
				pst.setString(6, BID);
				
				pst.executeUpdate();
				tableLoad();
				JOptionPane.showMessageDialog(null, "Record Updated!!!");
				
						}
					catch(Exception ex) 
						{
					    ex.printStackTrace();
					    }	
				
			}
		});
		btnNewButton_2.setBounds(465, 302, 93, 36);
		frame.getContentPane().add(btnNewButton_2);
	}
}

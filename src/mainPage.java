import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.BorderLayout;


import net.proteanit.sql.DbUtils;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class mainPage {

	//private static JPanel content;
	private static JFrame window;
	private static JTable table;
	private static JScrollPane content;
	private static JButton populateTable;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void constructMainPage() {
		window = new JFrame();
		window.setTitle("Content");
		window.setSize(500, 400);
		window.setResizable(false);
		
	//content = new JPanel();
	//content.setLayout(null);

		

		table = new JTable();
		table.setRowSelectionAllowed(false);
		
		content = new JScrollPane(table);
		window.getContentPane().add(content, BorderLayout.CENTER );
		content.setBounds(0, 0, 400, 50);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setLocation(0, 0);
		table.setSize(494, 316);
		
		
		populateTable = new JButton("Add Entry");
		populateTable.setBounds(0, 50, 50, 50);
		populateTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//code goes ehre
				addEntries(e);
			}
		});
		
		//code to constantly show the jtable entries from database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
			String entries = "SELECT * FROM entries";
			PreparedStatement statement = connection.prepareStatement(entries);
			
			//database table selection
			ResultSet rs = statement.executeQuery();
			
			//using rs2xml.jar library to populate the jtable with my database data
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch(Exception exception) {
 			 System.out.println(exception.getMessage());
 		}	
		
		content.setRowHeaderView(populateTable);
		
		window.getContentPane().add(content);
		window.setVisible(true);

	}
	
	
	/*
	 * Method to add entries into the database with user given strings and entries, going to also take user's current date and time and automatically input it into the table
	 * @param ActionEvent event given by another part of the program, in this case a jButton
	 */
	public static void addEntries(java.awt.event.ActionEvent event) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
			String entries = "SELECT * FROM entries";
			PreparedStatement statement = connection.prepareStatement(entries);
			
			//code to add entries
		} 
		 catch(Exception exception) {
 			 System.out.println(exception.getMessage());
 		 }	
	}
}


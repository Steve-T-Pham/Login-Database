import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

  public class Login {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel usernameLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JCheckBox showPassword;
    private static String username;
    private static String password;
    private static JPanel signupScreen;
    private static JLabel signupUsernameLabel;
    private static JLabel signupPasswordLabel;
    private static JLabel signupEmailLabel;
    private static JLabel signupPasswordConfirmLabel;
    private static JTextField signupUsername;
    private static JPasswordField signupPassword;
    private static JTextField signupEmail;
    private static JPasswordField signupPasswordConfirm;
    

    /* MySQL clause words:
     * CREATE DATABASE name; //creates a database
     * DROP DATABASE name; //deletes a database
     * USE name; //tells which database that will be used
     * CREATE TABLE name (columnname1 INT, columnname2 VARCHAR(255); //creates a table with columns and types
     * ALTER TABLE name
     * ADD name VARCHAR(255) NOT NULL; //VARCHAR is string or character array and (length) NOT NULL means that it must have an argument
     * INSERT INTO name (col, col) VALUES ("string") //dont need to account for id column because it auto increments
     * WHERE condition; //lists the condition of what to do the action on
     * FROM name; //labels from which table it will do the action on
     * ----GOOD IDEA-----
     * id INT NOT NULL AUTO_INCREMENT
     * always add an ID column which will distinguish same strings or entries from others
     * PRIMARY KEY(id) tells that the primary key is the id
     * FOREIGN KEY(name_id) REFERENCES table(column) //when you want to reference another table
     * ------------------
     */
    
    
    //need to eventually move each function to its own class and make method calls to make it look cleaner
    //maybe turn this into an eventual bug tracker where users can update information and so on
  public static void main(String[] args){ 
    //FRONT END GUI ======================================
	//LOGIN SCREEN ==========================================================================
    //width, height - size
	//setBounds(x, y, width, height)
    frame = new JFrame();
    panel = new JPanel();
    frame.setSize(300,225);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
   
    //username 
    usernameLabel = new JLabel("Username:");
    panel.setLayout(null);
    usernameLabel.setBounds(10, 20, 80, 25);
    panel.add(usernameLabel);
    
    //password
    //x, y, width, height
    passwordLabel = new JLabel("Password:");
    passwordLabel.setBounds(10, 50, 80, 25);
    panel.add(passwordLabel);
    
    //username field
    userText = new JTextField(20);
    userText.setBounds(100, 20, 165, 25);
    panel.add(userText);
    
    //password field
    passwordText = new JPasswordField();
    passwordText.setBounds(100, 50, 165, 25);
    panel.add(passwordText);
    
    //check box to show password
    showPassword = new JCheckBox("Show Password", false);
    showPassword.setBounds(96, 75, 130, 30);
  
    //checkbox action
    showPassword.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if (showPassword.isSelected() == true)
            passwordText.setEchoChar((char)0);
        else 
          passwordText.setEchoChar('�');
      }
    });
    
    panel.add(showPassword);
    
    //login button
    JButton loginButton = new JButton("Login");
    loginButton.setBounds(100, 110, 165, 25);
    /*
    loginButton.setBackground(Color.CYAN);
    loginButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    */
    //login button action
    panel.add(loginButton);

    //button creates new window if activated
    signupScreen = new JPanel();

    //login button action
    loginButton.addActionListener(new ActionListener(){
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
    	 //query for the given string
 		 try {  
   		  //establishes connection and driver
      		  Class.forName("com.mysql.cj.jdbc.Driver");
      		  //connection is connected by server url, server username, server password
      		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
      		  
      		  //statement to SELECT FROM the whole table
      		  PreparedStatement statement = connection.prepareStatement("SELECT * FROM login WHERE username = ? and password = ?");
      		  statement.setString(1, userText.getText());
      		  statement.setString(2,  passwordText.getText());
      		  ResultSet set = statement.executeQuery();  
      		  if (set.next()) {
      			  System.out.println(userText.getText() + " and " + passwordText.getText() + " found!");
          		  frame.getContentPane().removeAll();
          		  frame.revalidate();
          		  frame.setSize(300,250);
      		  }
      		  else {
      			  JOptionPane.showMessageDialog(frame, "Invalid Username or Password!");
      			  System.out.println("Not found!");
      		  }
    	  }
 		 catch(Exception exception) {
 			 System.out.println(exception.getMessage());
 		 }
      }}
  );
    
    //SIGN UP SCREEN ==========================================================================
    //sign up button
    JButton signupButton = new JButton("Sign Up");
    signupButton.setBounds(100, 140, 165, 25);
    panel.add(signupButton);
    
    //takes you to the signup screen
    signupButton.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent e){
    	        frame.getContentPane().removeAll();
    	        frame.getContentPane().add(signupScreen);
    	        frame.revalidate();
    	        frame.setSize(300,250);
    	        frame.add(signupScreen);
    	   }
    });
    signupScreen.setLayout(null);
    
    //signup username
    signupUsernameLabel = new JLabel("Username:");
    signupUsernameLabel.setBounds(10, 50, 80, 25);
    signupUsername = new JTextField();
    signupUsername.setBounds(100, 50, 165, 25);
    
    //signup email
    signupEmailLabel = new JLabel("Email:");
    signupEmailLabel.setBounds(10, 20, 80, 25);
    signupEmail = new JTextField();
    signupEmail.setBounds(100, 20, 165, 25);
    
    //signup password
    signupPasswordLabel = new JLabel("Password:");
    signupPasswordLabel.setBounds(10, 80, 80, 25);
    signupPassword = new JPasswordField();
    signupPassword.setBounds(100, 80, 165, 25);
    signupPasswordConfirmLabel = new JLabel("Confirm Password:");
    signupPasswordConfirm = new JPasswordField();
    signupPasswordConfirmLabel.setBounds(10, 110, 80, 25);
    signupPasswordConfirm.setBounds(100, 110, 165, 25);
    
    JButton createAccount = new JButton("Create Account");
    createAccount.setBounds(100, 140, 165, 25);
    
    //code to create a new acc and adds it into the database
    createAccount.addActionListener(new ActionListener(){
        @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
        	if (signupPassword.getText().equals(signupPasswordConfirm.getText()) && signupPassword.getText().isBlank() == false && signupUsername.getText().isBlank() == false && signupEmail.getText().isBlank() == false) {
        		 try {  
        		  //sends an email for verification
        		  String code = email.generateCode();
        		  try {
        		  email.sendEmail(signupEmail.getText(), code);
        		  }
        		  catch(Exception exception) {
        			  JOptionPane.showMessageDialog(frame, "Invalid E-Mail Address!");
        		  }
        			
        		  //establishes connection and driver
           		  Class.forName("com.mysql.cj.jdbc.Driver");
           		  //connection is connected by server url, server username, server password
           		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
           			  
           		  Statement statement = connection.createStatement();
           		  
           		  String popup = JOptionPane.showInputDialog(frame, "Enter Verification Code:");
           		  if (popup.contentEquals(code)) {
           			  username = signupUsername.getText();
           			  password = signupPassword.getText(); 
           		  

           			  //in the string goes the MySQL key words, INSERTING INTO class with columns (username,password) with values 'username', 'password' as inputs
           			  String insertion = "INSERT INTO login (username, password) VALUES ('" + username + "', '" + password + "')"; 
           		  
           			  statement.executeUpdate(insertion);
           			  //always make sure to close a statement
           			  System.out.println("Complete!");
           			  statement.close();
           		  }
           		  else 
           			  JOptionPane.showMessageDialog(frame, "Wrong Verification Code: Please retry again.");
           	  }
           	  catch(Exception exception) {
           		  System.out.println(exception.getMessage());
           	  }
        	}
        	else if(signupPassword.getText().isBlank() == true || signupUsername.getText().isBlank() == true || signupEmail.getText().isBlank() == true) {
        		System.out.println("Invalid Username or Password!");
        		JOptionPane.showMessageDialog(frame, "One of the fields is empty!");
        	}
        	else {
        		System.out.println("Passwords do not match!");
    		JOptionPane.showMessageDialog(frame, "Passwords do not match!");
    		}
        }
    });
    
    //Window won't update correctly and seems to be a mess when it swaps back and forth between login and signup screen
    /*
    JButton backToLogin = new JButton("Back");
    backToLogin.setBounds(100, 170, 165, 25);
    backToLogin.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
	        frame.getContentPane().removeAll();
    		frame.add(panel);
	        frame.revalidate();
	      //  frame.setSize(300,250);


    	}
    });
    
    */
    //  signupScreen.add(backToLogin);
    
    
    
    //adds everything to signupscreen
    signupScreen.add(signupEmail);
    signupScreen.add(signupEmailLabel);
    signupScreen.add(signupUsername);
    signupScreen.add(signupUsernameLabel);
    signupScreen.add(signupPassword);
    signupScreen.add(signupPasswordLabel);
    signupScreen.add(signupPasswordConfirm);
    signupScreen.add(signupPasswordConfirmLabel);
    signupScreen.add(createAccount);

    
    frame.setVisible(true);
  }

}
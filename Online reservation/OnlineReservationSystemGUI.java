package rrr;
import java.awt.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;  
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class OnlineReservationSystemGUI extends JFrame{
    
    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    public OnlineReservationSystemGUI() {
        setTitle("Online Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(5, 5, 5, 5);
        
        titleLabel = new JLabel("Welcome TO Online Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gc); 
        usernameLabel = new JLabel("Username:");
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, gc);
        usernameField = new JTextField(10);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, gc);
        passwordLabel = new JLabel("Password:");
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, gc);
        
        passwordField = new JPasswordField(10);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, gc);
        
        loginButton = new JButton("Login");
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(loginButton, gc);
        
        registerButton = new JButton("Register");
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(registerButton, gc);
        
        add(panel);
        setVisible(true);
        // Step 1: Create a login form with username and password fields and a JLabel that serves as a login button
    loginButton.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        // Step 3: Retrieve the username and password values from the login form
        String username = usernameField.getText();
        String password = passwordField.getText();
      try{
    	  Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "tiger");
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
           pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
        	String dbuser = rs.getString("username");
            String dbPassword = rs.getString("password");
            if (dbPassword.equals(password) && dbuser.equals(username)) {
            dispose();
              Home1 ny= new Home1();
               ny.setVisible(true);      
              }
       
        }
        else {
            JOptionPane.showMessageDialog(panel, "INVALID USER");
        }
        rs.close();
        pstmt.close();
        conn.close();
    }
    catch(Exception k){
        System.out.println("error in connection");

    }
    }
});     
    
    registerButton.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            String username = usernameField.getText();
            String password = passwordField.getText();
          try{
        	  Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "tiger");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES ( ?, ?)");
            pstmt.setString(1, username); 
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(panel, "REGISTERED SUCCESSFULLY");
            pstmt.close();
            conn.close();
        }
        catch(Exception k){
            System.out.println("error in connection");
        }
        }
    });     

    }
    
    public static void main(String[] args) {
        OnlineReservationSystemGUI gui = new OnlineReservationSystemGUI();
    }
}
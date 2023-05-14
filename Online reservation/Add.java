package rrr;
import java.awt.Font;
import java.util.Random;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
	class Add extends JFrame {
		private   String pnrString;
		private JLabel titleLabel, nameLabel,phonenumberLabel,trainnumberLabel,trainnameLabel,dateLabel,fromLabel,toLabel;
	    private JTextField nameField,phonenumberField,trainnumberField,trainnameField,toField,dateField,fromField;
	    private JButton submitButton;

	    public Add(){
	    	setTitle("Online Reservation  System");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(1000, 900);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        
	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints gc = new GridBagConstraints();
	        gc.fill = GridBagConstraints.HORIZONTAL;
	        gc.insets = new Insets(5, 5, 5, 5 );
	        titleLabel = new JLabel("Welcome to our Reservation System");
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
	        gc.gridx = 0;
	        gc.gridy = 0;
	        gc.gridwidth = 2;
	        gc.anchor = GridBagConstraints.CENTER;
	        panel.add(titleLabel, gc);
	        
	        nameLabel = new JLabel("Name");
	        gc.gridx = 0;
	        gc.gridy = 1;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(nameLabel, gc);
	        nameField = new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 1;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(nameField, gc);
	        
	        
	        phonenumberLabel = new JLabel("Phone Number");
	        gc.gridx = 0;
	        gc.gridy = 2;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(phonenumberLabel, gc);
	        phonenumberField = new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 2;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(phonenumberField, gc);
	        
	        dateLabel = new JLabel("Date");
	        gc.gridx = 0;
	        gc.gridy = 3;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(dateLabel, gc);
	        dateField = new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 3;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(dateField, gc);
	        
	        
	        fromLabel = new JLabel("From");
	        gc.gridx = 0;
	        gc.gridy = 4;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(fromLabel, gc);
	        fromField= new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 4;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(fromField, gc);
	         
	        
	        toLabel = new JLabel("To");
	        gc.gridx = 0;
	        gc.gridy = 5;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(toLabel, gc);
	        toField = new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 5;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(toField, gc);
	        
	        
	        trainnumberLabel = new JLabel("Train Number");
	        gc.gridx = 0;
	        gc.gridy = 6;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(trainnumberLabel, gc);
	        trainnumberField = new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 6;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(trainnumberField, gc);
	        
	        trainnameLabel = new JLabel("Train Name");
	        gc.gridx = 0;
	        gc.gridy = 7;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(trainnameLabel, gc);
	        trainnameField = new JTextField(10);
	        gc.gridx = 1;
	        gc.gridy = 7;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_START;
	        panel.add(trainnameField, gc);
	        
	        
	        submitButton = new JButton("Reserve");
	        gc.gridx = 1;
	        gc.gridy = 8;
	        gc.gridwidth = 1;
	        gc.anchor = GridBagConstraints.LINE_END;
	        panel.add(submitButton, gc); 
	        add(panel);
	        submitButton.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	           System.out.println("hey");
	          int p;
	          try{
	        	  Class.forName("com.mysql.cj.jdbc.Driver");
	              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "tiger");
	               p=1;
	              while(p==1) {
	            	  Random random = new Random();
	                   int pnr = random.nextInt(899999) + 100000;
	                   pnrString = String.valueOf(pnr);
	                  String query = "SELECT * FROM reserve WHERE pnr = ?";
	                  PreparedStatement statement = conn.prepareStatement(query);
	                  statement.setString(1, pnrString);
                       System.out.print(pnrString);
	                  // Execute the query and check the result set
	                  ResultSet resultSet = statement.executeQuery();
	                  if (resultSet.next()) {
	                     p=1;
	                  } else {
	                      p=0;
	                  }

	              }
	            
	                    String name = nameField.getText();
		                String pno = phonenumberField.getText();
		                String date=dateField.getText();
		                String from=fromField.getText();
		                String to=toField.getText();
		                String tno=trainnumberField.getText();
		                String tname=trainnameField.getText();
		                String sql = "INSERT INTO reserve (name,phonenumber,date,`from`,`to`,trainnumber,trainname,pnr) VALUES (?,?,?,?,?,?,?,?);";	                
		                PreparedStatement pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, name); 
	              pstmt.setString(2, pno);
	              pstmt.setString(3, date);
	              pstmt.setString(4, from);
	              pstmt.setString(5, to);
	              pstmt.setString(6, tno);
	              pstmt.setString(7,tname);
	              pstmt.setString(8,pnrString);
	              pstmt.executeUpdate();
	              JOptionPane.showMessageDialog(panel, "REGISTERED SUCCESSFULLY");
	              pstmt.close();
	              conn.close();
	      }
	      catch(Exception k){
	          System.out.println("error in connection"+k);

	      }
	        setVisible(true);
	        JOptionPane.showMessageDialog(null,"PNR:"+pnrString);
	        
	            }}
	        );    }
	    
	    public static void main(String args[])
		{
			Add obj=new Add();
		}
	 }
package rrr;
import java.sql.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.sql.*;
	import javax.swing.*;
	import javax.swing.border.*;

	public class Cancellation  extends JPanel implements ActionListener {
	    private JLabel labelId;
	    private JTextField textFieldId;
	    private JButton buttonDelete;
	    private JTextArea textArea;

	    private Connection conn;
	    private PreparedStatement pstmtSelectAll;
	    private PreparedStatement pstmtDelete;

	    public Cancellation() {
	        // Initialize the panel layout and border
	        setLayout(new BorderLayout());
	        setBorder(new EmptyBorder(10, 10, 10, 10));
	       
	
	        // Create the components for the panel
	        labelId = new JLabel("Enter the PNR you want to delete:");
	        textFieldId = new JTextField(10);
	        buttonDelete = new JButton("Delete");
	        textArea = new JTextArea(20, 40);
	        textArea.setEditable(false);

	        // Add the components to the panel
	        JPanel topPanel = new JPanel(new FlowLayout());
	        topPanel.add(labelId);
	        topPanel.add(textFieldId);
	        topPanel.add(buttonDelete);
	        add(topPanel, BorderLayout.NORTH);
	        add(new JScrollPane(textArea), BorderLayout.CENTER);
	        buttonDelete.addActionListener(this);

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/mydatabase", "root", "tiger");
	            String sqlSelectAll = "SELECT * FROM reserve";

	            pstmtSelectAll = conn.prepareStatement(sqlSelectAll);

	            // Execute the SELECT statement and retrieve the result set
	            ResultSet rs = pstmtSelectAll.executeQuery();
	            textArea.append("DATE"+"\t"+"FROM"+"\t"+"TO"+"\t"+"Train Number"+"\t"+"Train Name"+"\t"+"PNR NO"+"\n");
	            while (rs.next()) {
	                textArea.append(rs.getString("date") + "\t" + rs.getString("from") + "\t" + rs.getString("to") +"\t" +  rs.getString("trainnumber")+"\t" + rs.getString("trainname")+"\t" + rs.getString("pnr") +"\n");
	            }

	            // Create a DELETE statement to delete a row from the table
	            String sqlDelete = "DELETE FROM reserve WHERE pnr= ?";
	            pstmtDelete = conn.prepareStatement(sqlDelete);

	        } catch (Exception e) {
	          System.out.println("error in connection");
	        }
	    }

	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == buttonDelete) {
	            try {
	                int idToDelete = Integer.parseInt(textFieldId.getText());

	            
	                pstmtDelete.setInt(1, idToDelete);

	                int rowsDeleted = pstmtDelete.executeUpdate();
	                textArea.setText("");
	              
	                ResultSet rsUpdated = pstmtSelectAll.executeQuery();
	                textArea.append("DATE"+"\t"+"FROM"+"\t"+"TO"+"\t"+"Train Number"+"\t"+"Train Name"+"\t"+"PNR NO"+"\n");
	                while (rsUpdated.next()) {
	                    textArea.append(rsUpdated.getString("date") + "\t" + rsUpdated.getString("from") + "\t" + rsUpdated.getString("to") + "\t"+rsUpdated.getString("trainnumber")+"\t"+rsUpdated.getString("trainname")+"\t"+rsUpdated.getString("pnr") +"\n");
	                }

	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }

	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Cancellation");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(new Cancellation());
	        frame.pack();
	        frame.setSize(800, 700);
		     
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }
	}
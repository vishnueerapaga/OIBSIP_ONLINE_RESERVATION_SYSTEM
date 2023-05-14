package rrr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Home1 extends JFrame{
	    public JButton reservationButton;
	    public JButton cancellationButton;
	    public Home1() {
	    	 setTitle("Online Examination System");
	         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         setSize(400, 300);
	         setResizable(false);
	         setLocationRelativeTo(null);
	         
	         JPanel panel = new JPanel(new GridBagLayout());
	         GridBagConstraints gc = new GridBagConstraints();
	         gc.fill = GridBagConstraints.HORIZONTAL;
	         gc.insets = new Insets(5, 5, 5, 5);
	         reservationButton = new JButton("Reservation");
	         gc.gridx = 0;
	         gc.gridy = 3;
	         gc.gridwidth = 1;
	         gc.anchor = GridBagConstraints.LINE_END;
	         panel.add(reservationButton, gc);
	         
	         cancellationButton = new JButton("Cancellation");
	         gc.gridx = 1;
	         gc.gridy = 3;
	         gc.gridwidth = 1;
	         gc.anchor = GridBagConstraints.LINE_START;
	         panel.add(cancellationButton, gc);
	         
	         add(panel);
	         setVisible(true);
	         reservationButton.addActionListener(new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	                dispose();
	                Add ab=new Add();
	                ab.setVisible(true);
	             }
	         });
	         cancellationButton.addActionListener(new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	                dispose();
	                Cancellation  bc=new Cancellation();
	                bc.setVisible(true);
	             }
	         });
	    }

	    public static void main(String[] args) {
	    	Home1 a=new Home1();
	    }
	}

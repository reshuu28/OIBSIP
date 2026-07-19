import javax.swing.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class CancelTicketFrame extends JFrame {
	public CancelTicketFrame() {

	    setTitle("Cancel Ticket");
	    setSize(450, 300);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);

	    JLabel heading = new JLabel("Cancel Ticket");
	    heading.setFont(new Font("Arial", Font.BOLD, 22));
	    heading.setBounds(130, 20, 200, 30);
	    add(heading);

	    JLabel pnrLabel = new JLabel("Enter PNR:");
	    pnrLabel.setBounds(50, 80, 100, 30);
	    add(pnrLabel);

	    JTextField pnrField = new JTextField();
	    pnrField.setBounds(150, 80, 200, 30);
	    add(pnrField);

	    JButton cancelButton = new JButton("Cancel Ticket");
	    cancelButton.setBounds(120, 150, 180, 40);
	    add(cancelButton);
	    
	    cancelButton.addActionListener(new java.awt.event.ActionListener() {

	        @Override
	        public void actionPerformed(java.awt.event.ActionEvent e) {

	            try {

	                Connection con = DBConnection.getConnection();

	                String sql = "UPDATE bookings SET booking_status = ? WHERE pnr = ?";
	                PreparedStatement ps = con.prepareStatement(sql);

	                ps.setString(1, "Cancelled");
	                ps.setLong(2, Long.parseLong(pnrField.getText()));

	                int rows = ps.executeUpdate();

	                if(rows > 0){

	                    JOptionPane.showMessageDialog(
	                            null,
	                            "Ticket Cancelled Successfully!"
	                    );

	                }else{

	                    JOptionPane.showMessageDialog(
	                            null,
	                            "PNR Not Found!"
	                    );
	                }

	            } catch(Exception ex){

	                ex.printStackTrace();
	            }

	        }

	    });

	    setVisible(true);
	}

}

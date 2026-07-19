import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class MyBookingsFrame extends JFrame{
	public MyBookingsFrame() {

	    setTitle("My Bookings");
	    setSize(1000, 500);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(null);
	    String[] columns = {
	    	    "PNR",
	    	    "Passenger",
	    	    "Train",
	    	    "Source",
	    	    "Destination",
	    	    "Date",
	    	    "Class",
	    	    "Status"
	    	};

	    	DefaultTableModel model = new DefaultTableModel(columns, 0);

	    	JTable table = new JTable(model);

	    	JScrollPane scrollPane = new JScrollPane(table);
	    	scrollPane.setBounds(20, 20, 940, 400);

	    	add(scrollPane);
	    	try {

	    	    Connection con = DBConnection.getConnection();

	    	    String sql = "SELECT * FROM bookings";

	    	    PreparedStatement ps = con.prepareStatement(sql);

	    	    ResultSet rs = ps.executeQuery();

	    	    while(rs.next()){

	    	        model.addRow(new Object[]{

	    	            rs.getLong("pnr"),
	    	            rs.getString("passenger_name"),
	    	            rs.getString("train_name"),
	    	            rs.getString("source"),
	    	            rs.getString("destination"),
	    	            rs.getString("journey_date"),
	    	            rs.getString("travel_class"),
	    	            rs.getString("booking_status")

	    	        });

	    	    }

	    	} catch(Exception e){

	    	    e.printStackTrace();
	    	}
	    setVisible(true);
	}

}

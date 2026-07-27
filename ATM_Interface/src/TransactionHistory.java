import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class TransactionHistory extends JFrame {

    JTextArea area;

    public TransactionHistory(String username){

        setTitle("Transaction History");
        setSize(500,400);
        setLocationRelativeTo(null);

        area = new JTextArea();
        area.setEditable(false);

        add(new JScrollPane(area));

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM transactions WHERE username=?");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            area.append("Username\tType\tAmount\n");
            area.append("Username\tType\tAmount\n");
            area.append("------------------------------------------\n");

            while(rs.next()){

            	area.append(
            		    rs.getString("username") + "\t" +
            		    rs.getString("type") + "\t" +
            		    rs.getDouble("amount") + "\t" +
            		    rs.getTimestamp("date_time") + "\n"
            		);

            }
        }
       catch(Exception e){

            e.printStackTrace();

        }

        setVisible(true);

        

}
}
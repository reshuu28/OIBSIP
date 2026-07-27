import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class BalanceFrame extends JFrame {

    public BalanceFrame(String username) {

        setTitle("Check Balance");
        setSize(350,200);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT balance FROM users WHERE username=?");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                double balance = rs.getDouble("balance");

                JLabel lbl = new JLabel("Current Balance : ₹ " + balance);
                lbl.setFont(new Font("Arial",Font.BOLD,18));

                add(lbl);

            }

        } catch(Exception e){

            e.printStackTrace();

        }

        setVisible(true);

    }

}


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class WithdrawFrame extends JFrame implements ActionListener {

	JLabel lblAmount, lblPin;

	JTextField txtAmount;

	JPasswordField txtPin;

	JButton btnWithdraw;

    String username;
    Connection con;

    public WithdrawFrame(String username) {

        this.username = username;

        setTitle("Withdraw Money");
        setSize(350,260);
        setLayout(null);
        setLocationRelativeTo(null);

        lblAmount = new JLabel("Enter Amount");
        lblAmount.setBounds(40,40,100,25);

        txtAmount = new JTextField();
        txtAmount.setBounds(150,40,120,25);
        
        lblPin = new JLabel("Your PIN");
        lblPin.setBounds(40,80,100,25);

        txtPin = new JPasswordField();
        txtPin.setBounds(150,80,120,25);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(100,140,120,35);
        add(lblAmount);
        add(txtAmount);

        add(lblPin);
        add(txtPin);

        add(btnWithdraw);

        btnWithdraw.addActionListener(this);

        con = DBConnection.getConnection();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
        	if(txtAmount.getText().trim().isEmpty()){

        	    JOptionPane.showMessageDialog(this,
        	            "Enter Amount");

        	    return;

        	}
        	String pin = new String(txtPin.getPassword());

        	PreparedStatement pinCheck = con.prepareStatement(
        	        "SELECT * FROM users WHERE username=? AND pin=?");

        	pinCheck.setString(1, username);
        	pinCheck.setString(2, pin);

        	ResultSet pinRs = pinCheck.executeQuery();

        	if(!pinRs.next()){

        	    JOptionPane.showMessageDialog(this,
        	            "Invalid PIN!");

        	    return;

        	}

            double amount = Double.parseDouble(txtAmount.getText());
            if(amount<=0){

                JOptionPane.showMessageDialog(this,
                        "Enter Valid Amount");

                return;

            }

            PreparedStatement ps = con.prepareStatement(
                    "SELECT balance FROM users WHERE username=?");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                double balance = rs.getDouble("balance");

                if(balance >= amount){

                    PreparedStatement ps2 = con.prepareStatement(
                            "UPDATE users SET balance = balance - ? WHERE username=?");

                    ps2.setDouble(1, amount);
                    ps2.setString(2, username);

                    ps2.executeUpdate();

                    PreparedStatement ps3 = con.prepareStatement(
                            "INSERT INTO transactions(username,type,amount) VALUES(?,?,?)");

                    ps3.setString(1, username);
                    ps3.setString(2, "Withdraw");
                    ps3.setDouble(3, amount);

                    ps3.executeUpdate();

                    JOptionPane.showMessageDialog(this,
                            "Withdraw Successful!");

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Insufficient Balance!");

                }

            }

        } 
        catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(this,
                    "Please enter numbers only.");

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }

}


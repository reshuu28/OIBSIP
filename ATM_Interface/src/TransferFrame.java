import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TransferFrame extends JFrame implements ActionListener {

    JLabel lblUser, lblAmount, lblPin;

    JTextField txtUser, txtAmount;

    JPasswordField txtPin;

    JButton btnTransfer;

    Connection con;
    String sender;

    public TransferFrame(String username) {

        sender = username;

        setTitle("Transfer Money");
        setSize(400,320);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblUser = new JLabel("Receiver Username");
        lblUser.setBounds(30,40,130,25);

        txtUser = new JTextField();
        txtUser.setBounds(180,40,150,25);

        lblAmount = new JLabel("Amount");
        lblAmount.setBounds(30,90,130,25);

        txtAmount = new JTextField();
        txtAmount.setBounds(180,90,150,25);

        lblPin = new JLabel("Your PIN");
        lblPin.setBounds(30,140,130,25);

        txtPin = new JPasswordField();
        txtPin.setBounds(180,140,150,25);

        btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(120,200,120,35);

        add(lblUser);
        add(txtUser);
        add(lblAmount);
        add(txtAmount);
        add(lblPin);
        add(txtPin);
        add(btnTransfer);

        btnTransfer.addActionListener(this);

        con = DBConnection.getConnection();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            String receiver = txtUser.getText().trim();
            String pin = new String(txtPin.getPassword());
            if(txtUser.getText().trim().isEmpty() ||
            		   txtAmount.getText().trim().isEmpty() ||
            		   txtPin.getPassword().length==0){

            		    JOptionPane.showMessageDialog(this,
            		            "Please fill all fields.");

            		    return;

            		}
            double amount = Double.parseDouble(txtAmount.getText());
            if(amount<=0){

                JOptionPane.showMessageDialog(this,
                        "Enter Valid Amount");

                return;

            }

            if(sender.equals(receiver)){
                JOptionPane.showMessageDialog(this,
                        "You cannot transfer to yourself!");
                return;
            }

            // PIN Verification
            PreparedStatement pinCheck = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND pin=?");

            pinCheck.setString(1, sender);
            pinCheck.setString(2, pin);

            ResultSet pinRs = pinCheck.executeQuery();

            if(!pinRs.next()){

                JOptionPane.showMessageDialog(this,
                        "Invalid PIN!");
                return;

            }

            // Sender Balance
            PreparedStatement ps = con.prepareStatement(
                    "SELECT balance FROM users WHERE username=?");

            ps.setString(1, sender);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                double senderBalance = rs.getDouble("balance");

                if(senderBalance < amount){

                    JOptionPane.showMessageDialog(this,
                            "Insufficient Balance!");
                    return;

                }

            }

            // Receiver Check
            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT * FROM users WHERE username=?");

            ps2.setString(1, receiver);

            ResultSet rs2 = ps2.executeQuery();

            if(!rs2.next()){

                JOptionPane.showMessageDialog(this,
                        "Receiver not found!");
                return;

            }

            // Deduct Sender Balance
            PreparedStatement ps3 = con.prepareStatement(
                    "UPDATE users SET balance = balance - ? WHERE username=?");

            ps3.setDouble(1, amount);
            ps3.setString(2, sender);
            ps3.executeUpdate();

            // Add Receiver Balance
            PreparedStatement ps4 = con.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE username=?");

            ps4.setDouble(1, amount);
            ps4.setString(2, receiver);
            ps4.executeUpdate();

            // Save Transaction
            PreparedStatement ps5 = con.prepareStatement(
                    "INSERT INTO transactions(username,type,amount) VALUES(?,?,?)");

            ps5.setString(1, sender);
            ps5.setString(2, "Transfer");
            ps5.setDouble(3, amount);

            ps5.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Transfer Successful!");

            dispose();

        } catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(this,
                    "Please enter numbers only.");

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

}
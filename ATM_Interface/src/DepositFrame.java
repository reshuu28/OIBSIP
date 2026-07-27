import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DepositFrame extends JFrame implements ActionListener {

    JLabel lblAmount;
    JTextField txtAmount;
    JButton btnDeposit;

    String username;
    Connection con;

    public DepositFrame(String username) {

        this.username = username;

        setTitle("Deposit Money");
        setSize(350,220);
        setLayout(null);
        setLocationRelativeTo(null);

        lblAmount = new JLabel("Enter Amount");
        lblAmount.setBounds(40,40,100,25);

        txtAmount = new JTextField();
        txtAmount.setBounds(150,40,120,25);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(100,100,120,35);

        add(lblAmount);
        add(txtAmount);
        add(btnDeposit);

        btnDeposit.addActionListener(this);

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

            double amount = Double.parseDouble(txtAmount.getText());
            if(amount<=0){

                JOptionPane.showMessageDialog(this,
                        "Enter Valid Amount");

                return;

            }

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE username=?");

            ps.setDouble(1, amount);
            ps.setString(2, username);

            int i = ps.executeUpdate();

            if(i > 0){

                PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO transactions(username,type,amount) VALUES(?,?,?)");

                ps2.setString(1, username);
                ps2.setString(2, "Deposit");
                ps2.setDouble(3, amount);

                ps2.executeUpdate();

                JOptionPane.showMessageDialog(this,"Deposit Successful!");

                dispose();

            }

        } catch(Exception ex){

            ex.printStackTrace();

        }

    }

}



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class RegisterFrame extends JFrame implements ActionListener {

    JLabel lblTitle, lblName, lblUsername, lblPin, lblBalance;

    JTextField txtName, txtUsername, txtBalance;

    JPasswordField txtPin;

    JButton btnRegister, btnClear;

    Connection con;
    public RegisterFrame() {

        setTitle("ATM Registration");
        setSize(450, 420);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("ATM Registration");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(120, 20, 250, 30);

        lblName = new JLabel("Name");
        lblName.setBounds(50, 80, 100, 25);

        txtName = new JTextField();
        txtName.setBounds(170, 80, 180, 25);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 120, 100, 25);

        txtUsername = new JTextField();
        txtUsername.setBounds(170, 120, 180, 25);

        lblPin = new JLabel("PIN");
        lblPin.setBounds(50, 160, 100, 25);

        txtPin = new JPasswordField();
        txtPin.setBounds(170, 160, 180, 25);

        lblBalance = new JLabel("Initial Balance");
        lblBalance.setBounds(50, 200, 100, 25);

        txtBalance = new JTextField();
        txtBalance.setBounds(170, 200, 180, 25);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(70, 280, 120, 35);

        btnClear = new JButton("Clear");
        btnClear.setBounds(220, 280, 120, 35);

        add(lblTitle);
        add(lblName);
        add(txtName);
        add(lblUsername);
        add(txtUsername);
        add(lblPin);
        add(txtPin);
        add(lblBalance);
        add(txtBalance);
        add(btnRegister);
        add(btnClear);

        btnRegister.addActionListener(this);
        btnClear.addActionListener(this);

        con = DBConnection.getConnection();

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnClear) {
            txtName.setText("");
            txtUsername.setText("");
            txtPin.setText("");
            txtBalance.setText("");
        }

        if (e.getSource() == btnRegister) {
        	if(txtName.getText().trim().isEmpty() ||
        			   txtUsername.getText().trim().isEmpty() ||
        			   txtPin.getText().trim().isEmpty() ||
        			   txtBalance.getText().trim().isEmpty()){

        			    JOptionPane.showMessageDialog(this,"Please fill all fields.");
        			    return;
        			}
        	String name = txtName.getText();
        	String username = txtUsername.getText();
        	String pin = txtPin.getText();
        	double balance = Double.parseDouble(txtBalance.getText());

        	try {
        		PreparedStatement check = con.prepareStatement(
        		        "SELECT * FROM users WHERE username=?");

        		check.setString(1, username);

        		ResultSet rs = check.executeQuery();

        		if(rs.next()){

        		    JOptionPane.showMessageDialog(this,
        		            "Username already exists!");

        		    return;

        		}
        	    String sql = "INSERT INTO users(name,username,pin,balance) VALUES(?,?,?,?)";

        	    PreparedStatement ps = con.prepareStatement(sql);

        	    ps.setString(1, name);
        	    ps.setString(2, username);
        	    ps.setString(3, pin);
        	    ps.setDouble(4, balance);

        	    int i = ps.executeUpdate();

        	    if(i > 0) {

        	        JOptionPane.showMessageDialog(this, "Registration Successful!");
        	        new ATM();
        	        dispose();
        	        txtName.setText("");
        	        txtUsername.setText("");
        	        txtPin.setText("");
        	        txtBalance.setText("");

        	    }

        	}
        	catch(Exception ex) {

        	    ex.printStackTrace();

        	}
        }
    }
}
//public class RegisterFrame {

//}

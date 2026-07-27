import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ATM extends JFrame implements ActionListener {

    JLabel lblTitle, lblUsername, lblPin;

    JTextField txtUsername;

    JPasswordField txtPin;

    JButton btnLogin, btnRegister;

    Connection con;

    public ATM() {

        setTitle("ATM Login");
        setSize(400,350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle=new JLabel("ATM Login");
        lblTitle.setFont(new Font("Arial",Font.BOLD,22));
        lblTitle.setBounds(130,20,200,30);

        lblUsername=new JLabel("Username");
        lblUsername.setBounds(40,90,100,25);

        txtUsername=new JTextField();
        txtUsername.setBounds(150,90,180,25);

        lblPin=new JLabel("PIN");
        lblPin.setBounds(40,140,100,25);

        txtPin=new JPasswordField();
        txtPin.setBounds(150,140,180,25);

        btnLogin=new JButton("Login");
        btnLogin.setBounds(60,220,110,35);

        btnRegister=new JButton("Register");
        btnRegister.setBounds(200,220,110,35);

        add(lblTitle);
        add(lblUsername);
        add(txtUsername);
        add(lblPin);
        add(txtPin);
        add(btnLogin);
        add(btnRegister);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        con=DBConnection.getConnection();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnRegister){

            new RegisterFrame();
            dispose();
        }

        if(e.getSource()==btnLogin){

            try{
            	

                String username=txtUsername.getText();
                String pin=txtPin.getText();
                
                if(username.trim().isEmpty() || pin.trim().isEmpty()){

                    JOptionPane.showMessageDialog(this,
                            "Enter Username and PIN");

                    return;

                }

                PreparedStatement ps=con.prepareStatement(
                        "select * from users where username=? and pin=?");

                ps.setString(1, username);
                ps.setString(2, pin);

                ResultSet rs=ps.executeQuery();

                if(rs.next()){

                    JOptionPane.showMessageDialog(this,"Login Successful");
                    new Dashboard(username);
                    dispose();

                }else{

                    JOptionPane.showMessageDialog(this,"Invalid Username or PIN");

                }

            }catch(Exception ex){

                ex.printStackTrace();

            }

        }

    }

}
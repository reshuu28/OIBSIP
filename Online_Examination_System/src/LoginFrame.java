import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel lblTitle, lblUser, lblPass;
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin, btnRegister;

    Connection con;

    public LoginFrame() {

        setTitle("Online Examination System");
        setSize(400,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("ONLINE EXAMINATION");
        lblTitle.setFont(new Font("Arial",Font.BOLD,18));
        lblTitle.setBounds(70,20,250,30);

        lblUser = new JLabel("Username");
        lblUser.setBounds(50,80,100,25);

        txtUser = new JTextField();
        txtUser.setBounds(150,80,150,25);

        lblPass = new JLabel("Password");
        lblPass.setBounds(50,130,100,25);

        txtPass = new JPasswordField();
        txtPass.setBounds(150,130,150,25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(60,190,120,35);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(210,190,120,35);

        add(lblTitle);
        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnLogin);
        add(btnRegister);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        con = DBConnection.getConnection();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnRegister){

            new RegisterFrame();
            dispose();
            return;

        }

        if(e.getSource()==btnLogin){

            try {

                String username = txtUser.getText();
                String password = String.valueOf(txtPass.getPassword());

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM users WHERE username=? AND password=?");

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){

                    JOptionPane.showMessageDialog(this,
                            "Login Successful");

                    new Dashboard(username);

                    dispose();

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Invalid Username or Password");

                }

            } catch(Exception ex){

                ex.printStackTrace();

            }

        }

    }

}
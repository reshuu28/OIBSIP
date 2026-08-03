import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class RegisterFrame extends JFrame implements ActionListener {

    JLabel lblTitle,lblName,lblUser,lblPass,lblConfirm;

    JTextField txtName,txtUser;

    JPasswordField txtPass,txtConfirm;

    JButton btnRegister,btnBack;

    Connection con;

    public RegisterFrame(){

        setTitle("User Registration");
        setSize(420,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle=new JLabel("USER REGISTRATION");
        lblTitle.setFont(new Font("Arial",Font.BOLD,20));
        lblTitle.setBounds(90,20,250,30);

        lblName=new JLabel("Name");
        lblName.setBounds(40,80,100,25);

        txtName=new JTextField();
        txtName.setBounds(160,80,180,25);

        lblUser=new JLabel("Username");
        lblUser.setBounds(40,120,100,25);

        txtUser=new JTextField();
        txtUser.setBounds(160,120,180,25);

        lblPass=new JLabel("Password");
        lblPass.setBounds(40,160,100,25);

        txtPass=new JPasswordField();
        txtPass.setBounds(160,160,180,25);

        lblConfirm=new JLabel("Confirm Password");
        lblConfirm.setBounds(40,200,120,25);

        txtConfirm=new JPasswordField();
        txtConfirm.setBounds(160,200,180,25);

        btnRegister=new JButton("Register");
        btnRegister.setBounds(60,280,120,35);

        btnBack=new JButton("Back");
        btnBack.setBounds(220,280,120,35);

        add(lblTitle);
        add(lblName);
        add(txtName);
        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(lblConfirm);
        add(txtConfirm);
        add(btnRegister);
        add(btnBack);

        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);

        con=DBConnection.getConnection();

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==btnBack){

            new LoginFrame();

            dispose();

        }

        if(e.getSource()==btnRegister){

            try{

                String name=txtName.getText();
                String username=txtUser.getText();
                String password=String.valueOf(txtPass.getPassword());
                String confirm=String.valueOf(txtConfirm.getPassword());

                if(!password.equals(confirm)){

                    JOptionPane.showMessageDialog(this,
                            "Passwords do not match!");

                    return;

                }

                PreparedStatement ps=con.prepareStatement(
                        "INSERT INTO users(name,username,password) VALUES(?,?,?)");

                ps.setString(1,name);
                ps.setString(2,username);
                ps.setString(3,password);

                int i=ps.executeUpdate();

                if(i>0){

                    JOptionPane.showMessageDialog(this,
                            "Registration Successful!");

                    new LoginFrame();

                    dispose();

                }

            }catch(Exception ex){

                ex.printStackTrace();

            }

        }

    }

}


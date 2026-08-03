import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JLabel lblWelcome;
    JButton btnStart, btnLogout;

    String username;

    public Dashboard(String username){

        this.username = username;

        setTitle("Dashboard");
        setSize(400,250);
        setLayout(null);
        setLocationRelativeTo(null);

        lblWelcome = new JLabel("Welcome " + username);
        lblWelcome.setBounds(120,30,200,30);
        lblWelcome.setFont(new Font("Arial",Font.BOLD,18));

        btnStart = new JButton("Start Exam");
        btnStart.setBounds(120,90,140,35);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(120,140,140,35);

        add(lblWelcome);
        add(btnStart);
        add(btnLogout);

        btnStart.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==btnStart){

            new ExamFrame(username);

            dispose();

        }

        if(e.getSource()==btnLogout){

            new LoginFrame();

            dispose();

        }

    }

}


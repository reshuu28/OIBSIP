import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ResultFrame extends JFrame implements ActionListener {

    JLabel lblResult;
    JButton btnFinish;

    public ResultFrame(String username, int score, int total,String review){

        setTitle("Exam Result");
        setSize(420,380);
        setLayout(null);
        setLocationRelativeTo(null);

        lblResult = new JLabel("Congratulations " + username);
        lblResult.setBounds(70,40,300,30);
        lblResult.setFont(new Font("Arial",Font.BOLD,18));

        JLabel lblScore = new JLabel("Your Score : " + score + " / " + total);
        lblScore.setBounds(120,90,200,30);
        lblScore.setFont(new Font("Arial",Font.PLAIN,16));
        
        JTextArea area = new JTextArea();

        area.setText(review);

        area.setEditable(false);

        JScrollPane sp = new JScrollPane(area);

        sp.setBounds(30,130,320,150);

        add(sp);

        btnFinish = new JButton("Finish");
        btnFinish.setBounds(130,150,120,35);

        add(lblResult);
        add(lblScore);
        add(btnFinish);
        

        btnFinish.addActionListener(this);
        
        Connection con = DBConnection.getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO results(username,score,total) VALUES(?,?,?)");

            ps.setString(1, username);
            ps.setInt(2, score);
            ps.setInt(3, total);

            ps.executeUpdate();

        } catch(Exception ex){

            ex.printStackTrace();

        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        JOptionPane.showMessageDialog(this,
                "Thank You!");

        System.exit(0);

    }

}
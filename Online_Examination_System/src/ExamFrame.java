import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ExamFrame extends JFrame implements ActionListener {

    JLabel lblQuestion;
    JRadioButton r1, r2, r3, r4;
    ButtonGroup bg;
    JButton btnNext;

    String username;

    String questions[] = {
            "Java is developed by?",
            "Which package is used for Swing?",
            "Which keyword is used for inheritance?",
            "Which database are we using?",
            "JVM stands for?"
    };

    String options[][] = {
            {"Microsoft","Oracle","Sun Microsystems","Google"},
            {"java.awt","javax.swing","java.sql","java.io"},
            {"implements","extends","super","this"},
            {"Oracle","MongoDB","MySQL","SQLite"},
            {"Java Virtual Machine","Java Variable Method","Joint Virtual Machine","None"}
    };

    char answers[] = {'C','B','B','C','A'};
    String review = "";

    int index = 0;
    int score = 0;

    public ExamFrame(String username){

        this.username = username;

        setTitle("Online Examination");
        setSize(600,350);
        setLayout(null);
        setLocationRelativeTo(null);

        lblQuestion = new JLabel();
        lblQuestion.setBounds(30,30,500,30);
        lblQuestion.setFont(new Font("Arial",Font.BOLD,16));

        r1 = new JRadioButton();
        r1.setBounds(40,80,400,25);

        r2 = new JRadioButton();
        r2.setBounds(40,120,400,25);

        r3 = new JRadioButton();
        r3.setBounds(40,160,400,25);

        r4 = new JRadioButton();
        r4.setBounds(40,200,400,25);

        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);

        btnNext = new JButton("Next");
        btnNext.setBounds(220,250,120,35);

        add(lblQuestion);
        add(r1);
        add(r2);
        add(r3);
        add(r4);
        add(btnNext);

        btnNext.addActionListener(this);

        loadQuestion();

        setVisible(true);
    }

    void loadQuestion(){

        lblQuestion.setText((index+1)+". "+questions[index]);

        r1.setText(options[index][0]);
        r2.setText(options[index][1]);
        r3.setText(options[index][2]);
        r4.setText(options[index][3]);

        bg.clearSelection();
    }

    void checkAnswer(){

        char ans='X';

        if(r1.isSelected()) ans='A';
        if(r2.isSelected()) ans='B';
        if(r3.isSelected()) ans='C';
        if(r4.isSelected()) ans='D';

        if(ans == answers[index]){

            score++;

            review += "✔ Question " + (index+1) + " : Correct\n\n";

        }else{

            review += "❌ Question " + (index+1)
                    + " : Wrong\n";

            review += "Correct Answer : "
                    + options[index][answers[index]-'A']
                    + "\n\n";

        }
    }

    @Override
    public void actionPerformed(ActionEvent e){

        checkAnswer();

        index++;

        if(index<questions.length){

            loadQuestion();

        }else{

            new ResultFrame(username,score,questions.length,review);

            dispose();

        }

    }

}


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JOptionPane;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Online Reservation System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JLabel heading = new JLabel("Welcome to Online Reservation System");
        heading.setBounds(100, 30, 300, 30);
        add(heading);
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(70, 100, 100, 30);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(170, 100, 180, 30);
        add(userField);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(70, 150, 100, 30);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(170, 150, 180, 30);
        add(passField);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 220, 100, 35);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

              //  System.out.println("Login Button Clicked!");
            	String username = userField.getText();

            	String password = String.valueOf(passField.getPassword());

            	System.out.println(username);
            	System.out.println(password);
            	if (username.isEmpty() && password.isEmpty()) {

            	    JOptionPane.showMessageDialog(
            	            null,
            	            "Please enter Username and Password.",
            	            "Login Error",
            	            JOptionPane.ERROR_MESSAGE
            	    );

            	} else if (username.isEmpty()) {

            	    JOptionPane.showMessageDialog(
            	            null,
            	            "Please enter Username.",
            	            "Login Error",
            	            JOptionPane.ERROR_MESSAGE
            	    );

            	}
            	else if (password.isEmpty()) {

            	    JOptionPane.showMessageDialog(
            	            null,
            	            "Please enter Password.",
            	            "Login Error",
            	            JOptionPane.ERROR_MESSAGE
            	    );

            	}
            	else if (username.equals("admin") && password.equals("admin123")) {

            	    JOptionPane.showMessageDialog(
            	            null,
            	            "Login Successful!"
            	    );
            	    new Dashboard();

            	    dispose();


            	}
            	else {

            	    JOptionPane.showMessageDialog(
            	            null,
            	            "Invalid Username or Password!",
            	            "Login Failed",
            	            JOptionPane.ERROR_MESSAGE
            	    );

            	}
            	
            	}

            

        });
        
        
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        
        setVisible(true);

    }
}


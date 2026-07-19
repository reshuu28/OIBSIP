import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
//import java.awt.event.ActionListener;
public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Dashboard");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        getContentPane().setBackground(new java.awt.Color(230, 245, 255));

        JLabel heading = new JLabel("Welcome to Dashboard");
        heading.setBounds(180, 30, 250, 30);
        add(heading);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        
        heading.setForeground(java.awt.Color.BLUE);
     
        JButton reserveButton = new JButton("🚆 Reserve Ticket");
        reserveButton.setBounds(60, 100, 200, 80);
        add(reserveButton);
        
        reserveButton.setFont(new Font("Arial", Font.BOLD, 16));
        reserveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new ReservationForm();

            }

        });

        JButton cancelButton = new JButton("❌ Cancel Ticket");
        cancelButton.setBounds(320, 100, 200, 80);
        add(cancelButton);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new CancelTicketFrame();

            }
        });

        JButton bookingButton = new JButton("📖 My Bookings");
        bookingButton.setBounds(60, 220, 200, 80);
        add(bookingButton);
        bookingButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new MyBookingsFrame();

            }
        });

        JButton logoutButton = new JButton("🚪 Logout");
        logoutButton.setBounds(320, 220, 200, 80);
        add(logoutButton);
        
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    dispose();      // Dashboard close
                    new LoginFrame();    // Login page open
                }
            }
        });
        JButton aboutButton = new JButton("ℹ About");
        aboutButton.setBounds(190, 340, 200, 50);
        add(aboutButton);

        aboutButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                null,
                "SMART RAILWAY RESERVATION SYSTEM\n\n"
                + "Developed By : Reshmi Gupta\n"
                + "Language : Java\n"
                + "Database : MySQL"
            );

        });
        setVisible(true);
    }
}
//public class Dashboard {

//}

import javax.swing.*;
import java.awt.Font;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentFrame extends JFrame {

    public PaymentFrame(Booking booking) {
    	
    	int amount = 0;

    	if ("General".equals(booking.travelClass)) {
    	    amount = 150;
    	}

    	else if ("Sleeper".equals(booking.travelClass)) {
    	    amount = 350;
    	}
    	else if ("3A".equals(booking.travelClass)) {
    	    amount = 800;
    	}
    	else if ("2A".equals(booking.travelClass)) {
    	    amount = 1200;
    	}
    	else if ("1A".equals(booking.travelClass)) {
    	    amount = 2000;
    	}
        setTitle("Payment");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("Payment:" + amount);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(180, 30, 150, 30);
        add(heading);

        JLabel amountLabel = new JLabel("Amount:"+ booking.amount);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        amountLabel.setBounds(150, 90, 200, 30);
        add(amountLabel);

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(70, 150, 150, 30);
        add(paymentLabel);

        String[] methods = {
                "UPI",
                "Debit Card",
                "Credit Card",
                "Net Banking"
        };

        JComboBox<String> paymentBox = new JComboBox<>(methods);
        paymentBox.setBounds(220, 150, 180, 30);
        add(paymentBox);

        JButton payButton = new JButton("Pay Now");
        payButton.setBounds(170, 240, 150, 40);
        add(payButton);

        payButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                Random random = new Random();
                booking.coach = "S" + (random.nextInt(8) + 1);
                booking.seatNo = random.nextInt(72) + 1;
                int pnr = 100000000 + random.nextInt(900000000);
                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

                String bookingDate = now.format(dateFormat);
                String bookingTime = now.format(timeFormat);

                try {

                    Connection con = DBConnection.getConnection();

                    String sql = "INSERT INTO bookings (pnr, passenger_name, age, gender, passengers, mobile, email, train_no, train_name, source, destination, journey_date, travel_class, quota, payment_method, booking_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement ps = con.prepareStatement(sql);
                    booking.pnr = pnr;
                    booking.bookingDate = bookingDate;
                    booking.bookingTime = bookingTime;

                    ps.setInt(1, pnr);
                    ps.setString(2, booking.passengerName);
                    ps.setInt(3, Integer.parseInt(booking.age));
                    ps.setString(4, booking.gender);
                    ps.setInt(5, Integer.parseInt(booking.passengers));
                    ps.setString(6, booking.mobile);
                    ps.setString(7, booking.email);
                    ps.setString(8, booking.trainNo);
                    ps.setString(9, booking.trainName);
                    ps.setString(10, booking.source);
                    ps.setString(11, booking.destination);
                    ps.setString(12, booking.journeyDate);
                    ps.setString(13, booking.travelClass);
                    ps.setString(14, booking.quota);

                    // Payment method PaymentFrame se lena
                    ps.setString(15, paymentBox.getSelectedItem().toString());

                    ps.setString(16, "Booked");

                    ps.executeUpdate();

                    new TicketReceiptFrame(booking, pnr);

                    dispose();

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            null,
                            "Payment Failed!"
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PaymentFrame(new Booking());
    }
}
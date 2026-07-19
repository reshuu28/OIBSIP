import javax.swing.*;
import java.awt.Font;

public class TicketReceiptFrame extends JFrame {

    public TicketReceiptFrame(Booking booking, int pnr) {

        setTitle("Ticket Receipt");
        setSize(550, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("ONLINE RAILWAY RESERVATION SYSTEM");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(40, 20, 400, 30);
        add(heading);

        JLabel pnrLabel = new JLabel("PNR : " + pnr);
        pnrLabel.setBounds(40, 70, 300, 25);
        add(pnrLabel);

        JLabel nameLabel = new JLabel("Passenger : " + booking.passengerName);
        nameLabel.setBounds(40, 100, 350, 25);
        add(nameLabel);

        JLabel trainLabel = new JLabel("Train : " + booking.trainName);
        trainLabel.setBounds(40, 130, 350, 25);
        add(trainLabel);

        JLabel sourceLabel = new JLabel("From : " + booking.source);
        sourceLabel.setBounds(40, 160, 350, 25);
        add(sourceLabel);

        JLabel destinationLabel = new JLabel("To : " + booking.destination);
        destinationLabel.setBounds(40, 190, 350, 25);
        add(destinationLabel);

        JLabel dateLabel = new JLabel("Journey Date : " + booking.journeyDate);
        dateLabel.setBounds(40, 220, 350, 25);
        add(dateLabel);

        JLabel classLabel = new JLabel("Class : " + booking.travelClass);
        classLabel.setBounds(40, 250, 350, 25);
        add(classLabel);
        
        JLabel passengerLabel = new JLabel("Passengers : " + booking.passengers);
        passengerLabel.setBounds(40, 280, 350, 25);
        add(passengerLabel);
        
        JLabel bookingDateLabel = new JLabel("Booking Date : " + booking.bookingDate);
        bookingDateLabel.setBounds(40, 310, 350, 25);
        add(bookingDateLabel);

        JLabel bookingTimeLabel = new JLabel("Booking Time : " + booking.bookingTime);
        bookingTimeLabel.setBounds(40, 340, 350, 25);
        add(bookingTimeLabel);

        JLabel coachLabel = new JLabel("Coach : " + booking.coach);
        coachLabel.setBounds(40, 370, 350, 25);
        add(coachLabel);

        JLabel seatLabel = new JLabel("Seat No : " + booking.seatNo);
        seatLabel.setBounds(40, 400, 350, 25);
        add(seatLabel);
        
        JLabel amountLabel = new JLabel("Amount : ₹" + booking.amount);
        amountLabel.setBounds(40, 430, 300, 25);
        add(amountLabel);


        JLabel statusLabel = new JLabel("Status : Booked");
        statusLabel.setBounds(40, 460, 350, 25);
        add(statusLabel);

        JLabel message = new JLabel("✔ Happy Journey!");
        message.setFont(new Font("Arial", Font.BOLD, 18));
        message.setBounds(140, 540, 250, 30);
        add(message);
        
        JButton printButton = new JButton("Print Ticket");
        printButton.setBounds(70, 580, 150, 35);
        add(printButton);

        printButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                null,
                "Ticket Printed Successfully!"
            );
        });

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(280, 580, 120, 35);
        add(closeButton);

        closeButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}
//public class TicketReceiptFrame {

//}

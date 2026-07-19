import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class ReservationForm extends JFrame {

    public ReservationForm() {

        setTitle("Smart Railway Reservation System");
        setSize(750, 950);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Ticket Reservation Form");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(180, 20, 350, 30);
        add(heading);

        // Passenger Name
        JLabel nameLabel = new JLabel("Passenger Name:");
        nameLabel.setBounds(80, 80, 150, 30);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(250, 80, 250, 30);
        add(nameField);

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(80, 130, 150, 30);
        add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(250, 130, 250, 30);
        add(ageField);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(80, 180, 150, 30);
        add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setBounds(250, 180, 250, 30);
        add(genderBox);

        // Number of Passengers
        JLabel passengerLabel = new JLabel("Number of Passengers:");
        passengerLabel.setBounds(80, 230, 170, 30);
        add(passengerLabel);

        String[] passengers = {"1", "2", "3", "4", "5", "6"};
        JComboBox<String> passengerBox = new JComboBox<>(passengers);
        passengerBox.setBounds(250, 230, 250, 30);
        add(passengerBox);

        // Mobile Number
        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setBounds(80, 280, 150, 30);
        add(mobileLabel);

        JTextField mobileField = new JTextField();
        mobileField.setBounds(250, 280, 250, 30);
        add(mobileField);

        // Email
        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setBounds(80, 330, 150, 30);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(250, 330, 250, 30);
        add(emailField);
        
     // Train Number
        JLabel trainNoLabel = new JLabel("Train Number:");
        trainNoLabel.setBounds(80, 380, 150, 30);
        add(trainNoLabel);

        JTextField trainNoField = new JTextField();
        trainNoField.setBounds(250, 380, 250, 30);
        add(trainNoField);

        // Train Name
        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(80, 430, 150, 30);
        add(trainNameLabel);

        JTextField trainNameField = new JTextField();
        trainNameField.setBounds(250, 430, 250, 30);
        add(trainNameField);

        // Source
        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(80, 480, 150, 30);
        add(sourceLabel);

        String[] sources = {
        	    "Delhi",
        	    "Mumbai",
        	    "Kolkata",
        	    "Chennai",
        	    "Lucknow",
        	    "Patna",
        	    "Jaipur",
        	    "Bengaluru",
        	    "Hyderabad",
        	    "Bhubaneswar",
        	    "Vellore",
        	    "Sasaram",
        	    "Gaya",
        	};

        	JComboBox<String> sourceBox = new JComboBox<>(sources);
        	sourceBox.setBounds(250, 480, 250, 30);
        	add(sourceBox);

        // Destination
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(80, 530, 150, 30);
        add(destinationLabel);

        String[] destinations = {
        	    "Delhi",
        	    "Mumbai",
        	    "Kolkata",
        	    "Chennai",
        	    "Lucknow",
        	    "Patna",
        	    "Jaipur",
        	    "Bengaluru",
        	    "Hyderabad"
        	};

        	JComboBox<String> destinationBox = new JComboBox<>(destinations);
        	destinationBox.setBounds(250, 530, 250, 30);
        	add(destinationBox);

        // Journey Date
        JLabel dateLabel = new JLabel("Journey Date:");
        dateLabel.setBounds(80, 580, 150, 30);
        add(dateLabel);

        JTextField dateField = new JTextField("DD/MM/YYYY");
        dateField.setBounds(250, 580, 250, 30);
        add(dateField);

        // Class
        JLabel classLabel = new JLabel("Class:");
        classLabel.setBounds(80, 630, 150, 30);
        add(classLabel);

        String[] classes = {
        		"General",
                "Sleeper",
                "3A",
                "3E",
                "2A",
                "1A"
        };

        JComboBox<String> classBox = new JComboBox<>(classes);
        classBox.setBounds(250, 630, 250, 30);
        add(classBox);
        
     // Reservation Quota
        JLabel quotaLabel = new JLabel("Reservation Quota:");
        quotaLabel.setBounds(80, 680, 170, 30);
        add(quotaLabel);

        String[] quotas = {
            "General",
            "Tatkal",
            "Ladies",
            "Senior Citizen"
        };

        JComboBox<String> quotaBox = new JComboBox<>(quotas);
        quotaBox.setBounds(250, 680, 250, 30);
        add(quotaBox);
        
     // Payment Method
        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(80, 730, 170, 30);
        add(paymentLabel);

        String[] payments = {
            "UPI",
            "Debit Card",
            "Credit Card",
            "Net Banking",
            "Cash",
        };

        JComboBox<String> paymentBox = new JComboBox<>(payments);
        paymentBox.setBounds(250, 730, 250, 30);
        add(paymentBox);
        
     // Book Ticket Button
        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBounds(250, 760, 180, 40);
        add(bookButton);
        
        bookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String passengerName = nameField.getText();
                String age = ageField.getText();
                String mobile = mobileField.getText();
                String email = emailField.getText();
                String trainNo = trainNoField.getText();
                String trainName = trainNameField.getText();

                String source = sourceBox.getSelectedItem().toString();
                String destination = destinationBox.getSelectedItem().toString();

                String journeyDate = dateField.getText();
                String gender = genderBox.getSelectedItem().toString();
                String passengers = passengerBox.getSelectedItem().toString();
                String travelClass = classBox.getSelectedItem().toString();
                String quota = quotaBox.getSelectedItem().toString();
                String payment = paymentBox.getSelectedItem().toString();
               
                if(passengerName.isEmpty()){

                    JOptionPane.showMessageDialog(
                        null,
                        "Please Enter Passenger Name!"
                    );

                    return;
                }

                if(age.isEmpty()){

                    JOptionPane.showMessageDialog(
                        null,
                        "Please Enter Age!"
                    );

                    return;
                }
                if(mobile.isEmpty()){

                    JOptionPane.showMessageDialog(
                        null,
                        "Please Enter Mobile Number!"
                    );

                    return;
                }

                if(mobile.length() != 10){

                    JOptionPane.showMessageDialog(
                        null,
                        "Mobile Number must contain 10 digits!"
                    );

                    return;
                }
                if(email.isEmpty()){

                    JOptionPane.showMessageDialog(
                            null,
                            "Please Enter Email ID!"
                    );

                    return;
                }

                if(!email.contains("@") || !email.contains(".")){

                    JOptionPane.showMessageDialog(
                            null,
                            "Please Enter a Valid Email ID!"
                    );

                    return;
                }
                if(trainNo.isEmpty()){

                    JOptionPane.showMessageDialog(
                            null,
                            "Please Enter Train Number!"
                    );

                    return;
                }
                if(trainName.isEmpty()){

                    JOptionPane.showMessageDialog(
                            null,
                            "Please Enter Train Name!"
                    );

                    return;
                }
                if(journeyDate.isEmpty()){

                    JOptionPane.showMessageDialog(
                            null,
                            "Please Enter Journey Date!"
                    );

                    return;
                }
                if(source.equals(destination)){

                    JOptionPane.showMessageDialog(
                            null,
                            "Source and Destination cannot be the same!"
                    );

                    return;
                }

                Booking booking = new Booking();

                booking.passengerName = passengerName;
                booking.age = age;
                booking.gender = gender;
                booking.passengers = passengers;
                booking.mobile = mobile;
                booking.email = email;
                booking.trainNo = trainNo;
                booking.trainName = trainName;
                booking.source = source;
                booking.destination = destination;
                booking.journeyDate = journeyDate;
                booking.travelClass = travelClass;
                booking.quota = quota;
                booking.paymentMethod = payment;
                int fare = 0;

                if (travelClass.equals("General")) {
                    fare = 150;
                } else if (travelClass.equals("Sleeper")) {
                    fare = 350;
                } else if (travelClass.equals("3A")) {
                    fare = 800;
                } else if (travelClass.equals("2A")) {
                    fare = 1200;
                } else if (travelClass.equals("1A")) {
                    fare = 2000;
                }

                booking.amount = fare * Integer.parseInt(passengers);
                // 👇 Data popup SAB validation ke baad aayega
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Passenger : " + passengerName +
                        "\nTrain : " + trainName +
                        "\nFrom : " + source +
                        "\nTo : " + destination +
                        "\nDate : " + journeyDate +
                        "\nClass : " + travelClass +
                        "\n\nDo you want to confirm booking?",
                        "Booking Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if(choice == JOptionPane.YES_OPTION){

                    new PaymentFrame(booking);

                }else{

                    JOptionPane.showMessageDialog(
                            null,
                            "Booking Cancelled!"
                    );
                }
                }             
        });

        setVisible(true);
    }
}
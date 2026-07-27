import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JLabel lblWelcome;

    JButton btnBalance, btnDeposit, btnWithdraw, btnTransfer, btnHistory, btnLogout;

    String username;

    public Dashboard(String username) {

        this.username = username;

        setTitle("ATM Dashboard");
        setSize(500, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblWelcome = new JLabel("Welcome : " + username);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setBounds(130, 20, 250, 30);

        btnBalance = new JButton("Check Balance");
        btnBalance.setBounds(150, 80, 180, 35);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(150, 130, 180, 35);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(150, 180, 180, 35);

        btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(150, 230, 180, 35);

        btnHistory = new JButton("Transaction History");
        btnHistory.setBounds(150, 280, 180, 35);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(150, 330, 180, 35);

        add(lblWelcome);
        add(btnBalance);
        add(btnDeposit);
        add(btnWithdraw);
        add(btnTransfer);
        add(btnHistory);
        add(btnLogout);

        btnBalance.addActionListener(this);
        btnDeposit.addActionListener(this);
        btnWithdraw.addActionListener(this);
        btnTransfer.addActionListener(this);
        btnHistory.addActionListener(this);
        btnLogout.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBalance) {
        	  new BalanceFrame(username);
        }

        if (e.getSource() == btnDeposit) {
        	 new DepositFrame(username);
        }

        if (e.getSource() == btnWithdraw) {
        	   new WithdrawFrame(username);
        }

        if (e.getSource() == btnTransfer) {
        	 new TransferFrame(username);
        }

        if (e.getSource() == btnHistory) {
        	 new TransactionHistory(username);
        }

        if (e.getSource() == btnLogout) {
        	int choice = JOptionPane.showConfirmDialog(
        	        this,
        	        "Do you want to Logout?",
        	        "Logout",
        	        JOptionPane.YES_NO_OPTION);

        	if(choice==JOptionPane.YES_OPTION){

        	    new ATM();
        	    dispose();

        	}
        }

    }

}


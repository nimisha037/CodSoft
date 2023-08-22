import javax.swing.*;
import java.awt.*;

class Acc {
    private double bal;

    public Acc(double initialBal) {
        bal = initialBal;
    }

    public double getBalance() {
        return bal;
    }

    public void deposit(double amt) {
        if (amt > 0) {
            bal += amt;
        }
    }

    public boolean withdraw(double amt) {
        if (amt > 0 && amt <= bal) {
            bal -= amt;
            return true;
        } else {
            return false;
        }
    }
}

class ATMGUI extends JFrame {
    private final Acc userAcc;
    private JLabel balLabel;

    public ATMGUI(Acc account) {
        userAcc = account;

        setTitle("ATM Machine");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        balLabel = new JLabel("Balance: $" + userAcc.getBalance());
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        depositButton.addActionListener(e -> handleDeposit());

        withdrawButton.addActionListener(e -> handleWithdraw());

        exitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(balLabel);
        mainPanel.add(depositButton);
        mainPanel.add(withdrawButton);
        mainPanel.add(exitButton);

        add(mainPanel);
    }

    private void updateBalanceLabel() {
        balLabel.setText("Balance: Rs. " + userAcc.getBalance());
    }

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog("Enter deposit amt:");
        double amt = Double.parseDouble(input);
        userAcc.deposit(amt);
        updateBalanceLabel();
    }

    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog("Enter withdrawal amt:");
        double amt = Double.parseDouble(input);
        if (userAcc.withdraw(amt)) {
            updateBalanceLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds or invalid amt.");
        }
    }
}

public class atm_interface {
    public static void main(String[] args) {
        Acc account = new Acc(1000);
        ATMGUI atmGUI = new ATMGUI(account);
        atmGUI.setVisible(true);
    }
}

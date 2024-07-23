import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    private ATM atm;
    private JFrame frame;
    private JTextField userIdField;
    private JPasswordField pinField;
    private Account currentAccount;

    public ATMGUI() {
        atm = new ATM();
        atm.addAccount(new Account("user1", "1234", 1000));
        atm.addAccount(new Account("user2", "5678", 2000));

        frame = new JFrame("ATM System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/kunjaljoshi/Downloads/istockphoto-1960952508-612x612.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("ATM System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userIdLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(userIdLabel, gbc);

        userIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(userIdField, gbc);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pinLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(pinLabel, gbc);

        pinField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(pinField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBackground(new Color(34, 167, 240));
        loginButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAccount = new Account(userIdField.getText(), new String(pinField.getPassword()), 0); // Dummy account to bypass authentication
                showMainMenu();
            }
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showMainMenu() {
        JFrame menuFrame = new JFrame("ATM Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500, 400);
        menuFrame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/kunjaljoshi/Downloads/istockphoto-1960952508-612x612.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("ATM Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton transactionHistoryButton = new JButton("Transaction History");
        transactionHistoryButton.setFont(new Font("Arial", Font.PLAIN, 16));
        transactionHistoryButton.setBackground(new Color(34, 167, 240));
        transactionHistoryButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(transactionHistoryButton, gbc);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 16));
        withdrawButton.setBackground(new Color(34, 167, 240));
        withdrawButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        menuPanel.add(withdrawButton, gbc);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 16));
        depositButton.setBackground(new Color(34, 167, 240));
        depositButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        menuPanel.add(depositButton, gbc);

        JButton transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 16));
        transferButton.setBackground(new Color(34, 167, 240));
        transferButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        menuPanel.add(transferButton, gbc);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        quitButton.setBackground(new Color(192, 57, 43));
        quitButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        menuPanel.add(quitButton, gbc);

        transactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory(menuFrame);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWithdrawFrame(menuFrame);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepositFrame(menuFrame);
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransferFrame(menuFrame);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                frame.setVisible(true);
            }
        });

        menuFrame.add(menuPanel, BorderLayout.CENTER);
        frame.setVisible(false);
        menuFrame.setVisible(true);
    }

    private void showTransactionHistory(JFrame menuFrame) {
        JFrame historyFrame = new JFrame("Transaction History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historyFrame.setSize(500, 400);
        historyFrame.setLayout(new BorderLayout());

        JPanel historyPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/kunjaljoshi/Downloads/istockphoto-1960952508-612x612.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        historyPanel.add(titleLabel, gbc);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(189, 195, 199));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(textArea);
        historyPanel.add(scrollPane, gbc);

        for (Transaction transaction : currentAccount.getTransactions()) {
            textArea.append(transaction.toString() + "\n");
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 167, 240));
        backButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        historyPanel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyFrame.dispose();
                menuFrame.setVisible(true);
            }
        });

        historyFrame.add(historyPanel, BorderLayout.CENTER);
        menuFrame.setVisible(false);
        historyFrame.setVisible(true);
    }

    private void showWithdrawFrame(JFrame menuFrame) {
        JFrame withdrawFrame = new JFrame("Withdraw");
        withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        withdrawFrame.setSize(500, 400);
        withdrawFrame.setLayout(new BorderLayout());

        JPanel withdrawPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/kunjaljoshi/Downloads/istockphoto-1960952508-612x612.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Withdraw", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        withdrawPanel.add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        withdrawPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        withdrawPanel.add(amountField, gbc);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 16));
        withdrawButton.setBackground(new Color(34, 167, 240));
        withdrawButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        withdrawPanel.add(withdrawButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 167, 240));
        backButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        withdrawPanel.add(backButton, gbc);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (currentAccount.withdraw(amount)) {
                    JOptionPane.showMessageDialog(withdrawFrame, "Withdrawal successful");
                    withdrawFrame.dispose();
                    menuFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(withdrawFrame, "Insufficient funds");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawFrame.dispose();
                menuFrame.setVisible(true);
            }
        });

        withdrawFrame.add(withdrawPanel, BorderLayout.CENTER);
        menuFrame.setVisible(false);
        withdrawFrame.setVisible(true);
    }

    private void showDepositFrame(JFrame menuFrame) {
        JFrame depositFrame = new JFrame("Deposit");
        depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        depositFrame.setSize(500, 400);
        depositFrame.setLayout(new BorderLayout());

        JPanel depositPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/kunjaljoshi/Downloads/istockphoto-1960952508-612x612.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Deposit", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        depositPanel.add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        depositPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        depositPanel.add(amountField, gbc);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 16));
        depositButton.setBackground(new Color(34, 167, 240));
        depositButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        depositPanel.add(depositButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 167, 240));
        backButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        depositPanel.add(backButton, gbc);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                JOptionPane.showMessageDialog(depositFrame, "Deposit successful");
                depositFrame.dispose();
                menuFrame.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositFrame.dispose();
                menuFrame.setVisible(true);
            }
        });

        depositFrame.add(depositPanel, BorderLayout.CENTER);
        menuFrame.setVisible(false);
        depositFrame.setVisible(true);
    }

    private void showTransferFrame(JFrame menuFrame) {
        JFrame transferFrame = new JFrame("Transfer");
        transferFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transferFrame.setSize(500, 400);
        transferFrame.setLayout(new BorderLayout());

        JPanel transferPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/kunjaljoshi/Downloads/istockphoto-1960952508-612x612.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Transfer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        transferPanel.add(titleLabel, gbc);

        JLabel toUserLabel = new JLabel("To User ID:");
        toUserLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        toUserLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        transferPanel.add(toUserLabel, gbc);

        JTextField toUserField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        transferPanel.add(toUserField, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        transferPanel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        transferPanel.add(amountField, gbc);

        JButton transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 16));
        transferButton.setBackground(new Color(34, 167, 240));
        transferButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        transferPanel.add(transferButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(34, 167, 240));
        backButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        transferPanel.add(backButton, gbc);

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toUserId = toUserField.getText();
                double amount = Double.parseDouble(amountField.getText());
                Account toAccount = new Account(toUserId, "", 0); // Dummy account to bypass authentication
                if (currentAccount.transfer(toAccount, amount)) {
                    JOptionPane.showMessageDialog(transferFrame, "Transfer successful");
                    transferFrame.dispose();
                    menuFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(transferFrame, "Insufficient funds");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferFrame.dispose();
                menuFrame.setVisible(true);
            }
        });

        transferFrame.add(transferPanel, BorderLayout.CENTER);
        menuFrame.setVisible(false);
        transferFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}

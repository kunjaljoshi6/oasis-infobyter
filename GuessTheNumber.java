import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumber extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Random random;
    private int numberToGuess;
    private int maxAttempts;
    private int attempts;
    private int score;
    private int round;
    private final int maxRounds = 3;
    private JLabel feedbackLabel;
    private JTextField guessField;
    private String[] backgroundImages;
    private int bgIndex;

    public GuessTheNumber() {
        random = new Random();
        score = 0;
        round = 0;
        bgIndex = 0;
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        backgroundImages = new String[] {
            "/Users/kunjaljoshi/Downloads/360_F_524207725_cDk3moNgO4NYGQpogqLpoOWANpc9vzCF.jpg",
            "/Users/kunjaljoshi/Downloads/360_F_524207725_cDk3moNgO4NYGQpogqLpoOWANpc9vzCF.jpg",
            "/Users/kunjaljoshi/Downloads/360_F_524207725_cDk3moNgO4NYGQpogqLpoOWANpc9vzCF.jpg"
        };

        JPanel welcomePanel = createWelcomePanel();
        mainPanel.add(welcomePanel, "Welcome");

        add(mainPanel);
        setTitle("Guess The Number Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new BackgroundPanel(backgroundImages[bgIndex]);
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Guess The Number Game!", JLabel.CENTER);
        welcomeLabel.setForeground(Color.PINK);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewRound();
                createGamePanel();
                cardLayout.show(mainPanel, "Game");
            }
        });
        panel.add(continueButton, BorderLayout.SOUTH);

        return panel;
    }

    private void createGamePanel() {
        JPanel panel = new BackgroundPanel(backgroundImages[bgIndex]);
        panel.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:", JLabel.CENTER);
        instructionLabel.setForeground(Color.PINK);
        panel.add(instructionLabel, BorderLayout.NORTH);

        guessField = new JTextField();
        panel.add(guessField, BorderLayout.CENTER);

        feedbackLabel = new JLabel("", JLabel.CENTER);
        feedbackLabel.setForeground(Color.PINK);
        panel.add(feedbackLabel, BorderLayout.SOUTH);

        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userGuess;
                try {
                    userGuess = Integer.parseInt(guessField.getText());
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                    return;
                }

                attempts++;

                if (userGuess == numberToGuess) {
                    feedbackLabel.setText("Correct! You've guessed the number.");
                    score += (maxAttempts - attempts + 1) * 10;
                    round++;
                    if (round < maxRounds) {
                        JOptionPane.showMessageDialog(panel, "Round " + round + " complete! Your score: " + score);
                        startNewRound();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Game Over! Your total score: " + score);
                        System.exit(0);
                    }
                } else if (userGuess < numberToGuess) {
                    feedbackLabel.setText("Higher!");
                    JOptionPane.showMessageDialog(panel, "Try a higher number.");
                } else {
                    feedbackLabel.setText("Lower!");
                    JOptionPane.showMessageDialog(panel, "Try a lower number.");
                }

                if (attempts >= maxAttempts) {
                    feedbackLabel.setText("Sorry, you've used all attempts. The number was: " + numberToGuess);
                    round++;
                    if (round < maxRounds) {
                        JOptionPane.showMessageDialog(panel, "Round " + round + " complete! Your score: " + score);
                        startNewRound();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Game Over! Your total score: " + score);
                        System.exit(0);
                    }
                }
            }
        });

        mainPanel.add(panel, "Game");
        cardLayout.show(mainPanel, "Game");
    }

    private void startNewRound() {
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        maxAttempts = 5;
        bgIndex = (bgIndex + 1) % backgroundImages.length; // Change the background for each new round
    }

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String filePath) {
            try {
                backgroundImage = new ImageIcon(filePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumber();
            }
        });
    }
}




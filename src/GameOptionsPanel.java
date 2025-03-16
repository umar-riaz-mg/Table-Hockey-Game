import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOptionsPanel extends JPanel {
    private JTextField player1Field;
    private JTextField player2Field;
    private JTextField playerField;
    private JButton startGameButton;
    private Image backgroundImage;
    private GameFrame gameFrame;

    public GameOptionsPanel(GameFrame frame) {
        setLayout(new GridBagLayout());
        this.gameFrame = frame;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        backgroundImage = new ImageIcon("adil.jpg").getImage();
        JButton oneVsOneButton = new JButton("One vs One");
        JButton computerPlayerButton = new JButton("Computer Player");

        customizeButton(oneVsOneButton);
        customizeButton(computerPlayerButton);

        oneVsOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOneVsOneOptions(frame);
            }
        });

        computerPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showComputerPlayerOptions(frame);
            }
        });

        add(oneVsOneButton, gbc);
        add(computerPlayerButton, gbc);

        player1Field = new JTextField(15);
        player2Field = new JTextField(15);
        playerField = new JTextField(15);

        customizeTextField(player1Field);
        customizeTextField(player2Field);
        customizeTextField(playerField);

        startGameButton = new JButton("Start Game");
        customizeButton(startGameButton);
    }

    private void showOneVsOneOptions(GameFrame frame) {



        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(new NameLabel("Player 1 Name:"), gbc);
        add(player1Field, gbc);
        add(new NameLabel("Player 2 Name:"), gbc);
        add(player2Field, gbc);
        add(startGameButton, gbc);
        createBackButton();
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1Name = player1Field.getText().trim();
                String player2Name = player2Field.getText().trim();
                if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                    frame.loadGame("onevsone", player1Name, player2Name);
                    frame.setNames(player1Name, player2Name);
                    frame.panel.requestFocusInWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter names for both players.");
                }
            }
        }   );



        revalidate();
        repaint();
    }

    private void showComputerPlayerOptions(GameFrame frame) {



        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(new NameLabel("Player Name:"), gbc);
        add(playerField, gbc);
        add(startGameButton, gbc);
        createBackButton();
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerField.getText().trim();
                if (!playerName.isEmpty()) {
                    frame.loadGame("computerplayer", playerName, "Computer");
                    frame.setNames(playerName, "Computer");
                    frame.panel.requestFocusInWindow();
                    frame.panel.requestFocusInWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a name for the player.");
                }
            }
        });

        revalidate();
        repaint();
    }

    private void customizeButton(JButton button) {
        Dimension buttonSize = new Dimension(350, 40);
        button.setPreferredSize(buttonSize);
        button.setFocusPainted(false);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial Black", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    private void customizeTextField(JTextField textField) {
        Dimension textFieldSize = new Dimension(350, 30);
        textField.setPreferredSize(textFieldSize);
        textField.setFont(new Font("New Times Roman", Font.BOLD, 14));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public class NameLabel extends JLabel {
        public NameLabel(String label) {
            setForeground(Color.BLACK);
            setFont(new Font("Arial", Font.BOLD, 16));
            setText(label);
        }
    }

    public void createBackButton()
    {
        // Create and customize the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.BOLD, 24));
        backButton.setBackground(new Color(70, 130, 180)); // Steel blue background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false); // Remove focus border
        backButton.setBorderPainted(false); // Remove button border

        // Set the size and position of the back button
        backButton.setBounds(10, 10, 100, 50); // Adjust position and size as needed

        // Add action listener to the back button
        backButton.addActionListener( e -> {
            gameFrame.getContentPane().removeAll();
            gameFrame.add(new MainMenu(gameFrame));
            gameFrame.revalidate();
            gameFrame.repaint();
            // Implement end game functionality if needed
        });

        // Add the back button to the panel
        this.add(backButton);
    }
}

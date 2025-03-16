import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    static final int SIDE_PANEL_WIDTH = 250;
    static final int SIDE_PANEL_HEIGHT = (int) (1000 * 0.5555);
    private final JLabel player1ScoreLabel;
    private final JLabel player2ScoreLabel;
    private final GamePanel gamePanel;

    public SidePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        setFocusable(true);
        setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, SIDE_PANEL_HEIGHT));
        // setBackground(new Color(100, 65, 164)); // Light gray background

        // Create a vertical layout with some padding
        setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        contentPanel.setBackground(Color.decode("#36454F"));

        // Create scoreboard labels
        JLabel scoreLabel = new JLabel("SCORETABLE");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Larger, bold font
        player1ScoreLabel = new JLabel();
        updatePlayer1Score(0);
        player1ScoreLabel.setFont(new Font("Apostos", Font.BOLD, 20)); // Larger, bold font
        player2ScoreLabel = new JLabel();
        updatePlayer2Score(0);
        player2ScoreLabel.setFont(new Font("Apostos", Font.BOLD, 20)); // Larger, bold font

        // Add labels using BorderLayout for alignment
        JPanel labelPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        labelPanel.setBackground(Color.decode("#BDB76B")); // Match the background color
        labelPanel.add(scoreLabel);
        labelPanel.add(player1ScoreLabel);
        labelPanel.add(player2ScoreLabel);

        // Create buttons with custom styling
        JButton restartButton = createStyledButton("Restart");
        restartButton.addActionListener(e -> {
            gamePanel.getCurrentMatch().getPlayer1().resetScore();
            gamePanel.getCurrentMatch().getPlayer2().resetScore();
            updatePlayer1Score(0);
            updatePlayer2Score(0);
            gamePanel.gameState.newGame(gamePanel.gameFrame.matchType); // Assuming "SinglePlayer" is the match type
            updatePlayer1Score(gamePanel.getCurrentMatch().getPlayer1().getScore());
            updatePlayer2Score(gamePanel.getCurrentMatch().getPlayer2().getScore());
            gamePanel.requestFocusInWindow();
        });

        JButton resumeButton = createStyledButton("Resume");
        resumeButton.addActionListener(e -> {
            gamePanel.gameState.resumeGame();
            gamePanel.requestFocusInWindow();
        });
        // Add action listener for audio button if needed
        JButton mainMenuButton = createStyledButton("Main Menu");
        mainMenuButton.addActionListener(e -> {
            gamePanel.gameFrame.getContentPane().removeAll();
            gamePanel.panelThread.interrupt();
            gamePanel.gameFrame.add(new MainMenu(gamePanel.gameFrame));
            gamePanel.gameFrame.revalidate();
            gamePanel.gameFrame.repaint();
            // Implement end game functionality if needed
        });
        JButton pauseButton = createStyledButton("Pause");
        pauseButton.addActionListener(e -> {
            gamePanel.gameState.pauseGame();
            gamePanel.requestFocusInWindow();
        });


        // Add buttons using a vertical BoxLayout for alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#191970"));

        buttonPanel.add(Box.createVerticalStrut(20)); //
        addEqualSizedButton(buttonPanel, pauseButton);
        addEqualSizedButton(buttonPanel, resumeButton);
        addEqualSizedButton(buttonPanel, restartButton);
        addEqualSizedButton(buttonPanel, mainMenuButton);

        // Add components to contentPanel
        contentPanel.add(labelPanel);
        contentPanel.add(Box.createVerticalGlue()); // Push components to the top
        contentPanel.add(buttonPanel);

        // Add contentPanel to SidePanel
        add(contentPanel, BorderLayout.CENTER);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        button.setBackground(Color.decode("#008B8B")); // Blue background
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        button.setPreferredSize(new Dimension(190, 45)); // Set preferred size
        return button;
    }

    // Helper method to add buttons with equal size
    private void addEqualSizedButton(JPanel panel, JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        button.setMaximumSize(new Dimension(button.getPreferredSize().width, button.getPreferredSize().height));
        panel.add(button);
        panel.add(Box.createVerticalStrut(20)); // Add some vertical spacing
    }

    // Method to update player 1 score
    public void updatePlayer1Score(int score) {
        player1ScoreLabel.setText(gamePanel.gameFrame.getName1() +"     "+ score);
    }

    // Method to update player 2 score
    public void updatePlayer2Score(int score) {
        player2ScoreLabel.setText(gamePanel.gameFrame.getName2()  +"    "+ score);
    }
}

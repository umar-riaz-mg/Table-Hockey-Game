import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private final GameFrame frame;
    private final Image backgroundImage;

    public MainMenu(GameFrame frame) {
        this.frame = frame;
        // Load the background image
        backgroundImage = new ImageIcon("adil.jpg").getImage();
        this.setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton creditsButton = new JButton("Credits");
        JButton newGameButton = new JButton("New Game");
        JButton closeButton = new JButton("Close Game");

        customizeButton(creditsButton);
        customizeButton(newGameButton);
        customizeButton(closeButton);

        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMainMenu();
                // Add code here to load the credits panel
                //JOptionPane.showMessageDialog(frame, "Credits clicked");
                ScrollingPanel creditsPanel = new ScrollingPanel(frame);
                creditsPanel.showcredits();
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMainMenu();
                // Show options panel for new game
                showGameOptionsPanel();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMainMenu();
                frame.dispose();
                System.exit(0);
                // No need to open another panel when Close Game is clicked
            }
        });

        add(creditsButton, gbc);
        add(newGameButton, gbc);
        add(closeButton, gbc);
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

    private void closeMainMenu() {
        frame.getContentPane().remove(this);
        frame.revalidate();
        frame.repaint();
    }

    private void showGameOptionsPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new GameOptionsPanel(frame));
        frame.revalidate();
        frame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

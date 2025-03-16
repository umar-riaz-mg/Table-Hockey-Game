import javax.swing.*;
import java.awt.*;

class ScrollingPanel extends JPanel {
    private final String heading;
    private final String[] names;
    private final String[] regNos;
    private final Font customFont;
    private int y;
    private final GameFrame gameFrame;

    public ScrollingPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        //this.setBackground(Color.magenta);
        heading = "Made by:  ";
        names = new String[]{
                "Adil Hassan",
                "M. Ahmed Anjum",
                "Umar Riaz",
                "Shamoil",

        };

        regNos = new String[]{
                "FA23-BCS-022",
                "FA23-BCS-093",
                "FA23-BCS-165",
                "SP23-BCS-148",
                
        };

        // Create a custom font
        customFont = new Font("Serif", Font.BOLD, 30); // Example font, size 36
        this.y = getHeight();
        showcredits();
        setLayout(null); // Use null layout for absolute positioning

        // Create a Timer to update the text position
        Timer timer = new Timer(20, e -> {
            y -= 2;
            if (y < -((names.length + 1) * customFont.getSize())) {
                y = getHeight();
            }
            repaint();
        });

        timer.start();

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
        backButton.addActionListener(e -> {
            gameFrame.getContentPane().removeAll();
            gameFrame.add(new MainMenu(gameFrame));
            gameFrame.revalidate();
            gameFrame.repaint();
            // Implement end game functionality if needed
        });

        // Add the back button to the panel
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(customFont);
        g.setColor(Color.WHITE); // Set the text color to white

        int textHeight = g.getFontMetrics().getHeight();
        int panelWidth = getWidth();
        int spacing = 38; // Reasonable space between the names and registration numbers

        // Draw the heading centered
        int headingWidth = g.getFontMetrics().stringWidth(heading);
        int headingX = (panelWidth - headingWidth) / 2;
        g.drawString(heading, headingX, y);

        // Draw the names and registration numbers
        for (int i = 0; i < names.length; i++) {
            int textY = y + (i + 1) * textHeight; // Offset by one textHeight for the heading

            // Draw the name right-aligned
            int nameWidth = g.getFontMetrics().stringWidth(names[i]);
            int nameX = (panelWidth / 2) - spacing; // Offset to the left from the center
            g.drawString(names[i], nameX - nameWidth, textY);

            // Draw the registration number left-aligned
            int regNoX = (panelWidth / 2) + spacing; // Offset to the right from the center
            g.drawString(regNos[i], regNoX, textY);
        }
    }

    void showcredits() {
        gameFrame.getContentPane().removeAll(); // Remove all existing components from the content pane
        // Set the layout of the GameFrame to BorderLayout
        this.setLayout(new BorderLayout());

        // Set the preferred size of the credits panel to match the size of the GameFrame
        // Get the size of the GameFrame
        this.setPreferredSize(gameFrame.dimension); // Set the preferred size of the credits panel

        setBackground(Color.magenta);
        // Add the credits panel to the center of the GameFrame
        gameFrame.getContentPane().add(this, BorderLayout.CENTER);
        // Pack the components to adjust sizes and layouts
        gameFrame.pack();
        // Revalidate the content pane to reflect changes
        gameFrame.revalidate();
        // Repaint the frame to update the display
        gameFrame.repaint();
    }
}

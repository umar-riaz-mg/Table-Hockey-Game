import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.5555);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    Image image;
    Graphics graphics;
    public GameState gameState;
    private Match currentMatch;
    public Thread panelThread;
    GameFrame gameFrame;// Add a reference to the SidePanel

    public GamePanel(MatchType matchType, GameFrame gameFrame) throws IllegalArgumentException {
        gameState = new GameState(this);
        this.gameFrame = gameFrame;
        this.setFocusable(true);
        this.addKeyListener(new KeyInput());
        this.setPreferredSize(SCREEN_SIZE);
        initGame(matchType);
        panelThread = new Thread(currentMatch);
        panelThread.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

        //contentPanel.setBackground(Color.white);
    }

    public void draw(Graphics g) {
        currentMatch.paddle1.draw(g);
        currentMatch.paddle2.draw(g);
        currentMatch.ball.draw(g);
        Toolkit.getDefaultToolkit().sync();
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
    }

    public void initGame(MatchType matchType) {
        switch (matchType) {
            case ONE_VS_ONE:
                currentMatch = new OneVsOneMatch(this);
                break;
            case COMPUTERPLAYER:
                currentMatch = new ComputerPlayer(this);
                break;
            default:
                throw new IllegalArgumentException("Invalid match type");
        }
    }
    public void reset() {
        // Reset the game panel state
        currentMatch = null;
        //repaint(); // Optionally repaint the panel
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    private class KeyInput extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            currentMatch.paddle1.keyPressed(e);
            currentMatch.paddle2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            currentMatch.paddle1.keyReleased(e);
            currentMatch.paddle2.keyReleased(e);
        }
    }

    // Method to update player scores and update the score table in SidePanel
    public void updateScoreTable() {
        gameFrame.sidePanel.updatePlayer1Score(currentMatch.getPlayer1().getScore());
        gameFrame.sidePanel.updatePlayer2Score(currentMatch.getPlayer2().getScore());
    }
}

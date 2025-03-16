import javax.swing.*;

public class GameState {
    private final GamePanel gamePanel;
    private volatile boolean isPaused;
    public GameState(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.isPaused = false;
    }

    public void newGame(MatchType matchType) {
        SwingUtilities.invokeLater(() -> {
            stopCurrentGame();
            gamePanel.initGame(matchType);
            startNewGameThread();
        });
    }

    public void pauseGame() {
        if (!isPaused) {
            isPaused = true;
            if (gamePanel.panelThread != null && gamePanel.panelThread.isAlive()) {
                gamePanel.panelThread.interrupt();
            }
        }
    }

    public void resumeGame() {
        if (isPaused) {
            isPaused = false;
            SwingUtilities.invokeLater(() -> {
                try {
                    if (gamePanel.panelThread != null && gamePanel.panelThread.isAlive()) {
                        gamePanel.panelThread.join();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gamePanel.panelThread = new Thread(gamePanel.getCurrentMatch());
                gamePanel.panelThread.start();
            });
        }
    }

    private void startNewGameThread() {
        gamePanel.panelThread = new Thread(gamePanel.getCurrentMatch());
        gamePanel.panelThread.start();
    }

    private void stopCurrentGame() {
        if (gamePanel.panelThread != null && gamePanel.panelThread.isAlive()) {
            gamePanel.panelThread.interrupt();
            try {
                gamePanel.panelThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean isPaused() {
        return isPaused;
    }
}

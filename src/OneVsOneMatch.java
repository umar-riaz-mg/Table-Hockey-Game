public class OneVsOneMatch extends Match
{
    //Constructor
    public OneVsOneMatch(GamePanel gamePanel)
    {
        super(gamePanel);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (!Thread.currentThread().isInterrupted()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (!gamePanel.gameState.isPaused()) {
                if (delta >= 1) {
                    move();
                    checkCollision();
                    gamePanel.repaint();
                    delta--;
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore the interrupt status
                    break;
                }
            }
        }
    }
}

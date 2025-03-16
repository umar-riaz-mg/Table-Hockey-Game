public class ComputerPlayer extends Match
{
    //Constructor
    public ComputerPlayer(GamePanel gamePanel)
    {
        super(gamePanel);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0; // Desired FPS
        double ns = 1000000000 / amountOfTicks; // Time that should be taken by each frame
        double delta = 0;

        while (!Thread.currentThread().isInterrupted()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (!gamePanel.gameState.isPaused()) { // Check if the game is not paused
                if (delta >= 1) {
                    move(); // Move all objects
                    moveAIPaddle(); // Move AI paddle (paddle2)
                    checkCollision(); // Check for collisions
                    gamePanel.repaint(); // Repaint the game panel
                    delta--;
                }
            } else {
                // If the game is paused, sleep to reduce CPU usage
                try {
                    Thread.sleep(10); // Adjust sleep time as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    //Algorithm for automating the control of One paddle
    private void moveAIPaddle() {
        // Move paddle2 accordingly to track the ball
        int paddleCenter = paddle2.y + Paddle.PADDLE_HEIGHT / 2;
        int ballCenter = ball.y + Ball.BALL_DIAMETER / 2;

        if (ballCenter < paddleCenter) {
            paddle2.setYDirection(-paddle2.speed); // Move up
        } else if (ballCenter > paddleCenter) {
            paddle2.setYDirection(paddle2.speed); // Move down
        } else {
            paddle2.setYDirection(0); // Stay in place
        }
        paddle2.move();

        // Ensure the AI paddle stays within the game panel's boundaries
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        } else if (paddle2.y >= GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT) {
            paddle2.y = GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT;
        }
    }

    private void moveAIPaddle1 ()
    {
            // Move paddle2 accordingly to track the ball
            int paddleCenter = paddle1.y + Paddle.PADDLE_HEIGHT / 2;
            int ballCenter = ball.y + Ball.BALL_DIAMETER / 2;

            if (ballCenter < paddleCenter) {
                paddle1.setYDirection(-paddle1.speed); // Move up
            } else if (ballCenter > paddleCenter) {
                paddle1.setYDirection(paddle1.speed); // Move down
            } else {
                paddle1.setYDirection(0); // Stay in place
            }
            paddle1.move();

            // Ensure the AI paddle stays within the game panel's boundaries
            if (paddle1.y <= 0) {
                paddle1.y = 0;
            } else if (paddle1.y >= GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT) {
                paddle1.y = GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT;
            }
    }
}

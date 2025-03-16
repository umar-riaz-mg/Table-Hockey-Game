import java.util.Random;

public abstract class Match implements Runnable
{
    protected GamePanel gamePanel;
    protected Paddle paddle1;
    protected Paddle paddle2;
    protected Ball ball;
    protected Player player1;
    protected Player player2;
    protected Random random;

    public Match(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        initializeGame();
    }
    protected void initializeGame()
    {
        // Initialize paddles and ball and Players
        newBall();
        newPaddles();
        player1 = new Player();
        player2 = new Player();
        //For computer-Match Player2 name is Computer
        if (gamePanel.getCurrentMatch() instanceof ComputerPlayer) {
            player2.setName("Computer :)");
        }
    }
    // Abstract run method to be implemented by subclasses
    public abstract void run();

    // Move paddles and ball
    public void move()
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    // Collision detection and game rules enforcement
    public void checkCollision()
    {
        // Ball bouncing off the top and bottom edges of the game panel
        if (ball.y <= 0 || ball.y >= GamePanel.GAME_HEIGHT - Ball.BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        // Ball bouncing off paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // Power-Up in future User interface
            ball.setXDirection(ball.xVelocity);
//            if (ball.yVelocity > 0) {
//                ball.yVelocity++; // Power-Up in future User interface
//            } else {
//                ball.yVelocity--;
//            }
            ball.setYDirection(ball.yVelocity);
        }
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // Power-Up in future User interface
            ball.setXDirection(-ball.xVelocity);
//            if (ball.yVelocity > 0) {
//                ball.yVelocity++; // Power-Up in future User interface
//            } else {
//                ball.yVelocity--;
//            }
            ball.setYDirection(ball.yVelocity);
        }

        // Stopping paddles at the window edges
        if (paddle1.y < 0) {
            paddle1.y = 0;
        }
        if (paddle1.y > GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT) {
            paddle1.y = GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT;
        }
        if (paddle2.y < 0) {
            paddle2.y = 0;
        }
        if (paddle2.y > GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT) {
            paddle2.y = GamePanel.GAME_HEIGHT - Paddle.PADDLE_HEIGHT;
        }

        // Handling scoring when ball gets off right and left sides
        // Handling scoring when ball gets off right and left sides
        if (ball.x <= 0) {
            // Ball hit the left side; increase player 2's score
            player2.incrementScore();
            gamePanel.updateScoreTable();
            newBall(); // Reset the ball position
        }
        if (ball.x >= GamePanel.GAME_WIDTH - Ball.BALL_DIAMETER) {
            // Ball hit the right side; increase player 1's score
            player1.incrementScore();
            gamePanel.updateScoreTable();
            newBall(); // Reset the ball position
        }
    }

    public void newBall()
    {
        random = new Random();
        //Setting the position of ball with random coordinates
        ball = new Ball((GamePanel.GAME_WIDTH /2)-(Ball.BALL_DIAMETER /2),random.nextInt(GamePanel.GAME_HEIGHT-Ball.BALL_DIAMETER),Ball.BALL_DIAMETER,Ball.BALL_DIAMETER);
    }
    public void newPaddles()
    {
        //Setting up new paddles
        paddle1 = new Paddle(0, (GamePanel.GAME_HEIGHT / 2) - Paddle.PADDLE_HEIGHT / 2, Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GamePanel.GAME_WIDTH - Paddle.PADDLE_WIDTH, (GamePanel.GAME_HEIGHT / 2) - Paddle.PADDLE_HEIGHT / 2, Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT, 2);

    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}

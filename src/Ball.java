import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle
{
    // Define a constant for the diameter of the ball
    static final int BALL_DIAMETER = 20;

    // Declare variables
    Random random; // Random number generator
    protected int xVelocity; // Velocity of the ball in the x direction
    protected int yVelocity; // Velocity of the ball in the y direction
    protected int initialSpeed = 2; // Initial speed of the ball

    // Constructor for the Ball class
    Ball(int x, int y, int width, int height)
    {
        super(x, y, width, height); // Call the constructor of the Rectangle class

        // Initialize the random number generator
        random = new Random();

        // Generate a random direction for the x-axis movement
        int randomXDirection = random.nextInt(2);

        // Ensure the randomXDirection is either -1 or 1
        if(randomXDirection == 0)
            randomXDirection--;

        // Set the x-axis velocity based on the random direction and initial speed
        setXDirection(randomXDirection * initialSpeed);

        // Generate a random direction for the y-axis movement
        int randomYDirection = random.nextInt(2);

        // Ensure the randomYDirection is either -1 or 1
        if(randomYDirection == 0)
            randomYDirection--;

        // Set the y-axis velocity based on the random direction and initial speed
        setYDirection(randomYDirection * initialSpeed);
    }

    // Method to set the x-axis direction of the ball
    public void setXDirection(int randomXDirection)
    {
        xVelocity = randomXDirection;
    }

    // Method to set the y-axis direction of the ball
    public void setYDirection(int randomYDirection)
    {
        yVelocity = randomYDirection;
    }

    // Method to move the ball
    public void move()
    {
        x += xVelocity; // Update the x-coordinate based on the x velocity
        y += yVelocity; // Update the y-coordinate based on the y velocity
    }

    // Method to draw the ball
    public void draw(Graphics g)
    {
        g.setColor(Color.white); // Set the color of the graphics object to white
        g.fillOval(x, y, width, height); // Fill an oval (ball) with the specified dimensions at the current position
    }
}
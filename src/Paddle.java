// importing java AWT and KeyEvent class
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle
{
    // instance variable declaration
    int id;
    int yVelocity;
    int speed = 10;

    // static variable initialization
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;


    // paddle class constructor to initialize variables
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        // java rectangle class constructor
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }

    // method to move paddle based on pressed key
    public void keyPressed(KeyEvent e)
    {
        // swtich to decide player paddle
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed); // move paddle 1 up
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed); // move paddle 1 down
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(-speed); // move paddle 2 up
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(speed); // move paddle 2 down
                }
                break;
        }
    }

    // method to stop paddle based on pressed key
    public void keyReleased(KeyEvent e)
    {
        // swtich to decide player paddle
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0); // stop paddle 1 moving up
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0); // stop paddle 1 moving down
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0); // stop paddle 2 moving up
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0); // stop paddle 2 moving down
                }
                break;
        }
    }

    // method to set y-cordinate(verticle) paddle speed
    public void setYDirection(int yDirection)
    {
        yVelocity = yDirection;
    }

    // method to change y-cordinate position of paddle
    public void move()
    {
        y= y + yVelocity;
    }

    // method for visual representation of paddles
    public void draw(Graphics g)
    {
        if(id==1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        g.fillOval(x, y, width, height);
    }
}
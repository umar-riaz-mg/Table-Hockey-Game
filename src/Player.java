public class Player
{
    // instance variable declaration
    private String name;
    private int score;

    // player class contructor to initialize instance variables
    public Player()
    {
        setScore(0);
    }

    // setter getter methods for name and score
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getScore()
    {
        return score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }

    // method to increment score after point
    public void incrementScore()
    {
        score++;

    }
    public void resetScore()
    {
        this.score = 0;
    }
}

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(() -> {
            //Setting up a frame that initiates the panel and game
            GameFrame frame = new GameFrame();

        });

    }

}
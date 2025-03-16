import javax.swing.*;
import java.awt.*;    // importing packages to be used

public class GameFrame extends JFrame{

    //creating panel according to given prompt of match type
    //In test case an instance of one vs one game panel is created

    private String name1;
    private String name2;
    GamePanel panel;
    SidePanel sidePanel ;
    MatchType matchType ;
    Dimension dimension = new Dimension(GamePanel.GAME_WIDTH + SidePanel.SIDE_PANEL_WIDTH, GamePanel.GAME_HEIGHT);
    ImageIcon logo = new ImageIcon("logo.jpg");

    //Constructor for game frame
    GameFrame() {
        setSize(1250, (int) (1000 * 0.5555));
        this.setTitle("Table Hockey Game");
        this.setResizable(false);
        this.setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().add(new MainMenu(this), BorderLayout.CENTER);
        this.setIconImage(logo.getImage());
    }
    void loadGame(String matchChoice,String name1s,String name2s)
    {
        matchChoice = matchChoice.toUpperCase().replace("VS", "_VS_").replace(" ", "_");
        setNames(name1s,name2s);
        matchType= MatchType.valueOf(matchChoice);
        getContentPane().removeAll();
        panel = new GamePanel(matchType,this);

        panel.getCurrentMatch().getPlayer1().setName(name1s);
        panel.getCurrentMatch().getPlayer2().setName(name2s);

        sidePanel = new SidePanel(panel);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.EAST);
        //setting all desired properties of game frame
        this.add(panel);


        this.pack();
        revalidate();
        repaint();
    }
    public void setNames(String name1s, String name2s) {
        this.name1 = name1s;
        this.name2 = name2s;
    }

    public String getName2() {
        return name2;
    }
    public String getName1() {
        return name1;
    }
}

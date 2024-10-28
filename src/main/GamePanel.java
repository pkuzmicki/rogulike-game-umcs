package main;

import objects.Player;
import map.Map;

import javax.swing.*;
import java.awt.*;

//passphrase: kamyk

public class GamePanel extends JPanel {
    public int screenWidth = 1000;
    public int screenHeight = 600;

    Player player = new Player(this);
    Map map = new Map(this);

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public void setupGame(){
        player.setPlayerPosition();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        map.draw(g2);
        player.draw(g2);

        g2.dispose();

    }

    public void repaint(){

    }


}

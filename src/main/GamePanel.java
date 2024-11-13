package main;

import map.ObjectManager;
import objects.Player;
import map.Map;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public int screenWidth = 640;
    public int screenHeight = 480;

    public Player player;
    public Map map;
    public MouseHandler mouseHandler;
    public ObjectManager objectManager;
    public KeyHandler keyHandler;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addMouseListener(mouseHandler);
        setFocusable(true);

        map = new Map(this);
        player = new Player(this);
        objectManager = new ObjectManager(this);
//        mouseHandler = new MouseHandler();

        keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        map.draw(g2);

        player.draw(g2);
        objectManager.draw(g2);

        g2.dispose();
    }
}

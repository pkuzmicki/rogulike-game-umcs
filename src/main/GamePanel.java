package main;

import combat.CastingManager;
import combat.TurnManager;
import map.ObjectManager;
import objects.OBJ;
import objects.Player;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GamePanel extends JPanel {
    public int screenWidth = 640;
    public int screenHeight = 480;

    public Map map;

    public Player player;
    public ObjectManager objectManager;

    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;

    public TurnManager turnManager;
    public CastingManager castingManager;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        setFocusable(true);

        map = new Map(this);

        player = new Player(this);
        objectManager = new ObjectManager(this);

        mouseHandler = new MouseHandler(this);
        keyHandler = new KeyHandler(this);

        turnManager = new TurnManager(this);
        castingManager = new CastingManager(this);

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);


    }

    public void update() {
        if (keyHandler.onePressed) {
            castingManager.testicularTorsion.cast(keyHandler.directionOfAction());
        }
        if (keyHandler.twoPressed) {
            castingManager.lifebloom.cast(keyHandler.directionOfAction());
        }
        //System.out.println(turnManager.globalTurn);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        map.draw(g2);

        List<OBJ> objectsToDraw = new ArrayList<>();
        objectsToDraw.add(player);
        objectsToDraw.addAll(objectManager.entities);
        objectsToDraw.sort(Comparator.comparing(OBJ::getY));

        for (OBJ obj : objectsToDraw) {
            obj.draw(g2);
        }

        turnManager.draw(g2);

        g2.dispose();
    }
}

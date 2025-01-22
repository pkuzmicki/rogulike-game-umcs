package main;

import combat.CastingManager;
import combat.TurnManager;
import map.DungeonGeneration;
import map.Hideout;
import objects.*;
import map.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel {
    //public int screenWidth = 640;
    //public int screenHeight = 480;

    public int screenWidth = 960;
    public int screenHeight = 540;

    public Map map;
    public DungeonGeneration generation;
    public Hideout hideout;

    public Player player;
    public Equipment eq;
    public ObjectManager objectManager;

    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;

    public TurnManager turnManager;
    public CastingManager castingManager;

    public Commands commands;
    public Backgrounds backgrounds;
    public UI ui;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        map = new Map(this);
        hideout = new Hideout(this);

        player = new Player(this, 1, 1, 1);
        eq = new Equipment(this);
        objectManager = new ObjectManager(this);

        generation = new DungeonGeneration(this);

        mouseHandler = new MouseHandler(this);
        keyHandler = new KeyHandler(this);

        turnManager = new TurnManager(this);
        castingManager = new CastingManager(this);

        commands = new Commands(this);
        backgrounds = new Backgrounds(this);
        ui = new UI(this);

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);

    }

    public void update() {
        //System.out.println(player.isEquipmentOpen);

        //System.out.println(player.isInHideout);
        objectManager.printEntityMap();


        player.move();
        player.checkManaValue();

        if (eq.itemDropped) {
            if (turnManager.globalTurn == eq.turnWhenDropped + 1) {
                eq.itemDropped = false;
            }
        }

        if (!eq.itemDropped) {
            eq.pickUpItems();
        }

        eq.closeEq();
        eq.dropItems();

        player.checkStatusEffects(3, 7, 3);
        hideout.checkAltar();

        for (Entity entity : objectManager.entities) {
            if (entity.id < 30 && entity.id > 10) {
                Monster monster = (Monster) entity;
                //System.out.println(monster.name);

                monster.randomWalk();
                monster.follow(3);
                monster.attack();
            }
        }

        for (Entity entity : objectManager.entities) {
            if (entity.id < 30) {
                Monster monster = (Monster) entity;
                monster.checkForOverHeal();
                monster.checkIfShouldDie(monster.col, monster.row);
                repaint();
            }
        }
        objectManager.killAll();

        if (!player.isEquipmentOpen) {
            castingManager.updateCasting();
        }

        turnManager.updateTurn();
        //repaint();
    }

    public void drawScene(Graphics2D g2) {
        backgrounds.draw(g2);

        map.draw(g2);
        hideout.draw(g2);

        objectManager.draw(g2);

        castingManager.draw(g2);

        eq.draw(g2);

        turnManager.draw(g2);

        ui.draw(g2);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        double scaleX = (double) getWidth() / screenWidth;
        double scaleY = (double) getHeight() / screenHeight;

        g2.scale(scaleX, scaleY);

        drawScene(g2);

        g2.dispose();
    }

    void setFullScreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(Game.window);
    }

    public BufferedImage getImage(String path) {
        BufferedImage image;
        try {
            return image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package main;

import combat.CastingManager;
import combat.TurnManager;
import map.DungeonGeneration;
import map.Hideout;
import objects.ObjectManager;
import objects.Entity;
import objects.Monster;
import objects.Player;
import map.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    public ObjectManager objectManager;

    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;

    public TurnManager turnManager;
    public CastingManager castingManager;
    //public Spell spell;

    public Commands commands;
    public Backgrounds backgrounds;
    public UI ui;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        setFocusable(true);

        map = new Map(this);
        hideout = new Hideout(this);

        player = new Player(this, 1, 1, 1);
        objectManager = new ObjectManager(this);

        generation = new DungeonGeneration(this);

        mouseHandler = new MouseHandler(this);
        keyHandler = new KeyHandler(this);

        turnManager = new TurnManager(this);
        castingManager = new CastingManager(this);
        //spell = new Spell(this, 0);

        commands = new Commands(this);
        backgrounds = new Backgrounds(this);
        ui = new UI(this);

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);

    }

    public void update() {
        player.move();
        //commands.getCommand();

        for (Entity entity : objectManager.entities) {
            Monster monster = (Monster) entity;
            monster.checkForOverHeal();
            monster.checkIfShouldDie(monster.col, monster.row);
            //repaint();
        }

        if (turnManager.globalTurn % 2 != 0) {
            for (Entity entity : objectManager.entities) {
                Monster monster = (Monster) entity;
                if (monster.id != 1) {

                    //monster.attack(castingManager.testicularTorsion);
                }
            }
        }

//        if (keyHandler.xPressed) {
//            player.currentHP -= player.maxHP/10;
//            player.currentMana -= player.maxMana/10;
//        }

        if (keyHandler.onePressed || keyHandler.twoPressed || keyHandler.threePressed) {
            castingManager.chooseSpell();
        }

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.xPressed){
            castingManager.setSpellPosition(player);
        }

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.xPressed){
            castingManager.applyEffect();
        }


        turnManager.updateTurn();
        repaint();
    }

    public void drawScene(Graphics2D g2) {
        backgrounds.draw(g2);
        map.draw(g2);
        hideout.draw(g2);

        List<Entity> objectsToDraw = new ArrayList<>(objectManager.entities);
        objectsToDraw.sort(Comparator.comparing(Entity::getHeight));

        for (Entity entity : objectsToDraw) {
            entity.draw(g2);
        }

        castingManager.draw(g2);

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

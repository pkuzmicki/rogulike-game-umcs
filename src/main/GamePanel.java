package main;

import combat.CastingManager;
import combat.TurnManager;
import map.ObjectManager;
import objects.Entity;
import objects.Monster;
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

    public Camera camera;
    public Player player;
    public ObjectManager objectManager;

    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;

    public TurnManager turnManager;
    public CastingManager castingManager;

    public Commands commands;
    public UI ui;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        setFocusable(true);

        map = new Map(this);

        camera = new Camera(this);
        player = new Player(this, 1, 1, 1);
        objectManager = new ObjectManager(this);

        mouseHandler = new MouseHandler(this);
        keyHandler = new KeyHandler(this);

        turnManager = new TurnManager(this);
        castingManager = new CastingManager(this);

        commands = new Commands(this);
        ui = new UI(this);

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
    }

    public void update() {
        //commands.getCommand();
        //objectManager.printEntityMap();
        //System.out.println();
        //System.out.println(player.col +" "+player.row);

        for (Entity entity : objectManager.entities) {
            Monster monster = (Monster) entity;
            monster.checkForOverHeal();
            monster.checkIfShouldDie(monster.col, monster.row);
            repaint();

        }

        if (turnManager.globalTurn % 2 != 0) {
            for (Entity entity : objectManager.entities) {
                Monster monster = (Monster) entity;
                if (monster.id != 1){
                    monster.attack(castingManager.testicularTorsion);
                    //System.out.println(monster.name);
                }
            }
        }


        //CHOOSING SPELLS
        if (keyHandler.onePressed) {
            castingManager.playerCast(castingManager.testicularTorsion);
        }
        if (keyHandler.twoPressed) {
            castingManager.playerCast(castingManager.lifebloom);
        }
        if (keyHandler.threePressed) {
            //castingManager.rollingMagma.cast();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        map.draw(g2);

        List<Entity> objectsToDraw = new ArrayList<>(objectManager.entities);
        objectsToDraw.sort(Comparator.comparing(Entity::getHeight));

        for (Entity entity : objectsToDraw) {
            entity.draw(g2);
        }

        turnManager.draw(g2);

        if (ui.isGameOver) {
            ui.draw(g2);
        }

        g2.dispose();
    }
}

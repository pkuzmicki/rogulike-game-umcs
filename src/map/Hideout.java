package map;

import main.GamePanel;
import objects.Entity;
import objects.Monster;
import objects.items.Rune;

import java.awt.*;
import java.util.ArrayList;

public class Hideout {
    GamePanel gp;
    public Tile[][] hideoutMap;
    public ArrayList<Entity> entities;
    public int maxCol = 20, maxRow = 20;
    public int hideoutX;
    public int hideoutY;

    public Monster dummy;

    public double dmgBonus;

    public Hideout(GamePanel gamePanel){
        this.gp = gamePanel;
        hideoutMap = new Tile[maxCol][maxRow];
        entities = new ArrayList<>();
        hideoutX = 0;
        hideoutY = (gp.map.tileHeight/2) * 60;
        createHideout();
        createAltar();
        createDummy();
    }

    public void createDummy() {
        dummy = new Monster(gp, 3, 3, 14);
        dummy.setInHideOut = true;
    }

    public void createAltar() {
        hideoutMap[7][8].setTileType(9);
        hideoutMap[8][7].setTileType(9);
        hideoutMap[10][11].setTileType(9);
        hideoutMap[11][10].setTileType(9);
    }

    public void checkAltar() {
        double firstAltar = 0;
        double secondAltar = 0;
        double thirdAltar = 0;
        double forthAltar = 0;
        for (Entity entity : entities) {
            if (entity.col == 7 && entity.row == 8) {
                Rune rune = (Rune) entity;
                firstAltar = rune.dmg;
                System.out.println(rune.dmg);
            }
            if (entity.col == 8 && entity.row == 7) {
                Rune rune = (Rune) entity;
                secondAltar = rune.dmg;
            }
            if (entity.col == 10 && entity.row == 11) {
                Rune rune = (Rune) entity;
                thirdAltar = rune.dmg;
            }
            if (entity.col == 11 && entity.row == 10) {
                Rune rune = (Rune) entity;
                forthAltar = rune.dmg;
            }
        }
        dmgBonus = firstAltar + secondAltar + thirdAltar + forthAltar;
    }

    public void createHideout() {
        gp.map.createMap(gp.map.mapStartX, gp.map.mapStartY - hideoutY, maxCol, maxRow, hideoutMap);
        gp.map.createRoom(0, 0, "/maps/rooms/hideout", hideoutMap);
    }

    public void draw(Graphics2D g2) {
        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                g2.drawImage(hideoutMap[col][row].tileImage, hideoutMap[col][row].x + gp.map.dx, hideoutMap[col][row].y + gp.map.dy, null);
            }
        }
    }
}

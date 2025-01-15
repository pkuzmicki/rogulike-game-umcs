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

    //public Monster dummy;

    public double dmgBonus;
    public double critBonus;
    public int rangeBonus;
    public double hpBonus;
    public int manaBonus;
    public int runeSpaceBonus;

    public Hideout(GamePanel gamePanel){
        this.gp = gamePanel;
        hideoutMap = new Tile[maxCol][maxRow];
        entities = new ArrayList<>();
        hideoutX = 0;
        hideoutY = (gp.map.tileHeight/2) * 60;
        createHideout();
        createAltar();
        //createDummy();
    }

//    public void createDummy() {
//        dummy = new Monster(gp, 3, 3, 14);
//        dummy.setInHideOut = true;
//    }

    public void createAltar() {
        hideoutMap[7][8].setTileType(9);
        hideoutMap[8][7].setTileType(9);
        hideoutMap[10][11].setTileType(9);
        hideoutMap[11][10].setTileType(9);
    }

    public void checkAltar() {
        double[] altarsDmg = new double[4];
        double[] altarsCri = new double[4];
        int[] altarsRan = new int[4];
        double[] altarsHit = new double[4];
        int[] altarsMan = new int[4];
        int[] altarsSpa = new int[4];

        for (Entity entity : entities) {
            if (entity.col == 7 && entity.row == 8) {
                Rune rune = (Rune) entity;
                altarsDmg[0] = rune.dmg;
                altarsCri[0] = rune.critical;
                altarsRan[0] = rune.range;
                altarsHit[0] = rune.hp;
                altarsMan[0] = rune.mana;
                altarsSpa[0] = rune.runeSpace;
            }
            if (entity.col == 8 && entity.row == 7) {
                Rune rune = (Rune) entity;
                altarsDmg[1] = rune.dmg;
                altarsCri[1] = rune.critical;
                altarsRan[1] = rune.range;
                altarsHit[1] = rune.hp;
                altarsMan[1] = rune.mana;
                altarsSpa[1] = rune.runeSpace;
            }
            if (entity.col == 10 && entity.row == 11) {
                Rune rune = (Rune) entity;
                altarsDmg[2] = rune.dmg;
                altarsCri[2] = rune.critical;
                altarsRan[2] = rune.range;
                altarsHit[2] = rune.hp;
                altarsMan[2] = rune.mana;
                altarsSpa[2] = rune.runeSpace;
            }
            if (entity.col == 11 && entity.row == 10) {
                Rune rune = (Rune) entity;
                altarsDmg[3] = rune.dmg;
                altarsCri[3] = rune.critical;
                altarsRan[3] = rune.range;
                altarsHit[3] = rune.hp;
                altarsMan[3] = rune.mana;
                altarsSpa[3] = rune.runeSpace;
            }
        }
        dmgBonus = altarsDmg[0] + altarsDmg[1] + altarsDmg[2] + altarsDmg[3];
        critBonus = altarsCri[0] + altarsCri[1] + altarsCri[2] + altarsCri[3];
        rangeBonus = altarsRan[0] + altarsRan[1] + altarsRan[2] + altarsRan[3];
        hpBonus = altarsHit[0] + altarsHit[1] + altarsHit[2] + altarsHit[3];
        manaBonus = altarsMan[0] + altarsMan[1] + altarsMan[2] + altarsMan[3];
        runeSpaceBonus = altarsSpa[0] + altarsSpa[1] + altarsSpa[2] + altarsSpa[3];
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

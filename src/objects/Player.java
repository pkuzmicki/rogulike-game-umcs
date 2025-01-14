//passphrase: kamyk
package objects;

import combat.Spell;
import main.GamePanel;
import objects.items.Rune;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Monster {
    public int screenX = gp.screenWidth/2 - image.getWidth()/2;
    public int screenY = gp.screenHeight/2 - image.getHeight()/2;
    public boolean isInHideout = false;

    public boolean isEquipmentOpen = false;

    public ArrayList<Rune> playerRunes = new ArrayList<>();
    public ArrayList<Spell> playerSpells = new ArrayList<>();

    public int currentMana;
    public int maxMana;

    public Player(GamePanel gamePanel, int col, int row, int entityID) {
        super(gamePanel, col, row, entityID);

        maxMana = currentMana = 100;

        addSpells();
    }

    public void checkManaValue() {
         if (currentMana < 0) {
             currentMana = 0;
         }
         if (currentMana > maxMana) {
             currentMana = maxMana;
         }
    }

    public void setPositions() {
        x = screenX;
        y = screenY;
    }

    public void move(){
        switch (gp.keyHandler.directionOfMove()) {
            case "up" -> {
                if (isInHideout) {
                    if (col > 0 && gp.hideout.hideoutMap[col - 1][row].tileType >= 1) {
                        col -= 1;
                        gp.map.dx -= gp.map.tileWidth/2;
                        gp.map.dy += gp.map.tileHeight/2;
                    }
                } else {
                    if (col > 0 && gp.map.tileMap[col - 1][row].tileType >= 1 && gp.objectManager.entityMap[col - 1][row] == 0 || gp.objectManager.entityMap[col - 1][row] > 30) {
                        col -= 1;
                        gp.map.dx -= gp.map.tileWidth/2;
                        gp.map.dy += gp.map.tileHeight/2;

                        //gp.y += 1;
                        //gp.x += 1;
                    }
                }
            }
            case "down" -> {
                if (isInHideout) {
                    if (col < gp.hideout.maxCol - 1 && gp.hideout.hideoutMap[col + 1][row].tileType >= 1) {
                        col += 1;
                        gp.map.dx += gp.map.tileWidth/2;
                        gp.map.dy -= gp.map.tileHeight/2;
                    }
                } else {
                    if (col < gp.map.maxWorldCol - 1 && gp.map.tileMap[col + 1][row].tileType >= 1 && gp.objectManager.entityMap[col + 1][row] == 0 || gp.objectManager.entityMap[col + 1][row] > 30) {
                        col += 1;
                        gp.map.dx += gp.map.tileWidth/2;
                        gp.map.dy -= gp.map.tileHeight/2;

                        //gp.y -= 1;
                        //gp.x -= 1;
                    }
                }
            }
            case "left" -> {
                if (isInHideout) {
                    if (row > 0 && gp.hideout.hideoutMap[col][row - 1].tileType >= 1) {
                        row -= 1;
                        gp.map.dx += gp.map.tileWidth/2;
                        gp.map.dy += gp.map.tileHeight/2;
                    }
                } else {
                    if (row > 0 && gp.map.tileMap[col][row - 1].tileType >= 1 && gp.objectManager.entityMap[col][row - 1] == 0 || gp.objectManager.entityMap[col][row - 1] > 30) {
                        row -= 1;
                        gp.map.dx += gp.map.tileWidth/2;
                        gp.map.dy += gp.map.tileHeight/2;

                        //gp.x += 1;
                        //gp.y += 1;
                    }
                }
            }
            case "right" -> {
                if (isInHideout) {
                    if (row < gp.hideout.maxRow - 1 && gp.hideout.hideoutMap[col][row + 1].tileType >= 1) {
                        row += 1;
                        gp.map.dx -= gp.map.tileWidth/2;
                        gp.map.dy -= gp.map.tileHeight/2;
                    }
                } else {
                    if (row < gp.map.maxWorldRow - 1 && gp.map.tileMap[col][row + 1].tileType >= 1 && gp.objectManager.entityMap[col][ row + 1] == 0 || gp.objectManager.entityMap[col][row + 1] > 30) {
                        row += 1;
                        gp.map.dx -= gp.map.tileWidth/2;
                        gp.map.dy -= gp.map.tileHeight/2;

                        //gp.x -= 1;
                        //gp.y -= 1;
                    }
                }
            }
        }

    }

    public void teleportToHideout() {
        gp.map.dx = gp.hideout.hideoutX;
        gp.map.dy = gp.hideout.hideoutY;
        isInHideout = true;
        col = 1;
        row = 1;
    }

    public void teleportToLevel() {
        gp.map.dx = 0;
        gp.map.dy = 0;
        isInHideout = false;
        col = 1;
        row = 1;
    }

    public void addSpells() {
        Spell attackSpell1 = new Spell(gp,"1");
        attackSpell1.setSpellValues(this);
        playerSpells.add(attackSpell1);
        Spell attackSpell2 = new Spell(gp,"2");
        attackSpell2.setSpellValues(this);
        playerSpells.add(attackSpell2);
        Spell healingSpell = new Spell(gp,"3");
        healingSpell.setSpellValues(this);
        playerSpells.add(healingSpell);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, screenX, screenY, null);
    }
}

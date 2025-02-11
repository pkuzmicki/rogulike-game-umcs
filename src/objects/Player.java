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

    public boolean isPoisoned; //drains hp every turn
    public boolean isCursed; //drains mana every turn

    public boolean isEquipmentOpen = false;

    public ArrayList<Rune> playerRunes = new ArrayList<>();
    public ArrayList<Spell> playerSpells = new ArrayList<>();

    public int currentMana;
    public int maxMana;
    public double critChance;

    public double totalDmg;
    public double totalHp;
    public int totalMana;
    public double totalCrit;

    public Player(GamePanel gamePanel, int col, int row, int entityID) {
        super(gamePanel, col, row, entityID);

        maxMana = currentMana = 100;
        critChance = 0.05;

        addSpells();
        updateStats();
    }

    public void updateStats() {
        totalDmg = this.dmg + gp.hideout.dmgBonus;
        updateSpell();
        totalHp = this.maxHP + gp.hideout.hpBonus;
        totalMana = this.maxMana + gp.hideout.manaBonus;
        totalCrit = this.critChance + gp.hideout.critBonus;
    }

    public void checkManaValue() {
         if (currentMana < 0) {
             currentMana = 0;
             gp.repaint();
         }
         if (currentMana > totalMana) {
             currentMana = totalMana;
             gp.repaint();
         }
    }

    public void checkHpValue() {
        if (currentHP > totalHp) {
            currentHP = totalHp;
            gp.repaint();
        }
    }

    public void setPositions() {
        x = screenX;
        y = screenY;
    }

    private int poisonStartTurn;
    private int curseStartTurn;

    public void applyPoison() {
        isPoisoned = true;
        poisonStartTurn = gp.turnManager.globalTurn;
    }

    public void applyCurse() {
        isCursed = true;
        curseStartTurn = gp.turnManager.globalTurn;
    }

    public void checkStatusEffects(double hpDrain, int manaDrain, int duration) {
        if (isPoisoned) {
            if (gp.turnManager.globalTurn < poisonStartTurn + duration - 1) {
                currentHP -= hpDrain;
            } else {
                isPoisoned = false;
            }
        }

        if (isCursed) {
            if (gp.turnManager.globalTurn < curseStartTurn + duration - 1) {
                gp.player.currentMana -= manaDrain;
            } else {
                isCursed = false;
            }
        }
    }

    public void move(){
        int prevCol = col;
        int prevRow = row;
        switch (gp.keyHandler.directionOfMove()) {
            case "up" -> {
                if (isInHideout) {
                    if (col > 0 && gp.hideout.hideoutMap[col - 1][row].tileType >= 1) {
                        col -= 1;
                        gp.map.dx -= gp.map.tileWidth/2;
                        gp.map.dy += gp.map.tileHeight/2;
                    }
                } else {
                    if (col > 0) {
                        if (gp.map.tileMap[col - 1][row].tileType >= 1 && gp.objectManager.entityMap[col - 1][row] == 0 || gp.objectManager.entityMap[col - 1][row] > 30) {
                            col -= 1;
                            gp.map.dx -= gp.map.tileWidth/2;
                            gp.map.dy += gp.map.tileHeight/2;

                            gp.objectManager.entityMap[prevCol][prevRow] = 0;
                            gp.objectManager.entityMap[col][row] = id;
                        }
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
                    if (col < gp.map.maxWorldCol - 1) {
                        if (gp.map.tileMap[col + 1][row].tileType >= 1 && gp.objectManager.entityMap[col + 1][row] == 0 || gp.objectManager.entityMap[col + 1][row] > 30) {
                            col += 1;
                            gp.map.dx += gp.map.tileWidth/2;
                            gp.map.dy -= gp.map.tileHeight/2;

                            gp.objectManager.entityMap[prevCol][prevRow] = 0;
                            gp.objectManager.entityMap[col][row] = id;
                        }
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
                    if (row > 0) {
                        if (gp.map.tileMap[col][row - 1].tileType >= 1 && gp.objectManager.entityMap[col][row - 1] == 0 || gp.objectManager.entityMap[col][row - 1] > 30) {
                            row -= 1;
                            gp.map.dx += gp.map.tileWidth/2;
                            gp.map.dy += gp.map.tileHeight/2;

                            gp.objectManager.entityMap[prevCol][prevRow] = 0;
                            gp.objectManager.entityMap[col][row] = id;
                        }
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
                    if (row < gp.map.maxWorldRow - 1) {
                        if (gp.map.tileMap[col][row + 1].tileType >= 1 && gp.objectManager.entityMap[col][ row + 1] == 0 || gp.objectManager.entityMap[col][row + 1] > 30) {
                            row += 1;
                            gp.map.dx -= gp.map.tileWidth/2;
                            gp.map.dy -= gp.map.tileHeight/2;

                            gp.objectManager.entityMap[prevCol][prevRow] = 0;
                            gp.objectManager.entityMap[col][row] = id;
                        }
                    }
                }
            }
        }
    }

    public void teleportToHideout() {
        if (!isInHideout) {
            gp.objectManager.entityMap[col][row] = 0;
            gp.map.dx = gp.hideout.hideoutX + gp.map.tileWidth;
            gp.map.dy = gp.hideout.hideoutY - gp.map.tileHeight * 2;

            isInHideout = true;
            col = 4;
            row = 2;

            for (int i = gp.objectManager.entities.size() - 1; i >= 0 ; i--) {
                if (!gp.objectManager.entities.get(i).name.equals("silly_wizard")) {
                    gp.objectManager.entities.remove(i);
                }
            }
        }
    }

    public void teleportToLevel() {
        if (gp.hideout.hideoutMap[this.col][this.row].tileType == 3) {
            switch (gp.progression.startPoint) {
                case 0 -> {
                    gp.map.dx = 0;
                    gp.map.dy = 0;
                    this.col = 1;
                    this.row = 1;
                }
                case 1 -> {
                    gp.map.dx = (-gp.map.tileWidth/2) * (gp.map.maxWorldCol - 3);
                    gp.map.dy = (-gp.map.tileHeight/2) * (gp.map.maxWorldRow - 3);

                    this.col = 1;
                    this.row = gp.map.maxWorldRow - 2;
                }
                case 2 -> {
                    gp.map.dx = (gp.map.tileWidth/2) * (gp.map.maxWorldCol - 3);
                    gp.map.dy = (-gp.map.tileHeight/2) * (gp.map.maxWorldRow - 3);

                    this.col = gp.map.maxWorldCol - 2;
                    this.row = 1;
                }
                case 3 -> {
                    gp.map.dx = 0;
                    gp.map.dy = (-gp.map.tileHeight/2) * ((gp.map.maxWorldRow * 2) - 6);

                    this.col = gp.map.maxWorldCol - 2;
                    this.row = gp.map.maxWorldRow - 2;
                }
            }
            isInHideout = false;
            gp.objectManager.entityMap[this.col][this.row] = 1;
        }
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

    public void updateSpell() {
        for (Spell spell : playerSpells) {
            spell.setSpellValues(this);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, screenX, screenY, null);
    }
}

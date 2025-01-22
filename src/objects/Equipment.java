package objects;

import main.GamePanel;
import objects.items.Item;
import objects.items.Rune;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Equipment {
    GamePanel gp;
    public int itemSelected;
    public Equipment(GamePanel gamePanel) {
        this.gp = gamePanel;
        itemSelected = 0;
    }

    public void closeEq() {
        if (gp.keyHandler.wPressed || gp.keyHandler.aPressed || gp.keyHandler.sPressed || gp.keyHandler.dPressed) {
            gp.keyHandler.tabPressed = false;
            gp.player.isEquipmentOpen = false;
            itemSelected = 0;
        }
    }

    //private List<Entity> entitiesToRemove = new ArrayList<>();

    public void pickUpItems() {
        if (!gp.player.isInHideout) {
            for (Entity entity : gp.objectManager.entities) {
                if (entity.id > 30) {
                    if (gp.player.col == entity.col && gp.player.row == entity.row) {
                        Rune rune = (Rune) entity;
                        gp.player.playerRunes.add(rune);
                        gp.objectManager.entities.remove(rune);
                        //gp.objectManager.entityMap[rune.col][rune.row] = 0;
                        //entitiesToRemove.add(rune);
                        //gp.objectManager.destroyEntity(rune.col, rune.row, rune.id);

                        gp.repaint();
                    }
                }
            }
            //gp.objectManager.entities.removeAll(entitiesToRemove);
        } else {
            for (Entity entity : gp.hideout.entities) {
                if (gp.player.col == entity.col && gp.player.row == entity.row) {
                    if (entity.id > 30) {
                        Rune rune = (Rune) entity;
                        gp.player.playerRunes.add(rune);
                        gp.hideout.entities.remove(rune);
                        //entitiesToRemove.add(rune);
                        //gp.objectManager.destroyEntity(rune.col, rune.row, rune.id);

                        gp.repaint();
                    }
                }
            }
            //gp.hideout.entities.removeAll(entitiesToRemove);
        }
    }

    public boolean itemDropped;
    public int turnWhenDropped;

    public void dropItems() {
        if (gp.keyHandler.enterPressed && !gp.player.playerRunes.isEmpty()) {
            Rune selectedRune = gp.player.playerRunes.get(itemSelected);
            selectedRune.col = gp.player.col;
            selectedRune.row = gp.player.row;
            selectedRune.setPositions();
            if (!gp.player.isInHideout) {
                gp.objectManager.entities.add(selectedRune);
                //gp.objectManager.entityMap[selectedRune.col][selectedRune.row] = selectedRune.id;
            } else {
                gp.hideout.entities.add(selectedRune);
            }
            gp.player.playerRunes.remove(selectedRune);
            gp.keyHandler.tabPressed = false;
            gp.player.isEquipmentOpen = false;
            itemDropped = true;
            turnWhenDropped = gp.turnManager.globalTurn;
            gp.repaint();
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(gp.ui.wizardFont);
        g2.setColor(Color.WHITE);

        int slotsOffset = 150;

        if (!gp.player.playerRunes.isEmpty() && gp.keyHandler.tabPressed) {
            int eqWidth = gp.screenWidth/2 - gp.player.playerRunes.getFirst().image.getWidth();
            int eqHeight = gp.screenHeight/4 - gp.player.playerRunes.getFirst().image.getHeight();

            if (!gp.keyHandler.leftPressed && !gp.keyHandler.rightPressed) {
                g2.drawImage(gp.player.playerRunes.get(itemSelected).image,eqWidth, eqHeight,80, 80,  null);

                if (itemSelected < gp.player.playerRunes.size() - 1) {
                    g2.drawImage(gp.player.playerRunes.get(itemSelected + 1).image, eqWidth + slotsOffset, eqHeight, 50, 50, null);
                }
            }

            if (gp.keyHandler.leftPressed) {
                if (itemSelected == 0) {
                    itemSelected = 0;
                } else {
                    itemSelected--;
                }

                g2.drawImage(gp.player.playerRunes.get(itemSelected).image, eqWidth, eqHeight,80, 80,  null);

                if (itemSelected != 0) {
                    g2.drawImage(gp.player.playerRunes.get(itemSelected - 1).image,eqWidth - slotsOffset, eqHeight,50, 50,  null);
                }
                g2.drawImage(gp.player.playerRunes.get(itemSelected + 1).image,eqWidth + slotsOffset, eqHeight,50, 50,  null);
            }

            if (gp.keyHandler.rightPressed) {
                if (itemSelected < gp.player.playerRunes.size() - 1) {
                    itemSelected++;
                }

                g2.drawImage(gp.player.playerRunes.get(itemSelected).image,eqWidth, eqHeight,80, 80,  null);

                g2.drawImage(gp.player.playerRunes.get(itemSelected - 1).image,eqWidth - slotsOffset, eqHeight,50, 50,  null);
                if (itemSelected < gp.player.playerRunes.size() - 1) {
                    g2.drawImage(gp.player.playerRunes.get(itemSelected + 1).image, eqWidth + slotsOffset, eqHeight, 50, 50, null);
                }

            }
            g2.drawString(String.valueOf(itemSelected), gp.screenWidth/2, gp.screenHeight/2 + 100);

            //STATS
            FontMetrics metrics = g2.getFontMetrics(gp.ui.wizardFont);

            g2.drawString(String.valueOf("Level: " + gp.player.playerRunes.get(itemSelected).level), gp.screenWidth/2 - metrics.stringWidth("Level: "), gp.screenHeight/2 + 130);

            int lastLine = gp.screenHeight/2 + 130;
            int spacer = 25;
            for (String s : gp.player.playerRunes.get(itemSelected).listOfMods) {
                switch (s) {
                    case "dmg" -> {
                        lastLine += spacer;
                        g2.drawString("Damage:" + gp.player.playerRunes.get(itemSelected).dmg, gp.screenWidth / 2 - metrics.stringWidth("Damage: "), lastLine);
                    }
                    case "critical" -> {
                        lastLine += spacer;
                        g2.drawString("Critical Attack Chance: " + gp.player.playerRunes.get(itemSelected).critical, gp.screenWidth / 2 - metrics.stringWidth("Critical Attack Chance: "), lastLine);
                    }
                    case "range" -> {
                        lastLine += spacer;
                        g2.drawString("Range: " + gp.player.playerRunes.get(itemSelected).dmg, gp.screenWidth / 2 - metrics.stringWidth("Range: "), lastLine);
                    }
                    case "hp" -> {
                        lastLine += spacer;
                        g2.drawString("Hitpoints: " + gp.player.playerRunes.get(itemSelected).hp, gp.screenWidth / 2 - metrics.stringWidth("Hitpoints: "), lastLine);
                    }
                    case "mana" -> {
                        lastLine += spacer;
                        g2.drawString("Mana: " + gp.player.playerRunes.get(itemSelected).mana, gp.screenWidth / 2 - metrics.stringWidth("Mana: "), lastLine);
                    }
                    case "runeSpace" -> {
                        lastLine += spacer;
                        g2.drawString("Additional Rune Space: " + gp.player.playerRunes.get(itemSelected).runeSpace, gp.screenWidth / 2 - metrics.stringWidth("Additional Rune Space: "), lastLine);
                    }
                }
            }
        }

    }
}

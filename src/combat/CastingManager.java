package combat;

import main.GamePanel;
import objects.Monster;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class CastingManager {
    GamePanel gp;

    public Spell currentSpell;

    public CastingManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        currentSpell = new Spell(gp,"blank");
    }

    public void chooseSpell() {
        if (gp.keyHandler.onePressed) {
           currentSpell = gp.player.playerSpells.get(0);
        }
        if (gp.keyHandler.twoPressed) {
            currentSpell = gp.player.playerSpells.get(1);
        }
        if (gp.keyHandler.threePressed) {
            currentSpell = gp.player.playerSpells.get(2);
        }
        System.out.println("Current spell number is:" + currentSpell.name);
    }

    public void setSpellPosition(Monster caster) {
        switch (gp.keyHandler.directionOfAction()) {
            case "up" -> {
                currentSpell.col = caster.col - 1;
                currentSpell.row = caster.row;
            }
            case "down" -> {
                currentSpell.col = caster.col + 1;
                currentSpell.row = caster.row;
            }
            case "left" -> {
                currentSpell.col = caster.col;
                currentSpell.row = caster.row - 1;
            }
            case "right" -> {
                currentSpell.col = caster.col;
                currentSpell.row = caster.row + 1;
            }
            case "self" -> {
                currentSpell.col = caster.col;
                currentSpell.row = caster.row;
            }
        }

        currentSpell.x = gp.map.tileMap[currentSpell.col][currentSpell.row].x + gp.map.dx;
        currentSpell.y = gp.map.tileMap[currentSpell.col][currentSpell.row].y + gp.map.dy;
    }

    public void applyEffect() {
        if (gp.player.currentMana > currentSpell.manaCost){
            currentSpell.effect();
        }
    }


//        if (colOffset != 0 || rowOffset != 0 && gp.keyHandler.onePressed){
//            spell.cast(gp.player.col + colOffset, gp.player.row + rowOffset);
//        }
//
//        if (colOffset != 0 || rowOffset != 0 && gp.keyHandler.twoPressed){
//            if (spell instanceof RangeSpell rangeSpell) {
//                rangeSpell.cast(gp.player.col + colOffset, gp.player.row + rowOffset, colOffset, rowOffset);
//            }
//        }


    public void draw(Graphics2D g2) {
        if (gp.player.currentMana > currentSpell.manaCost) {
            if (gp.keyHandler.upPressed || gp.keyHandler.downPressed || gp.keyHandler.rightPressed || gp.keyHandler.leftPressed || gp.keyHandler.xPressed) {
                if (!currentSpell.name.equals("2")) {
                    g2.drawImage(currentSpell.image, currentSpell.x, currentSpell.y, null);
                } else if (gp.map.tileMap[currentSpell.col][currentSpell.row].tileType == 0) {
                    if (gp.keyHandler.upPressed ||  gp.keyHandler.downPressed) {
                        gp.map.tileMap[currentSpell.col][currentSpell.row].setTileType(7);
                        gp.player.currentMana -= currentSpell.manaCost;
                    } else if (gp.keyHandler.leftPressed || gp.keyHandler.rightPressed) {
                        gp.map.tileMap[currentSpell.col][currentSpell.row].setTileType(8);
                        gp.player.currentMana -= currentSpell.manaCost;
                    }

                }
            }
        }
    }

}

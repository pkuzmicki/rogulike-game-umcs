package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;
import objects.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spell {
    GamePanel gp;
    Random random;

    BufferedImage image;
    String name;

    int x, y;
    public int col, row;

    int damage;
    int manaCost;

    public Spell(GamePanel gamePanel, String name) {
        this.gp = gamePanel;
        this.name = name;
        random = new Random();

        getImages();
        //setSpellValues();
    }

    private void getImages() {
        switch (name) {
            case "1" -> image = gp.getImage("/sprites/spells/spell_black_hole.png");
            case "3" -> image = gp.getImage("/sprites/spells/spell_heal.png");
        }
    }

    public void setSpellValues(Player player) {
        switch (name) {
            case "1" -> {
                damage = player.dmg;
                manaCost = 3;
            }
            case "2" -> {
                manaCost = 5;
            }
            case "3" -> {
                damage = -3;
                manaCost = 5;
            }
        }
    }

    public void effect() {
        if (!gp.player.isInHideout) {
            for (Entity entity : gp.objectManager.entities) {
                if (entity.col == col && entity.row == row && entity.id <= 20) {
                    Monster monster = (Monster) entity;

                    //System.out.println(damage + gp.hideout.dmgBonus);
                    monster.currentHP -= damage + gp.hideout.dmgBonus;
                    gp.player.currentMana -= manaCost;
                    System.out.println(monster.name + monster.currentHP);

                    monster.checkIfShouldDie(monster.col, monster.row);
                }
            }
        } else {
            for (Entity entity : gp.hideout.entities) {
                if (entity.col == col && entity.row == row && entity.id <= 20) {
                    Monster monster = (Monster) entity;

                    //monster.currentHP -= damage * multiplier();
                    System.out.println(damage + gp.hideout.dmgBonus);
                    monster.currentHP -= (damage + gp.hideout.dmgBonus) * criticalHit();
                    gp.player.currentMana -= manaCost;
                    System.out.println(monster.name + monster.currentHP);

                    monster.checkIfShouldDie(monster.col, monster.row);
                }
            }
        }
    }

    public double criticalHit() {
        double[] multipliers = {0, 1.0, 2};
        double[] probabilities = {gp.player.missChance, gp.player.hitChance, gp.player.critChance};

        double randomNumber = random.nextDouble();
        double chance = 0.0;
        for (int i = 0; i < multipliers.length; i++) {
            chance += probabilities[i];
            if (randomNumber <= chance) {
                return multipliers[i];
            }
        }
        return 1;
    }

//    public void cast(int col, int row) {
//        if (col > -1 && col < gp.map.maxWorldCol && row > -1 && row < gp.map.maxWorldRow) {
//            effect(col, row);
//
//            x = gp.map.tileMap[col][row].x;
//            y = gp.map.tileMap[col][row].y;
//            System.out.println(x + " " + y);
//            System.out.println(col + " " + row);
//        }
//    }

//    public void cast1(int col, int row, int range, int spellID) {
//        for (int i = 0; i < range; i++) {
//            if (col > -1 && col < gp.map.maxWorldCol && row > -1 && row < gp.map.maxWorldRow && !gp.player.isInHideout) {
//                for (Entity entity : gp.objectManager.entities) {
//                    if (entity.col == col && entity.row == row && entity.id <= 20) {
//                        Monster monster = (Monster) entity;
//                        monster.currentHP -= damage;
//                    }
//                }
//            }
//        }
//    }


}

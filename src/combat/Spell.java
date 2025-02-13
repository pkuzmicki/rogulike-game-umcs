package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;
import objects.Player;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Spell {
    GamePanel gp;
    Random random;

    BufferedImage image;
    public String name;

    int x, y;
    public int col, row;

    public double totalDmg;
    double damage;
    int manaCost;

    public Spell(GamePanel gamePanel, String name) {
        this.gp = gamePanel;
        this.name = name;
        random = new Random();

        getImages();
    }

    private void getImages() {
        switch (name) {
            case "1" -> image = gp.getImage("/sprites/spells/spell_black_hole.png");
            case "3" -> image = gp.getImage("/sprites/spells/spell_heal.png");
        }
    }

    public void setSpellValues(Monster caster) {
        switch (name) {
            case "1" -> {
                if (caster.id == 1) {
                    Player player = (Player) caster;
                    damage = player.totalDmg;
                    manaCost = 3;
                } else {
                    damage = caster.dmg;
                    manaCost = 0;
                }
            }
            case "2" -> manaCost = 3;
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

                    totalDmg = damage * criticalHit();
                    monster.currentHP -= totalDmg;
                    gp.player.currentMana -= manaCost;

                    System.out.println(monster.name + monster.currentHP);
                    System.out.println("player dmg: " + gp.player.totalDmg);
                    //monster.checkIfShouldDie(monster.col, monster.row);
                }
            }
        } else {
            for (Entity entity : gp.hideout.entities) {
                if (entity.col == col && entity.row == row && entity.id <= 20) {
                    Monster monster = (Monster) entity;

                    totalDmg = damage * criticalHit();
                    monster.currentHP -= totalDmg;
                    gp.player.currentMana -= manaCost;

                    monster.checkIfShouldDie(monster.col, monster.row);
                }
            }
        }
    }

    public double criticalHit() {
        double missChance = gp.player.critChance * 0.25;
        double hitChance = 1.0 - missChance - gp.player.totalCrit;
        double[] multipliers = {0, 1.0, 2};
        double[] probabilities = {missChance, hitChance, gp.player.totalCrit};

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

}

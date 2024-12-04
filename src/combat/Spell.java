package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;

import java.util.Random;

public class Spell {
    GamePanel gp;
    Random random;
    int damage;
    //boolean canUseOnObstacles = false;

    public Spell(GamePanel gamePanel, int dmg) {
        this.gp = gamePanel;
        this.damage = dmg;
        random = new Random();
    }

//    public void checkDirection(int[] colOffset, int[] rowOffset) {
//        String direction = gp.keyHandler.directionOfAction();
//        if (direction != null) {
//            switch (direction) {
//                case "up" -> colOffset[0] = -1;
//                case "down" -> colOffset[0] = 1;
//                case "left" -> rowOffset[0] = -1;
//                case "right" -> rowOffset[0] = 1;
//                default -> {}
//            };
//        }
//    }

    //efekt czaru np zadanie obrazenia, uleczenie, nadanie jakiegos statusu itp
    public void effect(int col, int row) {
        for (Entity entity : gp.objectManager.entities) {
            if (entity.col == col && entity.row == row && entity.id <= 20) {
                Monster monster = (Monster) entity;
                monster.currentHP -= damage * multiplier();
                System.out.println(monster.name + monster.currentHP);
                monster.checkIfShouldDie(monster.col, monster.row);
            }
        }
    }

    public double multiplier() {
        double[] multipliers = {0.5, 0.7, 0.9, 1.0, 1.5};
        double[] probabilities = {0.04, 0.10, 0.25, 0.6, 0.01};

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

    //wywolanie czaru w podanym miejscu
    public void cast(int col, int row) {
        effect(col, row);
        gp.map.tileMap[col][row].setTileType(2);
        gp.turnManager.globalTurn++;
        gp.repaint();
    }
}

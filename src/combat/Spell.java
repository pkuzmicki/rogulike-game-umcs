package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;

public class Spell {
    GamePanel gp;
    int damage;
    String name;
    boolean canUseOnObstacles = false;

    Spell(GamePanel gamePanel, int dmg) {
        this.gp = gamePanel;
        this.damage = dmg;
    }

    public void checkDirection(int[] colOffset, int[] rowOffset) {
        String direction = gp.keyHandler.directionOfAction();
        if (direction != null) {
            switch (direction) {
                case "up" -> colOffset[0] = -1;
                case "down" -> colOffset[0] = 1;
                case "left" -> rowOffset[0] = -1;
                case "right" -> rowOffset[0] = 1;
                default -> {}
            };
        }
    }

    //efekt czaru np zadanie obrazenia, uleczenie, nadanie jakiegos statusu itp
    public void effect(int col, int row) {
        for (Entity entity : gp.objectManager.entities) {
            if (entity.col == col && entity.row == row && entity.entityID <= 20) {
                Monster monster = (Monster) entity;
                monster.currentHP -= damage;
                System.out.println(monster.currentHP);
            }
        }
    }

    //wywolanie czaru w podanym miejscu
    public void cast(int col, int row) {
        effect(col, row);
        gp.map.tileMap[col][row].setTileType(2);
        gp.turnManager.globalTurn++;
        gp.repaint();
    }
}

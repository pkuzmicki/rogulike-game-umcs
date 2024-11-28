package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;

public class RangeSpell extends Spell{
    int range;

    RangeSpell(GamePanel gamePanel, int dmg, int range) {
        super(gamePanel, dmg);
        this.range = range;
    }

    public void cast() {
        int[] rowOffset = new int[1];
        int[] colOffset = new int[1];
        checkDirection(colOffset, rowOffset);
        System.out.println(colOffset[0] + " " + rowOffset[0]);

        for (int castingRow = rowOffset[0]; castingRow < range; castingRow++) {
            for (Entity entity : gp.objectManager.entities) {
                if (entity.col == gp.player.col && entity.row == castingRow && entity.entityId > 10 && entity.entityId <= 20) {
                    Monster monster = (Monster) entity;
                    monster.currentHP -= damage;
                    System.out.println(monster.currentHP);
                    monster.checkIfShouldDie(gp.player.col + colOffset[0], gp.player.row + rowOffset[0]);
                    gp.repaint();
                }
            }
        }

//        for (Entity entity : gp.objectManager.entities) {
//            if (entity.col == gp.player.col + colOffset && entity.row == gp.player.row + rowOffset && entity.entityId > 10 && entity.entityId <= 20) {
//                Monster monster = (Monster) entity;
//                monster.currentHP -= damage;
//                System.out.println(monster.currentHP);
//                monster.checkIfShouldDie(gp.player.col + colOffset, gp.player.row + rowOffset);
//                gp.map.tileMap[gp.player.col + colOffset][gp.player.row + rowOffset].setTileType(2);
//                gp.turnManager.globalTurn++;
//                gp.repaint();
//            }
//        }
    }
}

package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;

public class SingleSpell extends Spell{
    SingleSpell(GamePanel gamePanel, int dmg) {
        super(gamePanel, dmg);
    }

    public void cast(String direction) {
        int colOffset = 0, rowOffset = 0;
        if (direction != null) {
            switch (direction) {
                case "up" -> colOffset = -1;
                case "down" -> colOffset = 1;
                case "left" -> rowOffset = -1;
                case "right" -> rowOffset = 1;
                default -> {}
            };
        }

        //efekt wizualny czaru
        //int tempTileType = gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].tileType;
        //gamePanel.map.tileMap[gamePanel.player.col + colOffset][gamePanel.player.row + rowOffset].setTileType(2);
        //gamePanel.turnManager.pause();
        //gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].setTileType(tempTileType);
        //System.out.println(gamePanel.player.col  + " " + gamePanel.player.row );
        //System.out.println((gamePanel.player.col + colOffset) + " " + (gamePanel.player.row + colOffset));
        //System.out.println(colOffset +" "+ rowOffset);


        for (Entity entity : gp.objectManager.entities) {
            if (entity.col == gp.player.col + colOffset && entity.row == gp.player.row + rowOffset && entity.entityId > 10 && entity.entityId <= 20) {
                Monster monster = (Monster) entity;
                monster.currentHP -= damage;
                System.out.println(monster.currentHP);
                monster.checkIfShouldDie(gp.player.col + colOffset, gp.player.row + rowOffset);
                gp.turnManager.globalTurn++;
                gp.repaint();
            }
        }
    }

    public void cast1() {
        String direction = gp.keyHandler.directionOfAction();
        int colOffset = 0, rowOffset = 0;
        if (direction != null) {
            switch (direction) {
                case "up" -> colOffset = -1;
                case "down" -> colOffset = 1;
                case "left" -> rowOffset = -1;
                case "right" -> rowOffset = 1;
                default -> {}
            };
        }

        cast(gp.player.col + colOffset, gp.player.row + rowOffset);
    }

}

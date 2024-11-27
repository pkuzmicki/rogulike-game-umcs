package combat;

import main.GamePanel;
import objects.Entity;
import objects.Monster;

public class SingleAttackSpell extends Spell{
    SingleAttackSpell(GamePanel gamePanel, int dmg, int col, int row) {
        super(dmg, col, row, gamePanel);
    }

    public void cast(int dmg, String direction) {
        int colOffset = 0, rowOffset = 0;
        switch (direction) {
            case "up" -> rowOffset = -1;
            case "down" -> rowOffset = 1;
            case "left" -> colOffset = -1;
            case "right" -> colOffset = 1;
            default -> {}
        };

        //System.out.println("I CAST TESTICULAR TORSION");

        //efekt wizualny czaru
        //int tempTileType = gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].tileType;
        gamePanel.map.tileMap[gamePanel.player.col + colOffset][gamePanel.player.row + rowOffset].setTileType(2);
        //gamePanel.turnManager.pause();
        //gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].setTileType(tempTileType);
        System.out.println(gamePanel.player.col  + " " + gamePanel.player.row );
        System.out.println((gamePanel.player.col + colOffset) + " " + (gamePanel.player.row + colOffset));
        System.out.println(colOffset +" "+ rowOffset);


        for (Entity entity : gamePanel.objectManager.entities) {
            if (entity.col == gamePanel.player.col + colOffset && entity.row == gamePanel.player.row + rowOffset && entity.entityId > 10 && entity.entityId <= 20) {
                Monster monster = (Monster) entity;
                monster.monsterHP -= dmg;
                //System.out.println(monster.monsterHP);
                monster.checkIfShouldDie(gamePanel.player.col + colOffset, gamePanel.player.row + rowOffset);
                gamePanel.repaint();
            }
        }
    }

}

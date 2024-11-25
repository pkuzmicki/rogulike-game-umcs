package combat;

import objects.Entity;

public class SingleAttackSpell extends Spell{
    SingleAttackSpell(int dmg, int col, int row) {
        super(dmg, col, row);
    }

//    public void attack(int castingCol, int castingRow, int maxRange, int dmg) {
//        if (gamePanel.player.playerCol < gamePanel.map.maxWorldCol - 1) {
//            gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].setTileType(2);
//            for (Entity entity : gamePanel.objectManager.entities) {
//                if (entity.col == gamePanel.player.playerCol + 1 && entity.row == gamePanel.player.playerRow) {
//
//                    gamePanel.objectManager.destroyEntity(gamePanel.player.playerCol + 1, gamePanel.player.playerRow);
//                    gamePanel.repaint();
//                }
//            }
//        }
//    }

}

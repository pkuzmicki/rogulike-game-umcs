package objects;

import main.GamePanel;

public class Monster extends Entity{
    public int monsterHP;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
        //checkIfShouldDie(col, row);
    }

    public void checkIfShouldDie(int col, int row) {
        if (monsterHP <= 0) {
            gamePanel.objectManager.destroyEntity(col, row);
        }
    }
}

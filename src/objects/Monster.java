package objects;

import main.GamePanel;

public class Monster extends Entity{
    public int monsterHP;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
        setMonsterHP(entityName);
    }

    public void setMonsterHP(String monsterName) {
        switch (monsterName) {
            case "keeper_green" -> this.monsterHP = 15;
            case "keeper" -> this.monsterHP = 20;
        }
    }

    public void checkIfShouldDie(int col, int row) {
        if (monsterHP <= 0) {
            gamePanel.objectManager.destroyEntity(col, row);
            gamePanel.objectManager.entityMap[col][row] = 0;
        }
    }
}

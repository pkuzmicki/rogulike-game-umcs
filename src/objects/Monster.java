package objects;

import main.GamePanel;

public class Monster extends Entity{
    public int maxHP;
    public int currentHP;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
        setMonsterHP(entityName);
    }

    public void setMonsterHP(String monsterName) {
        switch (monsterName) {
            case "keeper_green" -> this.maxHP = 15;
            case "keeper" -> this.currentHP = 20;
        }
    }

    public void checkIfShouldDie(int col, int row) {
        if (currentHP <= 0) {
            gamePanel.objectManager.destroyEntity(col, row);
            gamePanel.objectManager.entityMap[col][row] = 0;
        }
    }
}

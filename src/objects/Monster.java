package objects;

import main.GamePanel;

import java.util.Random;

public class Monster extends Entity{
    public double maxHP;
    public double currentHP;
    public double dmg;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
        setMonsterStats(this, entityName);
    }

    private static void setMonsterStats(Monster monster, String monsterName) {
        switch (monsterName) {
            case "player" -> {
                monster.maxHP = monster.currentHP = 20;
                monster.dmg = 5;
            }
            case "keeper_blue" -> {
                monster.maxHP = monster.currentHP = 10;
                monster.dmg = 4;
            }
            case "keeper_green" -> {
                monster.maxHP = monster.currentHP = 80;
                monster.dmg = 2;
            }
            case "keeper_red" -> {
                monster.maxHP = monster.currentHP = 25;
                monster.dmg = 15;
            }
            case "keeper_yellow" -> {
                monster.maxHP = monster.currentHP = 20;
                monster.dmg = 40;
            }
        }
    }

    public void checkForOverHeal() {
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public void checkIfShouldDie(int col, int row) {
        if (currentHP <= 0) {
            if (entityID == 1) {
                System.out.println("YOU DIED!");
                gp.ui.isGameOver = true;
            } else {
                gp.objectManager.destroyEntity(col, row);
                gp.objectManager.entityMap[col][row] = 0;
            }
        }
    }

    int moveCounter = 0;
    public void monsterMove() {
//        Random random = new Random();
//        int nextMove = random.nextInt(4) + 1;
        if (col > 0 && col < gp.map.maxWorldCol - 1 && row > 0 && row < gp.map.maxWorldRow - 1) {
            switch (moveCounter) {
                case 1 -> move(-1, 0);
                case 2 -> move(0, -1);
                case 3 -> move(1, 0);
                case 4 -> move(0, 1);
            }
        }
        moveCounter++;
        if (moveCounter > 4) {
            moveCounter = 0;
        }
    }
}

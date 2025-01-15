package objects;

import combat.Spell;
import main.GamePanel;

import java.util.Random;

public class Monster extends Entity{
    public double maxHP;
    public double currentHP;
    public int dmg;
    public int manaDrop;

    Random random;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
        setMonsterStats(this, name);

        random = new Random();
    }

    private static void setMonsterStats(Monster monster, String monsterName) {
        switch (monsterName) {
            case "player" -> {
                monster.maxHP = monster.currentHP = 20;
                monster.dmg = 5;
            }
            case "keeper_blue" -> {
                monster.maxHP = monster.currentHP = 10;
                monster.dmg = 3;
                monster.manaDrop = 5;
            }
            case "keeper_green" -> {
                monster.maxHP = monster.currentHP = 80;
                monster.dmg = 2;
                monster.manaDrop = 40;
            }
            case "keeper_yellow" -> {
                monster.maxHP = monster.currentHP = 15;
                monster.dmg = 40;
                monster.manaDrop = 20;
            }
            case "keeper_red" -> {
                monster.maxHP = monster.currentHP = 40;
                monster.dmg = 15;
                monster.manaDrop = 20;
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
            if (id == 1) {
                System.out.println("YOU DIED!");
                gp.ui.isGameOver = true;
            } else {
                gp.player.currentMana += manaDrop;
                generateDrop();
                gp.objectManager.destroyEntity(col, row, id);
                gp.objectManager.entityMap[col][row] = 0;
            }
        }
    }

    private void generateDrop() {
        boolean isSpawning = random.nextInt(100) > 60;
        if (isSpawning) {
            gp.objectManager.createEntity(row, col, random.nextInt(31, 35));
        }
//                switch (name) {
//            case "keeper_red" -> {
//
//            }
//            case "keeper_blue" -> {
//
//            }
//            case "keeper_green" -> {
//
//            }
//            case "keeper_yellow" -> {
//
//            }
//        }
    }

    public void attack() {
        for (Entity entity : gp.objectManager.entities){
            Monster monster = (Monster) entity;
            if (monster.id < 20){
                if (monster.col == col - 1 && monster.row == row){
                    //spell.cast(col - 1, row);
                }
                if (monster.col == col + 1 && monster.row == row){
                    //spell.cast(col + 1, row);
                }
                if (monster.col == col && monster.row == row - 1){
                    //spell.cast(col, row - 1);
                }
                if (monster.col == col && monster.row == row + 1){
                    //spell.cast(col, row + 1);
                }
            }
        }
    }

    int moveCounter = 0;
    public void monsterMove() {

        int nextMove = random.nextInt(4) + 1;
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

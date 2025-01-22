package objects;

import combat.Spell;
import main.GamePanel;

import java.util.*;

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
            if (entity.id < 20){
                Monster monster = (Monster) entity;
                if (monster.col == col - 1 && monster.row == row){
                    //gp.map.tileMap[col - 1][row].setTileType(6);
                    gp.player.currentHP -= monster.dmg;
                }
                if (monster.col == col + 1 && monster.row == row){
                    //gp.map.tileMap[col + 1][row].setTileType(6);
                    gp.player.currentHP -= monster.dmg;
                }
                if (monster.col == col && monster.row == row - 1){
                    //gp.map.tileMap[col][row - 1].setTileType(6);
                    gp.player.currentHP -= monster.dmg;
                }
                if (monster.col == col && monster.row == row + 1){
                    gp.player.currentHP -= monster.dmg;
                    //gp.map.tileMap[col][row + 1].setTileType(6);
                }
            }
        }
    }

    public void follow(int range) {
        boolean playerFound = false;

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                int checkCol = col + i;
                int checkRow = row + j;

                if (checkCol >= 0 && checkCol < gp.map.maxWorldCol &&
                        checkRow >= 0 && checkRow < gp.map.maxWorldRow &&
                        gp.objectManager.entityMap[checkCol][checkRow] == 1) {
                    playerFound = true;
                    break;
                }
            }
            if (playerFound) break;
        }

        if (!playerFound) return;

        if (isNextToPlayer(col, row)) {
            return;
        }

        boolean[][] visited = new boolean[gp.map.maxWorldCol][gp.map.maxWorldRow];
        Queue<int[]> queue = new LinkedList<>();
        int[][][] parent = new int[gp.map.maxWorldCol][gp.map.maxWorldRow][2];

        queue.add(new int[]{col, row});
        visited[col][row] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentCol = current[0];
            int currentRow = current[1];

            if (isNextToPlayer(currentCol, currentRow)) {
                int nextCol = currentCol;
                int nextRow = currentRow;

                while (parent[nextCol][nextRow][0] != col || parent[nextCol][nextRow][1] != row) {
                    int tmpCol = parent[nextCol][nextRow][0];
                    int tmpRow = parent[nextCol][nextRow][1];
                    nextCol = tmpCol;
                    nextRow = tmpRow;
                }

                move1(nextCol - col, nextRow - row);
                return;
            }

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newCol = currentCol + dir[0];
                int newRow = currentRow + dir[1];

                if (isValidMove(newCol, newRow, visited)) {
                    queue.add(new int[]{newCol, newRow});
                    visited[newCol][newRow] = true;
                    parent[newCol][newRow] = new int[]{currentCol, currentRow};
                }
            }
        }
    }

    private boolean isNextToPlayer(int checkCol, int checkRow) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int adjCol = checkCol + dir[0];
            int adjRow = checkRow + dir[1];

            if (adjCol >= 0 && adjCol < gp.map.maxWorldCol &&
                    adjRow >= 0 && adjRow < gp.map.maxWorldRow &&
                    gp.objectManager.entityMap[adjCol][adjRow] == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidMove(int col, int row, boolean[][] visited) {
        return col >= 0 && col < gp.map.maxWorldCol &&
                row >= 0 && row < gp.map.maxWorldRow &&
                gp.map.tileMap[col][row].tileType != 0 &&
                gp.objectManager.entityMap[col][row] == 0 &&
                !visited[col][row];
    }

//    private boolean isValidMove(int col, int row, boolean[][] visited) {
//        if (col < 0 || col >= gp.map.maxWorldCol ||
//                row < 0 || row >= gp.map.maxWorldRow ||
//                visited[col][row] ||
//                gp.map.tileMap[col][row].tileType == 0) {
//            return false;
//        }
//
//        int entityOnTile = gp.objectManager.entityMap[col][row];
//        if (entityOnTile != 0) {
//            return false;
//        }
//
//        return true;
//    }

    public List<Integer> possibleDirections = new ArrayList<>();

    public void checkSurrounding() {
        if (col + 1 > 0 && col + 1 < gp.map.maxWorldCol) {
            if (gp.map.tileMap[col + 1][row].tileType != 0 && gp.objectManager.entityMap[col + 1][row] == 0) {
                possibleDirections.add(0);
            }
        }
        if (col - 1 > 0 && col - 1 < gp.map.maxWorldCol) {
            if (gp.map.tileMap[col - 1][row].tileType != 0 && gp.objectManager.entityMap[col - 1][row] == 0) {
                possibleDirections.add(1);
            }
        }
        if (row + 1 > 0 && row + 1 < gp.map.maxWorldRow) {
            if (gp.map.tileMap[col][row + 1].tileType != 0 && gp.objectManager.entityMap[col][row + 1] == 0) {
                possibleDirections.add(2);
            }
        }if (row - 1 > 0 && row - 1 < gp.map.maxWorldRow) {
            if (gp.map.tileMap[col][row - 1].tileType != 0 && gp.objectManager.entityMap[col][row - 1] == 0) {
                possibleDirections.add(3);
            }
        }
    }

    public void move1(int dCol, int dRow) {
        this.col += dCol;
        this.row += dRow;
        setPositions();
    }


    public void move() {
        if (!possibleDirections.isEmpty()) {
            switch (possibleDirections.getFirst()) {
                case 0 -> col += 1;
                case 1 -> col -= 1;
                case 2 -> row += 1;
                case 3 -> row -= 1;
            }
            setPositions();
        }
        possibleDirections.clear();
    }

    public void randomWalk() {
        checkSurrounding();
        Collections.shuffle(possibleDirections);
        move();
    }
}

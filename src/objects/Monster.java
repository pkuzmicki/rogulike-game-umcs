package objects;

import main.GamePanel;

import java.util.*;

public class Monster extends Entity{
    public double maxHP;
    public double currentHP;
    public double dmg;
    public int manaDrop;
    public boolean isDead = false;

    Random random;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
        setMonsterStats(this, name);

        random = new Random();
    }

    public void checkForOverHeal() {
        if (id != 1) {
            if (currentHP > maxHP) {
                currentHP = maxHP;
            }
        }
    }

    public void checkIfShouldDie(int col, int row) {
        if (currentHP <= 0) {
            if (id == 1) {
                gp.ui.isGameOver = true;
            } else {
                isDead = true;
                gp.player.currentMana += manaDrop;
                generateDrop();
                gp.objectManager.destroyEntity(col, row, id);
                gp.objectManager.entityMap[col][row] = 0;
            }
        }
    }

    private static void setMonsterStats(Monster monster, String monsterName) {
        switch (monsterName) {
            case "silly_wizard" -> {
                monster.maxHP = monster.currentHP = 20;
                monster.dmg = 5;
            }
            case "flaming_spirit" -> {
                monster.maxHP = monster.currentHP = 5;
                monster.dmg = 0.5;
                monster.manaDrop = 7;
            }
            case "keeper_blue" -> {
                monster.maxHP = monster.currentHP = 15;
                monster.dmg = 2;
                monster.manaDrop = 15;
            }
            case "keeper_green" -> {
                monster.maxHP = monster.currentHP = 100;
                monster.dmg = 0.6;
                monster.manaDrop = 60;
            }
            case "keeper_yellow" -> {
                monster.maxHP = monster.currentHP = 10;
                monster.dmg = 40;
                monster.manaDrop = 45;
            }
            case "keeper_red" -> {
                monster.maxHP = monster.currentHP = 40;
                monster.dmg = 15;
                monster.manaDrop = 50;
            }
        }
    }

    private void generateDrop() {
        boolean isDropping;
        switch (name) {
            case "flaming_spirit" -> {
                isDropping = random.nextInt(100) > 10;
                if (isDropping) {
                    gp.objectManager.createRune(row, col, 0);
                }
            }
            case "keeper_blue" -> {
                isDropping = random.nextInt(100) > 10;
                if (isDropping) {
                    gp.objectManager.createRune(row, col, random.nextInt(0, 2));
                }
            }
            case "keeper_green" -> {
                isDropping = random.nextInt(100) > 50;
                if (isDropping) {
                    gp.objectManager.createRune(row, col, random.nextInt(1, 3));
                }
            }
            case "keeper_yellow" -> {
                isDropping = random.nextInt(100) > 30;
                if (isDropping) {
                    gp.objectManager.createRune(row, col, random.nextInt(2, 3));
                }
            }
            case "keeper_red" -> {
                isDropping = random.nextInt(100) > 20;
                if (isDropping) {
                    gp.objectManager.createRune(row, col, 4);
                }
            }
        }
    }

    public void attack(Monster monster) {
        if (monster.col == gp.player.col - 1 && monster.row == gp.player.row){
            if (random.nextInt(0, 100) > 50) {
                gp.player.currentHP -= monster.dmg;
                System.out.println(monster.dmg);
            }
        }
        if (monster.col == gp.player.col + 1 && monster.row == gp.player.row){
            if (random.nextInt(0, 100) > 50) {
                gp.player.currentHP -= monster.dmg;
                System.out.println(monster.dmg);
            }
        }
        if (monster.col == gp.player.col && monster.row == gp.player.row - 1){
            if (random.nextInt(0, 100) > 50) {
                gp.player.currentHP -= monster.dmg;
                System.out.println(monster.dmg);
            }
        }
        if (monster.col == gp.player.gp.player.col && monster.row == gp.player.row + 1){
            if (random.nextInt(0, 100) > 50) {
                gp.player.currentHP -= monster.dmg;
                System.out.println(monster.dmg);
            }
        }
    }

    public boolean playerFound = false;
    public void follow(int range) {
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

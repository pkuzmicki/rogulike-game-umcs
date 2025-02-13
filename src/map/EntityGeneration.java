package map;

import main.GamePanel;

import java.util.Random;

public class EntityGeneration {
    GamePanel gp;
    Random random;

    int prevAmount = 1;
    int currAmount = 4;

    public EntityGeneration(GamePanel gamePanel) {
        this.gp = gamePanel;
        this.random = new Random();
    }

    public void generateCaves() {
        setZone(3);
        int amountOfMonsters = random.nextInt(prevAmount, currAmount);
        spawnCaves(amountOfMonsters);
        setZone(0);
    }

    public void generateForest() {
        setZone(3);
        int amountOfMonsters = random.nextInt(prevAmount, currAmount);
        spawnForest(amountOfMonsters);
        setZone(0);
    }

    private void setZone(int id) {
        System.out.println("start point: " + gp.progression.startPoint);
        switch (gp.progression.startPoint) {
            case 0 -> checkSizeSetZone(0, id);
            case 1 -> checkSizeSetZone(1, id);
            case 2 -> checkSizeSetZone(2, id);
            case 3 -> checkSizeSetZone(3, id);
        }
    }

    private void checkSizeSetZone(int startPoint, int id) {
        int[][] locations = {{1, 1},    {1, gp.map.maxWorldRow - 2}, {gp.map.maxWorldCol - 2, 1}, {gp.map.maxWorldCol - 2, gp.map.maxWorldRow - 2}};
        if (gp.progression.levelCounter <= 2) {
            setSafeZone(2, locations[startPoint][0], locations[startPoint][1], id);
        } else {
            setSafeZone(4, locations[startPoint][0], locations[startPoint][1], id);
        }
    }

    public void setSafeZone(int range, int startCol, int startRow, int id) {
        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                int checkCol = startCol + i;
                int checkRow = startRow + j;

                if (checkCol >= 0 && checkCol < gp.map.maxWorldCol && checkRow >= 0 && checkRow < gp.map.maxWorldRow) {
                    gp.objectManager.entityMap[checkCol][checkRow] = id;
                    //gp.map.tileMap[checkCol][checkRow].setTileType(6);
                }
            }
        }
    }

    private void setAmount() {
        prevAmount += 3;
        currAmount += 3;
    }

    private void spawnForest(int amount) {
        while (amount > 0) {
            amount = spawn(15, amount);
        }
        setAmount();
    }

    private void spawnCaves(int amount) {
        if (gp.progression.levelCounter > 2) {
            int initAmount = amount;
            while (amount > initAmount * 0.3) {
                amount = spawn(13, amount);
                amount--;
            }

            while (amount > 0) {
                switch (random.nextInt(0, 3)) {
                    case 0 -> amount = spawn(12, amount);
                    case 1 -> amount = spawn(14, amount);
                    case 2 -> amount = spawn(11, amount);
                }
            }
        } else {
            while (amount > 0) {
                amount = spawn(13, amount);
            }
        }
        setAmount();
    }

    private int spawn(int id, int amount) {
        int col = random.nextInt(0, gp.map.maxWorldCol);
        int row = random.nextInt(0, gp.map.maxWorldRow);
        if (gp.map.tileMap[col][row].tileType != 0 && gp.objectManager.entityMap[col][row] != 3) {
            gp.objectManager.createEntity(col, row, id);
            amount--;
        }
        return amount;
    }

}

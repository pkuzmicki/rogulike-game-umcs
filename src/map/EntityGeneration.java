package map;

import main.GamePanel;

import java.util.Random;

public class EntityGeneration {
    GamePanel gp;
    Random random;
    public EntityGeneration(GamePanel gamePanel) {
        this.gp = gamePanel;
        this.random = new Random();
    }

    public void generateEntities() {
        int weakEntities = random.nextInt(5, 10);
        int mediumEntities = random.nextInt(2, 4);
        int roughEntities = random.nextInt(1, 2);
        int strongEntities = random.nextInt(0, 2);

        spawnEntities(weakEntities, 13, 5, 5);
        spawnEntities(mediumEntities, 11, 5, 5);
        spawnEntities(roughEntities, 14, 8, 8);
        spawnEntities(strongEntities, 12, 11, 11);
    }

    public void generateForest() {
        int spiritsCount = random.nextInt(10, 20);
        spawnEntities(spiritsCount, 15, 5, 5);
    }

    private void spawnEntities(int entityType, int entityId, int colsFormPlayer, int rowsFromPlayer) {
        int col, row;
        while (entityType > 0) {
            col = random.nextInt(0, gp.map.maxWorldCol);
            row = random.nextInt(0, gp.map.maxWorldRow);

            if (gp.map.tileMap[col][row].tileType != 0 && col > colsFormPlayer && row > rowsFromPlayer) {
                gp.objectManager.createEntity(col, row, entityId);
                entityType--;
            }
        }
    }
}
